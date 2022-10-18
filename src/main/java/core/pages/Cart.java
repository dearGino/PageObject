package main.java.core.pages;

import java.util.ArrayList;

public class Cart {

	//Корзина
	private Cart INSTANCE = null;
	//Список продуктов в корзине
	private static ArrayList <Product> products = new ArrayList <Product> ();

	//Реализация единственной корзины
    public Cart getProductsInCart() {
        if (INSTANCE == null) {
            INSTANCE = new Cart();
        }
        return INSTANCE;
    }
    
    
    private int getProductIfExist (String nameValue) {
    	try{
    		for(Product product : products) {
    			if (product.getName().toUpperCase().contains(nameValue.toUpperCase())){
    				return products.indexOf(product);
    				}
    			}
    	}
    	catch (Exception exc){
    		System.out.println("Такого продукта нет в корзине");
    	}
    	return -1;
    }
    
    
    //Добавить товар в корзину
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
    
    //Убрать товар из корзины полностью ????всё или количество уменьшить?
    public void removeProductFromCartByName(String nameValue){
    	int x = getProductIfExist(nameValue);
    	products.remove(products.indexOf(x));
    }
    
    
    public void increaseCountOfProductByName(String nameValue){
    	int x = getProductIfExist(nameValue);
    	int currentCount = products.get(x).getCount();
    	products.get(x).setCount(currentCount+1);
    }
   
    
    public void decreaseCountOfProductByName(String nameValue){
    	int x = getProductIfExist(nameValue);
    	int currentCount = products.get(x).getCount();
    	if(currentCount == 1) {
    		removeProductFromCartByName(nameValue);
    	}
    	else {
    		products.get(x).setCount(currentCount-1);
    	}
    }
            	
    
    public String getProductPrice (String nameValue){
    	int x = getProductIfExist(nameValue);
    	return products.get(x).getPrice();
    }
    
    public String getProductName (String nameValue){
    	int x = getProductIfExist(nameValue);
    	return products.get(x).getName();
    }
    
    public boolean getProductWarranty12 (String nameValue){
    	int x = getProductIfExist(nameValue);
    	return products.get(x).getWarranty12();
    }
    
    public boolean getProductWarranty24 (String nameValue){
    	int x = getProductIfExist(nameValue);
    	return products.get(x).getWarranty24();
    }
}
