package test.java;
import org.junit.Test;

public class TheTest extends BaseTest {

	@Test
	 public void firstTest() {
		startTest.getHomePage()
		.sendSearchQuery("Пылесос Samsung SC4520")
		.chooseProductInCatalogue()
		.choseTwoYearWarranty()
		.putProductInCart()
		.sendSearchQuery("Detroit")
		.putProductInCart()
		.hitCartButton()
		.checkWarrantyMatch("Пылесос Samsung SC4520");
	}

}

