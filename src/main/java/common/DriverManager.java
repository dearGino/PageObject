package main.java.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

	private WebDriver driver = null;
	private static DriverManager INSTANCE = null;
	
		
	
	private DriverManager() {
	}
	
	
	
    public static DriverManager getDriverManager() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }
    
    
    
    public WebDriver getDriver() {
        if (driver == null) {
            createDriver();
        }
        return driver;
    }
        
    
    
	public WebDriver createDriver() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
	}
	
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    
}
