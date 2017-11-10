# Java Web

## Deployment Descriptor (Web XML)
### Example : 
	<web-app 
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
		xmlns="http://java.sun.com/xml/ns/javaee"
		xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" 
		xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
		id="Your_WebApp_ID"
		version="2.5">
		
			<welcome-file-list>
				<welcome-file>template.jsp</welcome-file>
				<welcome-file>home.jsp</welcome-file>
			</welcome-file-list>
	
	</web-app>
## Servlet 
	java Servlet technology lets you define HTTP-specific servlet classes. A servlet class extends the capabilities of servers that host applications accessed by way of a request-response programming model
### Web.xml
	<servlet>
		<servlet-name>myhome</servlet-name>
		<servlet-class>com.xsis.trainingweb.controller.Home</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>myhome</servlet-name>
		<url-pattern>/home</url-pattern>
	</servlet-mapping>
	
### Controller 	
	public class Home extends HttpServlet{

	//1. GET
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			PrintWriter output = resp.getWriter();
			output.println("java servlet works!!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}