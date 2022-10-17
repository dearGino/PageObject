package main.java.core.pages;

import java.util.ArrayList;

import main.java.common.DriverManager;

public class Product {

	private String name;
	private String price;
	private int count;
	private boolean warranty12 = false;
	private boolean warranty24 = false;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public boolean getWarranty12() {
		return warranty12;
	}
	public void setWarranty12(boolean warranty12) {
		this.warranty12 = warranty12;
	}
	public boolean getWarranty24() {
		return warranty24;
	}
	public void setWarranty24(boolean warranty24) {
		this.warranty24 = warranty24;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
