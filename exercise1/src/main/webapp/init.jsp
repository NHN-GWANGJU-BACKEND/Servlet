<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${applicationScope.locale}"/>
<fmt:bundle basename="message">
    <a href="/foods.do"><fmt:message key="cartList"/></a>
</fmt:bundle>
