package yummypizza.model;

import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class Inventory {
	
	@Id
	private Integer id;
	
	private String itemName;
	private Boolean isPerishable;
	private Double itemQuantity;
	private String unit;
	private Double minimumQuantity;
	
	private Inventory () {
		
	}
	
	public Inventory(Integer id, String itemName, Boolean isPerishable, Double itemQuantity, String unit, Double minimumQuantity) {
		this.id = id;
		this.itemName = itemName;
		this.isPerishable = isPerishable;
		this.itemQuantity = itemQuantity;
		this.unit = unit;
		this.minimumQuantity = minimumQuantity;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public Boolean getIsPerishable() {
		return isPerishable;
	}
	
	public Double getItemQuantity() {
		return itemQuantity;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public Double getMinimumQuantity() {
		return minimumQuantity;
	}
	
	public Boolean isLowStock() {
		if(itemQuantity > minimumQuantity)
			return false;
		else
			return true;
	}
	
	public String generateReport() {
		return itemName + ": " + itemQuantity.toString() + " " + unit;
	}
}