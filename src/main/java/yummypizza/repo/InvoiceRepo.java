package yummypizza.repo;

import yummypizza.model.Invoice;
import org.springframework.data.repository.CrudRepository;

//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;


public interface InvoiceRepo extends CrudRepository<Invoice,String> {
//	private Map<String,Invoice> invoiceById;
//	
//	public InvoiceRepo() {
//		invoiceById = new HashMap<>();
//	}
//	
//	public void save(Invoice invoice) {
//		this.invoiceById.put(invoice.getInvoiceNumber(), invoice);
//	}
//	
//	public Invoice getById(String id) {
//		return this.invoiceById.get(id);
//	}
//	
//	public Collection<Invoice> getAll() {
//		return this.invoiceById.values();
//	}
//	
//	public int getSize() {
//		return this.invoiceById.size();
//	}
	Invoice findByName(String name);
}