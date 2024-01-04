package common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;



import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class XlsReader 
{
	public static String filename = System.getProperty("user.dir");
	public  String path;
	public  FileInputStream fis = null;
	public  FileOutputStream fileOut =null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row   =null;
	public XSSFCell cell = null;
	public static String sActionKeyword=null;

	/**
	 * 
	 * @param path
	 */
	public XlsReader(String path) 
	{
		
		this.path=path;
		try 
		{
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	// returns the row count in a sheet
	/**
	 * To get the Row count and returns the same integer.
	 * @param sheetName
	 * @return rowcount no.
	 */
	public int getRowCount(String sheetName)
	{
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return 0;
		else
		{
		sheet = workbook.getSheetAt(index);
		int number=sheet.getLastRowNum()+1;
		return number;
		}
		
	}
	
	public int getRowCountbyColName(String sheetName,String colName,int ColNameRowIndex) throws IOException
	{
		int col_Num=-1;
		String data;
		int[] dataCount = null;
	    try {
	    	int index = workbook.getSheetIndex(sheetName);
	    	sheet = workbook.getSheetAt(index);
	    	row=sheet.getRow(ColNameRowIndex);
	    	for(int i=0;i<row.getLastCellNum();i++)
			{
			
				if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
				{
					col_Num=i;
				}
			}
	        Iterator rowIter = sheet.rowIterator();
	        Row r = (Row)rowIter.next();
	        short lastCellNum = r.getLastCellNum();
	        dataCount = new int[lastCellNum];
	        int col = 0;
	        rowIter = sheet.rowIterator();
	        while(rowIter.hasNext()) {
	            Iterator cellIter = ((Row)rowIter.next()).cellIterator();
	            while(cellIter.hasNext()) {
	                Cell cell = (Cell)cellIter.next();
	                col = cell.getColumnIndex();
	                if(col==col_Num){
	                dataCount[col] += 1;
	                DataFormatter df = new DataFormatter();
	                data = df.formatCellValue(cell);
	                
	            }
	            }
	        }
	        fis.close();
	       
	            System.out.println("col " + col_Num + ": " + dataCount[col_Num]);
	        
	    }
	    catch(Exception e) {
	        e.printStackTrace();
	       
	    }
		return dataCount[col_Num];
	}
	
	public void removeRow(String sheetName, int rowIndex) throws IOException {
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return;
		else
		{
		sheet = workbook.getSheetAt(index);
		System.out.println(sheet.getLastRowNum());
		for(int i=rowIndex;i<sheet.getLastRowNum();i++) {
		row = sheet.getRow(i);
		System.out.println(i);
		
		sheet.removeRow(row);
		
		}
		}
	    fileOut = new FileOutputStream(path);
	    sheet.setActiveCell(new CellAddress(0, 0));
		workbook.write(fileOut);
		fileOut.close();
	}
	
	public boolean isEmptyRow(Row row) {
	    boolean isEmpty=true;
	    String data="";
	    for(Cell cell:row) {
	        data=data.concat(cell.getStringCellValue());
	    }
	    if(!data.trim().isEmpty()) {
	        isEmpty=false;
	    }
	    return isEmpty;
	}
	
	public void SaveWorkbook(String sheetName) throws IOException
	{ System.out.println(path);
		fis = new FileInputStream(path);
	workbook = new XSSFWorkbook(fis);
	sheet = workbook.getSheetAt(0);
	//CommonMethod.PressKey();
	//fis.close();
	//workbook.close();
		
	}
	// returns number of columns in a sheet	
		/**
		 * Returns the No of Coulums in a sheet.
		 * @param sheetName
		 * @return NoOfrows count.
		 */
		public int getColumnCount(String sheetName)
		{
			// check if sheet exists
			if(!isSheetExist(sheetName))
			 return -1;
			
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
			
			if(row==null)
				return -1;
			
			return row.getLastCellNum();
		}
	
	// returns the data from a cell
	/**
	 * To read a data from a cell based on column name and returns the same value.
	 * @param sheetName
	 * @param colName
	 * @param rowNum
	 * @return cellText - data value from a excel cell.
	 * @exception Exception
	 */
	public String getCellData(String sheetName,String colName,int rowNum)
	{
		try
		{
			if(rowNum <=0)
				return "";
			
		int index = workbook.getSheetIndex(sheetName);
		
		int col_Num=-1;
		if(index==-1)
			return "";
		
		sheet = workbook.getSheetAt(index);
		row=sheet.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++)
		{
			

			if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
				col_Num=i;
		}
		if(col_Num==-1)
			return "";
		
		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(rowNum-1);
		if(row==null)
			return "";
		cell = row.getCell(col_Num);
		
		if(cell==null)
			return "";
	
		if(cell.getCellType () == CellType.STRING)
			  return cell.getStringCellValue();
		else if(cell.getCellType()==CellType.NUMERIC || cell.getCellType()==CellType.FORMULA )
		{
			  
			  String cellText  = String.valueOf(cell.getNumericCellValue());
			  if (HSSFDateUtil.isCellDateFormatted(cell))
			  {
		           // format in form of M/D/YY
				  double d = cell.getNumericCellValue();

				  Calendar cal =Calendar.getInstance();
				  cal.setTime(HSSFDateUtil.getJavaDate(d));
		          cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
		          cellText = cal.get(Calendar.MONTH)+1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;
		           
		         }
	  
		return cellText;
		  }
		else if(cell.getCellType()==CellType.BLANK)
		      return ""; 
		  else 
			  return String.valueOf(cell.getBooleanCellValue());
		
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
			return "row "+rowNum+" or column "+colName +" does not exist in xls";
		}
	}
	
	
	
	// returns true if data is set successfully else false
	/**
	 * To write the data in a excel result sheet cell, using ColumnName. Return true/False.
	 * @param sheetName
	 * @param colName
	 * @param rowNum
	 * @param data
	 * @return True/False
	 */
	public boolean setCellData(String sheetName,String colName,int rowNum, String data)
	{
		try
		{
		fis = new FileInputStream(path); 
		workbook = new XSSFWorkbook(fis);

		if(rowNum<=0)
			return false;
		
		int index = workbook.getSheetIndex(sheetName);
		int colNum=-1;
		if(index==-1)
			return false;
		
		
		sheet = workbook.getSheetAt(index);
		

		row=sheet.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++)
		{
			System.out.println(row.getCell(i).getStringCellValue().trim());
			if(row.getCell(i).getStringCellValue().trim().equals(colName))
				colNum=i;
		}
		if(colNum==-1)
			return false;

		sheet.autoSizeColumn(colNum); 
		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);

	    
	    cell.setCellValue(data);

	    fileOut = new FileOutputStream(path);

		workbook.write(fileOut);

	    fileOut.close();	

		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	 // find whether sheets exists	
		/**
		 *  To Verify the sheet exists or not.
		 * @param sheetName
		 * @return boolean - true/faluse.
		 */
		public boolean isSheetExist(String sheetName)
		{
			int index = workbook.getSheetIndex(sheetName);
			if(index==-1){
				index=workbook.getSheetIndex(sheetName.toUpperCase());
					if(index==-1)
						return false;
					else
						return true;
			}
			else
				return true;
		}
		/**
		 * Overloaded method. To read a data from a cell based column No. Returns the data from a cell
		 * @param sheetName
		 * @param colNum
		 * @param rowNum
		 * @return cellText - the text value in the specific cell.
		 */
		// returns the data from a cell
		
		public boolean setCellDataByColRowNum(String sheetName,String colName,int rowNum, String data, int ColRowNum)
		{
			try
			{
				
			fis = new FileInputStream(path); 
			workbook = new XSSFWorkbook(fis);
			

			if(rowNum<=0)
				return false;
			
			int index = workbook.getSheetIndex(sheetName);
			int colNum=-1;
			if(index==-1)
				return false;
			
			
			sheet = workbook.getSheetAt(index);
			

			row=sheet.getRow(1);
			for(int i=0;i<row.getLastCellNum();i++)
			{
				
				if(row.getCell(i).getStringCellValue().trim().equals(colName)) {
					colNum=i;
				}
			}
			if(colNum==-1)
				return false;

			sheet.autoSizeColumn(colNum); 
			row = sheet.getRow(rowNum-1);
			if (row == null)
				row = sheet.createRow(rowNum-1);
			
			
			cell = row.getCell(colNum);	
			//XSSFCellStyle CellStyle= cell.getRow().getRowStyle();
			if (cell == null)
		        cell = row.createCell(colNum);
			
			XSSFFont wbFont = workbook.createFont();
			wbFont.setCharSet(XSSFFont.ANSI_CHARSET); //Your Character encoding goes in the parameter
			//Establish cell styles
			XSSFCellStyle cellStyle =workbook.createCellStyle();
			cellStyle.setFont(wbFont);

			cell.setCellStyle(cellStyle);
		    cell.setCellValue(data);
		   // removeEmptyRows(sheet);
		    //fis.close();
		    
		    IOUtils.closeQuietly(fis);
		   // XSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);

		    fileOut = new FileOutputStream(path);
		    sheet.setActiveCell(new CellAddress(0, 0));
		   
	    

			workbook.write(fileOut);
			
					
			
		
			/*fileOut.close();
			workbook.close();*/
			
			//IOUtils.closeQuietly(fileOut);   
		   

			}
			
			catch(Exception e)
			{
				e.printStackTrace();
				return false;
			}
			 finally {
		            // close the streams using close method
		            try {
		                if (fileOut != null) {
		                	fileOut.flush();
		                	fileOut.close();
		                	workbook.close();
		                }
		            }
		            catch (IOException ioe) {
		                System.out.println("Error while closing stream: " + ioe);
		            }
		 
		        }
		 
			return true;
		}
		public String getCellData(String sheetName,int colNum,int rowNum)
		{
			try
			{
				if(rowNum <=0)
					return "";
			
			int index = workbook.getSheetIndex(sheetName);

			if(index==-1)
				return "";
			
		
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum-1);
			if(row==null)
				return "";
			cell = row.getCell(colNum);
			if(cell==null)
				return "";
			
			  return cell.getStringCellValue();
		  
		  }
		 
			catch(Exception e)
			{
				
				e.printStackTrace();
				return "row "+rowNum+" or column "+colNum +" does not exist  in xlsx";
			}
		}
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
