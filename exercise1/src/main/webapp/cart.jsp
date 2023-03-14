<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${applicationScope.locale}"/>
<fmt:bundle basename="message">

    <form method="post" action="/pay.do">
        <ul>
            <c:forEach var="basket" items="${applicationScope.basket.getFoods()}">
                <li><fmt:message key="${basket.getName()}"/></li>
            </c:forEach>
        </ul>

        <h1><fmt:message key="price"/> : ${applicationScope.price}</h1>
        <input type="submit" value="<fmt:message key="pay"/>"/>
    </form>

</fmt:bundle>

