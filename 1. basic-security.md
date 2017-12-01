# Spring Security
	* Dependency
	* Create Security Configuration file 
	* Import from applicationContext
	* Define springSecurityFilterChain to Web.xml
	* Define Http auto-config
	* Define Authentication-manager
	 
## Library 
### property : 
		<property>
			<org.springframework.security.version>3.2.3.RELEASE</org.springframework.security.version>
		</properties>
	
### Dependency : 
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-core</artifactId>
			<version>${org.springframework.security.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-web</artifactId>
			<version>${org.springframework.security.version}</version>
		</dependency>
		
## Create Security configuration file
	<?xml version="1.0" encoding="UTF-8"?>
	<beans:beans xmlns="http://www.springframework.org/schema/security"
		xmlns:beans="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:schemaLocation="http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
		http://www.springframework.org/schema/security
		http://www.springframework.org/schema/security/spring-security-3.2.xsd">

	</beans:beans>
## Define springSecurityFilterChain to Web.xml
	<filter>
		<filter-name>springSecurityFilterChain</filter-name>
		<filter-class>org.springframework.web.filter.DelegatingFilterProxy
		</filter-class>
	</filter>

	<filter-mapping>
		<filter-name>springSecurityFilterChain</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
	
## Import from applicationContext
	<import resource="security-conf.xml"/>
	
## Define Http auto-config
	<http auto-config="true" use-expressions="true">
		<intercept-url pattern="/home**" access="hasRole('ROLE_USER')" />
		<intercept-url pattern="/employee**" access="hasRole('ROLE_STAFF')"/>
		<intercept-url pattern="/penjualan**" access="hasRole('ROLE_STAFF')"/>
	</http>
	
## Define Authentication-manager
	<authentication-manager>
		<authentication-provider>
			<user-service>
				<user name="masydaarrizaqu" password="123456" authorities="ROLE_USER"/>
				<user name="operator" password="operator" authorities="ROLE_STAFF"/>
				<user name="staff" password="staff" authorities="ROLE_STAFF"/>
			</user-service>
		</authentication-provider>
	</authentication-manager>
	
 