package spring_mvc.employee_management.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
import javax.servlet.http.HttpServletRequest;
 
@Component
public class WebUtil {
 
    private static HttpServletRequest request;
 
    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
 
    public static String getClientIp() {
 
        String remoteAddr = "";
 
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
 
        return remoteAddr;
    }
 
}