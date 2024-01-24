package spring_mvc.employee_management.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendanceTable {

	private Integer attendanceID;
	private Integer employeeID;
	private LocalDateTime checkInTime;
	private LocalDateTime checkOutTime;
	
	public AttendanceTable() {
		super();
	}

	public AttendanceTable(Integer attendanceID, Integer employeeID, LocalDateTime checkInTime, LocalDateTime checkOutTime) {
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

	public LocalDateTime getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(LocalDateTime checkInTime) {
		this.checkInTime = checkInTime;
	}

	public LocalDateTime getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(LocalDateTime checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	@Override
	public String toString() {
		return "AttendanceTable [attendanceID=" + attendanceID + ", employeeID=" + employeeID + ", checkInTime="
				+ checkInTime + ", checkOutTime=" + checkOutTime + "]";
	}
	
	
	
}
