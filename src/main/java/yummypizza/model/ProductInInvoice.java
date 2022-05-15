package yummypizza.model;

import javax.persistence.*;

@Entity
public class ProductInInvoice {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String invoiceNumber;
	
	private Integer productNumber;
	
	private Double quantity;
	
	private Double subtotalCost;
	
	private ProductInInvoice() {
		
	}
	
	public ProductInInvoice(String invoiceNumber, Integer productNumber, Double quantity, Double subtotalCost) {
		this.invoiceNumber = invoiceNumber;
		this.productNumber = productNumber;
		this.quantity = quantity;
		this.subtotalCost = subtotalCost;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	
	public Integer getProductNumber() {
		return productNumber;
	}
	
	public Double getQuantity() {
		return quantity;
	}
	
	public Double getSubtotalCost() {
		return subtotalCost;
	}
}