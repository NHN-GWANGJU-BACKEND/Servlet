<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>userRegister</title>
    <style>
        #border {
            border: 1px solid black;
            padding-bottom: 40px;
        }

        .container {
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 15px 40px 40px 0;
            width: 350px;
            height: 80px;
        }

        p {
            width: 100px;
        }

        input {
            width: 150px;
        }
    </style>
</head>
<body>
<fmt:setLocale value="${applicationScope.locale}"/>
<fmt:bundle basename="message">
    <form method="post" action="/user/register.do" enctype="multipart/form-data">
        <div id="border">
            <div class="container">
                <p><fmt:message key="Id"/>:</p><input type="text" name="id"/><br/>
            </div>
            <div class="container">
                <p><fmt:message key="Password"/>:</p><input type="text" name="password"/><br/>
            </div>
            <div class="container">
                <p><fmt:message key="Name"/>:</p><input type="text" name="name"/><br/>
            </div>
            <div class="container">
                <p ><fmt:message key="Profile"/>:</p><input type="file" name="profile"/>
            </div>
            <input style="width:450px; margin-left: 40px;" type="submit" value=<fmt:message key="userRegist"/> />
        </div>
    </form>
</fmt:bundle>
</body>
</html>
