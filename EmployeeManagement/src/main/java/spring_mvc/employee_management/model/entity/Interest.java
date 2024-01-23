package spring_mvc.employee_management.model.entity;

public class Interest {

	private Integer employeeID;
	private String interest;

	public Interest() {
		super();
	}

	public Interest(Integer employeeID, String interest) {
		super();
		this.employeeID = employeeID;
		this.interest = interest;
	}

	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	@Override
	public String toString() {
		return "Interest [employeeID=" + employeeID + ", interest=" + interest + "]";
	}

}
