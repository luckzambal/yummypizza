package yummypizza.model;

import javax.persistence.*;

@Entity
public class Manager {
	@Id
	private Integer id;
	
	private String managerFirstName;
	
	private String managerLastName;
	
	public String getManagerFirstName() {
		return managerFirstName;
	}
	
	public String getManagerLastName() {
		return managerLastName;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String toString() {
		return "Manager: " + managerFirstName + " " + managerLastName;
	}
}