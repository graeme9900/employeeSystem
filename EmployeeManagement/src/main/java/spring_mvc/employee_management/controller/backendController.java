package spring_mvc.employee_management.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.openjpa.kernel.BrokerImpl.StateManagerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import spring_mvc.employee_management.model.dao.EmployeeManagementDao;
import spring_mvc.employee_management.model.entity.AttendanceTable;
import spring_mvc.employee_management.model.entity.DepartmentInfo;
import spring_mvc.employee_management.model.entity.EmployeeInfo;
import spring_mvc.employee_management.model.entity.IntegerData;
import spring_mvc.employee_management.model.entity.LeaveRecord;
import spring_mvc.employee_management.model.entity.WorkHoursRecord;

// 後端控制
@Controller
@RequestMapping("/backend")
public class backendController {
	
	@Autowired
	private EmployeeManagementDao dao;
	
	// 後台目錄
	// http://localhost:8080/EmployeeManagement/mvc/backend/index
	@GetMapping("/index")
	public String backendIndex() {

		return "employee_management/backend/index";
	}
	
	
	// 員工表格
	
	// 員工表格表單
	// http://localhost:8080/EmployeeManagement/mvc/backend/modifyAndDeleteEmployeeInformationMenu
	@GetMapping("/modifyAndDeleteEmployeeInformationMenu")
	public String backendModifyAndDeleteEmployeeInformationMenu(Model model) {
		List<EmployeeInfo> employeeInfoList = dao.findAllEmployeeInfos();
		model.addAttribute("employeeInfoList", employeeInfoList);

		return "employee_management/backend/employeeForm/modifyAndDeleteEmployeeInformationMenu";
	}
	
	// 在表單裡刪除員工
	@GetMapping("/modifyAndDeleteEmployeeInformationMenu/delete")
	public String backendModifyAndDeleteEmployeeInformationMenuDelete(@RequestParam("employeeID") Integer employeeID,
																  Model model,
																  HttpSession session) {
		EmployeeInfo nowEmployeeInfo = (EmployeeInfo) session.getAttribute("employeeInfo");
		if(nowEmployeeInfo.getEmployeeID().equals(employeeID)) {
			// 提示
			model.addAttribute("errorMessage", "你不能刪除自己的員工資料");
			model.addAttribute("nextUrl", "modifyAndDeleteEmployeeInformationMenu");
			model.addAttribute("boolMessage" , true);
			
			return "redirect:/mvc/backend/modifyAndDeleteEmployeeInformationMenu";
			
		} else {
			Optional<EmployeeInfo> employeeInfoOpt = dao.findEmployeeInfoByEmployeeId(employeeID);
			EmployeeInfo employeeInfo = employeeInfoOpt.get();
			Integer departmentID = employeeInfo.getDepartmentID();
			
			
			Optional<DepartmentInfo> departmentInfoOpt = dao.findDepartmentInfo(departmentID);
			DepartmentInfo departmentInfo = departmentInfoOpt.get();
			Integer employeeCount = departmentInfo.getEmployeeCount();
			departmentInfo.setEmployeeCount(employeeCount-1);
			
			dao.updateDepartmentInfo(departmentInfo);
			
			dao.deleteEmployeeInfo(employeeID);
			
			
			// 提示
			model.addAttribute("errorMessage", "刪除成功");
			model.addAttribute("nextUrl", "modifyAndDeleteEmployeeInformationMenu");
			model.addAttribute("boolMessage" , true);
			
			
			return "redirect:/mvc/backend/modifyAndDeleteEmployeeInformationMenu";
		}

	}
	
	// 員工詳細改刪
	// http://localhost:8080/EmployeeManagement/mvc/backend/modifyAndDeleteEmployeeInformation
	@GetMapping("/modifyAndDeleteEmployeeInformation")
	public String backendModifyAndDeleteEmployeeInformation(@RequestParam(name = "employeeID", required = false) Integer employeeID,
															@RequestParam(name = "boolMessage", required = false) Boolean boolMessage,
															Model model,
															HttpSession session) {

		
		EmployeeInfo employeeInfo;
		if(employeeID == null) {
			try {
				employeeID = (Integer) session.getAttribute("employeeIDByBackendform");
			} catch (Exception e) {
				return "redirect:/mvc/backend/index";
			}
		}
		try {
			Optional<EmployeeInfo> employeeInfoObj = dao.findEmployeeInfoByEmployeeId(employeeID);
			employeeInfo = employeeInfoObj.get();
			session.setAttribute("employeeIDByBackendform", employeeID);
		} catch (Exception e) {

			employeeInfo = new EmployeeInfo();
		}
		
		
		
		model.addAttribute("employeeInfo", employeeInfo);

		return "employee_management/backend/employeeForm/modifyAndDeleteEmployeeInformation";
	}
	

	
	
	
	// 再詳細裡修改表單
	@GetMapping("/modifyAndDeleteEmployeeInformation/updata")
	public String backendModifyAndDeleteEmployeeInformationDelete(
			@RequestParam("employeeID") Integer employeeID,
			@RequestParam("personName") String personName,
			@RequestParam("departmentID") Integer departmentID,
			@RequestParam("position") String position,
			@RequestParam("positionrank") String positionrank,
			@RequestParam("hireDate") String hireDateString,
			@RequestParam("salary") Float salary,
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
//			employeeInfo = (EmployeeInfo) session.getAttribute("employeeInfo");
			Optional<EmployeeInfo> employeeInfoOpt = dao.findEmployeeInfoByEmployeeId(employeeID);
			employeeInfo = employeeInfoOpt.get();
		} catch (Exception e) {
			return "redirect:/mvc/backend/index";

		}

		LocalDate hireDate = LocalDate.parse(hireDateString);
		LocalDate birthday = LocalDate.parse(birthdayString);
		List<String> educationList = Arrays.asList(educationListString.split(","));
		List<String> workExperienceList = Arrays.asList(workExperienceListString.split(","));
		List<String> skillList = Arrays.asList(skillListString.split(","));
		List<String> interestList = Arrays.asList(interestListString.split(","));

		employeeInfo.setDepartmentID(departmentID);
		employeeInfo.setPersonName(personName);
		employeeInfo.setPosition(position);
		employeeInfo.setPositionrank(positionrank);
		employeeInfo.setHireDate(hireDate);
		employeeInfo.setSalary(salary);
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
		
		// 牽扯更改
		dao.updateLeaveRecordDepartmentIDByEmployeeID(employeeInfo.getEmployeeID(), departmentID);
		dao.deleteWorkHoursRecordByEmployeeID(employeeInfo.getEmployeeID());
		
		return "redirect:/mvc/backend/modifyAndDeleteEmployeeInformationMenu";
	}
	
	// 再詳細裡刪除員工
	@GetMapping("/modifyAndDeleteEmployeeInformation/delete")
	public String backendModifyAndDeleteEmployeeInformationUpdata(@RequestParam("employeeID") Integer employeeID,
																  Model model,
																  HttpSession session) {
		EmployeeInfo nowEmployeeInfo = (EmployeeInfo) session.getAttribute("employeeInfo");
		if(nowEmployeeInfo.getEmployeeID().equals(employeeID)) {
			// 提示
			model.addAttribute("errorMessage", "你不能刪除自己的員工資料");
			model.addAttribute("nextUrl", "modifyAndDeleteEmployeeInformationMenu");
			model.addAttribute("boolMessage" , true);
			
			return "redirect:/mvc/backend/modifyAndDeleteEmployeeInformation";
			
		} else {
			Optional<EmployeeInfo> employeeInfoOpt = dao.findEmployeeInfoByEmployeeId(employeeID);
			EmployeeInfo employeeInfo = employeeInfoOpt.get();
			Integer departmentID = employeeInfo.getDepartmentID();
			
			
			Optional<DepartmentInfo> departmentInfoOpt = dao.findDepartmentInfo(departmentID);
			DepartmentInfo departmentInfo = departmentInfoOpt.get();
			Integer employeeCount = departmentInfo.getEmployeeCount();
			departmentInfo.setEmployeeCount(employeeCount-1);
			
			dao.updateDepartmentInfo(departmentInfo);
			
			
			dao.deleteEmployeeInfo(employeeID);
			
			// 提示
			model.addAttribute("errorMessage", "刪除成功");
			model.addAttribute("nextUrl", "modifyAndDeleteEmployeeInformationMenu");
			model.addAttribute("boolMessage" , true);
			
			
			return "redirect:/mvc/backend/modifyAndDeleteEmployeeInformation";
		}

	}
	
	
	
	// 增加員工表格頁面
	// http://localhost:8080/EmployeeManagement/mvc/backend/addEmployeeInformation
	@GetMapping("/addEmployeeInformation")
	public String backendAddEmployeeInformation() {

		return "employee_management/backend/employeeForm/addEmployeeInformation";
	}
	

	
	// 增加員工表單
	@GetMapping("/addEmployeeInformation/add")
	public String backendAddEmployeeInformationAdd(@RequestParam("personName") String personName,
			@RequestParam("departmentID") Integer departmentID, @RequestParam("position") String position,
			@RequestParam("positionrank") String positionrank, @RequestParam("salary") Float salary,
			@RequestParam("birthday") String birthdayString, @RequestParam("email") String email,
			@RequestParam("phone") String phone, @RequestParam("educationList") String educationListString,
			@RequestParam("workExperienceList") String workExperienceListString,
			@RequestParam("skillList") String skillListString, @RequestParam("interestList") String interestListString,
			HttpSession session) {

		Optional<IntegerData> employeeIDOpt = dao.findIntegerData("employeeID");
		IntegerData employeeIDData = employeeIDOpt.get();
		Integer employeeID = employeeIDData.getNumber();
		employeeIDData.setNumber(employeeID+1);
		dao.updateIntegerData(employeeIDData);
		
		EmployeeInfo employeeInfo = new EmployeeInfo();


		LocalDate birthday = LocalDate.parse(birthdayString);
		List<String> educationList = Arrays.asList(educationListString.split(","));
		List<String> workExperienceList = Arrays.asList(workExperienceListString.split(","));
		List<String> skillList = Arrays.asList(skillListString.split(","));
		List<String> interestList = Arrays.asList(interestListString.split(","));

		employeeInfo.setEmployeeID(employeeID);
		employeeInfo.setPersonName(personName);
		employeeInfo.setDepartmentID(departmentID);
		employeeInfo.setPosition(position);
		employeeInfo.setPositionrank(positionrank);
		employeeInfo.setSalary(salary);
		employeeInfo.setBirthday(birthday);
		employeeInfo.setEmail(email);
		employeeInfo.setPhone(phone);
		employeeInfo.setEducation(educationList);
		employeeInfo.setWorkExperience(workExperienceList);
		employeeInfo.setSkill(skillList);
		employeeInfo.setInterest(interestList);


		dao.addEmployeeInfo(employeeInfo);
		dao.addEducation(employeeInfo);
		dao.addWorkExperience(employeeInfo);
		dao.addSkill(employeeInfo);
		dao.addInterest(employeeInfo);
		
		Optional<DepartmentInfo> departmentInfoOpt = dao.findDepartmentInfo(departmentID);
		DepartmentInfo departmentInfo = departmentInfoOpt.get();
		departmentInfo.setEmployeeCount(departmentInfo.getEmployeeCount()+1);
		dao.updateDepartmentInfo(departmentInfo);
		
		
		return "redirect:/mvc/backend/modifyAndDeleteEmployeeInformationMenu";
	}
	
	// 部門表格

	// 部門表格表單
	// http://localhost:8080/EmployeeManagement/mvc/backend/modifyAndDeleteDepartmentInformation
	@GetMapping("/modifyAndDeleteDepartmentInformation")
	//@ResponseBody
	public String backendModifyAndDeleteDepartmentInformation(Model model) {
		
		List<DepartmentInfo> departmeantInfoList = dao.findAllDepartmentInfos();
		departmeantInfoList.remove(0);
		List<String> managerNameList = new ArrayList<>();
		
		for (DepartmentInfo departmentInfo : departmeantInfoList) {
			Integer managerId = departmentInfo.getManagerID();
			if(managerId == 0) {
				managerNameList.add("無");
			} else {
				Optional<EmployeeInfo> employeeInfoOpt = dao.findEmployeeInfoByEmployeeId(managerId);
				EmployeeInfo employeeInfo = employeeInfoOpt.get();
				if(employeeInfo == null) {
					managerNameList.add("無");
				} else {
					managerNameList.add(employeeInfo.getPersonName());
				}
			}
		}
		
		
		
		model.addAttribute("departmeantInfoList", departmeantInfoList);
		model.addAttribute("managerNameList", managerNameList);
		
		//return "employee_management/backend/departmeantForm/modifyAndDeleteDepartmeantInformation";
		return "employee_management\\backend\\departmeantForm\\modifyAndDeleteDepartmentInformation";
	}
	
	// 部門表格修改
	@GetMapping("/modifyAndDeleteDepartmentInformation/update")
	public String backendModifyAndDeleteDepartmentInformationUpdate(@RequestParam("departmentID") Integer departmentID,
																	 @RequestParam("departmentName") String departmentName,
																	 @RequestParam("managerID") Integer managerID,
																	 Model model) {
		
		Optional<DepartmentInfo> departmentInfoOpt = dao.findDepartmentInfo(departmentID);
		DepartmentInfo departmentInfo = departmentInfoOpt.get();
		departmentInfo.setDepartmentName(departmentName);
		departmentInfo.setManagerID(managerID);
		
		dao.updateDepartmentInfo(departmentInfo);

		
		return "redirect:/mvc/backend/modifyAndDeleteDepartmentInformation";
	}
	
	// 部門表格刪除
	@GetMapping("/modifyAndDeleteDepartmentInformation/delete")
	public String backendModifyAndDeleteDepartmentInformationDelete(@RequestParam("departmentID") Integer departmentID,
																	 Model model) {
		
		dao.updateLeaveRecordByDepartmentID(departmentID, 0);
		dao.deleteWorkHoursRecordByDepartmeant(departmentID);
		dao.deleteDepartmentInfo(departmentID);
		
		return "redirect:/mvc/backend/modifyAndDeleteDepartmentInformation";
	}
	
	// 部門表格增加
	@GetMapping("/modifyAndDeleteDepartmentInformation/add")
	public String backendModifyAndDeleteDepartmentInformationAdd(Model model) {
		
		dao.addDepartmentInfo();
		
		return "redirect:/mvc/backend/modifyAndDeleteDepartmentInformation";
	}
	
	
	// 請假表格

	// 請假表格表單
	// http://localhost:8080/EmployeeManagement/mvc/backend/modifyAndDeleteLeaveRecord
	@GetMapping("/modifyAndDeleteLeaveRecord")
	public String backendModifyAndDeleteLeaveRecord(Model model) {
		List<LeaveRecord> leaveRecordList = dao.findAllLeaveRecord();
		System.out.println(leaveRecordList);
		model.addAttribute("leaveRecordList", leaveRecordList);
		
		return "employee_management/backend/leaveRecordForm/modifyAndDeleteLeaveRecord";
	}
	
	//請假表格修改
	@GetMapping("/modifyAndDeleteLeaveRecord/updata")
	public String backendModifyAndDeleteLeaveRecordUpdata(@RequestParam("leaveNumber") Integer leaveNumber,
														  @RequestParam("leaveStartDate") String leaveStartDateString,
														  @RequestParam("hours") Integer hours,
														  @RequestParam("approval") Boolean approval,
														  Model model) {
		
		LocalDateTime leaveStartDate = LocalDateTime.parse(leaveStartDateString);
		
		Optional<LeaveRecord> leaveRecordOpt = dao.findLeaveRecord(leaveNumber);
		LeaveRecord leaveRecord = leaveRecordOpt.get();
		
		leaveRecord.setLeaveStartDate(leaveStartDate);
		leaveRecord.setHours(hours);
		leaveRecord.setApproval(approval);
		dao.updateLeaveRecord(leaveRecord);
		
		return "redirect:/mvc/backend/modifyAndDeleteLeaveRecord";
	}
	
	// 請假表格刪除
	@GetMapping("/modifyAndDeleteLeaveRecord/delete")
	public String backendModifyAndDeleteLeaveRecordDelete(@RequestParam("leaveNumber") Integer leaveNumber,
														  Model model) {
		dao.deleteLeaveRecord(leaveNumber);
		
		return "redirect:/mvc/backend/modifyAndDeleteLeaveRecord";
	}
	
	
	
	// 增加請假頁面
	// http://localhost:8080/EmployeeManagement/mvc/backend/leaveForm
	@GetMapping("/leaveForm")
	public String backendLeaveForm(Model model) {
		

		return "employee_management/backend/leaveRecordForm/leaveForm";
	}
	
	// 請假增加
	@PostMapping("/leaveForm/add")
	public String backendModifyAndDeleteLeaveRecordAdd(@RequestParam("employeeId") Integer employeeId,
													   @RequestParam("leaveStartDate") String leaveStartDateString,
													   @RequestParam("hours") Integer hours,
													   @RequestParam("approval") Boolean approval,
													   Model model) {
		
		LocalDateTime leaveStartDate = LocalDateTime.parse(leaveStartDateString);
		
		
		LeaveRecord leaveRecord = new LeaveRecord();
		leaveRecord.setEmployeeID(employeeId);
		leaveRecord.setLeaveStartDate(leaveStartDate);
		leaveRecord.setHours(hours);
		leaveRecord.setApproval(approval);
		
		Optional<EmployeeInfo> employeeInfoOpt = dao.findEmployeeInfoByEmployeeId(employeeId);
		EmployeeInfo employeeInfo = employeeInfoOpt.get();
		Integer departmentID = employeeInfo.getDepartmentID();
		leaveRecord.setDepartmentID(departmentID);
		
		dao.addLeaveRecord(leaveRecord);

		return "redirect:/mvc/backend/modifyAndDeleteLeaveRecord";
	}
	
	
	// 工時表格
	
	// 工時表格表單
	// http://localhost:8080/EmployeeManagement/mvc/backend/modifyAndDeleteWorkHoursRecord
	@GetMapping("/modifyAndDeleteWorkHoursRecord")
	public String backendModifyAndDeleteWorkHoursRecord(Model model) {
		List<WorkHoursRecord> workHoursRecordList = dao.findAllWorkHoursRecord();
		model.addAttribute("workHoursRecordList", workHoursRecordList);
		return "employee_management/backend/workHoursRecord/modifyAndDeleteWorkHoursRecord";
	}
	
	// 工時表格修改
	@GetMapping("/modifyAndDeleteWorkHoursRecord/updata")
	public String backendModifyAndDeleteWorkHoursRecordUpdata(@RequestParam("workHoursRecordID") Integer workHoursRecordID,
															  @RequestParam("employeeID") Integer employeeID,
														  	  @RequestParam("startTime") String startTimeString, 
														  	  @RequestParam("endTime") String endTimeString,
														  	  Model model) {
		LocalDateTime startTime = LocalDateTime.parse(startTimeString);
		LocalDateTime endTime = LocalDateTime.parse(endTimeString);
		
		
		Optional<WorkHoursRecord> workHoursRecordOpt = dao.findWorkHoursRecord(workHoursRecordID);
		WorkHoursRecord workHoursRecord = workHoursRecordOpt.get();
		
		workHoursRecord.setEmployeeID(employeeID);
		workHoursRecord.setStartTime(startTime);
		workHoursRecord.setEndTime(endTime);
		
		Optional<EmployeeInfo> employeeInfoOpt = dao.findEmployeeInfoByEmployeeId(employeeID);
		EmployeeInfo employeeInfo = employeeInfoOpt.get();
		Integer departmentID = employeeInfo.getDepartmentID();
		workHoursRecord.setDepartmentID(departmentID);
		
		
		dao.updateWorkHoursRecord(workHoursRecord);


		return "redirect:/mvc/backend/modifyAndDeleteWorkHoursRecord";
	}
		
	// 工時表格刪除
	@GetMapping("/modifyAndDeleteWorkHoursRecord/delete")
	public String backendModifyAndDeleteWorkHoursRecordDelete(@RequestParam("workHoursRecordID") Integer workHoursRecordID, Model model) {

		dao.deleteWorkHoursRecord(workHoursRecordID);
		
		return "redirect:/mvc/backend/modifyAndDeleteWorkHoursRecord";
	}
	
	// 工時表格增加
	@GetMapping("/modifyAndDeleteWorkHoursRecord/add")
	public String backendModifyAndDeleteWorkHoursRecordAdd() {
		WorkHoursRecord workHoursRecord = new WorkHoursRecord();
		
		
		
		dao.addWorkHoursRecord(workHoursRecord);

		return "redirect:/mvc/backend/modifyAndDeleteWorkHoursRecord";
	}
	
	// 簽到表格

	// 簽到表格表單
	// http://localhost:8080/EmployeeManagement/mvc/backend/modifyAndDeleteAttendanceTable
	@GetMapping("/modifyAndDeleteAttendanceTable")
	public String backendModifyAndDeleteAttendanceTable(Model model) {
		List<AttendanceTable> attendanceTableList = dao.findAllAttendanceTable();
		model.addAttribute("attendanceTableList", attendanceTableList);
		return "employee_management/backend/attendanceTable/modifyAndDeleteAttendanceTable";
	}
	
	// 簽到表格修改
	@GetMapping("/modifyAndDeleteAttendanceTable/updata")
	public String backendModifyAndDeleteAttendanceTableUpdata(@RequestParam("attendanceID") Integer attendanceID,
															  @RequestParam("checkInTime") String checkInTimeString,
															  @RequestParam("checkOutTime") String checkOutTimeString,
															  Model model,
															  HttpSession session) {

		LocalDateTime checkInTime;
		LocalDateTime checkOutTime;
		
		try {
			checkInTime = LocalDateTime.parse(checkInTimeString);
		} catch (Exception e) {
			checkInTime = null;
		}
		try {
			checkOutTime = LocalDateTime.parse(checkOutTimeString);
		} catch (Exception e) {
			checkOutTime = null;
		}
		
		
		Optional<AttendanceTable> attendanceTableOpt = dao.findAttendanceTable(attendanceID);
		AttendanceTable attendanceTable = attendanceTableOpt.get();
		if (attendanceTable.getCheckOutTime() == null) {
			
			Optional<EmployeeInfo> employeeInfoOpt = dao.findEmployeeInfoByEmployeeId(attendanceTable.getEmployeeID());
			EmployeeInfo employeeInfo = employeeInfoOpt.get();
			if(!employeeInfo.getNowSignInNumber().equals(0)) {
				
				employeeInfo.setNowSignInNumber(0);
				dao.updateEmployeeInfoNowSignInNumber(employeeInfo);
				EmployeeInfo userEmployeeInfo = (EmployeeInfo) session.getAttribute("employeeInfo");
				if (userEmployeeInfo.getEmployeeID().equals(employeeInfo.getEmployeeID())) {
					
					session.setAttribute("employeeInfo", employeeInfo);
				}
			}
		}
		
		attendanceTable.setCheckInTime(checkInTime);
		attendanceTable.setCheckOutTime(checkOutTime);
		
		dao.updateAttendanceTableAllData(attendanceTable);

		return "redirect:/mvc/backend/modifyAndDeleteAttendanceTable";
	}
			
	// 簽到表格刪除
	@GetMapping("/modifyAndDeleteAttendanceTable/delete")
	public String backendModifyAndDeleteAttendanceTableDelete(@RequestParam("attendanceID") Integer attendanceID,
															  HttpSession session) {
		Optional<AttendanceTable> attendanceTableOpt = dao.findAttendanceTable(attendanceID);
		AttendanceTable attendanceTable = attendanceTableOpt.get();
		
		
		Optional<EmployeeInfo> employeeInfoOpt = dao.findEmployeeInfoByEmployeeId(attendanceTable.getEmployeeID());
		EmployeeInfo employeeInfo = employeeInfoOpt.get();
		if(!employeeInfo.getNowSignInNumber().equals(0)) {
			
			employeeInfo.setNowSignInNumber(0);
			dao.updateEmployeeInfoNowSignInNumber(employeeInfo);
			EmployeeInfo userEmployeeInfo = (EmployeeInfo) session.getAttribute("employeeInfo");
			if (userEmployeeInfo.getEmployeeID().equals(employeeInfo.getEmployeeID())) {
				
				session.setAttribute("employeeInfo", employeeInfo);
			}
		}
		
		dao.deleteAttendanceTable(attendanceID);

		return "redirect:/mvc/backend/modifyAndDeleteAttendanceTable";
	}
	
	// 簽到表格表單
	// http://localhost:8080/EmployeeManagement/mvc/backend/attendanceForm
	@GetMapping("/attendanceForm")
	public String backendAttendanceForm() {
		
		return "employee_management/backend/attendanceTable/attendanceForm";
	}
		
	// 簽到表格增加
	@PostMapping("/attendanceForm/add")
	public String backendModifyAndDeleteAttendanceTableAdd(@RequestParam("employeeId") Integer employeeId,
														   @RequestParam("checkInTime") String checkInTimeString,
														   @RequestParam("checkOutTime") String checkOutTimeString,
														   HttpSession session) {
		Optional<IntegerData> integerDataOpt = dao.findIntegerData("latestCheckInNumber");
		IntegerData integerData = integerDataOpt.get();
		Integer latestCheckInNumber = integerData.getNumber();
		
		integerData.setNumber(latestCheckInNumber + 1);
		dao.updateIntegerData(integerData);
		
		
		LocalDateTime checkInTime;
		LocalDateTime checkOutTime;
		
		try {
			checkInTime = LocalDateTime.parse(checkInTimeString);
		} catch (Exception e) {
			checkInTime = null;
		}
		try {
			checkOutTime = LocalDateTime.parse(checkOutTimeString);
		} catch (Exception e) {
			checkOutTime = null;
		}
		
		Optional<EmployeeInfo> employeeInfoOpt = dao.findEmployeeInfoByEmployeeId(employeeId);
		EmployeeInfo employeeInfo = employeeInfoOpt.get();
		if(!employeeInfo.getNowSignInNumber().equals(0)) {
			AttendanceTable attendanceTableOld = new AttendanceTable();
			attendanceTableOld.setAttendanceID(employeeInfo.getNowSignInNumber());
			
			dao.updateAttendanceTable(attendanceTableOld);
			employeeInfo.setNowSignInNumber(0);
			dao.updateEmployeeInfoNowSignInNumber(employeeInfo);
			
			EmployeeInfo userEmployeeInfo = (EmployeeInfo) session.getAttribute("employeeInfo");
			if (userEmployeeInfo.getEmployeeID().equals(employeeInfo.getEmployeeID())) {
				
				session.setAttribute("employeeInfo", employeeInfo);
			}
		}
		
		if(checkOutTime == null) {
			employeeInfo.setNowSignInNumber(latestCheckInNumber);
			dao.updateEmployeeInfoNowSignInNumber(employeeInfo);
			
			EmployeeInfo userEmployeeInfo = (EmployeeInfo) session.getAttribute("employeeInfo");
			if (userEmployeeInfo.getEmployeeID().equals(employeeInfo.getEmployeeID())) {
				
				session.setAttribute("employeeInfo", employeeInfo);
			}
		}
		
		
		AttendanceTable attendanceTable = new AttendanceTable();
		attendanceTable.setAttendanceID(latestCheckInNumber);
		attendanceTable.setEmployeeID(employeeId);
		attendanceTable.setCheckInTime(checkInTime);
		attendanceTable.setCheckOutTime(checkOutTime);
		
		dao.addAttendanceTableCanNull(attendanceTable);
		

		return "redirect:/mvc/backend/modifyAndDeleteAttendanceTable";
	}
	
}
