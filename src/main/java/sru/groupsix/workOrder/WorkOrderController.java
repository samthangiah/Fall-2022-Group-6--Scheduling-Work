package sru.groupsix.workOrder;



import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import sru.groupsix.workOrder.Incident.IExcelDataService;
import sru.groupsix.workOrder.Incident.IFileUploaderService;
import sru.groupsix.workOrder.Incident.IncidentRepository;
import sru.groupsix.workOrder.Incident.IncidentService;
import sru.groupsix.workOrder.Incident.IncidentWrapper;
import sru.groupsix.workOrder.Incident.incident;
import sru.groupsix.workOrder.User.User;
import sru.groupsix.workOrder.User.UserDetailService;
import sru.groupsix.workOrder.User.UserRepository;
import sru.groupsix.workOrder.User.Userdetail;
@Controller
public class WorkOrderController {

	@Autowired
	private IncidentService service; 
	@Autowired
	private UserRepository repo;
	@Autowired
	private IncidentWrapper form;

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
		@RequestMapping("/adminlogin")
		public String adminLogin() {
			return "adminlogin.html";
		}
		@RequestMapping("/techAssist")
		public String techAssist() {
			return "techAssist.html";
		}
		@RequestMapping("/index")
		public String index() {
			return "index.html";
		}
		@RequestMapping("/UserAccounts")
		public String useraccounts() {
			return "UserAccounts.html";
		}
		@RequestMapping("/EditUser")
		public String EditUser() {
			return "EditUser.html";
		}
		@RequestMapping("/ListUser")
		public String ListIncidentPage(Model model) {
			List<incident> listincidents = service.listAll();
			model.addAttribute("listincidents", listincidents);
			
			return "ListUser";
		}

		 @GetMapping("Userincident")
		 public String showAll1(Model model) {
		     model.addAttribute("incList", service.listAll());
		     List<User> listUsers = repo.findAll();
				model.addAttribute("listUsers", listUsers);
				List<User> techUsers = repo.findAll();
				techUsers.clear();
				for (int i = 0; i < listUsers.size(); i++) {
					if (listUsers.get(i).getRole().equals("TECHASSIST")) {
						techUsers.add(listUsers.get(i));
						}
				}
				model.addAttribute("techUsers", techUsers);	
		     return "Userincident";
		 }
		
		@RequestMapping("/new")
		public String showNewIncidentPage(@AuthenticationPrincipal Userdetail details, Model model) {
			SimpleDateFormat today = new SimpleDateFormat("MM/dd/yyyy");
			Date date = new Date();
			incident incident = new incident();
			incident.setSetdate(today.format(date));
			incident.setUser(details.getUsername());
			model.addAttribute("incident", incident);
			
			return "newIncident";
		}
		
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public String saveIncident(@ModelAttribute("incident") incident incident) {
			service.save(incident);
			
			return "index";
		}
		
		@RequestMapping("/edit/{id}")
		public ModelAndView showEditIncidentPage(@PathVariable(name = "id") int id) {
			ModelAndView mav = new ModelAndView("EditUser");
			incident incident = service.get(id);
			mav.addObject("incident", incident);
			
			return mav;
		}
		
		@RequestMapping("/delete/{id}")
		public String deleteIncident(@PathVariable(name = "id") int id) {
			service.delete(id);
			return "admin";		
		}
		@GetMapping("")
		public String viewHomePage() {
			return "loginpage";
		}
		@GetMapping("/register")
		public String showSignupForm(Model model) {
			model.addAttribute("user",new User());
			
			return "createlogin";
		}
		@PostMapping("/process_register")
		public String processRegistration(User user) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		    String encodedPassword = passwordEncoder.encode(user.getPassword());
		    user.setPassword(encodedPassword);
		     
			repo.save(user);
			return "registersuccess";
			
		}
		@GetMapping("/adminRegister")
		public String showAdminRegister(Model model) {
			model.addAttribute("user",new User());
			
			return "adminRegister";
		}
		@PostMapping("/admin_register")
		public String adminRegistration(User user) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		    String encodedPassword = passwordEncoder.encode(user.getPassword());
		    user.setPassword(encodedPassword);
		     
			repo.save(user);
			return "admin";
			
		}
		@GetMapping("/list_users")
		public String viewuserlist(Model model) {
			List<User> listUsers = repo.findAll();
			model.addAttribute("listUsers", listUsers);
			return "users";
		}
		
		 @GetMapping("/login")
		    public String login(){
		        return "loginpage";
		    }
		 @GetMapping("/export")
		 public void exportToCSV(HttpServletResponse response) throws IOException {
			 response.setContentType("text/csv");
			 String fileName = "incident.csv";
			 
			 String headerKey = "Content-Disposition";
			 String headervalue = "attachment; filename=" + fileName;
			 
			 response.setHeader(headerKey, headervalue);
			 
			 List<incident> incidentlist =  service.listAll();
			 
			 ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
			 
			 String[] csvHeader = {"id","User","Phone","CC","Department","Building","Subject"};
			 String[] nameMapping = {"id","User","Phone","CC","Department","Building","Subject"};
			 
			 csvWriter.writeHeader(csvHeader);
			 for (incident incident: incidentlist) {
				 csvWriter.write(incident, nameMapping);
			 }
			 csvWriter.close();
		 }
		 
		 @RequestMapping("/techListPage/{id}")
		 public ModelAndView techListPage(@PathVariable(name = "id") Long id, Model model) {
				ModelAndView mav = new ModelAndView("techList");
				User user = repo.getById(id);
				mav.addObject("user", user);
				List<incident> listincidents = service.listAll();
				model.addAttribute("listincidents", listincidents);
				List<incident> techIncidents = service.listAll();
				techIncidents.clear();
				for (int i = 0; i < listincidents.size(); i++) {
					if (listincidents.get(i).getUser_id() == user.getId()) {
						techIncidents.add(listincidents.get(i));
					}
				}
				model.addAttribute("techIncidents", techIncidents);				
				return mav;
			}
			
		 @Autowired
			IFileUploaderService fileService;
			
			@Autowired
			IExcelDataService excelservice;
			
			@Autowired
			IncidentRepository repo1;
			
		
		  

		    @PostMapping("/uploadFile")
		    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		        fileService.uploadFile(file);

		        redirectAttributes.addFlashAttribute("message",
		            "You have successfully uploaded '"+ file.getOriginalFilename()+"' !");
		        try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        return "redirect:/";
		    }
		    
		    @GetMapping("/saveData")
		    public String saveExcelData(Model model) {
		    	
		    	List<incident> excelDataAsList = excelservice.getExcelDataAsList();
		    	int noOfRecords = excelservice.saveExcelData(excelDataAsList);
		    	model.addAttribute("noOfRecords",noOfRecords);
		    	return "success";
		    }
		    
		    @GetMapping("listOfIncidents")
			 public String showAll(Model model) {
			     model.addAttribute("incList", service.listAll());
			     List<User> listUsers = repo.findAll();
					model.addAttribute("listUsers", listUsers);
					List<User> techUsers = repo.findAll();
					techUsers.clear();
					for (int i = 0; i < listUsers.size(); i++) {
						if (listUsers.get(i).getRole().equals("TECHASSIST")) {
							techUsers.add(listUsers.get(i));
							}
					}
					model.addAttribute("techUsers", techUsers);	
			     return "listOfInc";
			 }
			
			@GetMapping(value = "/editList")
		    public String showEditForm(Model model) {
		        List<incident> incList = new ArrayList<>();
		        service.listAll().iterator().forEachRemaining(incList::add);
		        
		        List<User> listUsers = repo.findAll();
				model.addAttribute("listUsers", listUsers);
				List<User> techUsers = repo.findAll();
				techUsers.clear();
				for (int i = 0; i < listUsers.size(); i++) {
					if (listUsers.get(i).getRole().equals("TECHASSIST")) {
						techUsers.add(listUsers.get(i));
						}
				}
				model.addAttribute("techUsers", techUsers);	

		        model.addAttribute("form", new IncidentWrapper(incList));

		        return "editList";
		    }
			
			@PostMapping(value = "/saveList")
		    public String saveBooks(@ModelAttribute IncidentWrapper form, Model model) {
				
				service.saveAll(form.getIncList());
		        model.addAttribute("incList", service.listAll());

		        return "redirect:listOfIncidents";
		        
		    }
			@GetMapping(value = "/TecheditList/{id}")
		    public ModelAndView showEditForm1(@PathVariable(name = "id") Long id, Model model) {
				ModelAndView mav = new ModelAndView("TecheditList");
				User user = repo.getById(id);
		        List<incident> tempList = new ArrayList<>();
		        service.listAll().iterator().forEachRemaining(tempList::add);
		        List<incident> incList = new ArrayList<>();
		        for (int i = 0; i < tempList.size(); i++) {
		        	if (tempList.get(i).getUser_id() == user.getId()) {
		        		incList.add(tempList.get(i));
		        	}
		        }
		        List<User> listUsers = repo.findAll();
				model.addAttribute("listUsers", listUsers);
				List<User> techUsers = repo.findAll();
				techUsers.clear();
				for (int i = 0; i < listUsers.size(); i++) {
					if (listUsers.get(i).getRole().equals("TECHASSIST")) {
						techUsers.add(listUsers.get(i));
						}
				}
				model.addAttribute("techUsers", techUsers);	
				model.addAttribute(user);
		        model.addAttribute("form", new IncidentWrapper(incList));

		        return mav;
		    }
			
			@PostMapping(value = "/TechsaveList")
			public String saveBooks1(@ModelAttribute IncidentWrapper form, Model model) {
				
				service.saveAll(form.getIncList());
		        model.addAttribute("incList", service.listAll());

		        return "techAssist";
		    }
			@RequestMapping("/userSubmitList/{id}")
			 public ModelAndView userSumbitListPage(@PathVariable(name = "id") Long id, Model model) {
					ModelAndView mav = new ModelAndView("userSubmitList");
					User user = repo.getById(id);
					mav.addObject("user", user);
					List<incident> listincidents = service.listAll();
					model.addAttribute("listincidents", listincidents);
					List<incident> userIncidents = service.listAll();
					userIncidents.clear();
					for (int i = 0; i < listincidents.size(); i++) {
						if (listincidents.get(i).getUser().equals(user.getEmail())) {
							userIncidents.add(listincidents.get(i));
						}
					}
					model.addAttribute("userIncidents", userIncidents);	
					
					return mav;
				}
	}
