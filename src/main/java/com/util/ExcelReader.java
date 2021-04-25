package com.util;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.opencsv.CSVReader;


public class ExcelReader extends BaseFunctions{

	public  String path;
	public  FileInputStream fis = null;
	private XSSFWorkbook workbook = null;

	public ExcelReader(String path) {
		this.path=path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
		} catch (Throwable e) {
			logFailed(e.getMessage().toString());
			e.printStackTrace();
		} 
	}
	
	public List<String[]> getDataFromCsv(String csvNameWithPath) {
		try {
			CSVReader csvReader = new CSVReader(new FileReader(csvNameWithPath));
			List<String[]> csvData = csvReader.readAll();
			return csvData;
		}catch(Throwable t){
			logFailed(t.toString());
		}
		return null;
	}
}
