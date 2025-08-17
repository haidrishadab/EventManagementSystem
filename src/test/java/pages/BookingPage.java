package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.ReusableFunctions;

public class BookingPage {
	private WebDriver driver;
	ReusableFunctions re;
	
	@FindBy(xpath="//a[text()='Booking']")
	WebElement bookingclick;
	
	@FindBy(xpath="//input[@id='firstName']")
	WebElement firstname;
	
	@FindBy(xpath="//input[@id='lastName']")
	WebElement lastname;
	
	@FindBy(xpath="//input[@id='phoneNo']")
	WebElement phn;
	
	@FindBy(xpath="//input[@id='emaiId']")
	WebElement email;
	
	@FindBy(xpath="//select[@id='eventType']")
	WebElement EventType;
	
	@FindBy(xpath="//input[@id='eventDate']")
	WebElement eventDate;
	
	@FindBy(xpath="//input[@id='eventTime']")
	WebElement eventTime;
	
	@FindBy(xpath="//input[@id='guestCount']")
	WebElement guestCount;
	
	@FindBy(xpath="//input[@id='vegFood']")
	WebElement VegRadio;
	
	@FindBy(xpath="//input[@id='nonVegFood']")
	WebElement NonVegRadio;
	
	@FindBy(xpath="//input[@id='address']")
	WebElement Address;
	
	@FindBy(xpath="//select[@id='city']")
	WebElement City;
	
	@FindBy(xpath="//input[@id='pincode']")
	WebElement Pincode;
	
	@FindBy(xpath="//button[@id='book-now']")
	WebElement BookNow;
	
	@FindBy(xpath="//button[@id='reset-now']")
	WebElement Reset;
	
	@FindBy(xpath="//div[@id='phoneErr']")
	WebElement phoneError;
	
	@FindBy(xpath="//div[@id='guestCountErr']")
	WebElement guestError;
	
	@FindBy(xpath="//div[@id='eventTypeErr']")
	WebElement eventError;
	
	
	public BookingPage(WebDriver driver) {
		this.driver = driver;
        PageFactory.initElements(driver, this);
        re=new ReusableFunctions(driver);
	}
	
	
	public void clickBooking() {
		re.waitForElementToBeVisible(bookingclick);
		re.clickElement(bookingclick);
	}
	public void fillBookingForm(String fName, String lName, String phoneNo, String emailId,
            String eType, String eDate, String eTime, String gCount,
            String foodType, String addr, String cityName, String pin) {
		
		
		
		if (!fName.isEmpty()) re.enterText(firstname, fName);
		if (!lName.isEmpty()) re.enterText(lastname, lName);
		if (!phoneNo.isEmpty()) re.enterText(phn, phoneNo);
		if (!emailId.isEmpty()) re.enterText(email, emailId);
		if (!eType.isEmpty()) re.selectByVisibleText(EventType, eType);
		if (!eDate.isEmpty()) re.enterText(eventDate, eDate);
		if (!eTime.isEmpty()) re.enterText(eventTime, eTime);
		if (!gCount.isEmpty()) re.enterText(guestCount, gCount);
		
		if (foodType.equalsIgnoreCase("veg")) {
			re.clickElement(VegRadio);
		}
		else if (foodType.equalsIgnoreCase("nonveg")) {
			re.clickElement(NonVegRadio);
		}
		
		if (!addr.isEmpty()) re.enterText(Address, addr);
		if (!cityName.isEmpty()) re.selectByVisibleText(City, cityName);
		if (!pin.isEmpty()) re.enterText(Pincode, pin);
		
		
		
		}
	public void BooknowSubmit() {
		re.clickElement(BookNow);
	}
	public void resetForm() {
        re.clickElement(Reset);
    }
	public String getphoneError() {
//		re.waitForElementContainText(phoneError);
		return re.getText(phoneError);
	}
	public String  getGuestError() {
//		re.waitForElementContainText(guestError);
		return re.getText(guestError);
	}
	public String  getEventError() {
//		re.waitForElementContainText(eventError);
		return re.getText(eventError);
	}
}
 