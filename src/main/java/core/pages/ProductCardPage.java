package main.java.core.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductCardPage extends BasePage {

	
	@FindBy(xpath = "//div[contains(@class,'product-buy')]/*[contains(.,'Купить')]")
	private WebElement buyButton;
	
	@FindBy(xpath = "//a[contains(@class,'cart-link')]/span[contains(@class,'cart-link__badge')]")
	private WebElement cartBadgeCount;
	
	@FindBy(xpath = "//a[contains(@class,'cart-link')]/span[contains(@class,'cart-link__lbl')]")
	private WebElement cartButton;
	
	//меняются элементы обрати внимание
	@FindBy(xpath = "//div[contains(@class,'product-buy__price_active')]")
	private WebElement priceLabel;
	
	@FindBy(xpath = "//div[contains(@class,'additional-sales-tabs__title')]/div[2]")
	private WebElement warrantyButton;
	
	@FindBy(xpath = "//label[contains(@class,'product-warranty__item_hit')]")
	private WebElement warrantyOptionTwoYears;
	
	@FindBy(xpath = "//h1[@class]")
	private WebElement productName;
	
	@FindBy(xpath = "//div[contains(@class, 'header-menu-wrapper')]/*/*/input[contains(@type,'search')]")
	private WebElement searchField;
	
	@FindBy(xpath = "//div[contains(@class, 'header-menu')]/*/*/*/span[contains(@class, 'icon_search')]")
	private WebElement searchButton;
	
	@FindBy(xpath = "//div[contains(@class,'product-card-top__code')]")
	private WebElement productCode;
	
	@FindBy(xpath = "//div[contains(@class,'additional-sales-tabs__title_active')]")
	private WebElement warrantyChosen;
	
	
	//Добавить продукт в корзину
	//
	public ProductCardPage putProductInCart () {
		scrollUpThePageJs(buyButton);
		waitUntilElementToBeVisible(buyButton);
		waitUntilElementToBeClickable(buyButton).click();
		products.addProductToCart(productName.getText(),getPrice(),1, is12warranty(), is24warranty());
		return this;
	}
	
		
	//Выбрать гарантию на 2 года (доп гарантия +12 месяцев)
	//
	public ProductCardPage choseTwoYearWarranty () {
		String beforePrice = priceLabel.getText();
		waitUntilElementToBeVisible(warrantyButton).click();
		scrollToElementJs(warrantyButton);
		clickOnElementJs(warrantyButton);
		scrollToElementJs(warrantyOptionTwoYears);
		waitUntilElementToBeClickable(warrantyOptionTwoYears);
		clickOnElementJs(warrantyOptionTwoYears);
		scrollToElementJs(priceLabel);
		waitUntilElementToBeVisible(priceLabel);
		String afterPrice = priceLabel.getText();
		Assert.assertFalse("После выбора гарантии цена не изменилась"
				,beforePrice == afterPrice);
		return this;
	}

	
	public CartPage clickOnCart () {
		waitUntilElementToBeClickable(cartButton).click();
		return pageManager.getCartPage();
	}
	
	
	// Вставить поисковой запрос в поисковое поле
	public SearchPage sendSearchQuery (String value) {
		putRequestInSearchField(searchField, value);
		waitUntilElementToBeClickable(searchButton).click();
		return pageManager.getSearchPage().setSearchWord(value);
	}
	
	public String getPrice() {
		waitUntilElementToBeVisible(priceLabel);
		return priceLabel.getText();
		
	}
		
	public boolean is12warranty () {
		try {
			String warranty = warrantyChosen.getAttribute("value");
			if (warranty == "Гарантия: 12 мес. " || warranty == "Гарантия: 36 мес.")
				return false;
			else {
				return true;
			}
		}
		catch (Exception exc) {
		}
		return false;
		
	}
	
	public boolean is24warranty () {
		try {
			String warranty = warrantyChosen.getAttribute("value");
			if (warranty == "Гарантия: 12 мес. " || warranty == "Гарантия: 24 мес.")
				return false;
			else {
				return true;
			}
		}
		catch (Exception exc) {
		}
		return false;

	}
}
