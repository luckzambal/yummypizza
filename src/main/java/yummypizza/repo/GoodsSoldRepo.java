package yummypizza.repo;

import yummypizza.model.GoodsSold;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface GoodsSoldRepo extends CrudRepository<GoodsSold,Integer> {
	
	Optional<GoodsSold> findById(Integer orderId);
	
}