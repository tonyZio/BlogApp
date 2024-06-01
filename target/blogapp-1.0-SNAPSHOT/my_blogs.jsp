<%-- 
    Document   : my_blogs
    Created on : May 18, 2024, 12:10:31 AM
    Author     : tonyo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./css/estilo.css">
        <script src="./js/GetMyBlogs.js"></script>
        <script src="./js/Logout.js"></script>
    </head>
    <body>
        <header>
            <h1>Mis Blogs</h1>
            <nav>
                <% if (session.getAttribute("user") != null) { %>
                <a href="index.jsp">Blogs Públicos</a>
                <a id="logout" href="#">Cerrar Sesión</a>
                <%}%>
                </c:choose>
            </nav>
        </header>
        <main>
            <div id="mis_blogs"></div>
        </main>

    </body>
</html>
