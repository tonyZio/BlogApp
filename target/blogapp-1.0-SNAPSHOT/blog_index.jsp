<%-- 
    Document   : post_details
    Created on : May 18, 2024, 12:11:17 AM
    Author     : tonyo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title id="page_title"></title>
      <link rel="stylesheet" href="./css/estilo.css">
        <script src="./js/BlogUtil.js"></script>
    </head>
    <body>
        <header>
            <img id="blog_image" alt="Blog header image">
            <div class="blog-info">
                <h1 id="blog_title"></h1>
                <p id="blog_date"></p>
                <button id="newPostButton" class="action-button">Nuevo Post</button>
            </div>
        </header>
        <main>
            <div id="modalPost" class="modal">
                <div class="modal-content">
                    <div class="modal-title">
                        <h2 id="modal-action"></h2>
                        <p id="close" class="close">&times;</p>
                    </div>
                    <form id="addpost" method="post" action="#">
                        <input type="text" id="titulo" placeholder="Titulo" required>
                        <textarea id="cuerpo" name="cuerpo" required  placeholder="Cuerpo del post"></textarea>
                        <input type="text" id="imagenuri" placeholder="Link de la imagen">
                        <input type="text" id="etiquetas" name="etiquetas" placeholder="Etiquetas">
                        <button type="submit" id="btnFormPost"></button>
                    </form>
                </div>
            </div>

            <div id="mis_posts">

            </div>

            <div id="eliminar_post"></div>
        </main>
    </body>
</html>
