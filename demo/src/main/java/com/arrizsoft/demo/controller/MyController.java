package com.arrizsoft.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

	@RequestMapping("/home")
	@ResponseBody
	public String index() {
		return "hello world";
	}
	
}
