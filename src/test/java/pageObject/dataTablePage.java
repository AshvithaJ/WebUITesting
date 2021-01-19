package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class dataTablePage {
	public WebDriver driver;
	public dataTablePage(WebDriver driver){
		this.driver = driver;
	}
	
	String buttonBeforeXpath = "//table[@id='table1']/tbody/tr[";
	String editButtonAfterXpath = "]/td[6]/a[1]";
	String deleteButtonAfterXpath = "]/td[6]/a[2]";
	By tablePresentTableOne = By.xpath("//table[@id='table1']/tbody");
	By tablePresentTableTwo = By.xpath("//table[@id='table2']/tbody");
	By editButtonClickable = By.xpath("//table[@id='table1']/tbody/tr[1]/td[6]/a[1]");
	By deleteButtonClickable = By.xpath("//table[@id='table1']/tbody/tr[1]/td[6]/a[2]");
	By deleteWorking = By.xpath("//table[@id='table1']/tbody");
	
	
	public String getButtonBeforeXpath() {
		
		return buttonBeforeXpath;
	}
	
	public String getEditButtonAfterXpath() {
		return editButtonAfterXpath;
	}
	
	public String deleteButtonAfterXpath() {
		return deleteButtonAfterXpath;
	}
	
	public By getTableOnePresent() {
		return tablePresentTableOne;
	}
	
	public By getTableTwoPresent() {
		return tablePresentTableTwo;
	}
	
	public By getEditButtonClickable() {
		return editButtonClickable;
	}
	
	public By getDeleteButtonClickable() {
		return deleteButtonClickable;
	}
	
	public By getDeleteisWorking() {
		return deleteWorking;
	}
}
