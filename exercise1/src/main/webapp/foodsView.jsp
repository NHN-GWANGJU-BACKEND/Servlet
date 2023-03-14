<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${applicationScope.locale}"/>
<fmt:bundle basename="message">
    <form method="post" action="/cartForm.do">
        <ul>
            <c:forEach var="food" items="${applicationScope.foodStand.getFoods()}">
                <li><fmt:message key="${food.getName()}"/></li>
            </c:forEach>
        </ul>
        <h1><fmt:message key="input"/></h1>
        <input type="text" name="foodInput"/>
        <input type="submit" value=<fmt:message key="goCart"/>/>
    </form>
</fmt:bundle>