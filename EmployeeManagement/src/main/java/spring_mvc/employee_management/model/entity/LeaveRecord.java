package spring_mvc.employee_management.model.entity;

import java.time.LocalDateTime;

public class LeaveRecord {
	private Integer leaveNumber;
	private Integer employeeID; // 員工編號
	private Integer departmentID; // 部門編號
	private LocalDateTime leaveStartDate; // 請假開始日期
	private Integer hours; // 小時數計
	private Boolean approval; // 簽核

	// 關聯物件
	private EmployeeInfo employeeInfo;
	private DepartmentInfo departmentInfo;

	public LeaveRecord() {
		super();
	}

	public LeaveRecord(Integer leaveNumber, Integer employeeID, Integer departmentID, LocalDateTime leaveStartDate,
			Integer hours, Boolean approval) {
		super();
		this.leaveNumber = leaveNumber;
		this.employeeID = employeeID;
		this.departmentID = departmentID;
		this.leaveStartDate = leaveStartDate;
		this.hours = hours;
		this.approval = approval;
	}

	public Integer getLeaveNumber() {
		return leaveNumber;
	}

	public void setLeaveNumber(Integer leaveNumber) {
		this.leaveNumber = leaveNumber;
	}

	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public Integer getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(Integer departmentID) {
		this.departmentID = departmentID;
	}

	public LocalDateTime getLeaveStartDate() {
		return leaveStartDate;
	}

	public void setLeaveStartDate(LocalDateTime leaveStartDate) {
		this.leaveStartDate = leaveStartDate;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public Boolean getApproval() {
		return approval;
	}

	public void setApproval(Boolean approval) {
		this.approval = approval;
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
		return "LeaveRecord [leaveNumber=" + leaveNumber + ", employeeID=" + employeeID + ", departmentID="
				+ departmentID + ", leaveStartDate=" + leaveStartDate + ", hours=" + hours + ", approval=" + approval
				+ ", employeeInfo=" + employeeInfo + ", departmentInfo=" + departmentInfo + "]";
	}

}
