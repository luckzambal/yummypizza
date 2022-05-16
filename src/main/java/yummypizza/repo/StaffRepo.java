package yummypizza.repo;

import yummypizza.model.Staff;

import org.springframework.data.repository.CrudRepository;

public interface StaffRepo extends CrudRepository<Staff,Integer> {
	Staff findTopByIdStaff(Integer idStaff);
}
