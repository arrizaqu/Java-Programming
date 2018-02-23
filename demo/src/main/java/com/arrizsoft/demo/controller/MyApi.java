package com.arrizsoft.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arrizsoft.demo.dao.EmployeeDao;
import com.arrizsoft.demo.entity.Employee;

@RestController
public class MyApi {

	@Autowired
	EmployeeDao employeeDao;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public Page<Employee> index(Pageable pageable) {
		return employeeDao.findAll(pageable);
	}
	
	@GetMapping(value= "/second/{search-name}")
	public Page<Employee> second(Pageable pageable, @PathVariable("search-name") String searchname){
		String name = "masyda arrizaqu";
		return employeeDao.getEmployeeByName(searchname, pageable);
	}
	
	@GetMapping(value="/getemp/{id}")
	public Employee getEmployeeById(@PathVariable("id") int id) {
		Employee employee = employeeDao.getEmployeeById(id);
		return employee;
				
	}
}
