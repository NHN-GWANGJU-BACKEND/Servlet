<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>userList</title>
    <style>
        .profileImg {
            width: 50px;
            height: 50px;
            border-radius: 50%;
        }

        a{
            text-decoration: none;
        }
        a:visited{
            color: blue;
        }
        body {
            width: 1024px;
            height: 100vh;
        }

        #body {
            margin: 20px;

        }

        .imgBox {
            border-radius: 50%;
            width: 50px;
            height: 50px;
            border: 1px solid gray;
        }

        .userContainer {
            border: 1px solid black;
            display: flex;
            gap: 10px;
            width: 400px;
            height: 80px;
            justify-content: center;
            align-items: center;
        }

        .userName {
            width: 120px;
            height: 30px;
            border: 1px solid black;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .modify, .delete {
            width: 70px;
            height: 30px;
            border: 1px solid black;
            display: flex;
            justify-content: center;
            align-items: center;
        }
    </style>
</head>
<body>
<fmt:setLocale value="${applicationScope.locale}"/>
<fmt:bundle basename="message">
    <div id="body">
        <c:forEach var="user" items="${requestScope.userList}">
            <div class="userContainer">
                <div class="imgBox">
                    <img class="profileImg" src="image${user.getProfileFileName()}">
                </div>
                <div class="userName"><a href="/user/view.do?userId=${user.getId()}">${user.getName()}</a></div>
                <div class="modify"><a href="/userModify.jsp?userId=${user.getId()}"><fmt:message key="modify"/></a>
                </div>
                <div class="delete"><a href="/user/delete.do?userId=${user.getId()}"><fmt:message key="delete"/></a>
                </div>
            </div>
        </c:forEach>
    </div>
</fmt:bundle>
</body>
</html>
