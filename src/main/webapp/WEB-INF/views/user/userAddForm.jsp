<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../header.jsp"/>
<head>
    <title>Add User</title>
</head>
<body>
<jsp:include page="../navbar.jsp"/>
<br>

<div class="container">
    <form method="Post">
        <table>
            <tr>
                <td>
                    <div class="col-md-8">
                        <label>User Name</label>
                        <br/>
                        <input type="text" name="firstName"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="col-md-8">
                        <label>Second Name</label>
                        <br/>
                        <input type="text" name="secondName"/>
                    </div>
                </td>
            </tr>

            <tr>

                <td>
                    <div class="col-md-8">
                        <button type="submit" value="Save">Save</button>
                    </div>
                </td>
            </tr>

        </table>


    </form>
</div>
</body>
</html>