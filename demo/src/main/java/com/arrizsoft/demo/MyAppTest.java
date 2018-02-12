package com.arrizsoft.demo;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.arrizsoft.demo.dao.EmployeeDao;
import com.arrizsoft.demo.entity.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyAppTest {

	@Autowired
	EmployeeDao employeeDao;
	
	@Test
	public void initSave() {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setEmail("arrizaquUpdate@yahoo.com");
		employee.setDate(new Date());
		employee.setName("masyda arrizaqu");
		employeeDao.save(employee);
	}
}
