package demo;

import javax.servlet.http.HttpServletRequest;

import base.annotation.RequestMapping;

public class LoginController {
	@RequestMapping("/toLogin.do")
	public String toLogin() {
		return "login";
	}
	// redirect
	@RequestMapping("/login.do")
	public String checkLogin(HttpServletRequest request) {
		String username=request.getParameter("username");
		String password=request.getParameter("pwd");
		if("Tim".equals(username) && "123".equals(password)) {
			return "redirect:toWelcome.do";
		}else {
			request.setAttribute("login_failed", "invalid username/password");
			return "login";
		}
		
	}
	@RequestMapping("/toWelcome.do")
	public String toWelcome() {
		return "welcome";
	}
		
}
