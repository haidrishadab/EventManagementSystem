package stepdefinitions;

import java.util.List;

//import org.junit.Assert;
import org.testng.Assert;

import baseClass.Library;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BookingPage;
import utilities.ReusableFunctions;

public class BookingModuleDefinitions extends Library {
	
	ReusableFunctions re;
	BookingPage booking;
	List<String> data;
	
	@Given("The user opens the Booking module")
	public void the_user_opens_the_booking_module() {
		
		re = new ReusableFunctions(driver);
		booking = new BookingPage(driver);
		booking.clickBooking();
	}
	@When("The user fills all required booking fields except the phone number")
	public void the_user_fills_all_required_booking_fields_except_the_phone_number() {
	    data = re.readExcel("src/test/resources/testdata/bookingTestData.xlsx", "Sheet1", 1, 13);
	    
	    booking.fillBookingForm(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5), data.get(6), data.get(7), data.get(8), data.get(9), data.get(10), data.get(11));
	    
	}
	@When("Submits the booking form")
	public void submits_the_booking_form() {
	    booking.BooknowSubmit();
	}
	
	
	@When("The user fills the booking form with an invalid guest count")
	public void the_user_fills_the_booking_form_with_an_invalid_guest_count() {
		data = re.readExcel("src/test/resources/testdata/bookingTestData.xlsx", "Sheet1", 2, 13);
	    
	    booking.fillBookingForm(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5), data.get(6), data.get(7), data.get(8), data.get(9), data.get(10), data.get(11));
	    
	}
	
	@When("The user fills the booking form without selecting an event type")
	public void the_user_fills_the_booking_form_without_selecting_an_event_type() {
		data = re.readExcel("src/test/resources/testdata/bookingTestData.xlsx", "Sheet1", 3, 13);
	    
	    booking.fillBookingForm(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5), data.get(6), data.get(7), data.get(8), data.get(9), data.get(10), data.get(11));
	    
	}
	
	
	@Then("A phone number error message should be displayed")
	public void a_phone_number_error_message_should_be_displayed() {
		String expetedRes = data.get(12);
		String error = booking.getphoneError();
		Assert.assertEquals( expetedRes,error);
		if (!error.equals(expetedRes)) {
		    System.out.println("Mismatch! Expected: \"" + expetedRes + "\", but found: \"" + error+"\"");
		    Assert.fail("Phone number error message is not as expected.");
		}
        re.takescreenshot("src/test/resources/screenshots/PhoneNumberError.png");


	}
	
	
	@Then("A guest count error message should be displayed")
	public void a_guest_count_error_message_should_be_displayed() {
		String expetedRes = data.get(12);
		String error = booking.getEventError();

//		Assert.assertEquals("The error message is not displayed", expetedRes, error);
		
		if (!error.equals(expetedRes)) {
		    System.out.println("Mismatch! Expected: \"" + expetedRes + "\", but found: \"" + error+"\"");
		    Assert.fail("Guest count error message is not as expected.");
		}

        re.takescreenshot("src/test/resources/screenshots/GuestCountError.png");


	}
	@Then("An event type error message should be displayed")
	public void an_event_type_error_message_should_be_displayed() {
		String expetedRes = data.get(12);
		String error = booking.getEventError();
//		Assert.assertEquals( expetedRes,error);
		if (!error.equals(expetedRes)) {
		    System.out.println(" Mismatch! Expected: \"" + expetedRes + "\", but found: \"" + error+"\"");
		    Assert.fail("Event type error message is not as expected.");
		}
        re.takescreenshot("src/test/resources/screenshots/EventTypeError.png");

	}
}
