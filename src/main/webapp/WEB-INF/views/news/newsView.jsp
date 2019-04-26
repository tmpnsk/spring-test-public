<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="../header.jsp"/>
<body>
<jsp:include page="../navbar.jsp"/>

<div class="container">
    <br>

    <h3>news:</h3>
    <br>


    <div class="row">
        <div class="col-md-6">
            <label>Title</label>
            <text class="form-control" type="text"/>
            ${news.title}
        </div>
        <div class="col-md-2">
            <label>Views</label>
            <text class="form-control" type="text"/>
            ${news.viewsAmount}
        </div>

        <div class="col-md-4">
            <label>User</label>
            <text class="form-control"/>
            ${news.user}
        </div>
    </div>
    <br/>

    <div class="row">
        <div class="col-md-12">
            <label>Content</label>
            <textarea maxlength="4096" rows="4" class="form-control" type="text"
                      name="content">${news.content}</textarea>
        </div>
    </div>

    <br>
    <br>
    <br>

    <h5> Comments </h5>
    <table width="100%">
        <c:forEach items="${comments}" var="comment">
            <tr style="height: 80px">
                <td style="width: 10%"><b>${comment.date}</b></td>
                <td style="width: 60%">${comment.content}</td>
                <td style="width: 10%; cursor: pointer;"><a href="${DOMAIN}/news/like/${news.id}/${comment.id}"><span
                        class="fa fa-thumbs-up"></span> ${comment.likesCount}</a></td>
                <td style="width: 10%; cursor: pointer;"><a href="${DOMAIN}/news/disLike/${news.id}/${comment.id}"><span
                        class="fa fa-thumbs-down"></span> ${comment.dislikesCount}</a></td>
            </tr>
        </c:forEach>
        <tr>
            <form method="post">
                <td colspan="3">
                    <input class="form-control" name="comment" style="width: 100%;" type="text"
                           placeholder="Enter your comment...">
                </td>
                <td>
                    <button class="form-control" type="submit"> Post</button>
            </form>
            </td>
        </tr>
    </table>


</div>
</body>
</html>
<script src="${webappRoot}/resources/js/jquery-1.12.3.min.js"></script>








