package spring_mvc.employee_management.model.dao;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DaoSupport;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring_mvc.employee_management.model.entity.AttendanceTable;
import spring_mvc.employee_management.model.entity.DepartmentInfo;
import spring_mvc.employee_management.model.entity.Education;
import spring_mvc.employee_management.model.entity.EmployeeInfo;
import spring_mvc.employee_management.model.entity.IntegerData;
import spring_mvc.employee_management.model.entity.Interest;
import spring_mvc.employee_management.model.entity.LeaveRecord;
import spring_mvc.employee_management.model.entity.Skill;
import spring_mvc.employee_management.model.entity.WorkExperience;
import spring_mvc.employee_management.model.entity.WorkHoursRecord;

@Repository
public class EmployeeManagementDaoMySQL implements EmployeeManagementDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// -------- 員工資料 --------

	// 添加員工資訊
	@Override
	public void addEmployeeInfo(EmployeeInfo employee) {

		String sql1 = "INSERT INTO employeeInfo (employeeID, personName, departmentID, position, positionrank, salary, birthday, email, phone, account)\n"
				+ "VALUES\n" + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		String sql2 = "UPDATE employeeInfo\r\n" + "SET account = CONCAT('user_', employeeID)\r\n"
				+ "WHERE employeeID = LAST_INSERT_ID();";

		jdbcTemplate.update(sql1, employee.getEmployeeID(), employee.getPersonName(), employee.getDepartmentID(), employee.getPosition(),
				employee.getPositionrank(), employee.getSalary(), employee.getBirthday(), employee.getEmail(),
				employee.getPhone(), employee.getAccount());

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		jdbcTemplate.update(sql2);

	}

	// 刪除員工資訊
	@Override
	public void deleteEmployeeInfo(Integer employeeId) {
		this.deleteEducation(employeeId);
		this.deleteWorkExperience(employeeId);
		this.deleteSkill(employeeId);
		this.deleteInterest(employeeId);
		this.deleteLeaveRecordByEmployeeID(employeeId);
		this.deleteWorkHoursRecordByEmployeeID(employeeId);
		
		
		List<DepartmentInfo> departmentInfoList = this.findDepartmentInfosByManagerID(employeeId);
		List<Integer> departmentIds = departmentInfoList.stream()
                .map(DepartmentInfo::getDepartmentID)
                .collect(Collectors.toList());
		for (Integer departmentId : departmentIds) {
			updateDepartmentInfoToManagerID(departmentId , 0);
		}
		
		String sql = "DELETE FROM employeeInfo\n" + "WHERE employeeID = ?";
		jdbcTemplate.update(sql, employeeId);

	}

	// 修改員工資訊
	@Override
	public void updateEmployeeInfo(EmployeeInfo employee) {
		String sql = "UPDATE employeeInfo\r\n" + "SET\r\n" + "    personName = ?,\r\n" + "    departmentID = ?,\r\n"
				+ "    position = ?,\r\n" + "    positionrank = ?,\r\n" + "    hireDate = ?,\r\n"
				+ "    salary = ?,\r\n" + "    birthday = ?,\r\n" + "    email = ?,\r\n" + "    phone = ?,\r\n"
				+ "    account = ?,\r\n" + "    password = ?,\r\n" + "    permissions = ?\r\n"
				+ "WHERE employeeID = ?\r\n";

		jdbcTemplate.update(sql, employee.getPersonName(), employee.getDepartmentID(), employee.getPosition(),
				employee.getPositionrank(), employee.getHireDate(), employee.getSalary(), employee.getBirthday(),
				employee.getEmail(), employee.getPhone(), employee.getAccount(), employee.getPassword(),
				employee.getPermissions(), employee.getEmployeeID());
	}
	
	// 修改員工密碼
	@Override
	public void updateEmployeeInfoPassword(EmployeeInfo employee) {
		String sql = "UPDATE employeeInfo\r\n" + "SET\r\n" + "    password = ?\r\n" + "WHERE employeeID = ?\r\n";

		jdbcTemplate.update(sql, employee.getPassword(), employee.getEmployeeID());
	}

	// 修改員工資訊的現在簽到號碼
	@Override
	public void updateEmployeeInfoNowSignInNumber(EmployeeInfo employee) {
		String sql = "UPDATE employeeInfo\r\n" + "SET\r\n" + "    nowSignInNumber = ?\r\n" + "WHERE employeeID = ?\r\n";
		jdbcTemplate.update(sql, employee.getNowSignInNumber(), employee.getEmployeeID());
	}

	// 用id查詢員工資訊
	@Override
	public Optional<EmployeeInfo> findEmployeeInfoByEmployeeId(Integer employeeId) {
		String sql = "select employeeID, personName, departmentID, position, positionrank, hireDate, salary, birthday, email, phone, account, password, permissions, nowSignInNumber from employeeinfo\n"
				+ "WHERE employeeID = ?";
		try {
			EmployeeInfo employeeInfo = jdbcTemplate.queryForObject(sql,
						new BeanPropertyRowMapper<>(EmployeeInfo.class), employeeId);
			employeeInfo.setEducation(this.findEducation(employeeId).stream()
					                    .map(Education::getEducation)
					                    .collect(Collectors.toList()));
			employeeInfo.setWorkExperience(this.findWorkExperience(employeeId).stream()
					                    .map(WorkExperience::getWorkExperience)
					                    .collect(Collectors.toList()));
			employeeInfo.setSkill(this.findSkill(employeeId).stream()
					                    .map(Skill::getSkill)
					                    .collect(Collectors.toList()));
			employeeInfo.setInterest(this.findInterest(employeeId).stream()
					                    .map(Interest::getInterest)
					                    .collect(Collectors.toList()));
			return Optional.ofNullable(employeeInfo);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	// 用帳號查詢員工資訊
	@Override
	public Optional<EmployeeInfo> findEmployeeInfoByAccount(String employeeAccount) {
		String sql = "select employeeID, personName, departmentID, position, positionrank, hireDate, salary, birthday, email, phone, account, password, permissions, nowSignInNumber from employeeinfo\n" + "WHERE account = ?";
		try {
			EmployeeInfo employeeInfo = jdbcTemplate.queryForObject(sql,
					new BeanPropertyRowMapper<>(EmployeeInfo.class), employeeAccount);
			employeeInfo.setEducation(this.findEducation(employeeInfo.getEmployeeID()).stream()
                    .map(Education::getEducation)
                    .collect(Collectors.toList()));
			employeeInfo.setWorkExperience(this.findWorkExperience(employeeInfo.getEmployeeID()).stream()
                    .map(WorkExperience::getWorkExperience)
                    .collect(Collectors.toList()));
			employeeInfo.setSkill(this.findSkill(employeeInfo.getEmployeeID()).stream()
                    .map(Skill::getSkill)
                    .collect(Collectors.toList()));
			employeeInfo.setInterest(this.findInterest(employeeInfo.getEmployeeID()).stream()
                    .map(Interest::getInterest)
                    .collect(Collectors.toList()));
			return Optional.ofNullable(employeeInfo);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}

	}

	// 用departmentID尋找員工資料
	@Override
	public List<EmployeeInfo> findAllEmployeeInfoByDepartmentID(Integer departmentID) {
		String sql = "select employeeID, personName, departmentID, position, positionrank, hireDate, salary, birthday, email, phone, account, password, permissions, nowSignInNumber from employeeinfo " + "where departmentID = ?";
		
		List<EmployeeInfo> employeeInfoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EmployeeInfo.class), departmentID);
		for (EmployeeInfo employeeInfo : employeeInfoList) {
			employeeInfo.setEducation(this.findEducation(employeeInfo.getEmployeeID()).stream()
                    .map(Education::getEducation)
                    .collect(Collectors.toList()));
			employeeInfo.setWorkExperience(this.findWorkExperience(employeeInfo.getEmployeeID()).stream()
                    .map(WorkExperience::getWorkExperience)
                    .collect(Collectors.toList()));
			employeeInfo.setSkill(this.findSkill(employeeInfo.getEmployeeID()).stream()
                    .map(Skill::getSkill)
                    .collect(Collectors.toList()));
			employeeInfo.setInterest(this.findInterest(employeeInfo.getEmployeeID()).stream()
                    .map(Interest::getInterest)
                    .collect(Collectors.toList()));
		}
		
		
		return employeeInfoList;
	}

	// 尋找所有員工資料
	@Override
	public List<EmployeeInfo> findAllEmployeeInfos() {
		String sql = "select employeeID, personName, departmentID, position, positionrank, hireDate, salary, birthday, email, phone, account, password, permissions, nowSignInNumber from employeeinfo\n";

		List<EmployeeInfo> employeeInfoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EmployeeInfo.class));
		for (EmployeeInfo employeeInfo : employeeInfoList) {
			employeeInfo.setEducation(this.findEducation(employeeInfo.getEmployeeID()).stream()
                    .map(Education::getEducation)
                    .collect(Collectors.toList()));
			employeeInfo.setWorkExperience(this.findWorkExperience(employeeInfo.getEmployeeID()).stream()
                    .map(WorkExperience::getWorkExperience)
                    .collect(Collectors.toList()));
			employeeInfo.setSkill(this.findSkill(employeeInfo.getEmployeeID()).stream()
                    .map(Skill::getSkill)
                    .collect(Collectors.toList()));
			employeeInfo.setInterest(this.findInterest(employeeInfo.getEmployeeID()).stream()
                    .map(Interest::getInterest)
                    .collect(Collectors.toList()));
		}
		
		
		return employeeInfoList;
	}

	// -------- 學歷 --------

	// 添加學歷
	@Override
	public void addEducation(EmployeeInfo employeeInfo) {
		String sql = "insert into education (employeeID ,education)\r\n" + "values(?, ?)";
		List<String> educationList = employeeInfo.getEducation();
		for (String education : educationList) {
			jdbcTemplate.update(sql, employeeInfo.getEmployeeID(), education);
		}

	}

	// 刪除學歷
	@Override
	public void deleteEducation(Integer employeeID) {
		String sql = "DELETE FROM education\r\n" + "where employeeID = ?";

		jdbcTemplate.update(sql, employeeID);

	}

	// 修改學歷
	@Override
	public void updateEducation(Education education) {
		// TODO Auto-generated method stub

	}

	// 查詢學歷
	@Override
	public List<Education> findEducation(Integer employeeId) {
		String sql = "SELECT educationID, employeeID, education FROM education where employeeID = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Education.class), employeeId);
	}

	// -------- 工作經歷 --------

	// 添加工作經歷
	@Override
	public void addWorkExperience(EmployeeInfo employeeInfo) {
		String sql = "insert into workexperience (employeeID ,workexperience)\r\n" + "values(?, ?)";
		List<String> workexperienceList = employeeInfo.getWorkExperience();
		for (String workexperience : workexperienceList) {
			jdbcTemplate.update(sql, employeeInfo.getEmployeeID(), workexperience);
		}

	}

	// 刪除工作經歷
	@Override
	public void deleteWorkExperience(Integer employeeID) {
		String sql = "DELETE FROM workexperience\r\n" + "where employeeID = ?";

		jdbcTemplate.update(sql, employeeID);

	}

	// 修改工作經歷
	@Override
	public void updateWorkExperience(WorkExperience workExperience) {
		// TODO Auto-generated method stub

	}

	// 查詢工作經歷
	@Override
	public List<WorkExperience> findWorkExperience(Integer employeeId) {
		String sql = "SELECT experienceID, employeeID, workExperience FROM workexperience where employeeID = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WorkExperience.class), employeeId);
	}

	// -------- 技能 --------

	// 添加技能
	@Override
	public void addSkill(EmployeeInfo employeeInfo) {
		String sql = "insert into skill (employeeID ,skill)\r\n" + "values(?, ?)";
		List<String> skillList = employeeInfo.getSkill();
		for (String skill : skillList) {
			jdbcTemplate.update(sql, employeeInfo.getEmployeeID(), skill);
		}

	}

	// 刪除技能
	@Override
	public void deleteSkill(Integer employeeID) {
		String sql = "DELETE FROM skill\r\n" + "where employeeID = ?";

		jdbcTemplate.update(sql, employeeID);

	}

	// 修改技能
	@Override
	public void updateSkill(Skill skill) {
		// TODO Auto-generated method stub

	}

	// 查詢技能
	@Override
	public List<Skill> findSkill(Integer employeeId) {
		String sql = "SELECT skillID, employeeID, skill FROM skill where employeeID = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Skill.class), employeeId);
	}

	// -------- 興趣 --------

	// 添加興趣
	@Override
	public void addInterest(EmployeeInfo employeeInfo) {
		String sql = "insert into interest (employeeID ,interest)\r\n" + "values(?, ?)";
		List<String> interestList = employeeInfo.getInterest();
		for (String interest : interestList) {
			jdbcTemplate.update(sql, employeeInfo.getEmployeeID(), interest);
		}

	}

	// 刪除興趣
	@Override
	public void deleteInterest(Integer employeeID) {
		String sql = "DELETE FROM interest\r\n" + "where employeeID = ?";
		
		jdbcTemplate.update(sql, employeeID);

	}

	// 修改興趣
	@Override
	public void updateInterest(Interest interest) {
		// TODO Auto-generated method stub

	}

	// 查詢興趣
	@Override
	public List<Interest> findInterest(Integer employeeId) {
		String sql = "SELECT interestID, employeeID, interest FROM interest where employeeID = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Interest.class), employeeId);
	}

	// -------- 部門資訊 --------

	// 添加部門資訊
	@Override
	public void addDepartmentInfo() {
		String sql = "insert into departmentinfo (departmentID, employeeCount, managerID) \r\n"
					+ "values (?, 0, 0)";
		
		Optional<IntegerData> integerDataOpt = this.findIntegerData("departmentID");
		IntegerData integerData = integerDataOpt.get();
		Integer departmentID = integerData.getNumber();
		integerData.setNumber(departmentID + 1);
		this.updateIntegerData(integerData);
		
		
		
		jdbcTemplate.update(sql, departmentID);

	}

	// 刪除部門資訊
	@Override
	public void deleteDepartmentInfo(Integer departmentId) {
		String sql = "delete from departmentinfo\r\n"
					+ "where departmentID = ?";
		jdbcTemplate.update(sql, departmentId);
	}

	// 修改部門資訊
	@Override
	public void updateDepartmentInfo(DepartmentInfo department) {
		String sql = "update departmentinfo \r\n"
				+ "set departmentName = ?, employeeCount = ?, managerID = ?\r\n"
				+ "where departmentID = ?";
		jdbcTemplate.update(sql, department.getDepartmentName(), department.getEmployeeCount(), department.getManagerID(), department.getDepartmentID());

	}
	
	// 用部門ID修改部門經理資訊
	@Override
	public void updateDepartmentInfoToManagerID(Integer departmentID, Integer employeeID) {
		String sql = "update departmentinfo set managerID = ? where departmentID = ?";
		jdbcTemplate.update(sql, employeeID, departmentID);

	}

	// 查詢部門資訊
	@Override
	public Optional<DepartmentInfo> findDepartmentInfo(Integer departmentId) {
		String sql = "select departmentID, departmentName, employeeCount, managerID from departmentinfo where departmentID = ?";
		try {
			DepartmentInfo departmentInfo = jdbcTemplate.queryForObject(sql,
					new BeanPropertyRowMapper<>(DepartmentInfo.class), departmentId);
			return Optional.ofNullable(departmentInfo);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}
	
	// 用經理ID尋找部門資料
	@Override
	public List<DepartmentInfo> findDepartmentInfosByManagerID(Integer managerID) {
		String sql = "select departmentID, departmentName, employeeCount, managerID from departmentInfo where managerID = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DepartmentInfo.class), managerID);
	}

	// 尋找所有部門資料
	@Override
	public List<DepartmentInfo> findAllDepartmentInfos() {
		String sql = "select departmentID, departmentName, employeeCount, managerID from departmentInfo";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DepartmentInfo.class));
	}

	// -------- 請假紀錄 --------

	// 添加請假紀錄
	@Override
	public void addLeaveRecord(LeaveRecord leaveRecord) {
		String sql = "INSERT INTO  leaverecord (employeeID, departmentID, leaveStartDate, hours)\r\n"
				+ "VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, leaveRecord.getEmployeeID(), leaveRecord.getDepartmentID(),
				leaveRecord.getLeaveStartDate(), leaveRecord.getHours());

	}

	// 刪除請假紀錄
	@Override
	public void deleteLeaveRecord(Integer leaveRecordId) {
		String sql = "delete from leaverecord where leaveNumber = ?";
		jdbcTemplate.update(sql, leaveRecordId);

	}
	
	// 刪除員工所有請假紀錄
	@Override
	public void deleteLeaveRecordByEmployeeID(Integer employeeID) {
		String sql = "delete from leaverecord where employeeID = ?";
		jdbcTemplate.update(sql, employeeID);

	}
	
	// 修改請假紀錄
	@Override
	public void updateLeaveRecord(LeaveRecord leaveRecord) {
		String sql = "update leaverecord  set  leaveStartDate = ?, hours= ?, approval = ? where leaveNumber = ?;";
		jdbcTemplate.update(sql);

	}

	// 修改請假紀錄部門
	@Override
	public void updateLeaveRecordByDepartmentID(Integer beforeDepartmentID, Integer afterDepartmentID) {
		String sql = "update leaverecord \r\n"
					+ "set departmentID = ?\r\n"
					+ "where departmentID = ?";
		jdbcTemplate.update(sql, afterDepartmentID, beforeDepartmentID);

	}

	// 簽核請假紀錄
	@Override
	public void updateLeaveRecordByLeaveNumberID(Integer leaveNumber) {
		String sql = "update leaverecord\r\n" + "set approval=true\r\n" + "where leaveNumber = ?";
		jdbcTemplate.update(sql, leaveNumber);

	}
	
	

	// 查詢請假紀錄
	@Override
	public Optional<LeaveRecord> findLeaveRecord(Integer leaveRecordId) {
		// TODO Auto-generated method stub
		return null;
	}

	// 用部門ID查詢未簽核請假紀錄
	@Override
	public List<LeaveRecord> findLeaveRecordByDepartmentID(Integer departmentId) {
		String sql = "select leaveNumber, employeeID, departmentID, leaveStartDate, hours, approval from leaverecord\r\n"
				+ "where departmentID = ? and approval = false";
		List<LeaveRecord> leaveRecordList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(LeaveRecord.class),
				departmentId);
		for (LeaveRecord leaveRecord : leaveRecordList) {
			leaveRecord.setEmployeeInfo(this.findEmployeeInfoByEmployeeId(leaveRecord.getEmployeeID()).get());
			leaveRecord.setDepartmentInfo(this.findDepartmentInfo(leaveRecord.getDepartmentID()).get());
		}
		return leaveRecordList;
	}
	
	// 用員工ID查詢未簽核請假紀錄
	@Override
	public List<LeaveRecord> findLeaveRecordByEmployeeID(Integer employeeID) {
		String sql = "select leaveNumber, employeeID, departmentID, leaveStartDate, hours, approval from leaverecord\r\n"
				+ "where employeeID = ? and approval = false";
		List<LeaveRecord> leaveRecordList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(LeaveRecord.class),
				employeeID);
		for (LeaveRecord leaveRecord : leaveRecordList) {
			leaveRecord.setEmployeeInfo(this.findEmployeeInfoByEmployeeId(leaveRecord.getEmployeeID()).get());
			leaveRecord.setDepartmentInfo(this.findDepartmentInfo(leaveRecord.getDepartmentID()).get());
		}
		return leaveRecordList;
	}
	
	// 查詢所有請假紀錄
	@Override
	public List<LeaveRecord> findAllLeaveRecord() {
		String sql = "select leaveNumber, employeeID, departmentID, leaveStartDate, hours, approval from leaverecord";
		List<LeaveRecord> leaveRecordList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(LeaveRecord.class));
		for (LeaveRecord leaveRecord : leaveRecordList) {
			leaveRecord.setEmployeeInfo(this.findEmployeeInfoByEmployeeId(leaveRecord.getEmployeeID()).get());
			leaveRecord.setDepartmentInfo(this.findDepartmentInfo(leaveRecord.getDepartmentID()).get());
		}
		return leaveRecordList;
	}

	// -------- 工時記錄 --------

	// 添加工時記錄
	@Override
	public void addWorkHoursRecord(WorkHoursRecord workHoursRecord) {
		// TODO Auto-generated method stub

	}

	// 刪除工時記錄
	@Override
	public void deleteWorkHoursRecord(Integer workHoursRecordId) {
		// TODO Auto-generated method stub

	}

	// 用員工ID刪除工時記錄
	@Override
	public void deleteWorkHoursRecordByEmployeeID(Integer employeeID) {
		String sql = "delete from workhoursrecord where employeeID=?";
		jdbcTemplate.update(sql, employeeID);

	}
	
	// 用部門ID刪除工時記錄
	@Override
	public void deleteWorkHoursRecordByDepartmeant(Integer departmeantID) {
		String sql = "delete from workhoursrecord where departmentID = ?";
		jdbcTemplate.update(sql, departmeantID);

	}
	
	// 修改工時記錄
	@Override
	public void updateWorkHoursRecord(WorkHoursRecord workHoursRecord) {
		// TODO Auto-generated method stub

	}

	// 查詢工時記錄
	@Override
	public Optional<WorkHoursRecord> findWorkHoursRecord(Integer workHoursRecordId) {
		// TODO Auto-generated method stub
		return null;
	}

	// 用部門ID查詢工時記錄
	@Override
	public List<WorkHoursRecord> findWorkHoursRecordByDepartmentID(Integer departmentID) {
		String sql = "select \r\n" + "departmentID, employeeID, startTime, endTime\r\n"
				+ "from workhoursrecord                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            "
				+ "where departmentID = ?";

		List<WorkHoursRecord> workHoursRecordList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<>(WorkHoursRecord.class), departmentID);
		for (WorkHoursRecord workHoursRecord : workHoursRecordList) {
			workHoursRecord.setEmployeeInfo(this.findEmployeeInfoByEmployeeId(workHoursRecord.getEmployeeID()).get());
			workHoursRecord.setDepartmentInfo(this.findDepartmentInfo(workHoursRecord.getDepartmentID()).get());
		}

		return workHoursRecordList;
	}

	// 用員工ID查詢工時記錄
	@Override
	public List<WorkHoursRecord> findWorkHoursRecordByEmployeeID(Integer employeeID) {
		// TODO Auto-generated method stub
		return null;
	}

	// -------- 簽到表 --------

	// 添加簽到
	@Override
	public void addAttendanceTable(AttendanceTable attendanceTable) {
		String sql = "INSERT INTO AttendanceTable (attendanceID, employeeID, checkInTime) \r\n"
				+ "values (?, ?, NOW());";
		jdbcTemplate.update(sql, attendanceTable.getAttendanceID(), attendanceTable.getEmployeeID());
	}

	// 刪除簽到
	@Override
	public void deleteAttendanceTable(Integer employeeID) {
		// TODO Auto-generated method stub

	}

	// 修改簽到
	@Override
	public void updateAttendanceTable(AttendanceTable attendanceTable) {
		String sql = "UPDATE attendanceTable\n" + "SET\n" + "checkOutTime = NOW()\n" + "WHERE attendanceID = ?";
		jdbcTemplate.update(sql, attendanceTable.getAttendanceID());

	}

	// 用employee修改簽到
	@Override
	public void updateAttendanceTableByEmployee(EmployeeInfo employeeInfo) {
		String sql = "UPDATE attendanceTable\n" + "SET\n" + "checkOutTime = NOW()\n" + "WHERE attendanceID = ?";
		jdbcTemplate.update(sql, employeeInfo.getNowSignInNumber());

	}

	// 查詢簽到
	@Override
	public Optional<AttendanceTable> findAttendanceTable(Integer employeeID) {
		return null;
	}

	// -------- 數字資料 --------

	// 添加數字資料
	@Override
	public void addIntegerData(IntegerData integerData) {
		// TODO Auto-generated method stub

	}

	// 刪除數字資料
	@Override
	public void deleteIntegerData(String dataName) {
		// TODO Auto-generated method stub

	}

	// 修改數字資料
	@Override
	public void updateIntegerData(IntegerData integerData) {
		String sql = "UPDATE integerData\n" + "SET\n" + "number = ?\n" + "WHERE dataName = ?";
		jdbcTemplate.update(sql, integerData.getNumber(), integerData.getDataName());

	}

	// 查詢數字資料
	@Override
	public Optional<IntegerData> findIntegerData(String dataName) {
		String sql = "select dataName, number from integerdata\r\n" + "where dataName = ?";
		try {
			IntegerData integerData = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(IntegerData.class),
					dataName);
			return Optional.ofNullable(integerData);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	// ...其他可能的操作

	// 關閉資料庫連接
	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub

	}

}
