package spring_mvc.employee_management.model.entity;

public class DepartmentInfo {
	private Integer departmentID; // 部門編號
	private String departmentName; // 部門
	private Integer employeeCount; // 人數
	private Integer managerID; // 主管ID

	public DepartmentInfo() {

	}

	public DepartmentInfo(Integer departmentID, String departmentName, Integer employeeCount, Integer managerID) {
		super();
		this.departmentID = departmentID;
		this.departmentName = departmentName;
		this.employeeCount = employeeCount;
		this.managerID = managerID;
	}

	public Integer getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(Integer departmentID) {
		this.departmentID = departmentID;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getEmployeeCount() {
		return employeeCount;
	}

	public void setEmployeeCount(Integer employeeCount) {
		this.employeeCount = employeeCount;
	}

	public Integer getManagerID() {
		return managerID;
	}

	public void setManagerID(Integer managerID) {
		this.managerID = managerID;
	}

	@Override
	public String toString() {
		return "DepartmentInfo [departmentID=" + departmentID + ", departmentName=" + departmentName
				+ ", employeeCount=" + employeeCount + ", managerID=" + managerID + "]";
	}

}
