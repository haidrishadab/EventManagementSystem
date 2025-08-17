package baseClass;
 
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
public class Library {
	
	protected static WebDriver driver;
    protected static Properties config = new Properties();
    private static final Logger logger = LogManager.getLogger(Library.class);
 
    // Load config.properties
    public static void loadConfig() {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/Configuration/Config.property");
            config.load(fis);
            logger.info("Read Property file");
        } catch (IOException e) {
            System.out.println("Failed to load configuration: " + e.getMessage());
        }
    }
 
    // Initialize browser based on config
    public static void initializeBrowser() {
        loadConfig();
        String browser = config.getProperty("browser", "chrome");
        logger.info("Launching browser...");
        int implicitWait = Integer.parseInt(config.getProperty("implicitWait", "10"));
 
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            logger.info("Launching Chrome browser...");
        }
        
        else if(browser.equalsIgnoreCase("firefox")){
        	 WebDriverManager.firefoxdriver().setup();
             driver = new FirefoxDriver();
             logger.info("Launching Firefox browser...");
        }
        	
        
        // You can add other browsers like Firefox, Edge etc.
 
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        logger.info("Maximixing the browser");
    }
 
    // Open application using config URL
    public static void openApplication() {
        String url = config.getProperty("url");
        driver.get(url);
        logger.info("Naivagated to url");
    }
 
    // Close the browser
    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
            logger.info("Closed Browser");  
        }
    }
 
}