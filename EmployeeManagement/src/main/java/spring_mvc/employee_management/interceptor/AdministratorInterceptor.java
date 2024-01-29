package spring_mvc.employee_management.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import spring_mvc.employee_management.model.entity.EmployeeInfo;

public class AdministratorInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("employeeInfo") != null) {
				
			EmployeeInfo employeeInfo = (EmployeeInfo) session.getAttribute("employeeInfo");
			
			if(employeeInfo.getPermissions() > 2) {
				return true;
			}
		}

		
		response.sendRedirect(request.getServletContext().getContextPath() + "/mvc/backend/index");
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
