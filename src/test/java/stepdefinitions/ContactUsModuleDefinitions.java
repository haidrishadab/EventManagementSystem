package stepdefinitions;
 
import java.util.List;

//import org.junit.Assert;
import org.testng.Assert;

import baseClass.Library;
import pages.ContactUsPage;
import utilities.ReusableFunctions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
 
public class ContactUsModuleDefinitions extends Library {
    ReusableFunctions re;
    ContactUsPage co;
    List<String> data;
 
    @Given("The user opens the Contact Us module")
    public void user_opens_contactmodule() {
        re = new ReusableFunctions(driver);
        co = new ContactUsPage(driver);
        co.clickContact();
    }
 
    @When("The user enters an invalid email ID and all other fields are valid")
    public void the_user_enters_an_invalid_email_id_and_all_other_fields_are_valid() {
        data = re.readExcel("src/test/resources/testdata/contactTestData.xlsx", "Sheet1", 3, 5);
        co.filldetails(data.get(0), data.get(1), data.get(2), data.get(3));
    }
 
    @When("Submits the Contact Us form")
    public void submits_the_contact_us_form() {
        co.Clicksend();
    }
 
    @Then("An email format error message should be displayed")
    public void an_email_format_error_message_should_be_displayed() {
    	String expetedRes = data.get(4);
        String error = co.getEmailErr();
        System.out.println("Email Error: " + error);
//		Assert.assertEquals( expetedRes,error);
        if (!error.equals(expetedRes)) {
		    System.out.println(" Mismatch! Expected: \"" + expetedRes + "\", but found: \"" + error+"\"");
		    Assert.fail("Email format error message is not as expected.");
		}
        re.takescreenshot("src/test/resources/screenshots/InvalidEmailError.png");
    }
 
    @When("The user enters an invalid name and all other fields are valid")
    public void the_user_enters_an_invalid_name_and_all_other_fields_are_valid() {
        data = re.readExcel("src/test/resources/testdata/contactTestData.xlsx", "Sheet1", 2, 5);
        co.filldetails(data.get(0), data.get(1), data.get(2), data.get(3));
    }
 
    @Then("A name error message should be displayed")
    public void a_name_error_message_should_be_displayed() {
        String error = co.getNameErr();
        String expetedRes = data.get(4);
        System.out.println("Email Error: " + error);
//		Assert.assertEquals( expetedRes,error);
        if (!error.equals(expetedRes)) {
		    System.out.println("Mismatch! Expected: \"" + expetedRes + "\", but found: \"" + error+"\"");
		    Assert.fail("Name error message is not as expected.");
		}
        re.takescreenshot("src/test/resources/screenshots/InvalidNameError.png");
    }
 
    @When("The user enters valid details in all fields")
    public void the_user_enters_valid_details_in_all_fields() {
        data = re.readExcel("src/test/resources/testdata/contactTestData.xlsx", "Sheet1", 1, 5);
        co.filldetails(data.get(0), data.get(1), data.get(2), data.get(3));
    }
 
    @Then("A success message should be displayed")
    public void a_success_message_should_be_displayed() {
    	
        String success = co.getSuccess();
        String expetedRes = data.get(4);
        System.out.println("Success message : " + success);
		Assert.assertEquals(expetedRes,success);
        re.takescreenshot("src/test/resources/screenshots/COntactFormSuccess.png");
    }
}