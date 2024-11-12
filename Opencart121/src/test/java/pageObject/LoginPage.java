package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver){
		 super(driver);
	}
	
@FindBy(xpath="//input[@id='input-email']")
WebElement txtLoginEmail;

@FindBy(xpath="//input[@id='input-password']")
WebElement txtLoginpassword;
	
@FindBy(xpath="//input[@value='Login']")	
WebElement btnLogin;


public void setLoginMail(String email) {
	txtLoginEmail.sendKeys(email);
}

public void setLoginPassword(String pwd) {
	txtLoginpassword.sendKeys(pwd);
}

public void clickLoginbtn() {
	btnLogin.click();
}
	
	
	
	
	
	
}
