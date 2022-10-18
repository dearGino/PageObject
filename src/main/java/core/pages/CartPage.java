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

	//Добавочный путь к страховке элемента, если есть
	private String warrantyCommonWraper = "//../../../../../../div[contains(@class,'additional-warranties')]/div[@class = 'additional-warranties-row__warranties']/*/div[@class='slider']/*";

	//@FindBy(xpath = "//div[contains(@class,'additional-warranties-row__warranty')]/*")
	private String warrantyRegular = "//div[contains(@class,'additional-warranties-row__warranty')]";
	
	//@FindBy(xpath = "//div[contains(@class,'additional-warranties-row__hit-warranty additional-warranties-row__warranty')]/*")
	private String warranty12 = "//div[contains(@class,'additional-warranties-row__hit-warranty additional-warranties-row__warranty')]";
	
	//@FindBy(xpath = "//div[contains(@class,'additional-warranties-row__warranty')]/*")
	private String warranty24 = "//div[contains(@class,'additional-warranties-row__warranty')]";
	
	
	//Сравнить гарантию товара в корзине и в Cart
	public CartPage checkWarrantyMatch(String productName) {
		WebElement warranty;
		try {
			warranty = getElementByName(productName)
					.findElement(By.xpath(warrantyCommonWraper));
		}
		catch (Exception exp) {
			System.out.println("У элемента не предусмотрена гарантия");
		}
		finally {
			waitUntilElementToBeVisible(productsNameInCart);
			warranty = getElementByName(productName)
					.findElement(By.xpath(warrantyCommonWraper));
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
	
	private WebElement getElementByName (String name) {
		String elementXpath = "/*[contains(.,'" + name + "')]";
		WebElement element;
		try {
			element = productsNameInCart.findElement(By.xpath(elementXpath));
			}
		catch (Exception exp) {
			System.out.println("Такого товара нет в корзине");
		}
		finally {
			waitUntilElementToBeClickable(productsNameInCart);
			element = productsNameInCart.findElement(By.xpath(elementXpath));
		}
		waitUntilElementToBeClickable(element);
		return element;
	}

	
}
//название товара
//a[contains(@class, 'base-ui-link base-ui-link_gray_dark')]

//div[contains(@class,'product-name')]/*
//название товара

// доп гарантия внутри где-то 
//div[contains(@class,'cart-items__additionals')]/*