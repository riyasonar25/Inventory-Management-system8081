package com.inventory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
	 @GetMapping("/login")
	    public String showLogin() {
	        return "login";
	    }

	    @PostMapping("/login")
	    public String login(@RequestParam String username, @RequestParam String password, Model model) {
	        if (username.equals("jethalal") && password.equals("admin")) {
	            return "redirect:/dashboard";
	        }
	        model.addAttribute("error", "Invalid credentials");
	        return "login";
	    }

//	    @GetMapping("/dashboard")
//	    public String dashboard() {
//	        return "dashboard";
//	    }
}
