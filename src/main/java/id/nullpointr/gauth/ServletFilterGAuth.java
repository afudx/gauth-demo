package id.nullpointr.gauth;

import java.io.IOException;
import java.security.Principal;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;

import id.nullpointr.security.UserPrincipal;

@Component
public class ServletFilterGAuth implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(ServletFilterGAuth.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
		
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();     
        SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        
        
        Authentication authentication = null;
        if(securityContext != null) {
        	UserPrincipal userPrincipal = (UserPrincipal) securityContext.getAuthentication().getPrincipal();
        	
        	if(httpServletRequest.getRequestURI().equalsIgnoreCase("/login")) {
        		if(session.getAttribute("GAUTH_LOGIN") != null)
            		httpServletResponse.sendRedirect("/restricted");
            	else {
            		if(userPrincipal.isGAuthEnabled() == 1)
            			httpServletResponse.sendRedirect("/gauth-login");
            		else
            			httpServletResponse.sendRedirect("/gauth-register");
            	}
        	}
        	
        	/*if(httpServletRequest.getRequestURI().equalsIgnoreCase("/restricted")) {
        		if(session.getAttribute("GAUTH_LOGIN") != null)
            		httpServletResponse.sendRedirect("/restricted");
            	else {
            		if(userPrincipal.isGAuthEnabled() == 1)
            			httpServletResponse.sendRedirect("/gauth-login");
            		else
            			httpServletResponse.sendRedirect("/gauth-register");
            	}
        	}*/
        	
        	if(httpServletRequest.getRequestURI().contains("gauth") && session.getAttribute("GAUTH_LOGIN") != null) {
            		httpServletResponse.sendRedirect("/restricted");
        	}
        	
        	if(httpServletRequest.getRequestURI().equalsIgnoreCase("/gauth-register") && userPrincipal.isGAuthEnabled() == 1) {
        		httpServletResponse.sendRedirect("/gauth-login");
        	}
        	
        }
        
        chain.doFilter(request, response);
    }

	@Override
	public void destroy() {

	}

}
