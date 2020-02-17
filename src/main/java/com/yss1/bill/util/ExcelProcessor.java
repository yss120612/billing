package com.yss1.bill.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.yss1.bill.dao.ServicesDao;
import com.yss1.bill.dao.TelDictDao;

@Service
public class ExcelProcessor{
 
	
	@Autowired
	private ServicesDao service;
	
	@Autowired
	private TelDictDao tdict;
	
	public ExcelProcessor() {
		
	}
	
	public String ProcessInfo(String name, BufferedInputStream bis) throws IOException {
		if (name.endsWith(".xlsx")) {
			return ProcessXSSF(bis);
		}
		else if (name.endsWith(".xls")) {
			return ProcessHSSF(bis);
		}
		return "Error extension!";
	}
	
	public String FillTel(String name, BufferedInputStream bis) throws IOException {
		if (name.endsWith(".xlsx")) {
			return FillTelXSSF(bis);
		}
		else if (name.endsWith(".xls")) {
			return FillTelHSSF(bis);
		}
		return "Error extension!";
	}
	
	private String FillTelXSSF(BufferedInputStream bis) throws IOException {
		return "";
	}
	
	
	private String FillTelHSSF(BufferedInputStream bis) throws IOException {
		HSSFWorkbook book = new HSSFWorkbook (bis);
		HSSFSheet sheet = book.getSheetAt(0);
		HSSFRow row;
		long tel;
		int counter=0;
		tdict.ClearAll();
		Iterator<Row> ri = sheet.rowIterator();
		while (ri.hasNext()) {
		row=(HSSFRow)ri.next();
		if (row.getCell(0).getCellType()==CellType.NUMERIC)
		{
		 tel=isTel(Long.toString((long) row.getCell(0).getNumericCellValue()));
		}
		else {
		 tel=isTel(row.getCell(0).getStringCellValue());
		}
		if (tel>0) {
			counter++;
			tdict.AddTel(tel);
		}
		}
		
		return "Добавлено "+counter+" позиций";
	}
	
	
	private String ProcessXSSF(BufferedInputStream bis) throws IOException {
		XSSFWorkbook book = new XSSFWorkbook (bis);
		XSSFSheet sheet = book.getSheetAt(0);
		XSSFRow row;
		Iterator<Row> ri = sheet.rowIterator();
		while (ri.hasNext()) {
			
			row=(XSSFRow)ri.next();
			 Iterator<Cell> ci = row.cellIterator();
			 while(ci.hasNext()) {
			        XSSFCell cell = (XSSFCell) ci.next();
			        System.out.println(cell.getRawValue());
			    }
			 
		}
		System.out.flush();
		return "";
	}
	
	
	private int services_added;
	private HashMap<Integer,String> hm;
	
	private String ProcessHSSF(BufferedInputStream bis) throws IOException {
        HSSFWorkbook book = new HSSFWorkbook (bis);
		HSSFSheet sheet = book.getSheetAt(0);
		HSSFRow row;
		int phaseNo=0;
		String res="";
		Iterator<Row> ri = sheet.rowIterator();
		services_added=0;
		if (hm==null) {
			hm=new HashMap<Integer,String>();
		}
		else {
			hm.clear();
		}
		while (ri.hasNext()) {
			row=(HSSFRow)ri.next();
			//if (row.getCell(0).getStringCellValue())
			//ProcessPhase0(row.getCell(0).getStringCellValue(),row.getLastCellNum());
			 phaseNo=checkPhase(row, phaseNo);
			 switch (phaseNo)
			 {
			 case 0:
				 break;
			 case 1:
				 ProcessPhase1(row);
				 break;
			 case 2:
				 ProcessPhase2(row);
				 break;
			 case 3:
				 break;
			 case 4:
				 break;
			 }
			 //Iterator<Cell> ci = row.cellIterator();
			 //while(ci.hasNext()) {
			 //       HSSFCell cell = (HSSFCell) ci.next();
			        
			        	//if (cell.getStringCellValue().startsWith("Разговоры по телефону")
			        	  //||cell.getStringCellValue().startsWith("Соединение через"))
			        	//if (isTel(cell.getStringCellValue())>-1)	
			        	//System.out.println(cell.getStringCellValue());
			  //      }
			        
			    }
		//System.out.println(hm);
		System.out.flush();
		book.close();
		res="Services add = "+services_added;
		return res;
	}
	
	
	
	
	private void ProcessPhase1(HSSFRow row) {
		String buff,tgt="";
		int i,id;
		
		if (row.getCell(0)!=null && row.getCell(0).getCellType()==CellType.STRING) {
		
		if (row.getCell(4,Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)!=null) {
			buff=row.getCell(4).getStringCellValue().trim();
			
			i=buff.indexOf(":");
			if (i>0) {
				tgt=buff.substring(0, i);
				
				if (tgt.matches("\\d+")) {
					id=Integer.parseInt(tgt);
					tgt=buff.substring(i+1).trim();
					//hm.put(id, tgt);
					if (service.addService(id, tgt, (short)1)!=null) services_added++;
				}
			}
		}
		}
	}
		
	
	private void ProcessPhase2(HSSFRow row) {
		String buff,tgt="";
		int i,id;
		
		if (row.getCell(0)!=null && row.getCell(0).getCellType()==CellType.STRING) {
		
		if (row.getCell(4,Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)!=null) {
			buff=row.getCell(4).getStringCellValue().trim();
			
			i=buff.indexOf(":");
			if (i>0) {
				tgt=buff.substring(0, i);
				
				if (tgt.matches("\\d+")) {
					//System.out.println(tgt);
					id=Integer.parseInt(tgt);
					tgt=buff.substring(i+1).trim();
					Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
				    Matcher matcher = pattern.matcher(tgt);
				    if (matcher.find()) {
				    	tgt=(tgt.substring(0,matcher.start()-1)).trim();
				    }
					if(service.addService(id, tgt, (short)0)!=null) services_added++;
				}
			}
		}
		}
	}
	
	
	
	private int checkPhase(HSSFRow row, int currPhase) {
		if (row.getFirstCellNum()==0 && row.getCell(0).getCellType()==CellType.STRING) {
			if (currPhase==0 && row.getCell(0).getStringCellValue().startsWith("Постоянные услуги")) {
				currPhase=1;
			}else if (currPhase==1 && row.getCell(0).getStringCellValue().startsWith("Разовые услуги")) {
				currPhase=2;
			}else if (currPhase==2 && row.getCell(0).getStringCellValue().startsWith("Детализация МТР")) {
				currPhase=3;//расшифровка повременки
			}else if (currPhase==3 && row.getCell(0).getStringCellValue().startsWith("Детализация МТР")) {
				currPhase=4;//межгород
			}
		}
		return currPhase;
	}
	
	private long isTel(String s) {
		
		if (s.trim().matches("\\d{10}"))
		{
			return Long.parseLong(s.trim());
		}
		else {
			return -1;
		}
		
	}
	
	
}
