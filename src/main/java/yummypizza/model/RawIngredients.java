package yummypizza.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RawIngredients {
	
	@Id
	private Integer productNumber;
	
	private String productName;
	
	private String unit;
	
	private Double productCost;
	
	private RawIngredients() {
		
	}
	
	public RawIngredients(Integer productNumber, String productName, String unit, Double productCost) {
		this.productNumber = productNumber;
		this.productName = productName;
		this.unit = unit;
		this.productCost = productCost;
	}
	
	public Integer getProductNumber() {
		return productNumber;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public Double getProductCost() {
		return productCost;
	}
	
	public String toString() {
		return productNumber.toString() + " " + productName + ": $" + productCost.toString() + "/" + unit;
	}
}