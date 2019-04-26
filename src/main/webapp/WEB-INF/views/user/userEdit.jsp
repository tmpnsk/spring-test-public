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

    <form method="post">
        <Table>
            <tr style=padding-right:40px>
                <div class="row" margin=6>
                    <label>User Name</label>
                    <input type="hidden" name="id" value="${userName.id}"/>
                    <br/>
                    <input type="text" name="firstName" value="${userName.firstName}"/>
                </div>
            </tr>
            <tr>
                <div class="row">
                    <label>Second Name</label>
                    <br/>
                    <input type="text" name="secondName" value="${userName.secondName}"/>
                </div>
            </tr>
            <td>

                <tr>

                </tr>
        </Table>

        <button type="submit" value="Save">Save</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    <table border="12">
        <br/>
        <tr>
            <c:forEach items="${userNews}" var="userNews">
                <td><a href="${DOMAIN}/news/view/${userNews.id}" style="color: #000000">${userNews.title}</a></td>
                <td><a href="${DOMAIN}/news/view/${userNews.id}" style="color: #000000">${userNews.viewsAmount}</a>
                </td>
            </c:forEach>

        </tr>


    </table>
</div>
</body>
</html>