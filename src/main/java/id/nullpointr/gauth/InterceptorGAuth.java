package id.nullpointr.gauth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import id.nullpointr.security.UserPrincipal;

public class InterceptorGAuth extends HandlerInterceptorAdapter{
	private static final Logger logger = LoggerFactory.getLogger(InterceptorGAuth.class);
	
	@Override
    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler){
		HttpSession session = request.getSession();  
		SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		RequestDispatcher requestDispatcher = null;
		
		try {
			if(securityContext != null) {
				UserPrincipal userPrincipal = (UserPrincipal) securityContext.getAuthentication().getPrincipal();
				
				if(request.getRequestURI().equalsIgnoreCase("/login")) {
					if(session.getAttribute("GAUTH_LOGIN") != null) {
						requestDispatcher = request.getRequestDispatcher("/restricted");
						requestDispatcher.forward(request, response);
					} else {
						if(userPrincipal.isGAuthEnabled() == 1) {
							requestDispatcher = request.getRequestDispatcher("/gauth-login");
							requestDispatcher.forward(request, response);
						} else {
							requestDispatcher = request.getRequestDispatcher("/gauth-register");
							requestDispatcher.forward(request, response);
						}
					}
				}
				
				if(request.getRequestURI().contains("gauth") && session.getAttribute("GAUTH_LOGIN") != null) {
					response.sendRedirect("/restricted");
				}
				
				if(request.getRequestURI().equalsIgnoreCase("/gauth-register") && userPrincipal.isGAuthEnabled() == 1)
					response.sendRedirect("/gauth-login");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
        return true;
    }
}
