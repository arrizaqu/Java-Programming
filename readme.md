###JAVA SERVLET AND SPRING WITH MAVEN

##DEVELOPMENT TOOLS
	- ECLIPSE MARS 2

##DEFINITION
	- CREATE WEB PROJECT WITH MAVEN
	- JSP DETECTION
	- RUN AND COMPILE MAVEN PROJECT 
	- SPRING DEPENDENCY
	- ADD JAVA WEB RESOURCES IF NOT EXIST
    - SPRING DISPATCHER AND CONTROLLER
		- DEPLOYMENT-DESCRIPTOR : WEB.XML
		- DISPATHCER-SERVLET.XML
		- CREATE SPRING CONTROLLER CLASS
	- VIEWRESOLVER JSP FILE 

##SCRIPT CODE
##CREATE WEB PROJECT WITH MAVEN
	- File new -> Other -> Maven -> "MAVEN PROJECT" -> NEXT
	- Choose artifact id => maven web 
	- Create Information about project in the dialog
	
##JSP DETECTION
	-> Right click on Project -> Properties
	-> Choose Project Facets -> Runtimes -> and select Apache Tomcat

##RUN AND COMPILE MAVEN PROJECT
	- Add Jetty plugin on POM.XML 
		- Code : 
			<plugins>
				<plugin>
				  <groupId>org.eclipse.jetty</groupId>
				  <artifactId>jetty-maven-plugin</artifactId>
				  <version>9.1.3.v20140225</version>
				</plugin>
				<plugin>
					<groupId>org.apache.maven.plugins</groupId>
					<artifactId>maven-compiler-plugin</artifactId>
					<version>3.1</version>
					<configuration>
						<source>1.7</source>
						<target>1.7</target>
					</configuration>
				</plugin>
			</plugins>
	- BUILD : mvn jetty:run
	- BROWSER : localhost:8080/index.jsp

##SPRING DEPENDENCY
	- Code : 
	<properties>
		<org.springframework.version>4.2.6.RELEASE</org.springframework.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>3.8.1</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-core</artifactId>
			<version>${org.springframework.version}</version>
		</dependency>
		<!--
			Expression Language (depends on spring-core)
			Define this if you use Spring Expression APIs (org.springframework.expression.*)
		-->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-expression</artifactId>
			<version>${org.springframework.version}</version>
		</dependency>
		<!-- 
			Bean Factory and JavaBeans utilities (depends on spring-core)
			Define this if you use Spring Bean APIs (org.springframework.beans.*) 
		-->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-beans</artifactId>
			<version>${org.springframework.version}</version>
		</dependency>
		
		<!--
			Aspect Oriented Programming (AOP) Framework (depends on spring-core, spring-beans)
			Define this if you use Spring AOP APIs (org.springframework.aop.*)
		-->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-aop</artifactId>
			<version>${org.springframework.version}</version>
		</dependency>
		
		<!--
			Application Context (depends on spring-core, spring-expression, spring-aop, spring-beans) 
			This is the central artifact for Spring's Dependency Injection Container and is generally always defined
		-->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>${org.springframework.version}</version>
		</dependency>
		
		<!--
			Various Application Context utilities, including EhCache, JavaMail, Quartz, and Freemarker integration
			Define this if you need any of these integrations
		-->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context-support</artifactId>
			<version>${org.springframework.version}</version>
		</dependency>
		
		<!--
			Transaction Management Abstraction (depends on spring-core, spring-beans, spring-aop, spring-context)
			Define this if you use Spring Transactions or DAO Exception Hierarchy
			(org.springframework.transaction.*/org.springframework.dao.*)
		-->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-tx</artifactId>
			<version>${org.springframework.version}</version>
		</dependency>
		
		<!--
			JDBC Data Access Library (depends on spring-core, spring-beans, spring-context, spring-tx)
			Define this if you use Spring's JdbcTemplate API (org.springframework.jdbc.*)
		-->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>${org.springframework.version}</version>
		</dependency>
		
		<!--
			Object-to-Relation-Mapping (ORM) integration with Hibernate, JPA, and iBatis.
			(depends on spring-core, spring-beans, spring-context, spring-tx)
			Define this if you need ORM (org.springframework.orm.*)
		-->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-orm</artifactId>
			<version>${org.springframework.version}</version>
		</dependency>
		
		<!--
			Object-to-XML Mapping (OXM) abstraction and integration with JAXB, JiBX, Castor, XStream, and XML Beans.
			(depends on spring-core, spring-beans, spring-context)
			Define this if you need OXM (org.springframework.oxm.*)
		-->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-oxm</artifactId>
			<version>${org.springframework.version}</version>
		</dependency>
		
		<!--
			Web application development utilities applicable to both Servlet and Portlet Environments
			(depends on spring-core, spring-beans, spring-context)
			Define this if you use Spring MVC, or wish to use Struts, JSF, or another web framework with Spring (org.springframework.web.*)
		-->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-web</artifactId>
			<version>${org.springframework.version}</version>
		</dependency>
		
		<!--
			Spring MVC for Servlet Environments (depends on spring-core, spring-beans, spring-context, spring-web)
			Define this if you use Spring MVC with a Servlet Container such as Apache Tomcat (org.springframework.web.servlet.*)
		-->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>${org.springframework.version}</version>
		</dependency>
		
		<!--
			Spring MVC for Portlet Environments (depends on spring-core, spring-beans, spring-context, spring-web)
			Define this if you use Spring MVC with a Portlet Container (org.springframework.web.portlet.*)
		-->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc-portlet</artifactId>
			<version>${org.springframework.version}</version>
		</dependency>
		
		<!--
			Support for testing Spring applications with tools such as JUnit and TestNG
			This artifact is generally always defined with a 'test' scope for the integration testing framework and unit testing stubs
		-->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-test</artifactId>
			<version>${org.springframework.version}</version>
			<scope>test</scope>
		</dependency>
		
		<dependency>
		  	<groupId>org.hibernate</groupId>
		  	<artifactId>hibernate-entitymanager</artifactId>
		  	<version>5.1.0.Final</version>
		</dependency>  
		<dependency>
		    <groupId>org.springframework.data</groupId>
		    <artifactId>spring-data-jpa</artifactId>
		    <version>1.10.1.RELEASE</version>
			</dependency>
		<dependency>
			<groupId>mysql</groupId>
		   	<artifactId>mysql-connector-java</artifactId>
		   	<version>5.1.35</version>
		</dependency>
	 <!-- https://mvnrepository.com/artifact/commons-dbcp/commons-dbcp -->
		<dependency>
		    <groupId>commons-dbcp</groupId>
		    <artifactId>commons-dbcp</artifactId>
		    <version>1.2.2</version>
		</dependency>
	  	<dependency>
		  	<groupId>javax.servlet</groupId>
		  	<artifactId>javax.servlet-api</artifactId>
		  	<version>3.0.1</version>
		  	<scope>provided</scope>
		</dependency>
	  
		<dependency>
			<groupId>javax.servlet.jsp</groupId>
			<artifactId>javax.servlet.jsp-api</artifactId>
			<version>2.2.1</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>jstl</groupId>
			<artifactId>jstl</artifactId>
			<version>1.2</version>
		</dependency>
		<dependency>
			<groupId>taglibs</groupId>
			<artifactId>standard</artifactId>
			<version>1.1.2</version>
		</dependency>
			<dependency>
			<groupId>com.oracle</groupId>
			<artifactId>ojdbc6</artifactId>
			<version>11.2.0</version>
		</dependency>
		<dependency>
	        <groupId>com.fasterxml.jackson.core</groupId>
	        <artifactId>jackson-core</artifactId>
	        <version>2.4.3</version>
	    </dependency>
	    <dependency>
	        <groupId>com.fasterxml.jackson.core</groupId>
	        <artifactId>jackson-databind</artifactId>
	        <version>2.4.3</version>
	    </dependency>
  </dependencies>
  
##ADD JAVA PACKAGE WEB RESOURCES IF NOT EXIST
	- Containing Java Code and Package
	- Add java folder : src/main/java
	- Add java Package under src/main/java like : om.soft.controller
	
##SPRING DISPATCHER AND CONTROLLER
	- DEPLOYMENT-DESCRIPTOR : WEB.XML
		* CODE : 
			<servlet>
				<servlet-name>dispatcher</servlet-name>
				<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
				<load-on-startup>1</load-on-startup>
			</servlet>
			<servlet-mapping>
				<servlet-name>dispatcher</servlet-name>
				<url-pattern>/</url-pattern>
			</servlet-mapping>
			
	- DISPATHCER-SERVLET.XML 
		* CREATE DISPATHER-SERVLET FILE (UNDER WEB-INF): 
			* STEPS : File -> New -> Other -> Spring -> Spring Configuration File.
		* CODE :
			<?xml version="1.0" encoding="UTF-8"?>
			<beans xmlns="http://www.springframework.org/schema/beans"
				xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
				xmlns:context="http://www.springframework.org/schema/context"
				xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
					http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.2.xsd">
				<context:component-scan base-package="om.soft.controller"></context:component-scan>
			</beans>
	
	- CREATE SPRING CONTROLLER CLASS	  
		* Code : 
			package om.soft.controller;
			import org.springframework.stereotype.Controller;
			import org.springframework.web.bind.annotation.RequestBody;
			import org.springframework.web.bind.annotation.RequestMapping;
			import org.springframework.web.bind.annotation.ResponseBody;

			@Controller
			public class Hallo {

				@ResponseBody
				@RequestMapping("/hallo")
				public String index(){
					return "hallo world Spring Controller";
				} 
			}
			
		* Access : 
			- localhost:8080/hallo
			
##VIEWRESOLVER JSP FILE
	* CODE : 
	<bean
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix">
			<value>/WEB-INF/jsp/</value>
		</property>
		<property name="suffix">
			<value>.jsp</value>
		</property>
	</bean>
