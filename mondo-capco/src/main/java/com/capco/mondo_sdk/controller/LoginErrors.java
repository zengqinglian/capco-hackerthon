package com.capco.mondo_sdk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginErrors {

	@RequestMapping("/dashboard/login")
	public String dashboard() {
		return "redirect:/#/";
	}

}
