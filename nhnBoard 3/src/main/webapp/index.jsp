<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <style>
        #body {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 1024px;
            margin: 50px 100px;
        }

        a {
            text-decoration: none;
            font-size: 20px;
        }

        a:visited {
            color: blue;
        }


        .btn {
            width: 250px;
            height: 120px;
            border: 1px solid black;
            background-color: #e2e2e2;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            gap: 15px;
        }

        #locale {
            margin-left: 85%;
            display: flex;
            flex-direction: column;
            gap: 7px;
        }
    </style>
</head>
<body>
<fmt:setLocale value="${applicationScope.locale}"/>
<fmt:bundle basename="message">
    <div id="locale">
        <p style="font-size: 12px"><fmt:message key="locale"/> : <fmt:message key="${applicationScope.locale}"/></p>
        <c:if test="${applicationScope.locale eq 'ko'}">
            <a style="font-size: 12px" class="change" href="/set-locale.do?locale=en"><fmt:message key="en"/></a><br/>
        </c:if>
        <c:if test="${applicationScope.locale eq 'en'}">
            <a style="font-size: 12px" href="/set-locale.do?locale=ko"><fmt:message key="ko"/></a><br/>
        </c:if>
    </div>
    <div id="body">
        <div class="btn">
            <c:if test="${sessionScope.user eq null}">
                <a href="/login.html"><fmt:message key="login"/></a>
            </c:if>
            <c:if test="${sessionScope.user ne null}">
                <a href="/logout.do"><fmt:message key="logout"/></a>
            </c:if>
        </div>
        <div class="btn">
            <c:if test="${sessionScope.user.getId().equals('admin')}">
                <a href="/userRegister.jsp"><fmt:message key="userRegist"/></a>
                <a href="/userList.do"><fmt:message key="userList"/></a>
            </c:if>
            <c:if test="${!sessionScope.user.getId().equals('admin')}">
                <a href="/postRegister.jsp"><fmt:message key="postRegist"/></a>
                <a href="/postList.do?page=1&size=10"><fmt:message key="postList"/></a>
            </c:if>
        </div>
        <div class="btn">
            <p><fmt:message key="visit"/> : ${applicationScope.visitCount}</p>
            <p><fmt:message key="loginCount"/> : ${applicationScope.userList.size()}</p>
        </div>
    </div>
</fmt:bundle>
</body>
</html>
