package main.java.core.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage  extends BasePage{
	
	protected String searchValue;
	
	@FindBy(xpath = "//div[@class=\"products-list__content\"]/*/*/a[contains(@class, 'catalog-product__name')]")
	private WebElement parentElement;
	
	@FindBy(xpath = "//div[contains(@class, 'header-menu-wrapper')]/*/*/input[contains(@type,'search')]")
	private WebElement searchField;
	
	@FindBy(xpath = "//button[contains(@class,'buy-btn')]")
	private WebElement buyButton;
	
	@FindBy(xpath = "//button[contains(@class,'buy-btn') and contains(.,'В корзине')]")
	private WebElement boughtButton;
	
	@FindBy(xpath = "//a[contains(@class,'catalog-product__name ui-link ui-link_black')]/*")
	private WebElement productName;
		
	@FindBy(xpath = "//div[contains(@class,'product-buy__price_active')]")
	private WebElement priceLabel;
	
	@FindBy(xpath = "//a[contains(@class,'cart-link')]/span[contains(@class,'cart-link__lbl')]")
	private WebElement cartButton;
	
	
	public SearchPage() {
	}

	public SearchPage setSearchWord(String value) {
		this.searchValue = value;
		return this;
	}
	

	public ProductCardPage chooseProductInCatalogue () {
		waitUntilElementToBeVisible(parentElement);
		String elementXpath = "//span[contains(.,'" + searchValue + "')]";
		WebElement element = parentElement.findElement(By.xpath(elementXpath));
		scrollToElementJs(element);
		clickOnElementJs(element);
		return pageManager.getProductCardPage();
	}
	
	public SearchPage putProductInCart (){
		scrollUpThePageJs(buyButton);
		waitUntilElementToBeVisible(buyButton);
		waitUntilElementToBeClickable(buyButton).click();
		products.addProductToCart(getName(),getPrice(),1, false, false);
		waitUntilElementToBeVisible(boughtButton);
		return this;
	}
		
	public String getPrice() {
		waitUntilElementToBeVisible(priceLabel);
		return priceLabel.getText();
		
	}
	
	public String getName() {
		String name = productName.getText();
		return name.split("\\[")[0];
	}
	
	public CartPage hitCartButton () {
		waitUntilElementToBeClickable(cartButton).click();
		return pageManager.getCartPage();
	}
}
