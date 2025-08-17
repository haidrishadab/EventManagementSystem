package stepdefinitions;
 
import baseClass.Library;
import io.cucumber.java.After;
import io.cucumber.java.Before;
 
public class Hooks extends Library{
	
		@Before
		 public void setUp() throws Exception {
			initializeBrowser();
			openApplication();
		    }
	
 
	   @After
	   public void tearDown() {
		   closeBrowser();
	   }
}