package yummypizza.repo;

import yummypizza.model.RawIngredients;
import org.springframework.data.repository.CrudRepository;

public interface RawIngredientsRepo extends CrudRepository<RawIngredients, Integer> {
	RawIngredients findByProductNumber(Integer productNumber);
}