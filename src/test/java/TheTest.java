package test.java;
import org.junit.Test;

public class TheTest extends BaseTest {

	@Test
	 public void firstTest() {
		startTest.getHomePage()
		.sendSearchQuery("PlayStation 5")
		.chooseProductInCatalogue()
		.choseTwoYearWarranty()
		.putProductInCart()
		.sendSearchQuery("Detroit")
		.putProductInCart()
		.hitCartButton();
	}

}

