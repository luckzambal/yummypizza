package yummypizza.model;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Invoice {
	@Id
	private String invoiceNumber;
	
	private Date transactionDate;
	
	private Double total;
	
	private Integer staffId;
	
	private Boolean isPaid;
	
	private Invoice() {
		
	}
	
	public Invoice(String invoiceNumber, Date transactionDate, Double total, Integer staffId, Boolean isPaid) {
		this.invoiceNumber = invoiceNumber;
		this.transactionDate = transactionDate;
		this.total = total;
		this.staffId = staffId;
		this.isPaid = isPaid;
	}
	
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	
	public Date getDate() {
		return transactionDate;
	}
	
	public Double getTotal() {
		return total;
	}
	
	public Integer getStaffId() {
		return staffId;
	}
	
	public Boolean getIsPaid() {
		return isPaid;
	}
}
