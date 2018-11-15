package com.yali.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class FileUtil {
	public static ArrayList<ArrayList<String>> importExcelAndXls(String filePath) throws FileNotFoundException{
		ArrayList<ArrayList<String>> uList=new ArrayList<ArrayList<String>>();
		FileInputStream fileInputStream=new FileInputStream(new File(filePath));
		//读取xls文件
		if(filePath.endsWith(".xls")) {
			try {
				HSSFWorkbook hssfWorkbook=new HSSFWorkbook(fileInputStream);
				int sheetsNum=hssfWorkbook.getNumberOfSheets();
				for(int i=0;i<sheetsNum;i++) {
					HSSFSheet sheet=hssfWorkbook.getSheetAt(i);
					int rowsNum=sheet.getLastRowNum();
					for(int j=1;j<=rowsNum;j++) {
						ArrayList<String> list=new ArrayList<String>();
						HSSFRow row=sheet.getRow(j);
						int cellsNum=row.getLastCellNum();
						for(int k=0;k<cellsNum;k++) {
							HSSFCell cell=row.getCell(k);
							String cellValue=getValue(cell);
							String cellReal=Trim_str(cellValue);
							list.add(cellReal);
						}
						uList.add(list);
					}
					fileInputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return uList;
	}
	public static ArrayList<ArrayList<String>> importExcelAndxlsx(String filePath) throws IOException {
		ArrayList<ArrayList<String>> uList=new ArrayList<ArrayList<String>>();
		FileInputStream fileInputStream=new FileInputStream(new File(filePath));
		try {
			XSSFWorkbook xssfWorkbook=new XSSFWorkbook(fileInputStream);
			int sheetsNum=xssfWorkbook.getNumberOfSheets();
			for(int i=0;i<sheetsNum;i++) {
				XSSFSheet sheet=xssfWorkbook.getSheetAt(i);
				int rowsNum=sheet.getLastRowNum();
				for(int j=1;j<=rowsNum;j++) {
					ArrayList<String> list=new ArrayList<String>();
					XSSFRow row=sheet.getRow(j);
					int cellsNum=row.getLastCellNum();
					for(int k=0;k<cellsNum;k++) {
						XSSFCell cell=row.getCell(k);
						String cellValue=getValue(cell);
						String cellReal=Trim_str(cellValue);
						list.add(cellReal);
					}
					uList.add(list);
				}
				fileInputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return uList;
			
	}
	
	/*判断excel文件的数据类*/
	public static String getValue(Cell cell){
		if(cell == null){
			return "";
		}
		if(cell.getCellType()==Cell.CELL_TYPE_BOOLEAN){
			 return String.valueOf(cell.getBooleanCellValue());
		}else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
			double cur=cell.getNumericCellValue();
        	long longVal = Math.round(cur);
        	Object inputValue = null;
        	if(Double.parseDouble(longVal + ".0") == cur){ 
        		inputValue = longVal;
        	}else{  
        	    inputValue = cur;
        	}
            return String.valueOf(inputValue);
		}else if(cell.getCellType() == Cell.CELL_TYPE_BLANK || cell.getCellType() == Cell.CELL_TYPE_ERROR){
        	return "";
        }else {
            return String.valueOf(cell.getStringCellValue());
        }
	}
	
	/*去空*/
	public static String Trim_str(String str){
        if(str==null)
            return null;
        return str.replaceAll("[\\s\\?]", "").replace("　", "");
	}
	
	//导出文件
	public static int exportExcelAndXls(String filePath,ArrayList<ArrayList<String>> listList) {
		return 0;}
}
