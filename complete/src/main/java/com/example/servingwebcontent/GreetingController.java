package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GreetingController {

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login/login.html";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "signup/signup.html";
	}
	
	@GetMapping("/landing")
	public String landing() {
		return "landing/landing.html";
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "profile/profile.html";
	}
	
	@GetMapping("/change-password")
	public String changePassword() {
		return "profile/change-password.html";
	}
	
//	@RequestMapping(value="/greeting", method=RequestMethod.GET)
//	public ModelAndView login() {
//	    ModelAndView mv = new ModelAndView("greeting");
//	    return mv;
//	}

}
