<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>userView</title>
    <style>
        a {
            text-decoration: none;
        }

        a:visited {
            color: blue;
        }

        .postContainer {
            display: flex;
            flex-direction: row;
            justify-content: center;
        }

        p, span {
            width: 150px;
            height: 40px;
            border-right: 0.5px solid black;
            padding: 5px;
        }

        p:nth-last-child(n) {
            width: 150px;
            height: 40px;
            padding: 5px;
        }

        .tmp {
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            display: flex;
            justify-content: center;
            align-items: center;
        }
    </style>
</head>
<body>
<fmt:setLocale value="${applicationScope.locale}"/>
<fmt:bundle basename="message">

    <h1>${requestScope.userName} <fmt:message key="postList"/></h1>
    <div class="postContainer">
        <p class="tmp" style="width: 15%"><fmt:message key="title"/></p>
        <p class="tmp" style="width: 50%"><fmt:message key="content"/></p>
        <p class="tmp" style="width: 20%"><fmt:message key="writeDay"/></p>
        <p class="tmp" style="width: 15%"><fmt:message key="viewCount"/></p>
    </div>

    <c:forEach items="${applicationScope.postList}" var="post">
        <div class="postContainer">
        <span class="tmp" style="width: 15%">
            <a href="/post/view.do?postId=${post.getId()}"> ${post.getTitle()} </a>
        </span>
            <p class="tmp" style="width: 50%">${post.getContent()}</p>
            <p class="tmp" style="width: 20%">${post.getWriteTime()}</p>
            <p class="tmp" style="width: 15%">${post.getViewCount()}</p>
        </div>
    </c:forEach>
</fmt:bundle>
</body>
</html>
