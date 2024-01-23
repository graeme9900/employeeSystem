/*

-- 員工資訊表 EmployeeInfo
+-------------------+-------------------+----------------+
|   英文欄位          |   中文欄位          |  資料型態        |
+-------------------+-------------------+----------------+
|   employeeID      |   員工編號          |   INT          |
|   personName      |   姓名             |   VARCHAR(255) |
|   departmentID    |   部門編號          |   INT          |
|   position        |   職位             |   VARCHAR(255) |
|   positionrank    |   職級             |   VARCHAR(255) |
|   hireDate        |   到職日期          |   DATE         |
|   salary          |   薪水             |   FLOAT        |
|   birthday        |   生日             |   DATE         |
|   email           |   電子郵箱          |   VARCHAR(255) |
|   phone           |   電話             |   VARCHAR(255) |
|   account         |   帳號				|   VARCHAR(255) |
|   password        |   密碼             |   VARCHAR(255) |
|   permissions     |   權限				|   INT          |
|   nowSignInNumber |   現在簽到號碼	    |   INT          |
+-------------------+-------------------+----------------+


-- 學歷 Education
+-------------------+-------------------+----------------+
|   英文欄位          |   中文欄位          |  資料型態        |
+-------------------+-------------------+----------------+
|   employeeID      |   員工編號          |   INT          |
|   education       |   學歷             |   VARCHAR(255) |
+-------------------+-------------------+----------------+

-- 工作經歷 WorkExperience
+-------------------+-------------------+----------------+
|   英文欄位          |   中文欄位          |  資料型態        |
+-------------------+-------------------+----------------+
|   employeeID      |   員工編號          |   INT          |
|   workExperience  |   工作經歷          |   VARCHAR(255) |
+-------------------+-------------------+----------------+

-- 技能 Skill
+-------------------+-------------------+----------------+
|   英文欄位          |   中文欄位          |  資料型態        |
+-------------------+-------------------+----------------+
|   employeeID      |   員工編號          |   INT          |
|   skill           |   技能             |   VARCHAR(255) |
+-------------------+-------------------+----------------+

-- 興趣 Interest
+-------------------+-------------------+----------------+
|   英文欄位          |   中文欄位          |  資料型態        |
+-------------------+-------------------+----------------+
|   employeeID      |   員工編號          |   INT          |
|   interest        |   興趣             |   VARCHAR(255) |
+-------------------+-------------------+----------------+

-- 部門資訊表 DepartmentInfo
+-------------------+-------------------+----------------+
|   英文欄位          |   中文欄位          |  資料型態        |
+-------------------+-------------------+----------------+
|   departmentID    |   部門編號          |   INT          |
|   departmentName  |   部門             |   VARCHAR(255) |
|   employeeCount   |   人數             |   INT          |
|   managerID       |   主管ID           |   INT          |
+-------------------+-------------------+----------------+

-- 請假紀錄表 LeaveRecord
+-------------------+-------------------+--------------+
|   英文欄位          |   中文欄位          |  資料型態      |
+-------------------+-------------------+--------------+
|   leaveNumber     |   請假編號          |   INT        |
|   employeeID      |   員工編號          |   INT        |
|   departmentID    |   部門編號          |   INT        |
|   leaveStartDate  |   請假開始日期       |   DATETIME   |
|   hours           |   小時數計          |   INT        |
|   approval        |   簽核             |   BOOLEAN    |
+-------------------+-------------------+--------------+

-- 工時記錄表 WorkHoursRecord
+-------------------+-------------------+----------------+
|   英文欄位          |   中文欄位          |  資料型態        |
+-------------------+-------------------+----------------+
|   departmentID    |   部門編號          |   INT          |
|   employeeID      |   員工編號          |   INT          |
|   workDate        |   日期             |   DATETIME     | 
|   startTime       |   開始時間          |   INT          |
|   endTime         |   結束時間          |   INT          |
|   hours           |   小時             |   INT          |
+-------------------+-------------------+----------------+

-- 簽到表 AttendanceTable
+-------------------+-------------------+----------------+
|   英文欄位          |   中文欄位          |  資料型態        |
+-------------------+-------------------+----------------+
|   attendanceID    |   出席編號          |   INT          |
|   employeeID      |   員工編號          |   INT          |
|   checkInTime     |   簽到時間          |   DATETIME     |
|   checkOutTime    |   簽退時間          |   DATETIME     |
+-------------------+-------------------+----------------+

-- 整數用資料  IntegerData
+-------------------+-------------------+----------------+
|   英文欄位          |   中文欄位          |  資料型態        |
+-------------------+-------------------+----------------+
|   dataName        |   資料名稱		    |   VARCHAR(255) |
|   number          |   數字		        |   INT          |
+-------------------+-------------------+----------------+



*/

-- 刪除資料表
DROP TABLE if EXISTS education;
DROP TABLE if EXISTS workExperience;
DROP TABLE if EXISTS skill;
DROP TABLE if EXISTS interest;

drop table if exists attendanceTable;
drop table if exists leaveRecord;
drop table if exists workHoursRecord;
drop table if exists departmentInfo;
drop table if exists employeeInfo;
drop table if exists integerData;







-- 建立 EmployeeInfo table

CREATE TABLE IF NOT EXISTS employeeInfo (
    employeeID INT PRIMARY KEY,
    personName VARCHAR(255),
    departmentID INT,
    position VARCHAR(255),
    positionrank VARCHAR(255),
    hireDate DATE DEFAULT (CURDATE()),
    salary FLOAT,
    birthday DATE,
    email VARCHAR(255),
    phone VARCHAR(255),
    account VARCHAR(255),
    password VARCHAR(255) DEFAULT "12345678",
    permissions INT DEFAULT 1,
    nowSignInNumber INT DEFAULT 0
);



-- 建立 Education table
CREATE TABLE IF NOT EXISTS education (
    educationID INT AUTO_INCREMENT PRIMARY KEY,
    employeeID INT,
    education VARCHAR(255),
    FOREIGN KEY (employeeID) REFERENCES employeeInfo(employeeID)
);

-- 建立 WorkExperience table
CREATE TABLE IF NOT EXISTS workExperience (
    experienceID INT AUTO_INCREMENT PRIMARY KEY,
    employeeID INT,
    workExperience VARCHAR(255),
    FOREIGN KEY (employeeID) REFERENCES employeeInfo(employeeID)
);

-- 建立 Skill table
CREATE TABLE IF NOT EXISTS skill (
    skillID INT AUTO_INCREMENT PRIMARY KEY,
    employeeID INT,
    skill VARCHAR(255),
    FOREIGN KEY (employeeID) REFERENCES employeeInfo(employeeID)
);

-- 建立 Interest table
CREATE TABLE IF NOT EXISTS interest (
    interestID INT AUTO_INCREMENT PRIMARY KEY,
    employeeID INT,
    interest VARCHAR(255),
    FOREIGN KEY (employeeID) REFERENCES employeeInfo(employeeID)
);

-- 建立 DepartmentInfo table
CREATE TABLE IF NOT EXISTS departmentInfo (
    departmentID INT auto_increment PRIMARY KEY,
    departmentName VARCHAR(255),
    employeeCount INT,
    managerID INT
    -- FOREIGN KEY (managerID) REFERENCES employeeInfo(employeeID)
);

-- 設置 AUTO_INCREMENT = 1
alter table departmentInfo auto_increment = 1;

-- 建立 leaveRecord table
CREATE TABLE IF NOT EXISTS leaveRecord (
	leaveNumber INT auto_increment PRIMARY KEY,
    employeeID INT,
    departmentID INT,
    leaveStartDate DATETIME,
    hours INT,
    approval BOOLEAN DEFAULT false,
    FOREIGN KEY (employeeID) REFERENCES employeeInfo(employeeID),
    FOREIGN KEY (departmentID) REFERENCES DepartmentInfo(departmentID)
);

-- 設置 AUTO_INCREMENT = 1
alter table leaveRecord auto_increment = 1;

-- 建立 workHoursRecord table
CREATE TABLE IF NOT EXISTS workHoursRecord (
    departmentID INT,
    employeeID INT,
    startTime DATETIME,
    endTime DATETIME,
    FOREIGN KEY (departmentID) REFERENCES DepartmentInfo(departmentID),
    FOREIGN KEY (employeeID) REFERENCES employeeInfo(employeeID)
);

-- 建立 attendanceTable table
CREATE TABLE IF NOT EXISTS attendanceTable (
    attendanceID INT,
    employeeID INT,
    checkInTime DATETIME,
    checkOutTime DATETIME,
    FOREIGN KEY (employeeID) REFERENCES employeeInfo(employeeID)
);

-- 建立 IntegerData table
CREATE TABLE IF NOT EXISTS integerData (
	dataName VARCHAR(255) PRIMARY KEY,
	number INT
);

-- 插入 IntegerData 資料
INSERT INTO integerData (dataName, number)
VALUES
('latestCheckInNumber', 1),
('employeeID', 1);


-- 插入 EmployeeInfo 資料
INSERT INTO employeeInfo (employeeID, personName, departmentID, position, positionrank, hireDate, salary, birthday, email, phone)
VALUES
(1, 'John Doe', 1, 'Software Engineer', 'Senior', '2022-01-15', 80000, '1990-05-20', 'john@example.com', '1234567890');
UPDATE employeeInfo
SET account = CONCAT('user_', employeeID)
WHERE employeeID = LAST_INSERT_ID();

INSERT INTO employeeInfo (employeeID, personName, departmentID, position, positionrank, hireDate, salary, birthday, email, phone)
VALUES
(2, 'Jane Smith', 2, 'Marketing Manager', 'Manager', '2022-02-20', 90000, '1985-08-10', 'jane@example.com', '9876543210');
UPDATE employeeInfo
SET account = CONCAT('user_', employeeID)
WHERE employeeID = LAST_INSERT_ID();

INSERT INTO employeeInfo (employeeID, personName, departmentID, position, positionrank, salary, birthday, email, phone)
VALUES
(3, 'Bob Johnson', 3, 'Sales Representative', 'Junior', 70000, '1992-12-05', 'bob@example.com', '5551234567');
UPDATE employeeInfo
SET account = CONCAT('user_', employeeID)
WHERE employeeID = LAST_INSERT_ID();

UPDATE integerData SET number = 1+3 WHERE dataName = 'employeeID';



-- 插入 Education 資料
INSERT INTO education (employeeID, education) VALUES
(1, 'Bachelor in Computer Science'),
(2, 'Master in Business Administration'),
(3, 'Associate in Marketing');

-- 插入 WorkExperience 資料
INSERT INTO workExperience (employeeID, workExperience) VALUES
(1, '5 years as Software Engineer'),
(2, '8 years as Marketing Manager'),
(3, '3 years as Sales Representative');

-- 插入 Skill 資料
INSERT INTO skill (employeeID, skill) VALUES
(1, 'Java, Python, SQL'),
(2, 'Marketing Strategy, SEO, Social Media'),
(3, 'Sales, Customer Relationship, Negotiation');

-- 插入 Interest 資料
INSERT INTO interest (employeeID, interest) VALUES
(1, 'Programming, Reading'),
(2, 'Traveling, Photography'),
(3, 'Sports, Music');


-- 插入八筆部門資訊
INSERT INTO departmentInfo (departmentName, employeeCount, managerID) VALUES 
('行政部門', 20, 1),
('財務部門', 15, 1),
('銷售與市場部門', 30, 1),
('研發與創新部門', 25, 2),
('生產與製造部門', 50, 2),
('客戶服務部門', 40, 2),
('資訊技術部門', 35, 3),
('法務與合規部門', 10, 3);

-- 插入 leaveRecord 資料
INSERT INTO leaveRecord (employeeID, departmentID, leaveStartDate, hours, approval) VALUES
(1, 1, '2023-01-15T08:30:00', 8, false),
(2, 2, '2023-02-20T12:45:00', 4, false),
(3, 3, '2023-03-10T16:20:00', 16, false),
(1, 1, '2023-04-05T06:10:00', 6, false),
(2, 2, '2023-05-12T08:55:00', 8, false),
(3, 3, '2023-06-18T10:40:00', 10, false),
(1, 1, '2023-07-25T12:15:00', 12, false),
(2, 2, '2023-08-30T08:05:00', 8, false);

-- 插入 workHoursRecord 資料
INSERT INTO workHoursRecord (employeeID, departmentID, startTime, endTime)
VALUES
  (1, 1, '2024-01-10T08:00:00', '2024-01-10T17:00:00'),
  (2, 2, '2024-01-11T09:00:00', '2024-01-11T18:00:00'),
  (3, 3, '2024-01-12T10:00:00', '2024-01-12T19:00:00'),
  (1, 1, '2024-01-10T07:30:00', '2024-01-10T16:30:00'),
  (2, 2, '2024-01-11T08:30:00', '2024-01-11T17:30:00'),
  (3, 3, '2024-01-12T09:30:00', '2024-01-12T18:30:00'),
  (1, 1, '2024-01-10T08:30:00', '2024-01-10T17:30:00'),
  (2, 2, '2024-01-11T09:30:00', '2024-01-11T18:30:00'),
  (3, 3, '2024-01-12T10:30:00', '2024-01-12T19:30:00'),
  (1, 1, '2024-01-13T08:00:00', '2024-01-13T17:00:00');
