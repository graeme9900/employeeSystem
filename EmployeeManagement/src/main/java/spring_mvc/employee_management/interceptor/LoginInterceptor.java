package spring_mvc.employee_management.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import spring_mvc.employee_management.model.entity.EmployeeInfo;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("employeeInfo") != null) {
			EmployeeInfo employeeinfo = (EmployeeInfo) session.getAttribute("employeeInfo");
			
			return true; // 放行
		}
		
		
//		return true;
		// 未登入, 導入到登入頁面
		response.sendRedirect(request.getServletContext().getContextPath() + "/mvc/login/login");
		return false; // 不放行
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		
	}

}
