<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../header.jsp"/>
<head>
    <title></title>
</head>
<body>
<jsp:include page="../navbar.jsp"/>
<div class="container">
    <br>

    <h3>List of user:</h3>
    <table class="table">
        <tr>
            <td>Id</td>
            <td>Name</td>
            <td>Second Name</td>
            <td>User Role</td>

            <td>
                <a title="AddUser" href="${DOMAIN}/user/add">
                    <span class="fa fa-plus"></span>
                </a>
            </td>
        </tr>

        <c:forEach items="${userName}" var="user">

            <tr>

                <td><a href="${DOMAIN}/profile/${user.id}" style="color: #000000">${user.id}</a> </td>
                <td><a href="${DOMAIN}/profile/${user.id}" style="color: #000000">${user.firstName}</a></td>
                <td><a href="${DOMAIN}/profile/${user.id}" style="color: #000000">${user.secondName}</a></td>
                <td><a href="${DOMAIN}/profile/${user.id}" style="color: #000000">${user.role}</a></td>
                <td>


                    <a title="EditUser"
                       href="${DOMAIN}/user/edit/${user.id}">
                        <span class="fa fa-pencil"> </span>
                    </a>
                    <a title="DeleteUser"
                       href="${DOMAIN}/user/delete/${user.id}"
                       onclick="return confirm('Are you sure you want to delete record?')">
                        <span class="fa fa-trash"> </span>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>