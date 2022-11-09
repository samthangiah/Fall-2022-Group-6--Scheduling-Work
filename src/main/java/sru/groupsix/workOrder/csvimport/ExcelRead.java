package sru.groupsix.workOrder.csvimport;


import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {
	public static void main(String[] args) throws Exception{
	    try {
	    
	        FileInputStream file = new FileInputStream("incidentlist.xlsx");
	        XSSFWorkbook workbook = new XSSFWorkbook(file);
	
	        int rowNo = 0;
	        int cellIndex = 0;
	        
	        XSSFSheet sheet = workbook.getSheetAt(0);
	        										 
	        int rows = sheet.getPhysicalNumberOfRows(); 
	        for(rowNo = 0; rowNo < rows; rowNo++){
	            XSSFRow row = sheet.getRow(rowNo);
	            if(row != null){
	                int cells = row.getPhysicalNumberOfCells(); 
	                for(cellIndex = 0; cellIndex <= cells; cellIndex++){  
	                    XSSFCell cell = row.getCell(cellIndex); 
	                    String value = "";	                    
	                    if(cell == null){ 
	                        continue;
	                    }else{
	                        
	                        switch (cell.getCellType()){
	                        case FORMULA:
	                            value = cell.getCellFormula();
	                            break;
	                        case NUMERIC:
	                            value = cell.getNumericCellValue() + "";
	                            break;
	                        case STRING:
	                            value = cell.getStringCellValue() + "";
	                            break;
	                        case BLANK:
	                            value = cell.getBooleanCellValue() + "";
	                            break;
	                        case ERROR:
	                            value = cell.getErrorCellValue() + "";
	                            break;
	                        }
	                    }
	                    System.out.println( rowNo + "row : " + cellIndex + "column: " + value);
	                }
	            }
	        }
	    }catch(Exception e) {
    		e.printStackTrace();
    	}
	}
}
