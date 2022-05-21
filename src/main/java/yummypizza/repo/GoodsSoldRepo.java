package yummypizza.repo;

import yummypizza.model.GoodsSold;
import org.springframework.data.repository.CrudRepository;

public interface GoodsSoldRepo extends CrudRepository<GoodsSold,Integer> {
	
}