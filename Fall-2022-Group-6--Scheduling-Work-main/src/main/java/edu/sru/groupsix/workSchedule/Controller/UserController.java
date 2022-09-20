package edu.sru.groupsix.workSchedule.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@RequestMapping("/email")
	public String email() {
		return "email.html";
	}
	
	@RequestMapping("/email_issue")
	public String emailissue() {
		return "email_issue.html";
	}
	
	@RequestMapping("/computer")
	public String computer() {
		return "computer.html";
	}
	
	@RequestMapping("/software_computer")
	public String software_computer() {
		return "software_computer.html";
	}
	
	@RequestMapping("/campus")
	public String campus() {
		return "campus.html";
		
	}
	@RequestMapping("/Display_name_change")
	public String displaync() {
		return "Display_name_change.html";
	}
	
	@RequestMapping("/request_name_change")
	public String requestnc() {
		return "request_name_change.html";
	}
}
