package testrunners;


//import org.junit.runner.RunWith;
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


//@RunWith(Cucumber.class)
@CucumberOptions(

	    features = {"src/test/resources/features"},      // Path to feature files

	    glue = {"stepdefinitions","Hooks"},              // Package for step definitions and hooks
//	    		tags="@smoke",
	    		plugin = {

	    		        "pretty",

	    		        "html:target/cucumber-html-report.html",

	    		        "json:target/cucumber.json",

	    		        "rerun:target/rerun.txt",
	    		        "testrunners.CucumberExtentReportPlugin"
	    		       	    		    },

	    		 monochrome = false


	)

	public class TestRunners extends AbstractTestNGCucumberTests {

	    // Empty class - the annotations drive the configuration

}
 
