package com.arrizsoft.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(
		executionPhase=ExecutionPhase.BEFORE_TEST_METHOD,
		scripts="/data/mysql.sql"
		)
public class MyTestApp {

	@Test
	public void before() {
		System.out.println("execute before");
	}
	
}
