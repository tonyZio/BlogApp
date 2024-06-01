<%-- 
    Document   : public_blog
    Created on : 29 may 2024, 19:21:46
    Author     : Andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title id="page_title"></title>
        <link rel="stylesheet" href="./css/estilo.css">
        <script src="./js/PublicBlogUtil.js"></script>
        <script src="./js/Logout.js"></script>
    </head>
    <body>
        <main>  
            <div id="modalComment" class="modal">
                <div class="modal-content">
                    <div class="modal-title">
                        <h2 id="modal-action">Agregar un comentario</h2>
                        <p id="close" class="close">&times;</p>
                    </div>
                    <form id="addcomment" method="post" action="#">
                        <input type="text" id="autor" placeholder="Autor" required>
                        <textarea id="contenido" placeholder="Comentario" required></textarea>
                        <button type="submit" id="btnFormComment">Agregar Comentario</button>
                    </form>
                </div>
            </div>
            <div class="blog-feed">
                <div class="auth-area">
                    <nav>
                        <% if (session.getAttribute("user") != null) { %>
                        <a href="my_blogs.jsp">Mis Blogs</a>
                        <a href="index.jsp">Blogs Públicos</a>
                        <a id="logout" href="#">Cerrar Sesión</a>
                        <% } else { %>
                        <a href="index.jsp">Inicio</a>
                        <a href="login.jsp">Iniciar Sesión</a>
                        <% }%>
                    </nav>
                </div>
                <div class="blog-header">
                    <h2 id="blog_title"></h2>
                </div>
                <div id="blog_posts"></div>

            </div></main>

    </body>
</html>
