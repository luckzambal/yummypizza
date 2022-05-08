package yummypizza.repo;

import yummypizza.model.Manager;
import org.springframework.data.repository.CrudRepository;

//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;

public interface ManagerRepo extends CrudRepository<Manager,Integer> {
//	private Map<Integer,Manager> managerById;
//	
//	public ManagerRepo() {
//		managerById = new HashMap<>();
//	}
//	
//	public void save(Manager manager) {
//		this.managerById.put(manager.getId(), manager);
//	}
//	
//	public Manager getById(Integer id) {
//		return this.managerById.get(id);
//	}
//	
//	public Collection<Manager> getAll() {
//		return this.managerById.values();
//	}
//	
//	public int getSize() {
//		return this.managerById.size();
//	}
	Manager findByName(String name);
}
