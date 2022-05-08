package yummypizza.model;

import java.util.Date;
import javax.persistence.*;
//import yummypizza.model.Inventory;

@Entity
public class Invoice {
	@Id
	private String invoiceNumber;
	
	private Date date;
	
	private String itemName;
	
	private Double itemQuantity;
	
	private Double total;
	
	private String staffName;
	
	private Boolean isPaid;
	
	public String getInvoiceNumber() {
		return invoiceNumber;
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
	
	public Double getTotal() {
		return total;
	}
	
	public String getStaffName() {
		return staffName;
	}
	
	public Boolean getIsPaid() {
		return isPaid;
	}
}
