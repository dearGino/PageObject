package main.java.core.pages;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import main.java.common.DriverManager;
import main.java.common.PageManager;

public class BasePage {
	
	protected final DriverManager driverManager = DriverManager.getDriverManager();
	
	protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), Duration.ofMillis(4000));
	
	protected Cart products = new Cart();
    
    protected JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();
    
    protected PageManager pageManager = PageManager.getPageManager();
    
    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }
    
    
    
    
    
    
    
    
    protected WebElement sendKeysToField(WebElement element, String value) {
    	element.click();
    	element.clear();
    	//js.executeScript("$element.val('" + value + "')");
    	//js.executeScript("element.setAttribute('input', value)");
    	element.sendKeys(value);
    	return element;
        
    }
    
    protected Boolean checkPageTitle(WebElement element, String value) {
    	return element.getAttribute("value").contains(value);
        
    }

    protected WebElement waitUntilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitUntilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    protected WebElement scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }
    
    protected WebElement scrollUpThePageJs(WebElement element) {
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
        return element;
    }
    
    protected WebElement clickOnElementJs(WebElement element) {
    	js.executeScript("arguments[0].click();", element);
        return element;
    }
    
	protected WebElement putRequestInSearchField (WebElement element, String value) {
		waitUntilElementToBeClickable(element).click();
		sendKeysToField(element, value);
		return element;
	}
	
}