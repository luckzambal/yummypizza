package yummypizza.model;

import java.util.Date;
import javax.persistence.*;

@Entity
public class GoodsSold {
	@Id
	private Integer orderId;
	
	private Date date;
	
	private String itemName;
	
	private Double itemQuantity;
	
	/*public GoodsSold(Integer orderId, Date date, String itemName, Double itemQuantity) {
		this.orderId = orderId;
		this.date = date;
		this.itemName = itemName;
		this.itemQuantity = itemQuantity;
	}*/
	
	public Integer getOrderId() {
		return orderId;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public Double getItemQuantity() {
		return itemQuantity;
	}
}