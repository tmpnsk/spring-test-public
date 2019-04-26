<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<jsp:include page="../header.jsp"/>
<head>
    <title>Profile</title>
</head>
<body>
<jsp:include page="../navbar.jsp"/>
<br/>

<div class="container">
    <div class="row">

        <div class="col-md-3"><img src="..." width="270" height="400"></div>
        </br>
        <div class="col-md-3">
        <div class="col-md-15" >
            <text class="form-control" type="text" />
            ${user.firstName}
        </div>
        <div class="col-md-15">
            <text class="form-control" type="text"/>
            ${user.secondName}
        </div>
        <div class="col-md-15">
            <text class="form-control" type="text"/>
            ${user.role}
        </div>


        </div>


    </div>
    <div class="container">
    <div class="form-text">
    <div class="col-md-5">
        <button class="col-md-4" type="submit" value="save">Button</button>
    </div>
    </div>


    <div class="row">
        <form method="post">
            </br> <label>Old Passwords</label></br> <h3 style="color: red" >${message}</h3>

            <div class="col-md-20"><input type="password" class="form-control" name="validPassword" maxlength="40" minlength="3" /></div>

            <label>New Passwords</label>

            <div class="col-md-20"><input type="password" class="form-control" name="newPassword" maxlength="40" minlength="3" placeholder="Макс. 40 симв."/></div>
            </br>

               <button  class="btn btn-primary btn-large" type="submit" name ="save">Submit</button>


        </form>
    </div>
</div>
</div>
</body>
</html>