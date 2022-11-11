package sru.groupsix.workOrder.Incident;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IncidentService {

	@Autowired
	private IncidentRepository repo;
	
	public List<incident> listAll() {
		return repo.findAll();
	}
	
	public void save(incident incident) {
		repo.save(incident);
	}
	
	public incident get(long id) {
		return repo.findById(id).get();
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
	
	public void saveAll(List<incident> listincident) {
		repo.saveAll(listincident);
	}
}
