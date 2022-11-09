package sru.groupsix.workOrder.csvimport;
 
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
 

public class Excel2DatabaseTest {
 
    private static final String String = null;

	public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/workorderdb";
        String username = "root";
        //INSERT PERSONAL SQL WORKBENCH PASSWORD
        String password = "Steelers22";
 
        String excelFilePath = "incidentlist.xlsx";
 
        int batchSize = 20;
 
        Connection connection = null;
 
        try {
            long start = System.currentTimeMillis();
             
            FileInputStream inputStream = new FileInputStream(excelFilePath);
 
            Workbook workbook = new XSSFWorkbook(inputStream);
 
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = firstSheet.iterator();
       

            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);
  
            String sql = "INSERT INTO incident (User, Phone, CC, Department ,Building ,Subject) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);    
             
            int count = 0;
             
            rowIterator.next(); // skip the header row
             
            while (rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
 
                while (cellIterator.hasNext()) {
                	 
                    Cell nextCell = cellIterator.next();
                    
 
                    int columnIndex = nextCell.getColumnIndex();
 
                    switch (columnIndex) {
                    case 0:
                    	String User = nextCell.getStringCellValue();
                        statement.setString(1, User);
                        break;
                    case 1:
                    	String Phone = nextCell.getStringCellValue();
                        statement.setString(2,Phone);
                        break;
                    case 2:
                    	String CC = nextCell.getStringCellValue();
                        statement.setString(3, CC);
                        break;
                    case 3:
                    	String Department = nextCell.getStringCellValue();
                        statement.setString(4, Department);
                        break;
                    case 4:
                    	String Building = nextCell.getStringCellValue();
                        statement.setString(5, Building);
                        break;
                    case 5:
                    	String Subject = nextCell.getStringCellValue();
                        statement.setString(6,Subject);
                        default:
                        break;
                                 
                    }
 
                }
                 
                statement.addBatch();
                 
                if (count % batchSize == 0) {
                    statement.executeBatch();
                }              
 
            }
 
            workbook.close();
             
            // execute the remaining queries
            statement.executeBatch();
  
            connection.commit();
            connection.close();
             
            long end = System.currentTimeMillis();
            System.out.printf("Import done in %d ms\n", (end - start));
             
        } catch (IOException ex1) {
            System.out.println("Error reading file");
            ex1.printStackTrace();
        } catch (SQLException ex2) {
            System.out.println("Database error");
            ex2.printStackTrace();
        }
 
    }
}
