package demo;

import base.annotation.RequestMapping;

public class HelloController {
	@RequestMapping("/hello.do")
	public String Hello() {
		// return name of view, hello.jsp
		return "hello";
	}
}
