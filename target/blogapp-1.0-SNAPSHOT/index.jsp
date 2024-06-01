<%-- 
   Document   : index
   Created on : May 17, 2024, 8:35:55 PM
   Author     : tonyo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Inicio</title>
        <link rel="stylesheet" href="./css/estilo.css">
        <script src="./js/GetBlogs.js"></script>
        <script src="./js/Logout.js"></script>
    </head>
    <body>
        <header>

            <div class="header-container">
                <h1>Blogging App</h1>
                <nav>
                    <% if (session.getAttribute("user") != null) { %>  
                    <a href="my_blogs.jsp">Mis Blogs</a>
                    <a href="index.jsp">Blogs Públicos</a>
                    <a id="logout" href="#">Cerrar Sesión</a>
                    <% } else { %>
                    <a href="login.jsp">Iniciar Sesión</a>
                    <% }%>
                </nav>
            </div>
        </header>
        <main>
            <h2>Últimos Blogs Públicos</h2>
            <div id="blogs"></div>
        </main>
        <footer>
            <p>&copy; tonyco 2024</p>
        </footer>
    </body>
</html>
