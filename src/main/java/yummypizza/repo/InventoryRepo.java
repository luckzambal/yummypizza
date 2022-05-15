package yummypizza.repo;

import yummypizza.model.Inventory;

import org.springframework.data.repository.CrudRepository;


public interface InventoryRepo extends CrudRepository<Inventory,Integer> {
	Inventory readById(Integer id);
}