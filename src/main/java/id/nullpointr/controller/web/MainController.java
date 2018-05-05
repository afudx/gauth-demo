package id.nullpointr.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import id.nullpointr.service.GAuthService;

@Controller
public class MainController {
	
	GAuthService gAuthService = null;
	
	@Autowired
	public MainController(GAuthService gAuthService) {
		this.gAuthService = gAuthService;
	}
	
	@RequestMapping("/login")
	public String loginController() {
		return "login";
	}
	
	@RequestMapping("/gauth")
	public ModelAndView gauthPageController(ModelAndView modelAndView) {
		String generatedUrl = gAuthService.generateKey("user@domain.com");
		
		modelAndView.addObject("gauthUrl", generatedUrl);
		modelAndView.setViewName("gauth");
		
		return modelAndView;
	}
	
}
