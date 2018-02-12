package com.arrizsoft.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Department {

	@Id
	private int id;
	@Column(name="name_department")
	private String nameDepartment;
	@Column(name="address_department")
	private String addressDepartment;
	@Column(name="contact_department")
	private String contactDepartment;
	@OneToMany
	private List<Employee> listEmployee;
	
	public List<Employee> getListEmployee() {
		return listEmployee;
	}
	public void setListEmployee(List<Employee> listEmployee) {
		this.listEmployee = listEmployee;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameDepartment() {
		return nameDepartment;
	}
	public void setNameDepartment(String nameDepartment) {
		this.nameDepartment = nameDepartment;
	}
	public String getAddressDepartment() {
		return addressDepartment;
	}
	public void setAddressDepartment(String addressDepartment) {
		this.addressDepartment = addressDepartment;
	}
	public String getContactDepartment() {
		return contactDepartment;
	}
	public void setContactDepartment(String contactDepartment) {
		this.contactDepartment = contactDepartment;
	}
	
	
}
