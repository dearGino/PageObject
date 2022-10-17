package main.java.common;

import main.java.core.pages.CartPage;
import main.java.core.pages.HomePage;
import main.java.core.pages.ProductCardPage;
import main.java.core.pages.SearchPage;

public class PageManager {

	private static PageManager pageManager;


    private HomePage homePage;


    private CartPage cartPage;


    private ProductCardPage productCardPage;


    private SearchPage searchPage;


    private PageManager() {
    }

    
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }


    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }


    public CartPage getInsurancePage() {
        if (cartPage == null) {
        	cartPage = new CartPage();
        }
        return cartPage;
    }


    public ProductCardPage getProductCardPage() {
        if (productCardPage == null) {
        	productCardPage = new ProductCardPage();
        }
        return productCardPage;
    }


    public SearchPage getSearchPage() {
        if (searchPage == null) {
        	searchPage = new SearchPage();
        }
        return searchPage;
    }
	
    public CartPage getCartPage() {
        if (cartPage == null) {
        	cartPage = new CartPage();
        }
        return cartPage;
    }
}
