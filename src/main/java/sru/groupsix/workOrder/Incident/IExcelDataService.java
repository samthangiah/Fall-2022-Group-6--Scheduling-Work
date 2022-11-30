package sru.groupsix.workOrder.Incident;

import java.util.List;

import sru.groupsix.workOrder.Incident.incident;

public interface IExcelDataService {

	List<incident> getExcelDataAsList();
	
	int saveExcelData(List<incident> incidents);
}