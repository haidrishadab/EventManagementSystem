package utilities;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
 
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import baseClass.Library;
 
public class ReusableFunctions {
	
	WebDriver driver;
	
    public ReusableFunctions(WebDriver driver) {
        this.driver = driver;
    }
// wait for visiblity
    public void waitForElementToBeVisible(WebElement element) {
    	try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOf(element));
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}
    }
    
    public void waitforclickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    
   //click the element
    public void clickElement(WebElement element) {
        waitForElementToBeVisible(element);
        element.click();
    }
    
    //wait for search element
    public void waitForSearchResults() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
            ExpectedConditions.visibilityOfElementLocated(By.cssSelector("._1YokD2 ._1AtVbE")) // Adjust based on result section
        );
    }
    
  //wait for search element
    public void waitForElementContainText(WebElement element) {
    	try {
    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        	wait.until(driver -> !element.getText().trim().isEmpty());
		} catch (Exception e) {
			System.out.println(e);
		}
    	

    }
 
    //wait for saerch page
    public void waitForSearchPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
            ExpectedConditions.visibilityOfElementLocated(By.name("q"))
        );
    
    }
    //enter text
    public void enterText(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }
    
// Dropdown: Select by visible text
    public void selectByVisibleText(WebElement dropdown, String visibleText) {
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }
 
    // Dropdown: Select by index
    public void selectByIndex(WebElement dropdown, int index) {
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }
 
    // Dropdown: Select by value
    public void selectByValue(WebElement dropdown, String value) {
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }
    
    //take screenshot
    public void takescreenshot(String path){
    	try {
	    	TakesScreenshot ts = (TakesScreenshot) driver;
	        File src = ts.getScreenshotAs(OutputType.FILE);
	 
	        // Now copy the screenshot to desired location using copyFile method
	        FileUtils.copyFile(src, new File(path));
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}
    }
    
    
    //scrolldown
    public void scrolldown(String element) throws InterruptedException {
 
		   JavascriptExecutor js =(JavascriptExecutor)driver;
		   js.executeScript("window.scrollBy(0,2000)");
		   Thread.sleep(5000);
		   WebElement ele = driver.findElement(By.xpath(element));
		   js.executeScript("arguments[0].click();", ele);
		   
    	
    }
    
    //window handling
    public void windowHandling(String element) {
    	
    	driver.findElement(By.xpath(element)).click();
		   String mainWindow = driver.getWindowHandle();
		   System.out.println(mainWindow);
		   Set<String> allWindows = driver.getWindowHandles();
		  
		   for (String window : allWindows) {
			   
			   driver.switchTo().window(window);
			   
            System.out.println("Switched to new window: " + driver.getTitle());
			   
		   }
		   driver.switchTo().window(mainWindow);
    	
    }
    
    //alert handling
    public void alerthandling(String value) throws InterruptedException {
    	
    	 Alert alert = driver.switchTo().alert();
		   Thread.sleep(4000);
		   alert.sendKeys(value);
		   alert.accept();
    	
    }
    
    //get the page title
    public void getTitle() {
        System.out.println( driver.getTitle());;
    }
    
    //get the tesxt form element
    public String getText(WebElement element) {
        return element.getText();
    }
    
    //read excel file
    public List<String> readExcel(String filename, String sheetname, int rowNumber, int noOfCell) {
        List<String> data = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(filename);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {

            XSSFSheet sheet = workbook.getSheet(sheetname);
            XSSFRow row = sheet.getRow(rowNumber);

            for (int i = 0; i < noOfCell; i++) {
                XSSFCell cell = row.getCell(i);
                if (cell == null) {
                    data.add("");
                    continue;
                }

                switch (cell.getCellType()) {
                    case STRING:
                        data.add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        data.add(cell.getNumericCellValue()+"");
                        break;
                    default:
                        data.add("");
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); 
        }

        return data;
    }

 
}