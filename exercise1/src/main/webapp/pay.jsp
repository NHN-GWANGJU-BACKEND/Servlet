<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${applicationScope.locale}"/>
<fmt:bundle basename="message">
    <h1><fmt:message key="paymentMsg"/> : ${applicationScope.price}</h1>
    <h1><fmt:message key="afterPaymentMsg"/> : ${applicationScope.user.getMoney()}</h1>
    <a href="/"><fmt:message key="comeBackHome"/></a>
</fmt:bundle>