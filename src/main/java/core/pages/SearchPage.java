package main.java.core.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage  extends BasePage{
	
	//Искомый запрос
	private String searchValue;
	
	//Элемент - всех результатов поиска
	@FindBy(xpath = "//div[@class=\"products-list__content\"]/*/*/a[contains(@class, 'catalog-product__name')]")
	private WebElement parentElement;
	
	//Поисковое поле
	@FindBy(xpath = "//div[contains(@class, 'header-menu-wrapper')]/*/*/input[contains(@type,'search')]")
	private WebElement searchField;
	
	//Кнопка "Купить"
	@FindBy(xpath = "//button[contains(@class,'buy-btn')]")
	private WebElement buyButton;
	
	//Кнопка "В корзине" (Измененённая кнопка "Купить")
	@FindBy(xpath = "//button[contains(@class,'buy-btn') and contains(.,'В корзине')]")
	private WebElement boughtButton;
	
	//Название товара
	@FindBy(xpath = "//a[contains(@class,'catalog-product__name ui-link ui-link_black')]/*")
	private WebElement productName;
		
	//Цена товара
	@FindBy(xpath = "//div[contains(@class,'product-buy__price_active')]")
	private WebElement priceLabel;
	
	@FindBy(xpath = "//div[contains(@class,'product-buy__price_active')]/span[@class='product-buy__prev']")
	private WebElement priceLabel2;
	
	//Кнопка "Корзина"
	@FindBy(xpath = "//a[contains(@class,'cart-link')]/span[contains(@class,'cart-link__lbl')]")
	private WebElement cartButton;
	
	
	public SearchPage() {
	}

	//Передает странице искомый запрос
	//Запрос не сохраняется в поле поиска => оттуда его не извлечь
	public SearchPage setSearchWord(String value) {
		this.searchValue = value;
		return this;
	}
	
	// Нажать на товар в результатах поиска
	//Открывает страницу карточки товара
	public ProductCardPage chooseProductInCatalogue () {
		waitUntilElementToBeVisible(parentElement);
		String elementXpath = "//span[contains(.,'" + searchValue + "')]";
		WebElement element = parentElement.findElement(By.xpath(elementXpath));
		scrollToElementJs(element);
		clickOnElementJs(element);
		return pageManager.getProductCardPage();
	}
	
	//Добавить товар в корзину прямо из результатов поиска
	public SearchPage putProductInCart (){
		scrollUpThePageJs(buyButton);
		waitUntilElementToBeVisible(buyButton);
		waitUntilElementToBeClickable(buyButton).click();
		products.addProductToCart(getName(),getPrice(),1, false, false);
		waitUntilElementToBeVisible(boughtButton);
		return this;
	}
	
	//Возвращает цену товара
	private String getPrice() {
		waitUntilElementToBeVisible(priceLabel);
		String wholePrice = priceLabel.getText().replaceAll("[^0-9]","");
		String oldPrice = priceLabel2.getText().replaceAll("[^0-9]","");
		return wholePrice.replace(oldPrice, "");
	}
	
	//Возвращает имя товара
	private String getName() {
		String name = productName.getText();
		return name.split("\\[")[0];
	}
	
	//Перейти в корзину
	public CartPage hitCartButton () {
		waitUntilElementToBeClickable(cartButton).click();
		return pageManager.getCartPage();
	}
}
