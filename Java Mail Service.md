# Java Mail Server in Spring Boot
## Dependency
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
```
## Service
```
public void sendHtmlMessage(Email email) throws MessagingException {
    MimeMessage message = emailSender.createMimeMessage();
    
    MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
    Context context = new Context();
    context.setVariables(email.getProperties());
    helper.setFrom(email.getFrom());
    helper.setTo(email.getTo());
    helper.setSubject(email.getSubject());
    String html = templateEngine.process(email.getTemplate(), context);
    helper.setText(html, true);

    //log.info("Sending email: {} with html body: {}", email, html);
    emailSender.send(message);
}
```
## Call Service
```
........
Email email = new Email();
email.setTo(rMember.getEmail());
email.setFrom("noreply@javalandmark.my.id");
email.setSubject("Registration");
email.setTemplate("welcome-email.html");
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
String regDate = sdf.format(new Date(rMember.getCreateTime()));

Map<String, Object> properties = new HashMap<>();
String fName = rMember.getFirstName() + " " + rMember.getLastName();
properties.put("name", fName);
properties.put("regid", rMember.getRegistrationNumber());
properties.put("area", rMember.getRecruitmentProgramMemberActive().getRecruitmentProgram().getArea());
properties.put("regdate",regDate);
email.setProperties(properties);
.............
 sendHtmlMessage(email);
```

## HTML Email Template
```
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
    <head>
        <title th:remove="all">Welcome</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    </head>
    <body>
        <p>
            Hello, <span th:text="${name}">??name</span>
        </p>
        <p>
            Thank you for registering with our company, please wait for the selection process which will take a few moments.
        </p>
        Registration Details:
        <p/>

        <table>
            <tr><td>Registration ID</td><td>:</td><td th:text="${regid}">??regid</td></tr>
            <tr><td>Full Name</td><td>:</td><td th:text="${name}">??fullname</td></tr>
            <tr><td>Area</td><td>:</td><td th:text="${area}">??area</td></tr>
            <tr><td>Registration Date</td><td>:</td><td th:text="${regdate}">??regdate</td></tr>
        </table>
        <p>
            Regards, <br/>
            <em>Hikari Man Power Team</em>
        </p>
    </body>
</html>
```

## Reference 
* https://codingnconcepts.com/spring-boot/send-email-with-thymeleaf-template/
