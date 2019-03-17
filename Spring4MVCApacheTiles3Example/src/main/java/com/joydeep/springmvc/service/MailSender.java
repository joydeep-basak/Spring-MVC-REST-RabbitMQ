package com.joydeep.springmvc.service;

import org.springframework.stereotype.Component;

@Component
public class MailSender {

	public static void sendMail() {
		System.out.println("Mail Sent called");
	}
}
