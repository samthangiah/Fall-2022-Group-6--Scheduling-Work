package sru.groupsix.workOrder.Incident;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import sru.groupsix.workOrder.Incident.incident;
import sru.groupsix.workOrder.Incident.IncidentRepository;
import sru.groupsix.workOrder.Incident.IExcelDataService;


@Service
public class ExcelDataServiceImpl implements IExcelDataService {

	@Value("${app.upload.file:${user.home}}")
	public String EXCEL_FILE_PATH;

	@Autowired
	IncidentRepository repo;

	Workbook workbook;

	public List<incident> getExcelDataAsList() {

		List<String> list = new ArrayList<String>();
		
		DataFormatter dataFormatter = new DataFormatter();

		try {
			workbook = WorkbookFactory.create(new File(EXCEL_FILE_PATH));
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
	
		System.out.println("-------Workbook has '" + workbook.getNumberOfSheets() + "' Sheets-----");

		
		Sheet sheet = workbook.getSheetAt(0);

		
		int noOfColumns = sheet.getRow(0).getLastCellNum();
		System.out.println("-------Sheet has '"+noOfColumns+"' columns------");

		
		for (Row row : sheet) {
			for (Cell cell : row) {
				String cellValue = dataFormatter.formatCellValue(cell);
				list.add(cellValue);
			}
		}

		
		List<incident> invList = createList(list, noOfColumns);

	
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return invList;
	}

	private List<incident> createList(List<String> excelData, int noOfColumns) {

		ArrayList<incident> invList = new ArrayList<incident>();

		int i = noOfColumns;
		do {
			incident inv = new incident();

			inv.setUser(excelData.get(i));
			inv.setPhone(excelData.get(i + 1));
			inv.setCC(excelData.get(i + 2));
			inv.setDepartment(excelData.get(i + 3));
			inv.setBuilding(excelData.get(i + 4));
			inv.setSubject(excelData.get(i + 5));
			inv.setSetdate(excelData.get(i + 6));

			invList.add(inv);
			i = i + (noOfColumns);
		} while (i < excelData.size());
		return invList;
	}

	@Override
	public int saveExcelData(List<incident> incidents) {
		incidents = repo.saveAll(incidents);
		return incidents.size();
	}
}