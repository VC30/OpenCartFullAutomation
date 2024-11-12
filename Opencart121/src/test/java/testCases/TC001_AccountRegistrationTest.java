package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AccountRegistrationPage;
import pageObject.Homepage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() {
		
		logger.info("********** starting TC001_AccountRegistrationTest *********");	
		
		try {
		Homepage hp = new Homepage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on my account");
		
		hp.clickRegister();
		logger.info("Clicked on register");
		
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		
		logger.info("Providing customers details");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@mail.com");
		regpage.setPhonenumber(randomNumber());
		
		String password = randomAlphaNumeric();
		
		regpage.setpassword(password);
		regpage.setConfirmpassword(password);
		
		regpage.clickPolicy();
		regpage.clickButton();
		
		
		logger.info("Validating expected message....");
		String ConfMsg = regpage.getConfirmationmsg();
		Assert.assertEquals(ConfMsg, "Your Account Has Been Created!");
		
		}  catch(Exception e){
			logger.error("test failed...");
			logger.debug("Debug logs");
			Assert.fail();
		}
		
		
		logger.info("********** finished TC001_AccountRegistrationTest *********");
		
	}
	

}
