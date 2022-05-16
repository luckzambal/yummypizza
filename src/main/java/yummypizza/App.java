package yummypizza;

import yummypizza.model.*;
import yummypizza.repo.*;
import yummypizza.gui.EnterLogin;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class App implements InitializingBean {
	
	@Autowired
	private GoodsSoldRepo goodsSold;
	
	@Autowired
	private InventoryRepo inventory;
	
	@Autowired
	private InvoiceRepo invoice;
	
	@Autowired
	private ProductInInvoiceRepo productInInvoice;
	
	@Autowired
	private RawIngredientsRepo rawIngredients;
	
	@Autowired
	private StaffRepo staffs;
	
	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(App.class);
		builder.headless(false).run(args);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
//		ShowInventory gui = new ShowInventory(this.inventory, this.rawIngredients, this.invoice, this.productInInvoice);
		EnterLogin gui = new EnterLogin(this.staffs, this.inventory, this.rawIngredients, this.invoice, this.productInInvoice);
		gui.pack();
		gui.setVisible(true);
	}
}