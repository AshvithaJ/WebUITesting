package differentChecks;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;

public class MethodsNeeded {
	public static int rowCount;
	public static String rowBeforeXpath = "//*[@id='table1']/tbody/tr[";
	public static String rowBeforeXpathHeader = "//*[@id='table1']/thead/tr";
	
		public static boolean isElementPresent(By xpath, WebDriver driver) {
			boolean present;
			try
		        {
		          driver.findElement(xpath);
		          present = true;
		        }catch (NoSuchElementException e)
		        {
		          present = false;
		         }
		     return present;
		}
		
		public static boolean isElementEditable(WebDriver driver) {
			boolean editable;
			try 
			{
				String beforeXpath = "//table[@id='table1']/tbody/tr[1]/td[";
				String afterXpath = "]";
				List<WebElement> columns = driver.findElements(By.xpath("//table[@id='table1']/thead/tr/th"));
				  int columnCount = columns.size();
				for(int i=1;i<columnCount;i++) {
					driver.findElement(By.xpath(beforeXpath+i+afterXpath)).sendKeys("xyz");
					
				}
				editable = true;
				
			} catch (ElementNotInteractableException e) {
				editable = false;
			}
			return editable;
		}
		
		public static int validateWebPageRowSize(WebDriver driver) {
			List<WebElement> rows = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr"));
			rowCount = rows.size();
			
			return rowCount;
		}
		
		public void verifyHtmlTableData(DataTable dataTable, WebDriver driver) {
			WebElement htmlTableElement = driver.findElement(By.xpath("//table[@id='table1']")); //get the table WebElement
	        List<WebElement> rowElements = htmlTableElement.findElements(By.xpath(".//tr")); //get all of the row WebElements from the table
	        rowElements.remove(0); //remove the "header" row from the list of row WebElements

	        List<List<String>> dataTableRows = dataTable.asLists(); //outer List<> is rows, inner List<> is cells
	        for (List<String> row : dataTableRows) { //loop through every row in the DataTable input
	            int rowIdx = dataTableRows.indexOf(row);
	            WebElement rowElem = rowElements.get(rowIdx); //get the row WebElement based on the index of the current row in the DataTable
	            List<WebElement> cellElements = rowElem.findElements(By.xpath(".//td")); //get all of the cells from the row WebElement

	            for (String expectedCell : row) { //loop through every cell in the current DataTable row
	                int cellIdx = row.indexOf(expectedCell);
	                String actualCell = cellElements.get(cellIdx).getText();

	                Assert.assertEquals("Expected value of cell should match actual value of cell",
	                        expectedCell, actualCell);
	            }
	        }
	    }
		
		public static boolean isClickable(WebElement el, WebDriver driver){
			try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(el));
			return true;
			}
			catch(Exception e) {
				return false;
			}
		}
		
		private static ArrayList<String> rowElement(WebDriver driver, String xPath) {
			List<WebElement> rows = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr"));
			rowCount = rows.size();
			System.out.println("Row Count: " + rowCount);
			//String rowAfterXpathLastName = "]/td[1]";
			ArrayList<String> aListLastName = new ArrayList<String>();
			
			for (int i = 1; i <= rowCount; i++) {
				WebElement rowElementLastName = driver.findElement(By.xpath(rowBeforeXpath+i+xPath));
				String rowElementsLastName = rowElementLastName.getText();
				System.out.println("Row Elements-------" + rowElementsLastName);
				aListLastName.add(rowElementsLastName);
			}
			return aListLastName;
		}

		public static void verifyWebTableSorting(WebDriver driver,String columnNumber,ArrayList<String> columnHeaderName,String columnHeaderNumber) {
			columnHeaderName = rowElement(driver,columnNumber);
			
			
			System.out.println("Before sorting Column Values to Ascending in code------" + columnHeaderName);
			Collections.sort(columnHeaderName);
			ArrayList<String> sortedListAscending = columnHeaderName;
			System.out.println("After sorting Column Values to Ascending in code------" + columnHeaderName);

			 driver.findElement(By.xpath(rowBeforeXpathHeader+columnHeaderNumber)).click();
			 rowElement(driver,columnNumber);
			 System.out.println("After clicking once on Header on web page Ascending------"+columnHeaderName);
			 
			 Assert.assertEquals(sortedListAscending, columnHeaderName);
				
			 System.out.println("Before sorting Column values to descending in code------" + columnHeaderName);
			 Collections.reverse(columnHeaderName);
			 ArrayList<String> sortedListDescending = columnHeaderName;
			 System.out.println("After sorting Column values to descending in code------" + columnHeaderName);
			 driver.findElement(By.xpath(rowBeforeXpathHeader+columnHeaderNumber)).click();
			 rowElement(driver,columnNumber);;
			 System.out.println("After clicking again on Header of web Page descending------"+columnHeaderName);
			 Assert.assertEquals(sortedListDescending, columnHeaderName);
		}

}
