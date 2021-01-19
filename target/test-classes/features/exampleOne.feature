Feature: Data table automation

@DataTableTest
Scenario: Verify two tables are present on the web page
Given User is on the desired web page
When The web page window is maximized
Then Verify the page has two tables

@DataTableTest
Scenario: Total number of rows in first table
Given User is on the desired web page
When The web page window is maximized
Then Verify the html table has same number of rows as below table
|Smith	|	John	|	jsmith@gmail.com			|	$50.00	|	http://www.jsmith.com		|
|Bach		|	Frank	|	fbach@yahoo.com				|	$51.00	|	http://www.frank.com		|
|Doe		|	Jason	|	jdoe@hotmail.com			|	$100.00	|	http://www.jdoe.com			|
|Conway	|	Tim		|	tconway@earthlink.net	|	$50.00	|	http://www.timconway.com|

@DataTableTest
Scenario: The values of each cell
Given User is on the desired web page
When The web page window is maximized
Then Verify if the webpage cell values are same as dataTable below
|Smith	|	John	|	jsmith@gmail.com			|	$50.00	|	http://www.jsmith.com		|
|Bach		|	Frank	|	fbach@yahoo.com				|	$51.00	|	http://www.frank.com		|
|Doe		|	Jason	|	jdoe@hotmail.com			|	$100.00	|	http://www.jdoe.com			|
|Conway	|	Tim		|	tconway@earthlink.net	|	$50.00	|	http://www.timconway.com|

@DataTableTest
Scenario: Edit button is clickable
Given User is on the desired web page
When The web page window is maximized
Then Verify that the edit button is clickable

@DataTableTest
Scenario: Delete button is clickable
Given User is on the desired web page
When The web page window is maximized
Then Verify that the delete button is clickable

@DataTableTest
Scenario: Sorting of the columns in ascending and descending order
Given User is on the desired web page
When The web page window is maximized
Then Click on header and verify the table is sorted in ascending order


@DataTableTest
Scenario: Cells are editable
Given User is on the desired web page
When The web page window is maximized
And User clicks on Edit button
Then Verify user is able to edit the cells

@DataTableTest
Scenario Outline: Delete button is working as expected
Given User is on the desired web page
When The web page window is maximized
And User clicks on Delete button
Then User should not be able to see the row in the table
|Smith	|
|Bach		|
|Doe		|
|Conway	|



