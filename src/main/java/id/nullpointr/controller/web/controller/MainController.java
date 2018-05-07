package id.nullpointr.controller.web.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import id.nullpointr.gauth.GAuthService;
import id.nullpointr.model.GAuthFormData;

@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	private GAuthService gAuthService = null;
	
	@Autowired
	public MainController(GAuthService gAuthService) {
		this.gAuthService = gAuthService;
	}
	
	@RequestMapping("/login")
	public String loginController() {
		return "login";
	}
	
	@RequestMapping("/gauth-register")
	public ModelAndView gauthPageController(ModelAndView modelAndView, Principal principal) {
		logger.info("Service /gauth-register has been called ...");
		String generatedUrl = gAuthService.generateKey(principal.getName());
		
		modelAndView.addObject("gauthUrl", generatedUrl);
		modelAndView.addObject("gAuthFormData", new GAuthFormData());
		
		modelAndView.setViewName("gauth-register");
		
		return modelAndView;
	}
	
	@RequestMapping("/gauth-login")
	public String gauthLoginPageController() {
		
		return "gauth-login";
	}
	
	@RequestMapping("/restricted")
	public String gauthRestrictedPageController(Model model, Principal principal) {
		model.addAttribute("principal", principal.getName());
		return "restricted";
	}
	
	@RequestMapping(path="/authorize", method=RequestMethod.POST, produces="application/json")
	public String authorizeController(Model model,  @ModelAttribute GAuthFormData gAuthFormData, 
			Principal principal, HttpServletRequest request) {
		HttpSession session = request.getSession();
		logger.info("Authorizing "+principal.getName());
		boolean isAuthenticated = gAuthService.authorizeUser(principal.getName(), gAuthFormData.getClientKey());
		model.addAttribute("principal", principal.getName());
		if(isAuthenticated) {
			session.setAttribute("GAUTH_LOGIN", true);
			return "/restricted";
		}else
			return "/login";
			
	}
	
	
	/*@RequestMapping(path="/authorize", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<?, ?> authorizeController(@ModelAttribute GAuthFormData gAuthFormData, Principal principal) {
		Map<String, Object> result = new HashMap<>();
		
		logger.info("Authorizing "+principal.getName());
		boolean isAuthenticated = gAuthService.authorizeUser(principal.getName(), gAuthFormData.getClientKey());
		result.put("status", isAuthenticated);
		
		return result;
	}
	*/
}
