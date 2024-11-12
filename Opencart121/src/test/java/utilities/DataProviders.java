package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException{
		String path = ".\\testData\\OpenCartLogin.xlsx"; //taking xl from testData
		//String path = System.getProperty("user.dir")+"\\testData\\OpenCartLogin.xlsx";
		
		ExcelUtility xlutil = new ExcelUtility(path); //creating an object for xlutility
		
		int totalRows = xlutil.getRowCount("Sheet1");
		int totalCols = xlutil.getCellCount("Sheet1", 1);
		
		String logindata [][] = new String [totalRows][totalCols]; //created for 2d arrays which can store 
		
		for(int i=1;i<=totalRows;i++) {  //read the data from xl storing in 2d arrays
			
			for(int j=0;j<totalCols;j++) { // i is rows and j is columns
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			}
			
		}
		return logindata; //returning 2d array
				
	}

}
