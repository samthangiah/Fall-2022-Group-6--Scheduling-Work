package edu.sru.groupsix.workSchedule.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@RequestMapping("/Email")
	public String email() {
		return "email.html";
	}
	
	@RequestMapping("/emailissue")
	public String emailissue() {
		return "emailissue.html";
	}
	
	@RequestMapping("/computer")
	public String computer() {
		return "computer.html";
	}
	
	@RequestMapping("/socomputer")
	public String socomputer() {
		return "socomputer.html";
	}
	
	@RequestMapping("/Campuse")
	public String campus() {
		return "Campuse.html";
	}
}
