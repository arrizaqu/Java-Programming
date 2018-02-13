# Spring boot
* Spring-boot starter
* First setup application.properties
* Mapping Entity (POJO)
* Dao
	* extends PagingAndSortingRepository
	* run unit test
* Connection with Datasource
* @Sql Annotation
	
## Spring-boot starter
* go to : https://start.spring.io/
* simple package
	* web 
	* JPA
	* Mysql
  
## first setup application.properties
```xml
spring.datasource.url=jdbc:mysql://localhost:3306/springboot
spring.datasource.username=root
spring.datasource.password=
spring.jpa.generate-ddl=true 
```

## Mapping Entity (POJO)
```java
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="name", nullable=false)
	private String name;
	private String address;
	private String email;
	private Date date;

	//setter and getter
}
```

## Dao 
* extends PagingAndSortingRepository
* run unit testing

#### extends PagingAndSortingRepository
```java
package com.arrizsoft.demo.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.arrizsoft.demo.entity.Employee;

public interface EmployeeDao extends PagingAndSortingRepository<Employee, Integer> {

}
```

#### run junit testing
##### Saving
```java
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
		employee.setEmail("arrizaqu@yahoo.com");
		employee.setDate(new Date());
		employee.setName("masyda arrizaqu");
		employeeDao.save(employee);
	}
}
```

##### Update
```java
@Test
public void initSave() {
	Employee employee = new Employee();
	employee.setId(1);
	employee.setEmail("arrizaqu@yahoo.com");
	employee.setDate(new Date());
	employee.setName("masyda arrizaqu");
	employeeDao.save(employee);
}
```

##### Delete 
```java
@Test
public void deleteEmployee() {
	Employee emp = new Employee();
	emp.setId(2);
	employeeDao.delete(emp);
}
```

## Connection with Datasource
#### Define DataSource 
```java
import javax.sql.DataSource;

@Autowired
private DataSource dataSource;
```

#### example
```java
try {
	Connection connection = dataSource.getConnection();
	String sql = "select count(*) as jumlah from employee";	
	ResultSet hasilQuery = connection.createStatement().executeQuery(sql);
}catch(Exception e) {}
```

## @Sql Annotation
#### create SQL file 
```sql
create sql file : (ex : src/test/resources/data/mysql.sql)

insert into employee(id, name, address) values (1, "masyda arrizaqu", "seputih banyak");
insert into employee(id, name, address) values (2, "sri wahyuni", "jakarta timur");
```

#### Example
```java
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
```