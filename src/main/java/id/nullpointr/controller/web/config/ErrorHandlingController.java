package id.nullpointr.controller.web.config;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorHandlingController implements ErrorController{
	private static final String ERROR_PATH = "/error";
	
	@RequestMapping(value = ERROR_PATH)
    public String error(Model model, Principal principal, HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
		String errorMessage = exception == null ? "N/A" : exception.getMessage();
		
		model.addAttribute("principal", principal.getName());
		model.addAttribute("statusCode", statusCode);
		model.addAttribute("errorMessage", errorMessage);
		
		
        return "error";
    }

	
	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

}
