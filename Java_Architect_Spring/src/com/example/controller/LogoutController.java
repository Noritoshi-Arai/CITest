package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {
	@Autowired
	HttpSession session;
	
	@RequestMapping(value = "logout")
	public String logout() {
		session.invalidate();
		return "Logout";
	}

}
