package core;

import java.util.DoubleSummaryStatistics;

public class Stock {
	
	private int stockID, quantity;
	private Double price;
	private String itemName;
	private String description;
	
	public Stock(){
		
	}
	
	public Stock(String itemName){
		this.itemName = itemName;
	}
	
	public Stock(int stockID, String itemName, int quantity){
		this.stockID = stockID;
		this.itemName = itemName;
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getStockID() {
		return stockID;
	}

	public void setStockID(int stockID) {
		this.stockID = stockID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
}
