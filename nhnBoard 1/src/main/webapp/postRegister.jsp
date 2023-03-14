<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>postRegister</title>
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
        }

        p {
            width: 80px;
        }

        input {
            width: 150px;
        }

        form {
            width: 100%;
            height: 100vh;
        }
    </style>
</head>
<body>
<fmt:setLocale value="${applicationScope.locale}"/>
<fmt:bundle basename="message">
    <form action="/post/register.do" method="post">
        <div id="border">
            <div class="container">
                <p><fmt:message key="title"/> : </p><input type="text" name="title"/><br/>
            </div>
            <div class="container">
                <p><fmt:message key="content"/> : </p><textarea name="content" cols="100" rows="15"></textarea>
            </div>
        </div>
        <input style="margin-top: 20px; padding: 10px" type="submit" value=<fmt:message key="postRegist"/>/>
    </form>
</fmt:bundle>
</body>
</html>
