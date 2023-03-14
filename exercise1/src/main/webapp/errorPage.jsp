<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" language="java" %>

<fmt:setLocale value="${applicationScope.locale}"/>
<fmt:bundle basename="message">
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1> <strong>error : </strong>${applicationScope.exception}</h1>
<a href="/"><fmt:message key="comeBackHome"/></a>
</body>
</html>
</fmt:bundle>
