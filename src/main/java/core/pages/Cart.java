package main.java.core.pages;

import java.util.ArrayList;

public class Cart {

	private Cart INSTANCE = null;
	private static ArrayList <Product> products = new ArrayList <Product> ();

    public Cart getProductsInCart() {
        if (INSTANCE == null) {
            INSTANCE = new Cart();
        }
        return INSTANCE;
    }
    
    public void addProductToCart(String name, String price, int count, boolean warranty12, boolean warranty24){
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setCount(count);
		product.setWarranty12(warranty12);
		product.setWarranty24(warranty24);
		if (products.contains(product)) {
			//увеличить количество
		} else {
			products.add(product);
		}
		
    }
    
    public void removeProductFromCartByName(String nameValue){
    	for(Product product : products) {
    		if (product.getName().toUpperCase().contains(nameValue.toUpperCase())){
    			products.remove(products.indexOf(product));
    			break;
    		}
    	}
    }
   
    
}
