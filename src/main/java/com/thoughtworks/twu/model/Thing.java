package com.thoughtworks.twu.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "things")
public class Thing implements Serializable {

	private static final long serialVersionUID = -4060739788760795254L;

	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

    //TODO: need to make price numeric
	@DecimalMax("99999")
    @NotNull
	private String price;

	@NotNull
	private String description;
	

	/* Getters and setters below this row */

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
