package pages;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
 
import utilities.ReusableFunctions;
 
public class ContactUsPage {
	private WebDriver driver;
	ReusableFunctions re;
	@FindBy(xpath="//a[text()='Contact Us']")
	WebElement ClickContact;
	@FindBy(xpath="//input[@id='contact_name']")
	WebElement Name;
	@FindBy(xpath="//input[@id='contact_email']")
	WebElement Email;
	@FindBy(xpath="//input[@id='contact_subject']")
	WebElement Subject;
	@FindBy(xpath="//textarea[@id='contact_message']")
	WebElement Message;
	@FindBy(xpath="//button[@id='message']")
	WebElement SendMessage;
	@FindBy(xpath="//p[@id='mesgtab']")
	WebElement Succ;
	@FindBy(xpath="//div[@id='contactNameErr']")
	WebElement NameErr;
	@FindBy(xpath="//div[@id='contactEmailErr']")
	WebElement EmailErr;
	public ContactUsPage(WebDriver driver) {
		this.driver = driver;
        PageFactory.initElements(driver, this);
        re=new ReusableFunctions(driver);
	}
	public void clickContact() {
		re.clickElement(ClickContact);
	}
	public void filldetails(String name,String email,String subject,String message) {
		if(!name.isEmpty()) re.enterText(Name, name);
		if(!email.isEmpty()) re.enterText(Email, email);
		if(!subject.isEmpty()) re.enterText(Subject, subject);
		if(!message.isEmpty()) re.enterText(Message, message);
	}
	public void Clicksend() {
		re.clickElement(SendMessage);
	}
	public String getSuccess() {
		re.waitForElementContainText(Succ);
		System.out.println(re.getText(Succ));
		return re.getText(Succ);
	}
	public String getNameErr() {
		re.waitForElementContainText(NameErr);
		return re.getText(NameErr);
	}
	public String getEmailErr() {
		re.waitForElementContainText(EmailErr);
		return re.getText(EmailErr);
	}
}