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
	
	/*@Test
	public void initSave() {
		Employee employee = new Employee();
		employee.setId(2);
		employee.setEmail("arrizaqu2@yahoo.com");
		employee.setDate(new Date());
		employee.setName("masyda arrizaqu 2");
		employeeDao.save(employee);
	}*/
	
	@Test
	public void deleteEmployee() {
		Employee emp = new Employee();
		emp.setId(2);
		
		employeeDao.delete(emp);
	}
}
