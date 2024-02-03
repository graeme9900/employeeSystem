package spring_mvc.employee_management.model.entity;

import java.time.LocalDate;
import java.util.List;

public class EmployeeInfo {
	// 員工資訊欄位
	private Integer employeeID; // 員工編號
	private String personName; // 姓名
	private Integer departmentID; // 部門編號
	private String position; // 職位
	private String positionrank; // 職級
	private LocalDate hireDate; // 到職日期
	private Float salary; // 薪水
	private LocalDate birthday; // 生日
	private String email; // 電子郵箱
	private String phone; // 電話

	private List<String> education; // 學歷
	private List<String> workExperience; // 工作經歷
	private List<String> skill; // 技能
	private List<String> interest; // 興趣

	private String account; // 帳號
	private String password; // 密碼
	private Integer permissions; // 權限
	private Integer nowSignInNumber; // 現在簽到號碼

	public EmployeeInfo() {
		super();
	}

	public EmployeeInfo(Integer employeeID, String personName, Integer departmentID, String position,
			String positionrank, LocalDate hireDate, Float salary, LocalDate birthday, String email, String phone,
			List<String> education, List<String> workExperience, List<String> skill, List<String> interest,
			String account, String password, Integer permissions, Integer nowSignInNumber) {
		super();
		this.employeeID = employeeID;
		this.personName = personName;
		this.departmentID = departmentID;
		this.position = position;
		this.positionrank = positionrank;
		this.hireDate = hireDate;
		this.salary = salary;
		this.birthday = birthday;
		this.email = email;
		this.phone = phone;
		this.education = education;
		this.workExperience = workExperience;
		this.skill = skill;
		this.interest = interest;
		this.account = account;
		this.password = password;
		this.permissions = permissions;
		this.nowSignInNumber = nowSignInNumber;
	}
	

	public EmployeeInfo(Integer employeeID, String personName, Integer departmentID, String position, String positionrank,
		LocalDate hireDate, Float salary, LocalDate birthday, String email, String phone) {
	super();
	this.employeeID = employeeID;
	this.personName = personName;
	this.departmentID = departmentID;
	this.position = position;
	this.positionrank = positionrank;
	this.hireDate = hireDate;
	this.salary = salary;
	this.birthday = birthday;
	this.email = email;
	this.phone = phone;
	}
	
	public Integer getEmployeeID() {
			return employeeID;
		}
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Integer getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(Integer departmentID) {
		this.departmentID = departmentID;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPositionrank() {
		return positionrank;
	}

	public void setPositionrank(String positionrank) {
		this.positionrank = positionrank;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<String> getEducation() {
		return education;
	}

	public void setEducation(List<String> education) {
		this.education = education;
	}

	public List<String> getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(List<String> workExperience) {
		this.workExperience = workExperience;
	}

	public List<String> getSkill() {
		return skill;
	}

	public void setSkill(List<String> skill) {
		this.skill = skill;
	}

	public List<String> getInterest() {
		return interest;
	}

	public void setInterest(List<String> interest) {
		this.interest = interest;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPermissions() {
		return permissions;
	}

	public void setPermissions(Integer permissions) {
		this.permissions = permissions;
	}

	public Integer getNowSignInNumber() {
		return nowSignInNumber;
	}

	public void setNowSignInNumber(Integer nowSignInNumber) {
		this.nowSignInNumber = nowSignInNumber;
	}

	@Override
	public String toString() {
		return "EmployeeInfo [employeeID=" + employeeID + ", personName=" + personName + ", departmentID="
				+ departmentID + ", position=" + position + ", positionrank=" + positionrank + ", hireDate=" + hireDate
				+ ", salary=" + salary + ", birthday=" + birthday + ", email=" + email + ", phone=" + phone
				+ ", education=" + education + ", workExperience=" + workExperience + ", skill=" + skill + ", interest="
				+ interest + ", account=" + account + ", password=" + password + ", permissions=" + permissions
				+ ", nowSignInNumber=" + nowSignInNumber + "]";
	}
	


}
