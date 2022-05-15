package yummypizza.model;

import java.util.Date;
import javax.persistence.*;

@Entity
public class GoodsSold {
	@Id
	private Integer orderId;
	
	private Date orderDate;
	
	private String itemName;
	
	private Double itemQuantity;
	
	private GoodsSold() {
		
	}
	
	public Integer getOrderId() {
		return orderId;
	}
	
	public Date getDate() {
		return orderDate;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public Double getItemQuantity() {
		return itemQuantity;
	}
	
	public GoodsSold(Integer orderId, Date orderDate, String itemName, Double itemQuantity) {
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.itemName = itemName;
		this.itemQuantity = itemQuantity;
	}
}