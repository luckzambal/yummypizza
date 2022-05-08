package yummypizza.repo;

import yummypizza.model.GoodsSold;
import org.springframework.data.repository.CrudRepository;

/*
import java.util.Collection;
import java.util.HashMap; 
import java.util.Map;
*/

public interface GoodsSoldRepo extends CrudRepository<GoodsSold,Integer> {
	/*
	private Map<Integer,GoodsSold> goodsSoldById;
	
	public GoodsSoldRepo() {
		goodsSoldById = new HashMap<>();
	}
	
	public void save(GoodsSold goodsSold) {
		this.goodsSoldById.put(goodsSold.getOrderId(), goodsSold);
	}
	
	public GoodsSold getByOrderId(Integer id) {
		return this.goodsSoldById.get(id);
	}
	
	public Collection<GoodsSold> getAll() {
		return this.goodsSoldById.values();
	}
	
	public int getSize() {
		return this.goodsSoldById.size();
	}
	*/
	GoodsSold findByName(String name);
}