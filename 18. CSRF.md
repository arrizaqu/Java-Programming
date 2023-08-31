# CSRF Spring Security
* Handle Enable CSRF
## Standart Spring From
```
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta th:name="_csrf" th:content="${_csrf.token}"/>
<meta th:name="_csrf_header" th:content="${_csrf.headerName}"/>
...............................

<form action="/login/dofp" method="POST">
<input type="email" name="email" placeholder="Email Address" class="input-field" id="email">
<input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"/>
<button class="submit-button" id="resetPasswordButton">Reset Password</button>
```

## Ajax Setup
```
<meta id="_csrf" name="_csrf" th:content="${_csrf.token}"/>
<meta id="_csrf_header" name="_csrf_header" th:content="${_csrf.headerName}"/>
...............

function ajaxSetupCRSF(){
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

	$(document).ajaxSend(function(e, xhr, options) {
	    xhr.setRequestHeader(header, token);
	});
}
```
