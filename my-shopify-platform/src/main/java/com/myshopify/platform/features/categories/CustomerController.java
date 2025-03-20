package com.myshopify.platform.features.categories;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {
	
	@RequestMapping("/home")
	public String displyaHome() {
		System.out.println("i am in home controller");
		return "home";
	}

}
