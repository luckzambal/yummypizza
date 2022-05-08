package yummypizza.repo;

import yummypizza.model.Inventory;
import org.springframework.data.repository.CrudRepository;

/*
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
*/

public interface InventoryRepo extends CrudRepository<Inventory,Integer> {
	/*private Map<Integer,Inventory> inventoryById;
	
	public InventoryRepo() {
		inventoryById = new HashMap<>();
	}
	
	public void save(Inventory inventory) {
		this.inventoryById.put(inventory.getId(), inventory);
	}
	
	public Inventory getById(Integer id) {
		return this.inventoryById.get(id);
	}
	
	public Collection<Inventory> getAll(){
		return this.inventoryById.values();
	}
	
	public int getSize() {
		return this.inventoryById.size();
	}
	*/
	Inventory findByName(String name);
}