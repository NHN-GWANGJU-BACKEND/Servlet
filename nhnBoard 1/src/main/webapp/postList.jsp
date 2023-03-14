<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>postList</title>
    <style>

        a {
            text-decoration: none;
            font-size: 17px;
        }

        a:visited {
            color: blue;
        }

        .userContainer {
            display: flex;
            border: 1px solid black;
            justify-content: center;
            align-items: center;
            gap: 5px;
        }

        .postTitle, .userId {
            display: flex;
            justify-content: center;
            border: 1px solid black;
            width: 14%;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .postContent {
            display: flex;
            border: 1px solid black;
            width: 40%;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .writeTime {
            display: flex;
            justify-content: center;
            border: 1px solid black;
            width: 20%;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .postContent > a {
            width: 30%;
        }

        .modify, .delete {
            width: 70px;
            border: 1px solid black;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .mode {
            display: flex;
        }


    </style>
</head>
<body>
<fmt:setLocale value="${applicationScope.locale}"/>
<fmt:bundle basename="message">
    <div class="userContainer">
        <div class="postTitle"><fmt:message key="title"/></div>
        <div class="postContent" style="justify-content: center"><fmt:message key="content"/></div>
        <div class="writeTime"><fmt:message key="writeDay"/></div>
        <div class="userId"><fmt:message key="writer"/></div>
        <div class="mode">
            <div class="modify"><fmt:message key="modify"/></div>
            <div class="delete"><fmt:message key="delete"/></div>
        </div>
    </div>

    <c:forEach var="post" items="${requestScope.postList}">
        <div class="userContainer">
            <div class="postTitle">
                <a href="/post/view.do?postId=${post.getId()}">${post.getTitle()}</a>
            </div>
            <div class="postContent"><a href="/post/view.do?postId=${post.getId()}">${post.getContent()}</a></div>
            <div class="writeTime">${post.getWriteTime()}</div>
            <div class="userId"><a href="/user/view.do?userId=${post.getWriterUserId()}">${post.getWriterUserId()}</a>
            </div>
            <div class="mode">
                <c:if test="${sessionScope.user.getId().equals(post.getWriterUserId())}" var="equalUser">
                    <div class="modify"><a href="/postModify.jsp?postId=${post.getId()}"><fmt:message
                            key="modify"/> </a></div>
                    <div class="delete"><a href="/post/delete.do?postId=${post.getId()}"><fmt:message key="delete"/></a>
                    </div>
                </c:if>
                <c:if test="${!sessionScope.user.getId().equals(post.getWriterUserId())}" var="notEqualUser">
                    <div class="modify"></div>
                    <div class="delete"></div>
                </c:if>
            </div>
        </div>
    </c:forEach>
</fmt:bundle>
</body>
</html>
