package stepDefinition;

import Cucumber.Automation.Base;
import io.cucumber.java.After;

public class Hooks extends Base{

	
	@After("DataTableTest")
	public void afterValidation() {
		
		driver.quit();
	}
}
