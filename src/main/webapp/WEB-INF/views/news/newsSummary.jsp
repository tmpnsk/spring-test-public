<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<jsp:include page="../header.jsp"/>
<body>
<jsp:include page="../navbar.jsp"/>
<div class="container">
    <br>

    <h3>List of news:</h3>

    <table class="table">
        <tr>
            <td>Id</td>
            <td>Title</td>
            <td>Content</td>
            <td>User</td>
            <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')">
                <td>
                    <a title="Add" href="${DOMAIN}/news/add">
                        <span class="fa fa-plus"></span>
                    </a>
                </td>
            </sec:authorize>
        </tr>

        <c:forEach items="${recordsList}" var="record">
            <tr>

                <td><a href="${DOMAIN}/news/view/${record.id}" style="color: #000000">${record.id}</a></td>
                <td><a href="${DOMAIN}/news/view/${record.id}" style="color: #000000">${record.title}</a></td>
                <td><a href="${DOMAIN}/news/view/${record.id}" style="color: #000000">${record.content}</a></td>
                <td><a href="${DOMAIN}/news/view/${record.id}" style="color: #000000">${record.user}</a></td>
                <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')">
                    <td>
                        <a title="Edit"
                           href="${DOMAIN}/news/edit/${record.id}">
                            <span class="fa fa-pencil"> </span>
                        </a>
                        <a title="Delete"
                           href="${DOMAIN}/news/delete/${record.id}"
                           onclick="return confirm('Are you sure you want to delete record?')">
                            <span class="fa fa-trash"> </span>
                        </a>
                    </td>
                </sec:authorize>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>
<script src="${webappRoot}/resources/js/jquery-1.12.3.min.js"></script>