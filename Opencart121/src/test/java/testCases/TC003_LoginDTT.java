package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.Homepage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDTT extends BaseClass{
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven") //Getting data provider from a different class
	public void verify_loginDDT(String email, String pwd, String expRes){
		
		try {
		//homepage
		Homepage hp = new Homepage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		
		//Loginpage
		LoginPage lp = new LoginPage(driver);
		lp.setLoginMail(email);
		lp.setLoginPassword(pwd);
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		lp.clickLoginbtn();
		
		//MyAccountPage
//		MyAccountPage map = new MyAccountPage(driver);
//		boolean targetPage = map.isMyAccountPageExist();
//		
//		if(expRes.equalsIgnoreCase("Valid")) //data is valid
//		{
//			if(targetPage=true) //login is successful
//			{
//				map.clickLogout();  // then logout
//				Assert.assertTrue(true); //test passed
//				
//			}
//			else 
//			{
//				Assert.assertTrue(false); // data is valid but login is unsuccessful // test failed
//			}
//		}
//		
//		
//		if(expRes.equalsIgnoreCase("Invalid")) //data is invalid
//		{
//			if(targetPage=true) //login is successful
//			{
//				map.clickLogout();  // then logout
//				Assert.assertTrue(false); // data is invalid but login is successful // failed
//		
//			}
//			else
//			{
//				Assert.assertTrue(true); // data is invalid and login is unsuccessful // passed
//			}
//		}
		
		//chatgpt
		MyAccountPage map = new MyAccountPage(driver);
		boolean targetPage = map.isMyAccountPageExist();

		if (expRes.equalsIgnoreCase("Valid")) { // Data is valid
		    if (targetPage) { // Login is successful
		        map.clickLogout(); // Then logout
		        Assert.assertTrue(true); // Test passed
		    } else {
		        Assert.assertTrue(false, "Data is valid but login is unsuccessful."); // Test failed
		    }
		}

		if (expRes.equalsIgnoreCase("Invalid")) { // Data is invalid
		    if (targetPage) { // Login is successful
		        map.clickLogout(); // Then logout
		        Assert.assertTrue(false, "Data is invalid but login is successful."); // Failed
		    } else {
		        Assert.assertTrue(true); // Data is invalid and login is unsuccessful // Passed
		    }
		}

		
			
		
		
		} catch(Exception e) {
			Assert.fail();
		}
		
	}

}
