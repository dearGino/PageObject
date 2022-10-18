package main.java.core.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage{
	
	//Родитель для всех товаров в корзине
	@FindBy(xpath = "//div[@class='cart-items__products']")
	private WebElement productsWrap;
	
	//Шаблон имени товаров в корзине
	@FindBy(xpath = "//div[contains(@class,'product-name')]")
	private WebElement productsNameInCart;

	//Стоимость товаров в корзине
	@FindBy(xpath = "//span[contains(@class, 'buttons__link-price cart-link__price')]")
	private WebElement productsPriceInChart;
	
	//Добавочный путь ко кнопке Удалить у каждого товара в корзине
	private String deleteButtonPattern = "//button[@class='menu-control-button remove-button']";
	
	//Добавочный путь к страховке элемента, если есть
	private String warrantyCommonPattern = "//../../../../../../div[contains(@class,'additional-warranties')]/div[@class = 'additional-warranties-row__warranties']/*/div[@class='slider']/*";

	private String warrantyRegular = "//div[contains(@class,'additional-warranties-row__warranty')]";
	
	private String warranty12 = "//div[contains(@class,'additional-warranties-row__hit-warranty additional-warranties-row__warranty')]";
	
	private String warranty24 = "//div[contains(@class,'additional-warranties-row__warranty')]";
	
	
	//Сравнить гарантию товара в корзине и в Cart
	public CartPage checkWarrantyMatch(String productName) {
		WebElement warranty;
		try {
			warranty = getElementByName(productName)
					.findElement(By.xpath(warrantyCommonPattern));
		}
		catch (Exception exp) {
			System.out.println("У элемента не предусмотрена гарантия");
		}
		finally {
			waitUntilElementToBeVisible(productsNameInCart);
			warranty = getElementByName(productName)
					.findElement(By.xpath(warrantyCommonPattern));
		}
		boolean warranty12CartProduct = products.getProductWarranty12(productName);
		boolean warranty24CartProduct = products.getProductWarranty24(productName);
		warranty.findElement(By.xpath(warranty24));
		String addChecked = "/*[contains(@class,'checked')]";
		boolean warranty12PageCart = false;
		if (warranty.findElement(By.xpath(warranty12+addChecked))!=null) {
			warranty12PageCart = true;
		}
		boolean warranty24PageCart = false;
		if (warranty.findElement(By.xpath(warranty24+addChecked))!=null) {
			warranty24PageCart = true;
		}
		Assert.assertTrue("Гарантия не совпадает (см. гарантия + 12 мес.)", warranty12PageCart ==warranty12CartProduct);
		Assert.assertTrue("Гарантия не совпадает (см. гарантия + 24 мес.)", warranty24PageCart ==warranty24CartProduct);
		return this;
	}
	
	
	public CartPage deleteProductByName(String productName) throws InterruptedException {
		WebElement producToDelete;
		try {
			producToDelete = getElementByName(productName)
					.findElement(By.xpath(deleteButtonPattern));
		}
		catch (Exception exp) {
			System.out.println("У элемента не предусмотрена гарантия");
		}
		finally {
			waitUntilElementToBeVisible(productsNameInCart);
			producToDelete = getElementByName(productName)
					.findElement(By.xpath(deleteButtonPattern));
		}
		int cartPriceBefore = convertStringToInt(checkCartPrice());
		int productToDeletePrice = convertStringToInt(products.getProductPrice(productName));
		scrollToElementJs(producToDelete);
		clickOnElementJs(producToDelete);
		scrollUpThePageJs(productsPriceInChart);
		products.removeProductFromCartByName(productName);

		int cartPriceAfter = convertStringToInt(checkCartPrice());
		System.out.println(cartPriceBefore);
		System.out.println(productToDeletePrice);
		System.out.println(cartPriceAfter);
		Assert.assertTrue("Стоимость корзины после удаления товара изменилась неправильно",
				cartPriceAfter == cartPriceBefore-productToDeletePrice);
		return this;
	}
	
	
	private WebElement getElementByName (String name) {
		String elementXpath = "//a[contains(.,'" + name + "')]";
		WebElement element;
		try {
			element = productsWrap.findElement(By.xpath(elementXpath));
			}
		catch (Exception exp) {
			System.out.println("Такого товара нет в корзине");
		}
		finally {
			waitUntilElementToBeClickable(productsNameInCart);
			element = productsWrap.findElement(By.xpath(elementXpath));
		}
		waitUntilElementToBeClickable(element);
		return element;
	}
	
	
	public String checkCartPrice () {
		return productsPriceInChart.getText();
	}
	
	private int convertStringToInt (String stringValue) {
		int intValue = 0;
		try {
			intValue = Integer.parseInt(stringValue.replaceAll("[^0-9]", ""));			}
		catch (NumberFormatException e) {
			   System.out.println("Ошибка конвертации цены из String в Int");
			}
		return intValue;
	}
	
}
