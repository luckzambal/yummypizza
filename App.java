package yummypizza;

import yummypizza.model.*;
import yummypizza.repo.*;
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
	private ManagerRepo manager;

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(App.class);
		builder.headless(false).run(args);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
}