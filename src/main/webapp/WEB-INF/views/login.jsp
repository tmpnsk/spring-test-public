<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="header.jsp"/>
<body>
<div class="container">
    <div class="row" style="height:150"></div>
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div class="panel panel-login">
                <div class="panel-body">
                    <p class="text-center">${message}</p>
                </div>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6 col-md-offset-4">
            <form name="f" action="${DOMAIN}/j_spring_security_check" method="POST"
                  style="display: block;">
                <div class="form-group">
                    <input type="text" name="username" class="form-control" placeholder="Login" value=""></div>
                <div class="form-group">
                    <input type="password" name="password" class="form-control" placeholder="Password">
                </div>
                <input type="submit" name="submit" class="form-control btn" value="Enter">

            </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>
</body>
</html>