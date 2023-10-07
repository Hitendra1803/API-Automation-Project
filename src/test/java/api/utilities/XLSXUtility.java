package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSXUtility {
	
	public FileInputStream fileInput;
	public FileOutputStream fileOutput;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	
	public XLSXUtility(String path) {
		this.path = path;
		
	}
	
	public int getRowCount(String sheetName) throws IOException {
		fileInput = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInput);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		fileInput.close();
		return rowCount;

	}
	
	public int getCellCount(String sheetName, int rownum) throws IOException {
		fileInput = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInput);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fileInput.close();
		return cellCount;

	}
	
	public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
		fileInput = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInput);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		
		try {
		data = formatter.formatCellValue(cell);
		}
		catch(Exception e) {
			data = " ";
		}
		workbook.close();
		fileInput.close();
		return data;

	}
	
	public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
		File excelFile = new File(path);
		
		//If there is not file exist. Create a new file
		if(!excelFile.exists()) {
			workbook = new XSSFWorkbook();
			fileOutput = new FileOutputStream(path);
			workbook.write(fileOutput);
		}
		
		fileInput = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInput);
		
		//Create new sheet if there is no sheet
		if(workbook.getSheetIndex(sheetName) == -1)
			workbook.createSheet(sheetName);
		sheet = workbook.getSheet(sheetName);
		
		//Create row if there is no row
		if(sheet.getRow(rownum) == null)
			sheet.createRow(rownum);
		row = sheet.getRow(rownum);
		
		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fileOutput = new FileOutputStream(path);
		workbook.write(fileOutput);
		workbook.close();
		fileOutput.close();
		fileInput.close();
	
	}
	
	public void fillGreenColor (String sheetName, int rownum, int colnum) throws IOException {
		fileInput = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInput);
		sheet = workbook.getSheet(sheetName) ;
		row = sheet.getRow(rownum); 
		cell = row.getCell(colnum);
		
		style = workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex()) ; 
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workbook.write(fileOutput);
		
		workbook. close ();
		fileInput.close(); 
		fileOutput.close();
	}
	
	
	public void fillRedColor (String sheetName, int rownum, int colnum) throws IOException {
		fileInput = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInput);
		sheet = workbook.getSheet(sheetName) ;
		row = sheet.getRow(rownum); 
		cell = row.getCell(colnum);
		
		style = workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex()) ; 
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workbook.write(fileOutput);
		
		workbook. close ();
		fileInput.close(); 
		fileOutput.close();
	}

}
