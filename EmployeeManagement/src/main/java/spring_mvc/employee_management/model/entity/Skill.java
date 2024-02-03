package spring_mvc.employee_management.model.entity;

public class Skill {

	private Integer employeeID;
	private String skill;

	public Skill() {
		super();
	}

	public Skill(Integer employeeID, String skill) {
		super();
		this.employeeID = employeeID;
		this.skill = skill;
	}

	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	@Override
	public String toString() {
		return "Skill [employeeID=" + employeeID + ", skill=" + skill + "]";
	}

}
