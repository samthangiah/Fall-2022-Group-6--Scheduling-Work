package sru.groupsix.workOrder.Incident;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class IncidentWrapper {
	
private List<incident> incList;
	

	public IncidentWrapper() {
		this.incList = new ArrayList<>();
	}
	
	public IncidentWrapper(List<incident> incList) {
		this.incList = incList;
	}

	public void addIncident(incident incident) {
		this.incList.add(incident);
	}

	public List<incident> getIncList() {
		return incList;
	}

	public void setIncList(List<incident> incList) {
		this.incList = incList;
	}


}
