package yummypizza.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "goods_sold")
public class GoodsSold {
	@Id
	@Column(name = "order_id")
	private Integer orderId;
	
	@Column(name = "order_date")
	private Date orderDate;
	
	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "item_quantity")
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