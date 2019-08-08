package demo;

import base.annotation.RequestMapping;

public class LoginController {
	@RequestMapping("/toLogin.do")
	public String toLogin() {
		return "login";
	}
	// redirect
	@RequestMapping("/login.do")
	public String checkLogin() {
		return "redirect:toWelcome.do";
	}
	@RequestMapping("/toWelcome.do")
	public String toWelcome() {
		return "welcome";
	}
		
}
