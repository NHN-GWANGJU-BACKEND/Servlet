<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <style>
        body {
            display: flex;
            justify-content: space-between;
            width: 1024px;
            margin: 50px 100px;
        }

        a {
            text-decoration: none;
            margin-bottom: 10px;
            font-size: 20px;
        }

        a:visited {
            color: black;
        }

        a:hover {
            color: blue;
        }

    </style>
</head>
<body>
<fmt:setLocale value="${applicationScope.locale}"/>
<fmt:bundle basename="message">
    <div>
        <a href="/init.do"><fmt:message key="/init"/></a><br/>
        <a href="/foods.do"><fmt:message key="/foods"/></a><br/>
        <a href="/cart.do"><fmt:message key="/cart"/></a><br/>
        <a href="/loginForm.do"><fmt:message key="/loginForm"/></a><br/>
        <a href="/logout.do"><fmt:message key="/logout"/></a><br/>
        <a href="/set-locale.do?locale=ko"><fmt:message key="ko"/></a><br/>
        <a href="/set-locale.do?locale=en"><fmt:message key="en"/></a><br/>
    </div>
    <div>
        <p><fmt:message key="locale"/> : <fmt:message key="${applicationScope.locale}"/></p>
        <c:if test="${sessionScope.id ne null}">
            <p><fmt:message key="balance"/> : ${applicationScope.user.getMoney()}</p>
        </c:if>
    </div>
</fmt:bundle>
</body>
</html>