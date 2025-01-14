package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.testng.annotations.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.logging.log4j.LogManager;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.nio.file.Files;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;
public class BaseClass {
	
public static WebDriver driver; //static so it does not conflict with the webdriver in the ExtentReportManager page.
public Properties p;
public org.apache.logging.log4j.Logger logger;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setUp(@Optional("Windows") String os,@Optional("chrome") String br) throws IOException {
		
		FileReader file = new FileReader("./src//test//resources//config.properties"); //to fetch parameters from config file
		p = new Properties();
		p.load(file);
		
		logger= LogManager.getLogger(this.getClass());
		
		String executionEnv = p.getProperty("execution_env").toLowerCase();
		
		//setting up the remote running of the script
		if("remote".equals(executionEnv)) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//selecting the prefered OS 
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			} 
			else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			} 
			else if (os.equalsIgnoreCase("linux")) {
			    capabilities.setPlatform(Platform.LINUX);}
			
			else {
				throw new IllegalArgumentException("Unknown OS: " + os);
			}
			
			//selecting browser
			switch(br.toLowerCase()) 
			{
			case "chrome" : capabilities.setBrowserName("chrome"); break;
			case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox": capabilities.setBrowserName("firefox"); break;
			default : System.out.println("Invalid browser name for running locally..."); return; //return means it will automatically exit the loop
			}
			
			//driver = new RemoteWebDriver(new URL("http://192.168.0.167:4444/wd/hub"),capabilities);
			//driver = new RemoteWebDriver(new URL("http://localhost:81/wd/hub"),capabilities);
			//logger.info("Remote WebDriver initialized with OS: " + os + ", Browser: " + br);
			
			try {
			    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
			    logger.info("Remote WebDriver initialized successfully.");
			} catch (Exception e) {
			    logger.error("Failed to initialize Remote WebDriver: ", e);
			    throw new RuntimeException("Could not connect to Selenium Grid.", e);
			}
		}
		
		//setting up the local running of the script
	/*	else if("local".equals(executionEnv)) 
		{
			switch(br.toLowerCase()) 
			{
			case "chrome": driver = new ChromeDriver(); break; 
			case "firefox":driver = new FirefoxDriver(); break;
			case "edge" : driver = new EdgeDriver(); break;
			default : System.out.println("Invalid browser name..."); return; 
			}
			logger.info("Local WebDriver initialized with Browser: " + br);
		}
		*/
		
	/*	else if("local".equals(executionEnv)) 
		{
		    switch(br.toLowerCase()) 
		    {
		        case "chrome": 
		            ChromeOptions chromeOptions = new ChromeOptions();
		            chromeOptions.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
		            driver = new ChromeDriver(chromeOptions);
		            break; 

		        case "firefox":
		            FirefoxOptions firefoxOptions = new FirefoxOptions();
		            firefoxOptions.addArguments("--headless");
		            driver = new FirefoxDriver(firefoxOptions);
		            break;

		        case "edge": 
		            EdgeOptions edgeOptions = new EdgeOptions();
		            edgeOptions.addArguments("--headless");
		            driver = new EdgeDriver(edgeOptions);
		            break; //breaking

		        default: 
		            System.out.println("Invalid browser name...");
		            return; 
		    }
		    logger.info("Local WebDriver initialized in headless mode with Browser: " + br);
		}

	*/
		else if("local".equals(executionEnv)) 
{
    switch(br.toLowerCase()) 
    {
        case "chrome": 
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
            driver = new ChromeDriver(chromeOptions);
            break; 

        case "firefox":
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--headless");
            driver = new FirefoxDriver(firefoxOptions);
            break;

        case "edge": 
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--headless");
            driver = new EdgeDriver(edgeOptions);
            break;

        default: 
            System.out.println("Invalid browser name...");
            return; 
    }
    logger.info("Local WebDriver initialized in headless mode with Browser: " + br);
}


		else {
			throw new IllegalArgumentException("Invalid execution environment: " + executionEnv);
		}
		
		//driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appurl"));
		driver.manage().window().maximize(); 
		
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown() {
		driver.quit();
	}
	
	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomNumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(11);
		return generatedNumber;
	}
	
	public String randomAlphaNumeric() {
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		
		return (generatedString+generatedNumber);
	}
	
	public String captureScreen(String tname) throws IOException {
	    // Format the timestamp for the screenshot filename
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss");
	    String timeStamp = LocalDateTime.now().format(formatter);

	    // Verify if driver supports TakesScreenshot
	    if (driver == null || !(driver instanceof TakesScreenshot)) {
	        throw new IllegalStateException("Driver is not initialized or does not support screenshots.");
	    }

	    // Capture screenshot
	    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

	    // Define the target file path
	    String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
	    File targetFile = new File(targetFilePath);

	    // Copy the source file to the target location
	    Files.copy(sourceFile.toPath(), targetFile.toPath());

	    return targetFilePath; // Return the file path for reporting/logging
	}
	

}
