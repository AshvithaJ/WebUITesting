package Cucumber.Automation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {

	public static WebDriver driver;
	public static Properties prop;
	
	public static WebDriver getDriver() throws IOException {
		prop = new Properties();
		FileInputStream inputStream = new FileInputStream("E:\\Ashvitha\\eclipse-workspace\\Automation\\src\\test\\java\\Cucumber\\Automation\\global.properties");		
		prop.load(inputStream);
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(prop.getProperty("url"));
		return driver;
        
	}
	
}
