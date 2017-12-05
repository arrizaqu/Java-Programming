# Common Issues "Not working" in Java
	1. Change Port
	2. Include JS or CSS files in a JSP page
	3. Could not obtain transaction-synchronized Session for current thread
	4. Spring Datatable library
	5. Filed to lazily initialize a collection of role
	6. Handling Error Page
		* Deployment Descriptor
		* Controller Exception
	7. Error Eclipse code 13 (common)
	
	
## Change Port
	see files list.

## Include JS or CSS files in a JSP page
### Define Resource File Assets on SpringConfiguration
	<mvc:resources mapping="/resources/**" location="/resources/theme-new/" />
### Activate Spring tag library (Base URL): 
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
### example code : 
	<spring:url value="/resources/css/main.css" var="mainCss" />
	<spring:url value="/resources/js/jquery.1.10.2.min.js"  var="jqueryJs" />
	<spring:url value="/resources/js/main.js" var="mainJs" />

	<link href="${mainCss}" rel="stylesheet" />
    <script src="${jqueryJs}"></script>
    <script src="${mainJs}"></script>
	
## Could not obtain transaction-synchronized Session for current thread
	* enable the transaction support 
		* (<tx:annotation-driven> or @EnableTransactionManagement)
		* declare the transactionManager and it should work through the SessionFactory.
	* add @Transactional into your @Repository
	* With @Transactional in your @Repository Spring is able to apply transactional support into your repository.
	
## Spring Datatable library
	See in files list.
	
## Filed to lazily initialize a collection of role
### Solving 1 : OpenEntityManagerInViewFilter
	<filter>
		<filter-name>OpenEntityManagerInViewFilter</filter-name>
		<filter-class>org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>OpenEntityManagerInViewFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
	
### Solving 2 :
	Change or Set FetchType.Eager
	
### Solving 3 : 
#### Maven JSON Dependency : 
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-core</artifactId>
			<version>2.6.3</version>
		</dependency>
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
			<version>2.6.3</version>
		</dependency>   
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-annotations</artifactId>
			<version>2.6.3</version>
		</dependency>   
		<!-- https://mvnrepository.com/artifact/org.codehaus.jackson/jackson-core-asl -->
		<dependency>
			<groupId>org.codehaus.jackson</groupId>
			<artifactId>jackson-core-asl</artifactId>
			<version>1.9.13</version>
		</dependency>
		 <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.datatype/jackson-datatype-hibernate4 -->
		<dependency>
			<groupId>com.fasterxml.jackson.datatype</groupId>
			<artifactId>jackson-datatype-hibernate5</artifactId>
			<version>2.9.2</version>
		</dependency>

#### Download and Include to the library :
	- http://blog.pastelstudios.com/wp-content/uploads/2012/03/spring3-jackson2-src.zip
	- note : change to hibernate 5		

#### Configure Json Message Converter 
	<mvc:annotation-driven>
	  <mvc:message-converters>
	    <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
	      <property name="objectMapper">
	        <bean class="com.pastelstudios.json.HibernateAwareObjectMapper" />
	      </property>
	    </bean> 
	  </mvc:message-converters> 
	</mvc:annotation-driven>
		
#### Example Fetching Data Manually 
	public List<Department> home(){
		List<Department> departments = departmentDao.getAllDept();
		for(Department dept : departments){
			List<Employee> employees = employeeDao.getEmployeesByDepartment(dept);
			dept.setEmployees(employees);
		}
		
		return departments;
	}
	
## Handling Error Page
	* Deployment Descriptor
	* Controller Exception
	
### Deployment Descriptor
	<error-page>
		<error-code>404</error-code>
		<location>/404</location>
	</error-page>
	
### Controller Exception
	@Controller
	public class HttpErrorHandler {

		 @RequestMapping(value="/404")
		 public String error404(){
			 
		  return "error/pagenotfound";
		 }
	}
	
## Error Eclipse code 13 (common)
	...
	--launcher.appendVmargs
	-vm
	C:\Program Files\Java\jre1.8.0_45\bin\javaw.exe
	-vmargs
	...