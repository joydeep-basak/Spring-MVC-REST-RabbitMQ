package com.joydeep.springmvc.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.joydeep.springmvc.model.UserDetails;
import com.joydeep.springmvc.rabbit.MessageProducer;
import com.joydeep.springmvc.service.MailSender;
import com.joydeep.springmvc.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
//	@Autowired
//	private static MailSender mailSender;
	
	@Autowired
    private MessageProducer messageProducer;

	@GetMapping("/")
	public String userForm(Locale locale, Model model) {
		model.addAttribute("login", new UserDetails());
		return "login";
	}
	
	@ModelAttribute("login")
    public UserDetails formBackingObject() {
        return new UserDetails();
    }
	
	@PostMapping("/login")
	public String login(@ModelAttribute("user") UserDetails user, BindingResult result, Model model) {

		System.out.println("User : " + user.getName());
		System.out.println("User : " + user.getPassword());
		
		//mailSender.sendMail();
		messageProducer.sendMessage("Hi Jodeep, This is a test message sent to message queue");
		
		return "home";
	}

	@PostMapping("/addUser")
	public String saveUser(@ModelAttribute("user") @Valid UserDetails user, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("users", userService.list());
			return "editUsers";
		}

		userService.save(user);
		return "redirect:/";
	}
}
