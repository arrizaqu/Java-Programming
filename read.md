# Common Issues "Not working" in Java
	1. Change Port
	2. Include JS or CSS files in a JSP page
	3. Could not obtain transaction-synchronized Session for current thread
	4. Spring Datatable library
	
	
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
	
## 