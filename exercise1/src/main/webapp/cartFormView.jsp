<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${applicationScope.locale}"/>
<fmt:bundle basename="message">

<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/cart.do"><fmt:message key="cartList"/></a>
</body>
</html>

</fmt:bundle>