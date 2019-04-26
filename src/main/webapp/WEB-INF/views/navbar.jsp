<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-light bg-light">

    <a class="navbar-brand" href="/">Content Management System</a>
    <a class="navbar-link" href="/news">View news</a>
    <%-- делает ссылку видимой только для Админа --%>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <a class="navbar-link" href="/user">View user</a>
    </sec:authorize>
    <sec:authentication property="principal" var="user"/>
    <h3 class="profile-title">${user.name} ${user.surname}</h3>
    <a href="#" onclick="document.getElementById('logoutForm').submit();">
        <span class="fa fa-sign-out"> </span>
        Log out
    </a>

    <form id="logoutForm" method="POST" action="${DOMAIN}/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</nav>