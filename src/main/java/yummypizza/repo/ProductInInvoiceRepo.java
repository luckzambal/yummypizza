package yummypizza.repo;

import yummypizza.model.ProductInInvoice;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductInInvoiceRepo extends CrudRepository<ProductInInvoice, Integer> {
	List<ProductInInvoice> findByProductNumber(Integer productNumber);
	
	List<ProductInInvoice> findByInvoiceNumber(String invoiceNumber);
	
	ProductInInvoice findByProductNumberAndInvoiceNumber(Integer productNumber, String invoiceNumber);
}