package spring_mvc.employee_management.model.entity;

import java.time.LocalDate;

public class AttendanceTable {

	private Integer attendanceID;
	private Integer employeeID;
	private LocalDate checkInTime;
	private LocalDate checkOutTime;
	
	public AttendanceTable() {
		super();
	}

	public AttendanceTable(Integer attendanceID, Integer employeeID, LocalDate checkInTime, LocalDate checkOutTime) {
		super();
		this.attendanceID = attendanceID;
		this.employeeID = employeeID;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
	}

	public Integer getAttendanceID() {
		return attendanceID;
	}

	public void setAttendanceID(Integer attendanceID) {
		this.attendanceID = attendanceID;
	}

	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public LocalDate getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(LocalDate checkInTime) {
		this.checkInTime = checkInTime;
	}

	public LocalDate getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(LocalDate checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	@Override
	public String toString() {
		return "AttendanceTable [attendanceID=" + attendanceID + ", employeeID=" + employeeID + ", checkInTime="
				+ checkInTime + ", checkOutTime=" + checkOutTime + "]";
	}
	
	
	
}
