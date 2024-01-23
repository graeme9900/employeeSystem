package spring_mvc.employee_management.model.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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


public interface EmployeeManagementDao {
	
	// -------- 員工資料 --------

	
    /**
     *  1.添加員工資訊
     * @param employee
     */
    void addEmployeeInfo(EmployeeInfo employee);

    /**
     *  2.刪除員工資訊
     * @param employeeId
     */
    void deleteEmployeeInfo(Integer employeeId);

    // 修改員工資訊
    void updateEmployeeInfo(EmployeeInfo employee);
    
    // 修改員工密碼
 	void updateEmployeeInfoPassword(EmployeeInfo employee);
    
	// 修改員工資訊的現在簽到號碼
    void updateEmployeeInfoNowSignInNumber(EmployeeInfo employee);

    /**
     *  6.用id查詢員工資訊
     * @param employeeId
     * @return 員工資訊(Opt)
     */
    Optional<EmployeeInfo> findEmployeeInfoByEmployeeId(Integer employeeId);
    
    /**
     *  7.用帳號查詢員工資訊
     * @param employeeAccount
     * @return 員工資訊(Opt)
     */
    Optional<EmployeeInfo> findEmployeeInfoByAccount(String employeeAccount);
    
    /**
     *  8.用departmentID尋找員工資料
     * @param departmentId
     * @return 員工資訊(Opt)
     */
 	List<EmployeeInfo> findAllEmployeeInfoByDepartmentID(Integer departmentId);
	
	// 尋找所有員工資料
	List<EmployeeInfo> findAllEmployeeInfos();
	
	
	
    // -------- 學歷 --------

    // 添加學歷
    void addEducation(EmployeeInfo employeeInfo);

    // 刪除學歷
    void deleteEducation(Integer employeeId);

    // 修改學歷
    void updateEducation(Education education);

    // 查詢學歷
    List<Education> findEducation(Integer employeeId);
    
    // -------- 工作經歷 --------

    // 添加工作經歷
    void addWorkExperience(EmployeeInfo employeeInfo);

    // 刪除工作經歷
    void deleteWorkExperience(Integer employeeId);

    // 修改工作經歷
    void updateWorkExperience(WorkExperience workExperience);

    // 查詢工作經歷
    List<WorkExperience> findWorkExperience(Integer employeeId);
    
    // -------- 技能 --------

    // 添加技能
    void addSkill(EmployeeInfo employeeInfo);

    // 刪除技能
    void deleteSkill(Integer employeeId);

    // 修改技能
    void updateSkill(Skill skill);

    // 查詢技能
    List<Skill> findSkill(Integer employeeId);
    
    // -------- 興趣 --------

    // 添加興趣
    void addInterest(EmployeeInfo employeeInfo);

    // 刪除興趣
    void deleteInterest(Integer employeeId);

    // 修改興趣
    void updateInterest(Interest interest);

    // 查詢興趣
    List<Interest> findInterest(Integer employeeId);
    
    // -------- 部門資訊 --------
	
    // 添加部門資訊
    void addDepartmentInfo();

    // 刪除部門資訊
    void deleteDepartmentInfo(Integer departmentId);

    // 修改部門資訊
    void updateDepartmentInfo(DepartmentInfo department);
    
    // 用部門ID修改部門經理資訊
 	void updateDepartmentInfoToManagerID(Integer departmentID, Integer employeeID);

    // 查詢部門資訊
    Optional<DepartmentInfo> findDepartmentInfo(Integer departmentId);
    
    // 用經理ID尋找部門資料
 	List<DepartmentInfo> findDepartmentInfosByManagerID(Integer managerID);
 	
 	// 尋找所有部門資料
 	List<DepartmentInfo> findAllDepartmentInfos();
    
    // -------- 請假紀錄 --------

    // 添加請假紀錄
    void addLeaveRecord(LeaveRecord leaveRecord);

    // 刪除請假紀錄
    void deleteLeaveRecord(Integer leaveRecordId);

    // 修改請假紀錄
    void updateLeaveRecord(LeaveRecord leaveRecord);
    
    // 刪除員工所有請假紀錄
    void deleteLeaveRecordByEmployeeID(Integer employeeID);
    
    // 查詢請假紀錄
    Optional<LeaveRecord> findLeaveRecord(Integer leaveRecordNumber);
    
    // 修改請假紀錄部門
 	void updateLeaveRecordByDepartmentID(Integer beforeDepartmentID, Integer afterDepartmentID);
    
    // 簽核請假紀錄
    void updateLeaveRecordByLeaveNumberID(Integer leaveNumber);
    
    // 用員工ID修改請假紀錄部門
 	void updateLeaveRecordDepartmentIDByEmployeeID(Integer employeeID, Integer departmentID);
    
    // 用部門ID查詢請假紀錄
 	List<LeaveRecord> findLeaveRecordByDepartmentID(Integer departmentId);
 	
 	// 用員工ID查詢未簽核請假紀錄
 	List<LeaveRecord> findLeaveRecordByEmployeeID(Integer employeeID);
 	
 	// 查詢所有請假紀錄
 	List<LeaveRecord> findAllLeaveRecord();
    
    // -------- 工時記錄 --------

    // 添加工時記錄
    void addWorkHoursRecord(WorkHoursRecord workHoursRecord);

    // 刪除工時記錄
    void deleteWorkHoursRecord(Integer workHoursRecordId);

    // 修改工時記錄
    void updateWorkHoursRecord(WorkHoursRecord workHoursRecord);
    
    // 用員工ID刪除工時記錄
 	void deleteWorkHoursRecordByEmployeeID(Integer employeeID);
 	
 	// 用部門ID刪除工時記錄
 	void deleteWorkHoursRecordByDepartmeant(Integer departmeantID);

    // 查詢工時記錄
    Optional<WorkHoursRecord> findWorkHoursRecord(Integer workHoursRecordId);
    
    // 用部門ID查詢工時記錄
 	List<WorkHoursRecord> findWorkHoursRecordByDepartmentID(Integer departmentID);
    
    // 用員工ID查詢工時記錄
    List<WorkHoursRecord> findWorkHoursRecordByEmployeeID(Integer employeeID);
    
    // 用部門ID查詢工時記錄
 	List<WorkHoursRecord> findAllWorkHoursRecord();

    // -------- 簽到表 --------
    
    // 添加簽到
    void addAttendanceTable(AttendanceTable attendanceTable);

    // 刪除簽到
    void deleteAttendanceTable(Integer employeeID);

    // 修改簽到
    void updateAttendanceTable(AttendanceTable attendanceTable);
    
    // 用employee修改簽到
 	void updateAttendanceTableByEmployee(EmployeeInfo employeeInfo);

    // 查詢簽到
    Optional<AttendanceTable> findAttendanceTable(Integer employeeID);
    
    
    
    // -------- 數字資料 --------
    
    // 添加數字資料
    void addIntegerData(IntegerData integerData);

    // 刪除數字資料
    void deleteIntegerData(String dataName);

    // 修改數字資料
    void updateIntegerData(IntegerData integerData);

    // 查詢數字資料
    Optional<IntegerData> findIntegerData(String dataName);
    
    
    // ...其他可能的操作

    // 關閉資料庫連接
    void closeConnection();
}
