package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	public AccountRegistrationPage(WebDriver driver){
		
		 super(driver);
		 
	}
	
	
@FindBy(xpath="//input[@id='input-firstname']")
WebElement txtFirstName;

@FindBy(xpath="//input[@id='input-lastname']")
WebElement txtLastName;

@FindBy(xpath="//input[@id='input-email']")
WebElement txtEmail;

@FindBy(xpath="//input[@id='input-telephone']")
WebElement txtPhoneNumber;

@FindBy(xpath="//input[@id='input-password']")
WebElement txtPassword;

@FindBy(xpath="//input[@id='input-confirm']")
WebElement txtConfirmPassword;

@FindBy(xpath="//input[@name='agree']")
WebElement checkPolicy;

@FindBy(xpath="//input[@value='Continue']")
WebElement btnContinue;

@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
WebElement ConfirmationMsg;

public void setFirstName(String fname) {
	txtFirstName.sendKeys(fname);
}

public void setLastName(String lname) {
	txtLastName.sendKeys(lname);
}

public void setEmail(String email) {
	txtEmail.sendKeys(email);
}

public void setPhonenumber(String tel) {
	txtPhoneNumber.sendKeys(tel);
}

public void setpassword (String pwd) {
	txtPassword.sendKeys(pwd);
}

public void setConfirmpassword(String confirmpwd) {
	txtConfirmPassword.sendKeys(confirmpwd);
}

public void clickPolicy() {
	checkPolicy.click();
}

public void clickButton() {
	btnContinue.click();
}


public String getConfirmationmsg() {
	try {
		return(ConfirmationMsg.getText());
	}catch(Exception e){
		return(e.getMessage());			
	}
}
	
	
	

}
