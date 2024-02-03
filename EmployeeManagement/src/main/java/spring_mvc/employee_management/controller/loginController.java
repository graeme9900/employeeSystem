package spring_mvc.employee_management.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
import java.util.Random;

import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import spring_mvc.employee_management.model.dao.EmployeeManagementDao;
import spring_mvc.employee_management.model.entity.ConstantData;
import spring_mvc.employee_management.model.entity.EmployeeInfo;
import spring_mvc.employee_management.util.FaceRecognition;
import spring_mvc.employee_management.util.KeyUtil;

// 登入與註冊控制
@Controller
@RequestMapping("/login")
public class loginController {
	
	
	@Autowired
	private EmployeeManagementDao dao;
	
	private Boolean checkMove = true;
	
	// 網頁開啟
	
	// 登入
	// http://localhost:8080/EmployeeManagement/mvc/login/login
	@GetMapping("/login")
	public String loginLoginInterface() {
		return "/employee_management/login/loginInterface";
	}
	
	// 前台登入處理
	@PostMapping("/login/login")
	public String loginLogin(@RequestParam("username") String username, 
			 				 @RequestParam("password") String password,
			 				 @RequestParam("captcha") String captcha,
			 				 HttpSession session,
			 				 Model model) throws Exception {
//		// 比對驗證碼
//		if(!captcha.equals(session.getAttribute("code")+"")) {
//			session.invalidate(); // session 過期失效
//			model.addAttribute("loginMessage", "驗證碼錯誤");
//			return "/employee_management/login/loginInterface";
//		}
		Optional<EmployeeInfo> employeeOpt = dao.findEmployeeInfoByAccount(username);
		if(employeeOpt.isPresent()) {
			EmployeeInfo employeeInfo = employeeOpt.get();
	        // 将 password 进行 AES 加密 -------------------------------------------------------------------
	        Optional<ConstantData> constantDataOpt = dao.findConstantData("AESKey");
	        ConstantData constantData = constantDataOpt.get();
	        
	        final String KEY = constantData.getConstant();
	        SecretKeySpec aesKeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
	        byte[] encryptedPasswordECB = KeyUtil.encryptWithAESKey(aesKeySpec, password);
	        String encryptedPasswordECBBase64 = Base64.getEncoder().encodeToString(encryptedPasswordECB);
	        //-------------------------------------------------------------------------------------------
			if(employeeInfo.getPassword().equals(encryptedPasswordECBBase64)) {
				session.setAttribute("employeeInfo", employeeInfo); // 將 user 物件放入到 session 變數中
				return "redirect:/mvc/frontend/index"; // OK, 導向前台首頁
			} else {
				session.invalidate(); // session 過期失效
				model.addAttribute("loginMessage", "密碼錯誤");
				return "/employee_management/login/loginInterface";
			}
		} else {
			session.invalidate(); // session 過期失效
			model.addAttribute("loginMessage", "無此使用者");
			return "/employee_management/login/loginInterface";
		}

	}
	
	// 登出
	@GetMapping("logout")
	public String loginLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/mvc/login/login";
	}
	
	// 人臉辨識
	// http://localhost:8080/EmployeeManagement/mvc/login/faceRecognitionMenu
	@GetMapping("faceRecognitionMenu")
	public String loginFaceRecognition() {
		return "/employee_management/login/faceRecognitionMenu";
	}
	
	// html流傳輸圖片

	@PostMapping("/faceRecognitionMenu/return")
    public void uploadImage(@RequestBody String imageData,
    						RedirectAttributes redirectAttributes) {
		
		if(checkMove) {
			try {
	        // 创建 ObjectMapper 对象
	        ObjectMapper objectMapper = new ObjectMapper();
	        
	        // 解析 JSON 字符串
	        JsonNode jsonNode = objectMapper.readTree(imageData);
	        
	        // 提取图像数据
	        String imageDataString = jsonNode.get("imageData").asText();
	        

	        FaceRecognition faceRecognition = new FaceRecognition();
	        
	        // 解碼成 img
	        BufferedImage bufferedImage = faceRecognition.decodeBase64(imageDataString);
	        
	        Boolean checkFace = faceRecognition.determineHumanFace(bufferedImage);
	        // 根据检查结果进行重定向
	        if (checkFace) {

	        	checkMove = false;
	            
	        }
	        
	    } catch (Exception e) {
	        // 处理解析异常
	        e.printStackTrace();
	       
	    }
		
    }
		
}
		
	
	
	// 驗證碼
	@GetMapping("/getcode")
	private void getCodeImage(HttpSession session, HttpServletResponse response) throws IOException {
		// 產生一個驗證碼 code
		Random random = new Random();
		String code1 = String.format("%c", (char)(random.nextInt(90-65+1) + 65));
		String code2 = String.format("%c", (char)(random.nextInt(90-65+1) + 65));
		String code3 = String.format("%c", (char)(random.nextInt(90-65+1) + 65));
		String code4 = String.format("%c", (char)(random.nextInt(90-65+1) + 65));
		
		String code  = code1+code2+code3+code4;
		session.setAttribute("code", code);
		
		// Java 2D 產生圖檔
		// 1. 建立圖像暫存區
		BufferedImage img = new BufferedImage(80, 30, BufferedImage.TYPE_INT_BGR);
		// 2. 建立畫布
		Graphics g = img.getGraphics();
		// 3. 設定顏色
		g.setColor(Color.YELLOW);
		// 4. 塗滿背景
		g.fillRect(0, 0, 80, 30);
		// 5. 設定顏色
		g.setColor(Color.BLACK);
		// 6. 設定自型
		g.setFont(new Font("新細明體", Font.PLAIN, 30));
		// 7. 繪字串
		g.drawString(code, 10, 23); // code, x, y
		// 8. 干擾線
		g.setColor(Color.RED);
		for(int i=0;i<10;i++) {
			int x1 = random.nextInt(80);
			int y1 = random.nextInt(30);
			int x2 = random.nextInt(80);
			int y2 = random.nextInt(30);
			g.drawLine(x1, y1, x2, y2);
		}
		
		// 設定回應類型
		response.setContentType("image/png");
		
		// 將影像串流回寫給 client
		ImageIO.write(img, "PNG", response.getOutputStream());
	}
	
	
	
}
