package com.vtiger.genericFileUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {
	public String getDataFromExcelUtilityTest(String sheet,int row,int col) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("D:\\Nikita\\TekP\\practice_testcases_vtiger\\src\\test\\resources\\PracticeTestcasesVtigerTestScriptsData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		String value = wb.getSheet(sheet).getRow(row).getCell(col).getStringCellValue();
		return value;
	}
	public void setDatatoExcelFileTest(String sheet,int row,int col,String data) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("D:\\Nikita\\TekP\\practice_testcases_vtiger\\src\\test\\resources\\PracticeTestcasesVtigerTestScriptsData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Cell cell = wb.getSheet(sheet).getRow(row).getCell(col);
		FileOutputStream fos=new FileOutputStream("D:\\Nikita\\TekP\\practice_testcases_vtiger\\src\\test\\resources\\PracticeTestcasesVtigerTestScriptsData.xlsx");
		wb.write(fos);
		wb.close();
	}
	public int getRowCount(String sheet) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("D:\\Nikita\\TekP\\practice_testcases_vtiger\\src\\test\\resources\\PracticeTestcasesVtigerTestScriptsData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		int rowCount = wb.getSheet(sheet).getLastRowNum();
		return rowCount;
	}
}
