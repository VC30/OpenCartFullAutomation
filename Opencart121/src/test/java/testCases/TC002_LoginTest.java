package testCases;

import pageObject.Homepage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002_LoginTest extends BaseClass {
	
	
	@Test(groups={"Sanity","Master"}) //grouping test cases //master means all groups should be executed
	public void verify_login() {
		
		try {
		//Homepage
		Homepage hp = new Homepage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		
		//Loginpage
		LoginPage lp = new LoginPage(driver);
		lp.setLoginMail(p.getProperty("email"));
		lp.setLoginPassword(p.getProperty("password"));
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		lp.clickLoginbtn();
		
		
		//MyAccountPage
		MyAccountPage map = new MyAccountPage(driver);
		boolean targetPage = map.isMyAccountPageExist();

		//Assert.assertEquals(targetPage, true,"Login failed");
		Assert.assertTrue(targetPage);
		} catch(Exception e) {
			Assert.fail();
		}
		
		
	}
	

}
