# Spring boot
* Spring-boot starter
* First setup application.properties
* Mapping Entity (POJO)

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





