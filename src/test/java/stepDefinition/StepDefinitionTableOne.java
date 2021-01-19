package stepDefinition;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import Cucumber.Automation.Base;
import differentChecks.MethodsNeeded;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.dataTablePage;

public class StepDefinitionTableOne extends MethodsNeeded{
	public WebDriverWait wait;
	public WebDriver driver;
	dataTablePage dataPage = new dataTablePage(driver);;

    @Given("^User is on the desired web page$")
    public void user_is_on_the_desired_web_page() throws Throwable {
        System.out.println("User is on desired page");
        driver = Base.getDriver();        
    }

    @When("^The web page window is maximized$")
    public void the_web_page_window_is_maximized() throws Throwable {
        System.out.println("web page is maximized");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
    }
    
    @And("^User clicks on Edit button$")
    public void user_clicks_on_edit_button() throws Throwable {
    	
    	String buttonBeforeXpath = dataPage.getButtonBeforeXpath();
    	String editButtonAfterXpath = dataPage.getEditButtonAfterXpath();
    	for(int i=1;i<=rowCount;i++) {
        driver.findElement(By.xpath(buttonBeforeXpath+i+editButtonAfterXpath)).click();
    	}
    }
    
    @And("^User clicks on Delete button$")
    public void user_clicks_on_delete_button() throws Throwable {
    	String beforeXpath = dataPage.getButtonBeforeXpath();
    	String afterXpath = dataPage.deleteButtonAfterXpath();
    	for(int i=1;i<=rowCount;i++) {
        driver.findElement(By.xpath(beforeXpath+i+afterXpath)).click();
    	}
    }

    @Then("^Verify the page has two tables$")
    public void verify_the_page_has_two_tables() throws Throwable {
        System.out.println("two tables are present");
        boolean firstTablePresent = isElementPresent(dataPage.getTableOnePresent(),driver);
        Assert.assertTrue(firstTablePresent);
        
        boolean secondTablePresent = isElementPresent(dataPage.getTableTwoPresent(),driver);
		Assert.assertTrue(secondTablePresent);		
    }
    
    @Then("^Verify the html table has same number of rows as below table$")
    public void verify_the_html_table_has_same_number_of_rows__as_below_table(DataTable dataTable) {
    	List<List<String>> dataList = dataTable.asLists();
    	int rowSize = dataList.size();
    	int webPageRowSize = validateWebPageRowSize(driver);
    	Assert.assertEquals(rowSize, webPageRowSize);
    	   	
    }
    
    @Then("^Verify if the webpage cell values are same as dataTable below$")
    public void verify_if_the_webpage_cell_values_are_same_as_datatable_below(DataTable dataTable) throws Throwable {
    	verifyHtmlTableData(dataTable,driver);
    }

    @Then("^Verify that the edit button is clickable$")
    public void verify_that_the_edit_button_is_clickable() throws Throwable {
		WebElement editButton = driver.findElement(dataPage.getEditButtonClickable());
		Assert.assertTrue(isClickable(editButton, driver));
    }
    
    @Then("^Verify that the delete button is clickable$")
    public void verify_that_the_delete_button_is_clickable() throws Throwable {
    	WebElement deleteButton = driver.findElement(dataPage.getDeleteButtonClickable());
		Assert.assertTrue(isClickable(deleteButton, driver));
		
	}
    
    @Then("^Verify user is able to edit the cells$")
	public void verify_user_is_able_to_edit_the_cells() throws Throwable {
			Assert.assertTrue(isElementEditable(driver));
		}
		
    @Then("^User should not be able to see the row in the table$")
    public void user_should_not_be_able_to_see_the_row_in_the_table(DataTable data) throws Throwable {
    	
    	List<List<String>> dataList = data.asLists();
    	for(int i=0;i<dataList.size();i++) {
    		Assert.assertFalse(driver.findElement(dataPage.getDeleteisWorking()).getText().contains(dataList.get(i).get(0)));
    	}
    }
    
    @Then("^Click on header and verify the table is sorted in ascending order$")
    public void click_on_header_and_verify_the_table_is_sorted_in_ascending_order() throws Throwable {
    	ArrayList<String> columnHeaderName = null;
		
		verifyWebTableSorting(driver,"]/td[1]",columnHeaderName,"/th[1]");
		verifyWebTableSorting(driver,"]/td[2]",columnHeaderName,"/th[2]");
		verifyWebTableSorting(driver,"]/td[3]",columnHeaderName,"/th[3]");
		verifyWebTableSorting(driver,"]/td[4]",columnHeaderName,"/th[4]");
		verifyWebTableSorting(driver,"]/td[5]",columnHeaderName,"/th[5]");
    }
}
