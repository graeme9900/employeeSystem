package spring_mvc.employee_management.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring_mvc.employee_management.model.dao.EmployeeManagementDao;
import spring_mvc.employee_management.model.entity.EmployeeInfo;

@Controller
@RequestMapping("/administrator")
public class administratorController {

	@Autowired
	private EmployeeManagementDao dao;
	
	// 管理員目錄
	// http://localhost:8080/EmployeeManagement/mvc/administrator/index
	@GetMapping("/index")
	public String administratorIndex() {
		return "employee_management/administrator/index";
	}
	
	// 修改權限頁面
	// http://localhost:8080/EmployeeManagement/mvc/administrator/modifyPermissions
	@GetMapping("/modifyPermissions")
	public String administratorModifyPermissions(Model model) {
		List<EmployeeInfo> employeeInfoList = dao.findAllEmployeeInfos();
		model.addAttribute("employeeInfoList", employeeInfoList);
		return "employee_management/administrator/permissions/modifyPermissions";
	}
	
	// 修改權限
	@GetMapping("/modifyPermissions/updata")
	public String administratorModifyPermissionsUpdata(@RequestParam("employeeID") Integer employeeID,
													   @RequestParam("permissions") Integer permissions,
													   HttpSession session,
													   Model model) {
		EmployeeInfo nowEmployeeInfo = (EmployeeInfo) session.getAttribute("employeeInfo");

		Optional<EmployeeInfo> employeeInfoOpt = dao.findEmployeeInfoByEmployeeId(employeeID);
		EmployeeInfo employeeInfo = employeeInfoOpt.get();
			
		employeeInfo.setPermissions(permissions);
			
		dao.updateEmployeeInfo(employeeInfo);
			
			
		return "redirect:/mvc/administrator/modifyPermissions";
		
		
	}
}
