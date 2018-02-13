package com.arrizsoft.demo.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.arrizsoft.demo.entity.Employee;

public interface EmployeeDao extends PagingAndSortingRepository<Employee, Long> {

}
