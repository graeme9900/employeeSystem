package spring_mvc.employee_management.model.entity;

public class WorkExperience {
	private Integer employeeID;
	private String workExperience;

	public WorkExperience() {
		super();
	}

	public WorkExperience(Integer employeeID, String workExperience) {
		super();
		this.employeeID = employeeID;
		this.workExperience = workExperience;
	}

	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	@Override
	public String toString() {
		return "WorkExperience [employeeID=" + employeeID + ", workExperience=" + workExperience + "]";
	}

}
