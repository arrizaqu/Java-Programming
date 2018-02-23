package com.arrizsoft.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.arrizsoft.demo.entity.Employee;

public interface EmployeeDao extends PagingAndSortingRepository<Employee, Long> {

	@Query(value="from Employee")
	public Page<Employee> getAllEmployee(Pageable pageable);
	
	//search by name
	@Query("from Employee emp where emp.name like %:thename%")
	public Page<Employee> getEmployeeByName(@Param("thename") String name, Pageable pageable);
	
	@Query("from Employee emp where emp.id = :id")
	public Employee getEmployeeById(@Param("id") long id);

}
