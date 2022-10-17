package main.java.core.pages;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {

	@FindBy(xpath = "//div[contains(@class, 'header-menu-wrapper')]/*/*/input[contains(@type,'search')]")
	private WebElement searchField;
	
	@FindBy(xpath = "//div[contains(@class, 'header-menu')]/*/*/*/span[contains(@class, 'icon_search')]")
	private WebElement searchButton;
	
	@FindBy(xpath = "//title[contains(.,'DNS – интернет магазин цифровой и бытовой техники')]")
	private WebElement title;
	
	
	
	// Проверить заголовок домашней страницы
	public Boolean checkHomePageTitle(String value) {
		return checkPageTitle(title, value);
	}

	// Вставить поисковой запрос в поисковое поле
	public SearchPage sendSearchQuery (String value) {
		putRequestInSearchField(searchField, value);
		waitUntilElementToBeClickable(searchButton).click();
		return pageManager.getSearchPage().setSearchWord(value);
	}
	
	//Нажать кнопку поиска	
	public SearchPage hitSearchButton () {
		waitUntilElementToBeClickable(searchButton).click();
		return pageManager.getSearchPage();
	}

	
}
