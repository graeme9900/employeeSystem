package spring_mvc.employee_management.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import spring_mvc.employee_management.model.dao.EmployeeManagementDao;
import spring_mvc.employee_management.model.entity.AttendanceTable;
import spring_mvc.employee_management.model.entity.DepartmentInfo;
import spring_mvc.employee_management.model.entity.Education;
import spring_mvc.employee_management.model.entity.EmployeeInfo;
import spring_mvc.employee_management.model.entity.IntegerData;
import spring_mvc.employee_management.model.entity.LeaveRecord;
import spring_mvc.employee_management.model.entity.WorkHoursRecord;

// 前端控制
@Controller
@RequestMapping("/frontend")
public class frontendController {

	@Autowired
	private EmployeeManagementDao dao;

	// -----------------------------------------------------------------
	
	// 首頁
	// http://localhost:8080/EmployeeManagement/mvc/frontend/index
	@GetMapping("/index")
	public String frontendindex() {
		

		return "employee_management/frontend/index";

	}

	// 簽到

	// 簽到簽退
	// http://localhost:8080/EmployeeManagement/mvc/frontend/checkInSelection
	@GetMapping("/checkInSelection")
	public String frontendCheckInSelection() {
		
		List<Education> test=dao.findEducation(1);
		System.out.println(test);
		System.out.println(dao.findEmployeeInfoByEmployeeId(1));

		return "employee_management/frontend/signInSystem/checkInSelection";

	}

	// 驗證碼介面
	// http://localhost:8080/EmployeeManagement/mvc/frontend/verificationPage
	@GetMapping("/verificationPage")
	public String frontendVerificationPage() {
		return "employee_management/frontend/signInSystem/verificationPage";
	}

	// 簽到驗證邏輯
	@PostMapping("/checkInVerification")
	public String frontendCheckInVerification(@RequestParam("action") String action,
											  @RequestParam("captcha") String captcha,
											  Model model, HttpSession session) {
		// 比對驗證碼
//		if(!captcha.equals(session.getAttribute("code")+"")) {
//
//
//			// 提示
//			model.addAttribute("errorMessage", "驗證碼錯誤");
//			model.addAttribute("nextUrl", "verificationPage");
//			model.addAttribute("boolMessage" , true);
//
//
//			return "redirect:/mvc/frontend/verificationPage";
//		}
		EmployeeInfo employee = (EmployeeInfo) session.getAttribute("employeeInfo");

		if (action.equals("signIn")) {
			if (employee.getNowSignInNumber().equals(0)) {
				Optional<IntegerData> integerDataOpt = dao.findIntegerData("latestCheckInNumber");
				IntegerData integerData = integerDataOpt.get();
				Integer latestCheckInNumber = integerData.getNumber();
				
				integerData.setNumber(latestCheckInNumber + 1);
				dao.updateIntegerData(integerData);

				employee.setNowSignInNumber(latestCheckInNumber);
				dao.updateEmployeeInfoNowSignInNumber(employee);

				AttendanceTable attendanceTable = new AttendanceTable();
				attendanceTable.setAttendanceID(latestCheckInNumber);
				attendanceTable.setEmployeeID(employee.getEmployeeID());
				dao.addAttendanceTable(attendanceTable);

				


				// 提示
				model.addAttribute("errorMessage", "簽到成功");
				model.addAttribute("nextUrl", "index");
				model.addAttribute("boolMessage" , true);


				return "redirect:/mvc/frontend/verificationPage";
			}
			// 提示
			model.addAttribute("errorMessage", "存在未簽退，請先簽退");
			model.addAttribute("nextUrl", "checkInSelection");
			model.addAttribute("boolMessage" , true);


			return "redirect:/mvc/frontend/verificationPage";
		} else if (action.equals("signOut")) {
			if (!employee.getNowSignInNumber().equals(0)) {
				dao.updateAttendanceTableByEmployee(employee);
				employee.setNowSignInNumber(0);
				dao.updateEmployeeInfoNowSignInNumber(employee);

				// 提示
				model.addAttribute("errorMessage", "簽退成功");
				model.addAttribute("nextUrl", "index");
				model.addAttribute("boolMessage" , true);


				return "redirect:/mvc/frontend/verificationPage";
			}


			// 提示
			model.addAttribute("errorMessage", "未簽到，請先簽到");
			model.addAttribute("nextUrl", "checkInSelection");
			model.addAttribute("boolMessage" , true);


			return "redirect:/mvc/frontend/verificationPage";

		}
		return "redirect:/mvc/frontend/index";
	}

	// 請假系統

	// 請假選單
	// http://localhost:8080/EmployeeManagement/mvc/frontend/leaveMenu
	@GetMapping("/leaveMenu")
	public String frontendLeaveMenu() {
		return "employee_management/frontend/leaveSystem/leaveMenu";

	}

	// 請假
	// http://localhost:8080/EmployeeManagement/mvc/frontend/leaveForm
	@GetMapping("/leaveForm")
	public String frontendLeaveForm(Model model, HttpSession session) {
		EmployeeInfo employee = (EmployeeInfo) session.getAttribute("employeeInfo");
		model.addAttribute("employeeName", employee.getPersonName());
		model.addAttribute("employeeID", employee.getEmployeeID());

		return "employee_management/frontend/leaveSystem/leaveForm";

	}

	// 請假邏輯
	@PostMapping("/leave")
	public String frontendleave(@RequestParam("startDate") String startDateStr, @RequestParam("hours") Integer hours, HttpSession session) {
		EmployeeInfo employee = (EmployeeInfo) session.getAttribute("employeeInfo");
		LocalDateTime startDate = LocalDateTime.parse(startDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		LeaveRecord leaveRecord = new LeaveRecord();
		leaveRecord.setEmployeeID(employee.getEmployeeID());
		leaveRecord.setDepartmentID(employee.getDepartmentID());
		leaveRecord.setLeaveStartDate(startDate);
		leaveRecord.setHours(hours);
		leaveRecord.setApproval(false);
		
		dao.addLeaveRecord(leaveRecord);
		
		return "redirect:/mvc/frontend/index";
	}

	// 班表開啟選單
	// http://localhost:8080/EmployeeManagement/mvc/frontend/departmentSelectionToClassSchedule
	@GetMapping("/departmentSelectionToClassSchedule")
	public String frontendDepartmentSelectionToClassSchedule(Model model) {
		List<DepartmentInfo> departmentInfoList = dao.findAllDepartmentInfos();
		model.addAttribute("departmentInfoList", departmentInfoList);
		return "employee_management/frontend/leaveSystem/departmentSelectionToClassSchedule";
	}
	
	// 班表開啟選單邏輯
	@PostMapping("/departmentSelectionToClassScheduleLogic")
	public String frontendDepartmentSelectionToClassScheduleLogic(@RequestParam("departmentID") Integer departmentID, 
																  Model model,
																  HttpSession session) {
		session.setAttribute("workScheduleDepartmentID", departmentID);
		return "redirect:/mvc/frontend/workSchedule";
	}

	// 班表
	// http://localhost:8080/EmployeeManagement/mvc/frontend/workSchedule
	@GetMapping("/workSchedule")
	public String frontendWorkSchedule(Model model,
									   HttpServletRequest request,
									   HttpSession session) {
		Integer departmentID;
		try {
			departmentID = (Integer) session.getAttribute("workScheduleDepartmentID");
		} catch (Exception e) {
			return "redirect:/mvc/frontend/index";

		}
		List<WorkHoursRecord> workHoursRecordList = dao.findWorkHoursRecordByDepartmentID(departmentID);
		System.out.println(workHoursRecordList);
		model.addAttribute("workHoursRecordList", workHoursRecordList);
		return "employee_management/frontend/leaveSystem/workSchedule";
	}
	
	

	

	// 假單簽核選單
	// http://localhost:8080/EmployeeManagement/mvc/frontend/departmentSelectionToSignOff
	@GetMapping("/departmentSelectionToSignOff")
	public String frontendDepartmentSelectionToSignOff(Model model) {
		List<DepartmentInfo> departmentInfoList = dao.findAllDepartmentInfos();
		model.addAttribute("departmentInfoList", departmentInfoList);
		return "employee_management/frontend/leaveSystem/departmentSelectionToSignOff";
	}
	
	// 假單開啟選單邏輯
	@PostMapping("/departmentSelectionToSignOffLogic")
	public String frontendDepartmentSelectionToSignOffLogic(@RequestParam("departmentID") Integer departmentID, 
																  Model model,
																  HttpSession session) {
		session.setAttribute("falseDocumentApprovalDepartmentID", departmentID);
		return "redirect:/mvc/frontend/falseDocumentApproval";
	}
	

	// 假單簽核表單
	// http://localhost:8080/EmployeeManagement/mvc/frontend/falseDocumentApproval
	@GetMapping("/falseDocumentApproval")
	public String frontendfalseDocumentApproval(Model model,
												HttpSession session) {
			Integer departmentID;
		try {
			departmentID = (Integer) session.getAttribute("falseDocumentApprovalDepartmentID");
		} catch (Exception e) {
			return "redirect:/mvc/frontend/index";

		}										
		List<LeaveRecord> leaveRecordList = dao.findLeaveRecordByDepartmentID(departmentID);
		System.out.println(leaveRecordList);
		model.addAttribute("leaveRecordList",leaveRecordList);
		return "employee_management/frontend/leaveSystem/falseDocumentApproval";
	}
	
	// 簽核假單
	@GetMapping("/falseDocumentApproval/update")
	public String frontendfalseDocumentApprovalUpdate(@RequestParam("leaveNumber") Integer leaveNumber,
													  HttpSession session) {

		dao.updateLeaveRecordByLeaveNumberID(leaveNumber);	
		return "redirect:/mvc/frontend/falseDocumentApproval";
	}

	// 員工管理

	// 部門選擇
	// http://localhost:8080/EmployeeManagement/mvc/frontend/employeeManagementPage
	@GetMapping("/employeeManagementPage")
	public String frontendEmployeeManagementPage(Model model) {
		List<DepartmentInfo> departmentInfoList = dao.findAllDepartmentInfos();
		model.addAttribute("departmentInfoList", departmentInfoList);
		return "employee_management/frontend/employeeManagementSystem/employeeManagementPage";
	}
	
	// 員工資料開啟邏輯
	@PostMapping("/employeeManagementPageLogic")
	public String frontendEmployeeManagementPageLogic(@RequestParam("departmentID") Integer departmentID, 
																  Model model,
																  HttpSession session) {
		session.setAttribute("employeeManagementPageDepartmentID", departmentID);
		return "redirect:/mvc/frontend/employeesAtAGlance";
	}
	

	// 員工資料
	// http://localhost:8080/EmployeeManagement/mvc/frontend/employeeManagementPage
	@GetMapping("/employeesAtAGlance")
	public String frontendEmployeesAtAGlance(Model model,
											 HttpSession session) {
		Integer departmentID;
		try {
			departmentID = (Integer) session.getAttribute("employeeManagementPageDepartmentID");
		} catch (Exception e) {
			return "redirect:/mvc/frontend/index";

		}
		List<EmployeeInfo> employeeInfoList = dao.findAllEmployeeInfoByDepartmentID(departmentID);
		model.addAttribute("employeeInfoList", employeeInfoList);
		return "employee_management/frontend/employeeManagementSystem/employeesAtAGlance";
	}
	
	
	// 詳細資料
	// http://localhost:8080/EmployeeManagement/mvc/frontend/employeeDetails
	@GetMapping("/employeeDetails")
	public String frontendEmployeeDetails(@RequestParam("employeeID") Integer employeeID, Model model) {
		EmployeeInfo employeeInfo;
		try {
			Optional<EmployeeInfo> employeeInfoOBJ = dao.findEmployeeInfoByEmployeeId(employeeID);
			employeeInfo = employeeInfoOBJ.get();
		} catch (Exception e) {
			return "redirect:/mvc/frontend/index";
		}
		System.out.println(employeeInfo);
		model.addAttribute("employeeInfo", employeeInfo);
		return "employee_management/frontend/employeeManagementSystem/employeeDetails";
	}
	

	// 個人資料

	// 資料選單
	// http://localhost:8080/EmployeeManagement/mvc/frontend/dataMenu
	@GetMapping("/dataMenu")
	public String frontendDataMenu() {
		return "employee_management/frontend/personalInformation/dataMenu";

	}

	// 修改資料頁面
	// http://localhost:8080/EmployeeManagement/mvc/frontend/modifyInformation
	@GetMapping("/modifyInformation")
	public String frontendModifyInformation(Model model,
											HttpSession session) {
		EmployeeInfo employeeInfo;
		try {
			employeeInfo = (EmployeeInfo) session.getAttribute("employeeInfo");
		} catch (Exception e) {
			return "redirect:/mvc/frontend/index";
		}
		

		
		model.addAttribute("employeeInfo", employeeInfo);

		

		return "employee_management/frontend/personalInformation/modifyInformation";

	}
	

	
	// 修改表單
	@GetMapping("/modifyInformation/updata")
	public String frontendModifyInformationUpdata(@RequestParam("personName") String personName,
												  @RequestParam("birthday") String birthdayString,
												  @RequestParam("email") String email,
												  @RequestParam("phone") String phone,
												  @RequestParam("educationList") String educationListString,
												  @RequestParam("workExperienceList") String workExperienceListString,
												  @RequestParam("skillList") String skillListString,
												  @RequestParam("interestList") String interestListString,
												  HttpSession session) {
		
	    
	    EmployeeInfo employeeInfo;
		try {
			employeeInfo = (EmployeeInfo) session.getAttribute("employeeInfo");
		} catch (Exception e) {
			return "redirect:/mvc/frontend/index";

		}
		
		LocalDate birthday = LocalDate.parse(birthdayString);
		List<String> educationList = Arrays.asList(educationListString.split(","));
		List<String> workExperienceList = Arrays.asList(workExperienceListString.split(","));
		List<String> skillList = Arrays.asList(skillListString.split(","));
		List<String> interestList = Arrays.asList(interestListString.split(","));
		
		
		
		employeeInfo.setPersonName(personName);
		employeeInfo.setBirthday(birthday);
		employeeInfo.setEmail(email);
		employeeInfo.setPhone(phone);
		employeeInfo.setEducation(educationList);
		employeeInfo.setWorkExperience(workExperienceList);
		employeeInfo.setSkill(skillList);
		employeeInfo.setInterest(interestList);
		

		
		dao.updateEmployeeInfo(employeeInfo);
		dao.deleteEducation(employeeInfo.getEmployeeID());
		dao.addEducation(employeeInfo);
		dao.deleteWorkExperience(employeeInfo.getEmployeeID());
		dao.addWorkExperience(employeeInfo);
		dao.deleteSkill(employeeInfo.getEmployeeID());
		dao.addSkill(employeeInfo);
		dao.deleteInterest(employeeInfo.getEmployeeID());
		dao.addInterest(employeeInfo);
		
		
		return "redirect:/mvc/frontend/index";
	}

	// 修改密碼
	// http://localhost:8080/EmployeeManagement/mvc/frontend/changePassword
	@GetMapping("/changePassword")
	public String frontendChangePassword() {
		return "employee_management/frontend/personalInformation/changePassword";

	}
	
	// 修改密碼邏輯
	@PostMapping("/changePasswordLogic")
	public String frontendChangePasswordLogic(@RequestParam("oldPassword") String oldPassword,
											  @RequestParam("newPassword") String newPassword,
											  @RequestParam("confirmNewPassword") String confirmNewPassword,
											  HttpSession session) {
		EmployeeInfo employeeInfo;
		try {
			employeeInfo = (EmployeeInfo) session.getAttribute("employeeInfo");
		} catch (Exception e) {
			return "redirect:/mvc/frontend/index";
		}
		if(oldPassword.equals(employeeInfo.getPassword())) {
			if (newPassword.equals(confirmNewPassword)) {
				employeeInfo.setPassword(newPassword);
				session.setAttribute("employeeInfo", employeeInfo);
				dao.updateEmployeeInfoPassword(employeeInfo);
			}
		}
		
		
		
		return "redirect:/mvc/login/logout";
	}
	
	// 查看假單
	// http://localhost:8080/EmployeeManagement/mvc/frontend/leaveInquiry
	@GetMapping("/leaveInquiry")
	public String frontendLeaveInquiry(Model model,
									   HttpSession session) {
		EmployeeInfo employeeInfo;
	try {
		employeeInfo = (EmployeeInfo) session.getAttribute("employeeInfo");
	} catch (Exception e) {
		return "redirect:/mvc/frontend/index";

	}										
	List<LeaveRecord> leaveRecordList = dao.findLeaveRecordByEmployeeID(employeeInfo.getEmployeeID());
	System.out.println(leaveRecordList);
	model.addAttribute("leaveRecordList",leaveRecordList);
	
	return "employee_management/frontend//personalInformation/leaveInquiry";

	}
	
	// 取消假單
	@GetMapping("/leaveInquiry/delete")
	public String frontendLeaveInquiryDelete(@RequestParam("leaveNumber") Integer leaveNumber,
													  HttpSession session) {

		dao.deleteLeaveRecord(leaveNumber);	
		return "redirect:/mvc/frontend/leaveInquiry";
	}
	

}
