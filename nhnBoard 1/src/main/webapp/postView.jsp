<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>postView</title>
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .container {
            margin: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: flex-start;
        }

        .container2 {
            display: flex;
            align-items: center;
            justify-content: center;
        }

        p {
            width: 100px;
            font-size: 17px;
        }

        a, span {
            display: flex;
            justify-content: center;
            align-items: center;
            width: 70%;
            text-decoration: none;
            font-size: 17px;
        }

        a:visited {
            color: blue;
        }


        .border {
            padding: 15px;
            display: flex;
            border: 1px solid black;
            width: 400px;
            height: 30px;
            align-items: center;
        }

        #content {
            width: 1024px;
            overflow: scroll;
        }


    </style>
</head>
<body>
<fmt:setLocale value="${applicationScope.locale}"/>
<fmt:bundle basename="message">

    <div class="container2">
        <div class="border">
            <p><fmt:message key="writer"/> : </p>
            <a href="/user/view.do?userId=${requestScope.post.getWriterUserId()}">${requestScope.post.getWriterUserId()}</a>
        </div>
        <div class="border">
            <p><fmt:message key="writeDay"/> : </p><span>${requestScope.post.getWriteTime()}</span>
        </div>
    </div>

    <div class="container">
        <div>
            <h2><fmt:message key="title"/> : ${requestScope.post.getTitle()}</h2>
        </div>
        <div class="container">
            <h2><fmt:message key="content"/></h2>
            <div id="content">
                    ${requestScope.post.getContent()}
            </div>
        </div>
    </div>
    <div class="container">
        <p><fmt:message key="visit"/> : ${requestScope.post.getViewCount()}</p>
    </div>
</fmt:bundle>
</body>
</html>
