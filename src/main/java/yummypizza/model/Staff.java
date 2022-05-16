package yummypizza.model;

import javax.persistence.*;

@Entity
@Table(name = "Staff")
public class Staff {
	@Id
	@Column(name = "idStaff")
	private Integer idStaff;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Phone_number")
	private Integer phoneNumber;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "User_name")
	private String username;
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "Position_id")
	private Integer positionId;
	
	@Column(name = "Active")
	private Boolean active;
	
	@Column(name = "Discount")
	private Double discount;
	
	private Staff() {
		
	}
	
	public Staff(Integer idStaff, String name, Integer phoneNumber, String email, String username, String password, Integer positionId, Boolean active, Double discount) {
		this.idStaff = idStaff;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.username = username;
		this.password = password;
		this.positionId = positionId;
		this.active = active;
		this.discount = discount;
	}
	
	public Integer getIdStaff() {
		return idStaff;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Integer getPositionId() {
		return positionId;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public Double getDiscount() {
		return discount;
	}
}
