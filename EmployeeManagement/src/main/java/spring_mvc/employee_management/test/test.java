package spring_mvc.employee_management.test;

import org.springframework.beans.factory.annotation.Autowired;

import spring_mvc.employee_management.model.dao.EmployeeManagementDao;
import spring_mvc.employee_management.model.dao.EmployeeManagementDaoMySQL;


public class test {
	@Autowired
	private static EmployeeManagementDao dao;

	public static void main(String[] args) {
		
		System.out.println(dao.findEmployeeInfoByEmployeeId(1));

	}

}
