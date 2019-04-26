<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="../header.jsp"/>
<body>
<jsp:include page="../navbar.jsp"/>

<div class="container">
    <br>

    <h3>Add news:</h3>
    <br>

    <form method="post">

        <input type="hidden" name="id"/>

        <div class="row">
            <div class="col-md-8">
                <label>Title</label>
                <input class="form-control" type="text" name="title"/>
            </div>
            <div class="col-md-4">
                <label>User</label>
                <select class="form-control" name="user">
                    <option></option>

                    <c:forEach items="${options.users}" var="opt">
                        <option value="${opt.id}" <c:if test="${editNews.user.id == opt.id}"> selected </c:if>>
                                ${opt}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <br/>

        <div class="row">
            <div class="col-md-12">
                <label>Content</label>
                <textarea maxlength="4096" rows="4" class="form-control" type="text"
                          name="content"> </textarea>
            </div>
        </div>

        <br>

        <div class="row">
            <button class="btn btn-primary btn-large" type="submit" value="Save">Save</button>
        </div>

    </form>
</div>
</body>
</html>
<script src="${webappRoot}/resources/js/jquery-1.12.3.min.js"></script>