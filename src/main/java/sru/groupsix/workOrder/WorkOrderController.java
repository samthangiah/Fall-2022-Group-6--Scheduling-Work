package sru.groupsix.workOrder;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WorkOrderController {

	@Autowired
	private IncidentService service; 
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<incident> listincidents = service.listAll();
		model.addAttribute("listincidents", listincidents);
		
		return "admin";
	}
	
	@RequestMapping("/new")
	public String showNewIncidentPage(Model model) {
		incident incident = new incident();
		model.addAttribute("incident", incident);
		
		return "newIncident";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveIncident(@ModelAttribute("incident") incident incident) {
		service.save(incident);
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditIncidentPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_incident");
		incident incident = service.get(id);
		mav.addObject("incident", incident);
		
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteIncident(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";		
	}
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
		@RequestMapping("/admin")
		public String admin() {
			return "admin.html";
		}
		@RequestMapping("/index")
		public String index() {
			return "index.html";
		}
}
