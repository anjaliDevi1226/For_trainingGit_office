package com.excel.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriteUtils {

	private static Workbook wbook;
	private static Sheet sheet;
	private static int row_num=0;
	
	//open excel file, workbook, sheet
	public static void init() {
		try {
	
		//create workbook
		wbook = new XSSFWorkbook();
		
		//create sheet
		sheet = wbook.createSheet();
		System.out.println("init.....1");
		
		}catch(Exception e) {
			System.out.println("Exception...."+e.getMessage());
				e.printStackTrace();
		}
	}
	
	public static void writeTCResult(String testcaseid,String tcresult,String details) {
		Row row = sheet.createRow(row_num++);
		Cell tcid_cell = row.createCell(0,CellType.STRING);
		tcid_cell.setCellValue(testcaseid);
		
		Cell tcresult_cell = row.createCell(1,CellType.STRING);
		tcresult_cell.setCellValue(tcresult);
		
		Cell details_cell = row.createCell(2,CellType.STRING);
		details_cell.setCellValue(details);
	}
	
	public static void generateExcel() {
		try {
		FileOutputStream fos = new FileOutputStream("./testreport.xlsx");
		wbook.write(fos);
		wbook.close();
		fos.close();
		
		}catch(Exception et) {
			et.printStackTrace();
		}
	}
}
