package spring_mvc.employee_management.test;

import java.util.Base64;
import java.util.Optional;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;

import spring_mvc.employee_management.model.dao.EmployeeManagementDao;
import spring_mvc.employee_management.model.dao.EmployeeManagementDaoMySQL;
import spring_mvc.employee_management.model.entity.ConstantData;
import spring_mvc.employee_management.util.KeyUtil;
import spring_mvc.employee_management.util.WebUtil;


public class Test {

//	@Autowired
//    private static EmployeeManagementDao dao;

   

    public static void main(String[] args) throws Exception {

//        String password = "12345678";
//        // 将 password 进行 AES 加密 -------------------------------------------------------------------
//        Optional<ConstantData> constantDataOpt = dao.findConstantData("AESKey");
//        ConstantData constantData = constantDataOpt.get();
//        
//        final String KEY = constantData.getConstant();
//        SecretKeySpec aesKeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
//        byte[] encryptedPasswordECB = KeyUtil.encryptWithAESKey(aesKeySpec, password);
//        String encryptedPasswordECBBase64 = Base64.getEncoder().encodeToString(encryptedPasswordECB);
//        //-------------------------------------------------------------------------------------------
//        System.out.println(encryptedPasswordECBBase64);
    	
    	String iPString = WebUtil.getClientIp();
    	System.out.println(1);
    	System.out.println(iPString);
    	System.out.println(2);
    }
}