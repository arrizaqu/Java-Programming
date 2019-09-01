# Common Issues "Not working" in Java
	1. Change Port
	2. Include JS or CSS files in a JSP page
	3. Could not obtain transaction-synchronized Session for current thread
	4. Spring Datatable library
	5. Filed to lazily initialize a collection of role
	6. Handling Error Page
		* Deployment Descriptor
		* Controller Exception
		* Error Listener
	7. Error Eclipse code 13 (common)
	8. org.hibernate.NonUniqueObjectException: a different object with the same identifier value was already associated with the session
	9. object references an unsaved transient instance - save the transient instance before flushing
	10. Save Date Format 
	11. could not resolve archetype (maven)
	12. Http Status 406 Not Acceptable
	13. Avoid multiple Instance of Application
## Change Port
	see files list.

## Include JS or CSS files in a JSP page
### Define Resource File Assets on SpringConfiguration
```xml
<mvc:resources mapping="/resources/**" location="/resources/theme-new/" />
```

### Activate Spring tag library (Base URL): 
```java
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
```

### example code :
```java
<spring:url value="/resources/css/main.css" var="mainCss" />
<spring:url value="/resources/js/jquery.1.10.2.min.js"  var="jqueryJs" />
<spring:url value="/resources/js/main.js" var="mainJs" />

<link href="${mainCss}" rel="stylesheet" />
<script src="${jqueryJs}"></script>
<script src="${mainJs}"></script>
```

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
```xml
<filter>
	<filter-name>OpenEntityManagerInViewFilter</filter-name>
	<filter-class>org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter</filter-class>
</filter>
<filter-mapping>
	<filter-name>OpenEntityManagerInViewFilter</filter-name>
	<url-pattern>/*</url-pattern>
</filter-mapping>
```

### Solving 2 :
	Change or Set FetchType.Eager
	
### Solving 3 : 
#### Maven JSON Dependency :
```xml
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
```

#### Download and Include to the library :
	- http://blog.pastelstudios.com/wp-content/uploads/2012/03/spring3-jackson2-src.zip
	- note : change to hibernate 5		
	
#### HibernateAwareObjectMapper
```java
package com.pastelstudios.json; 
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

public class HibernateAwareObjectMapper extends ObjectMapper {

	public HibernateAwareObjectMapper() {
		Hibernate4Module hm = new Hibernate4Module();
		registerModule(hm);
	}
}
```
	
#### PastelsStudios
```java
package com.pastelstudios.json;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.Assert;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * Implementation of {@link org.springframework.http.converter.HttpMessageConverter HttpMessageConverter}
 * that can read and write JSON using <a href="http://jackson.codehaus.org/">Jackson's</a> {@link ObjectMapper}.
 *
 * <p>This converter can be used to bind to typed beans, or untyped {@link java.util.HashMap HashMap} instances.
 *
 * <p>By default, this converter supports {@code application/json}. This can be overridden by setting the
 * {@link #setSupportedMediaTypes(List) supportedMediaTypes} property.
 *
 * @author Arjen Poutsma
 * @since 3.0
 * @see org.springframework.web.servlet.view.json.MappingJacksonJsonView
 */
public class MappingJackson2HttpMessageConverter extends AbstractHttpMessageConverter<Object> {

	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");


	private ObjectMapper objectMapper = new ObjectMapper();

	private boolean prefixJson = false;


	/**
	 * Construct a new {@code BindingJacksonHttpMessageConverter}.
	 */
	public MappingJackson2HttpMessageConverter() {
		super(new MediaType("application", "json", DEFAULT_CHARSET));
	}

	/**
	 * Set the {@code ObjectMapper} for this view. If not set, a default
	 * {@link ObjectMapper#ObjectMapper() ObjectMapper} is used.
	 * <p>Setting a custom-configured {@code ObjectMapper} is one way to take further control of the JSON
	 * serialization process. For example, an extended {@link org.codehaus.jackson.map.SerializerFactory}
	 * can be configured that provides custom serializers for specific types. The other option for refining
	 * the serialization process is to use Jackson's provided annotations on the types to be serialized,
	 * in which case a custom-configured ObjectMapper is unnecessary.
	 */
	public void setObjectMapper(ObjectMapper objectMapper) {
		Assert.notNull(objectMapper, "ObjectMapper must not be null");
		this.objectMapper = objectMapper;
	}

	/**
	 * Return the underlying {@code ObjectMapper} for this view.
	 */
	public ObjectMapper getObjectMapper() {
		return this.objectMapper;
	}

	/**
	 * Indicate whether the JSON output by this view should be prefixed with "{} &&". Default is false.
	 * <p>Prefixing the JSON string in this manner is used to help prevent JSON Hijacking.
	 * The prefix renders the string syntactically invalid as a script so that it cannot be hijacked.
	 * This prefix does not affect the evaluation of JSON, but if JSON validation is performed on the
	 * string, the prefix would need to be ignored.
	 */
	public void setPrefixJson(boolean prefixJson) {
		this.prefixJson = prefixJson;
	}


	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		JavaType javaType = getJavaType(clazz);
		return (this.objectMapper.canDeserialize(javaType) && canRead(mediaType));
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return (this.objectMapper.canSerialize(clazz) && canWrite(mediaType));
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		// should not be called, since we override canRead/Write instead
		throw new UnsupportedOperationException();
	}

	@Override
	protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {

		JavaType javaType = getJavaType(clazz);
		try {
			return this.objectMapper.readValue(inputMessage.getBody(), javaType);
		}
		catch (JsonProcessingException ex) {
			throw new HttpMessageNotReadableException("Could not read JSON: " + ex.getMessage(), ex);
		}
	}

	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {

		JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());
		JsonGenerator jsonGenerator =
				this.objectMapper.getJsonFactory().createJsonGenerator(outputMessage.getBody(), encoding);
		try {
			if (this.prefixJson) {
				jsonGenerator.writeRaw("{} && ");
			}
			this.objectMapper.writeValue(jsonGenerator, object);
		}
		catch (JsonProcessingException ex) {
			throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
		}
	}


	/**
	 * Return the Jackson {@link JavaType} for the specified class.
	 * <p>The default implementation returns {@link TypeFactory#type(java.lang.reflect.Type)},
	 * but this can be overridden in subclasses, to allow for custom generic collection handling.
	 * For instance:
	 * <pre class="code">
	 * protected JavaType getJavaType(Class&lt;?&gt; clazz) {
	 *   if (List.class.isAssignableFrom(clazz)) {
	 *     return TypeFactory.collectionType(ArrayList.class, MyBean.class);
	 *   } else {
	 *     return super.getJavaType(clazz);
	 *   }
	 * }
	 * </pre>
	 * @param clazz the class to return the java type for
	 * @return the java type
	 */
	protected JavaType getJavaType(Class<?> clazz) {
		return TypeFactory.defaultInstance().constructType(clazz);
	}

	/**
	 * Determine the JSON encoding to use for the given content type.
	 * @param contentType the media type as requested by the caller
	 * @return the JSON encoding to use (never <code>null</code>)
	 */
	protected JsonEncoding getJsonEncoding(MediaType contentType) {
		if (contentType != null && contentType.getCharSet() != null) {
			Charset charset = contentType.getCharSet();
			for (JsonEncoding encoding : JsonEncoding.values()) {
				if (charset.name().equals(encoding.getJavaName())) {
					return encoding;
				}
			}
		}
		return JsonEncoding.UTF8;
	}

}
```
	
#### Configure Json Message Converter
```java
<mvc:annotation-driven>
  <mvc:message-converters>
    <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
      <property name="objectMapper">
	<bean class="com.pastelstudios.json.HibernateAwareObjectMapper" />
      </property>
    </bean> 
  </mvc:message-converters> 
</mvc:annotation-driven>
```

#### Example Fetching Data Manually 
```java
public List<Department> home(){
	List<Department> departments = departmentDao.getAllDept();
	for(Department dept : departments){
		List<Employee> employees = employeeDao.getEmployeesByDepartment(dept);
		dept.setEmployees(employees);
	}

	return departments;
}
```

## Handling Error Page
	* Deployment Descriptor
	* Controller Exception
	* Error Listener
	
### Deployment Descriptor
```xml
<error-page>
	<error-code>404</error-code>
	<location>/404</location>
</error-page>
```

### Controller Exception
```java
@Controller
public class HttpErrorHandler {

	 @RequestMapping(value="/404")
	 public String error404(){

	  return "error/pagenotfound";
	 }
}
```

### Error Listener
```java
@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest request, Exception e)   {
		Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Request: " + request.getRequestURL() + " raised " + e);
		return new ModelAndView("error");
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handleError404(HttpServletRequest request, Exception e)   {
		Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Request: " + request.getRequestURL() + " raised " + e);
		return new ModelAndView("404");
	}
}
```

## Error Eclipse code 13 (common)
```xml	
...
--launcher.appendVmargs
-vm
C:\Program Files\Java\jre1.8.0_45\bin\javaw.exe
-vmargs
...
```

## org.hibernate.NonUniqueObjectException: a different object with the same identifier value was already associated with the session
### Example :
```java
public void updateUserRole(User user) {
	// TODO Auto-generated method stub
	Session session = sessionFactory.getCurrentSession();
	User newUser = (User)session.merge(user);
	session.saveOrUpdate(newUser);
	session.flush();
}
```
	
### reference : 
	https://stackoverflow.com/questions/11934944/saving-updating-object-with-hibernate
	
## object references an unsaved transient instance - save the transient instance before flushing
### Solustion
	Have you annotate cascade=CascadeType.ALL to your entity object mapping
	@ManyToOne(cascade = CascadeType.ALL)
    private String entityType;
	
### Reference : 
	https://stackoverflow.com/questions/30165319/save-transient-object-before-flushing-error
	
## Save Date Format
### Entity
```java
@Temporal(TemporalType.DATE)
@Column(name="birth_date")
private Date birthDate;
```

### HTML
```html
<form:input type="text" path="birthDate" class="form-control"/>
```

### Controller (Binder)
```java
@InitBinder
public void dataBinding(WebDataBinder binder) {
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	dateFormat.setLenient(false);
	binder.registerCustomEditor(Date.class, "birthDate", new CustomDateEditor(dateFormat, true));
} 
```

## could not resolve archetype
1. Open Window > Preferences
2. Open Maven > Archetypes
3. Click 'Add Remote Catalog' and add the following:
4. Catalog File: http://repo1.maven.org/maven2/archetype-catalog.xml
5. Description: maven catalog

## Http Status 406 Not Acceptable
* add in dispatcher servlet <mvc:annotation-driven>

## Avoid multiple Instance of Application

http://www.java2s.com/Tutorial/Java/0180__File/PreventingmultipleinstancesofanapplicationThefilelocktechnique.htm
```java
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

class JustOneLock {
  private String appName;

  FileLock lock;

  FileChannel channel;

  public JustOneLock(String appName) {
    this.appName = appName;
  }

  public boolean isAppActive() throws Exception{
    File file = new File(System.getProperty("user.home"), appName + ".tmp");
    channel = new RandomAccessFile(file, "rw").getChannel();

    lock = channel.tryLock();
    if (lock == null) {
      lock.release();
      channel.close();
      return true;
    }
    Runtime.getRuntime().addShutdownHook(new Thread() {
      public void run() {
        try {
          lock.release();
          channel.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
    return false;
  }
}

public class Main {
  public static void main(String[] args)throws Exception {
    JustOneLock ua = new JustOneLock("JustOneId");
    if (ua.isAppActive()) {
      System.out.println("Already active.");
      System.exit(1);
    } else {
      System.out.println("NOT already active.");
    }
  }
}
```

### see more : https://stackoverflow.com/questions/7462202/spring-json-request-getting-406-not-acceptable
