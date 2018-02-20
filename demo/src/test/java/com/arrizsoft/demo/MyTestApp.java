package com.arrizsoft.demo;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;

import com.arrizsoft.demo.dao.EmployeeDao;
import com.arrizsoft.demo.entity.Employee;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(
		executionPhase=ExecutionPhase.BEFORE_TEST_METHOD,
		scripts="/data/mysql.sql"
		)
public class MyTestApp {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void before() {
		Employee emp = this.employeeDao.findOne((long)105);
		List<Employee> listEmployee = (List<Employee>) employeeDao.findAll();
		
		Assert.assertNotNull(emp);
		Assert.assertEquals("arrizaqu@yahoo.com", emp.getEmail());
		
	}

	@After
	public void after() {
		Connection connection = null;
		String sql = "delete from employee";
		try {
			connection = this.dataSource.getConnection();
		//	connection.createStatement().executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
