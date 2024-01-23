package spring_mvc.employee_management.model.entity;

import java.time.LocalDateTime;
import java.time.LocalDate;

public class WorkHoursRecord {
	private Integer departmentID;
	private Integer employeeID;
	private LocalDateTime startTime;
	private LocalDateTime endTime;

	private EmployeeInfo employeeInfo;
	private DepartmentInfo departmentInfo;

	public WorkHoursRecord() {

	}

	public WorkHoursRecord(Integer departmentID, Integer employeeID, LocalDateTime startTime, LocalDateTime endTime) {
		super();
		this.departmentID = departmentID;
		this.employeeID = employeeID;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Integer getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(Integer departmentID) {
		this.departmentID = departmentID;
	}

	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public EmployeeInfo getEmployeeInfo() {
		return employeeInfo;
	}

	public void setEmployeeInfo(EmployeeInfo employeeInfo) {
		this.employeeInfo = employeeInfo;
	}

	public DepartmentInfo getDepartmentInfo() {
		return departmentInfo;
	}

	public void setDepartmentInfo(DepartmentInfo departmentInfo) {
		this.departmentInfo = departmentInfo;
	}

	@Override
	public String toString() {
		return "WorkHoursRecord [departmentID=" + departmentID + ", employeeID=" + employeeID + ", startTime="
				+ startTime + ", endTime=" + endTime + ", employeeInfo=" + employeeInfo + ", departmentInfo="
				+ departmentInfo + "]";
	}

	
	
	
}