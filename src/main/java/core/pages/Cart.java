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
    
    
    private int getIndexIfProductExists (String nameValue) {
    	int index = -1;
    	try{
    		for(Product product : products) {
    			if (product.getName().toUpperCase().contains(nameValue.toUpperCase())){
    				index = products.indexOf(product);
    				}
    			}
    	}
    	catch (Exception exc){
    		System.out.println("Такого продукта нет в корзине");
    	}
    	return index;
    }
    
    
    //Добавить товар в корзину
    public void addProductToCart(String name, String price, int count, boolean warranty12, boolean warranty24){
		Product product = new Product();
		product.setName(name);
		product.setPrice(price.replaceAll("[^0-9]",""));
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
    	int indexOfProduct = getIndexIfProductExists(nameValue);
    	products.remove(indexOfProduct);
    }
    
    
    public void increaseCountOfProductByName(String nameValue){
    	int indexOfProduct = getIndexIfProductExists(nameValue);
    	int currentCount = products.get(indexOfProduct).getCount();
    	products.get(indexOfProduct).setCount(currentCount+1);
    }
   
    
    public void decreaseCountOfProductByName(String nameValue){
    	int indexOfProduct = getIndexIfProductExists(nameValue);
    	int currentCount = products.get(indexOfProduct).getCount();
    	if(currentCount == 1) {
    		removeProductFromCartByName(nameValue);
    	}
    	else {
    		products.get(indexOfProduct).setCount(currentCount-1);
    	}
    }  	
    
    public String getProductPrice (String nameValue){
    	int indexOfProduct = getIndexIfProductExists(nameValue);
    	return products.get(indexOfProduct).getPrice();
    }
    
    public String getProductName (String nameValue){
    	int indexOfProduct = getIndexIfProductExists(nameValue);
    	return products.get(indexOfProduct).getName();
    }
    
    public boolean getProductWarranty12 (String nameValue){
    	int indexOfProduct = getIndexIfProductExists(nameValue);
    	return products.get(indexOfProduct).getWarranty12();
    }
    
    public boolean getProductWarranty24 (String nameValue){
    	int indexOfProduct = getIndexIfProductExists(nameValue);
    	return products.get(indexOfProduct).getWarranty24();
    }
}
