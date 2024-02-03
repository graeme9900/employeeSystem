package spring_mvc.employee_management.model.entity;

public class Education {
	private Integer employeeID;
	private String education;

	public Education() {
		super();
	}

	public Education(Integer employeeID, String education) {
		super();
		this.employeeID = employeeID;
		this.education = education;
	}

	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Override
	public String toString() {
		return "Education [employeeID=" + employeeID + ", education=" + education + "]";
	}

}
