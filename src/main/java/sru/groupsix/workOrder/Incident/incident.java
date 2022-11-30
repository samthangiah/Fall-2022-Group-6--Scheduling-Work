package sru.groupsix.workOrder.Incident;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class incident {
	private Long id;
	private String User;
	private String Phone;
	private String CC;
	private String Department;
	private String Building;
	private String Subject;
	private String setdate;
	private Long user_id;
	private String tech_notes;

	public incident() {
	}

	protected incident(Long id, String User, String Phone, String CC, String Department, String Building, String Subject, Long user_id,String setdate,String tech_notes) {
		super();
		this.id = id;
		this.User = User;
		this.Phone = Phone;
		this.CC = CC;
		this.Department = Department;
		this.Building = Building;
		this.Subject = Subject;
		this.user_id = user_id;
		this.setdate = setdate;
		this.tech_notes = tech_notes;
	}

	public String getSetdate() {
		return setdate;
	}

	public void setSetdate(String setdate) {
		this.setdate = setdate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		this.User = user;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		this.Phone = phone;
	}

	public String getCC() {
		return CC;
	}

	public void setCC(String cc) {
		this.CC = cc;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		this.Department = department;
	}
	public String getBuilding() {
		return Building;
	}

	public void setBuilding(String building) {
		this.Building = building;
	}
	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		this.Subject = subject;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getTech_notes() {
		return tech_notes;
	}

	public void setTech_notes(String tech_notes) {
		this.tech_notes = tech_notes;
	}
}
