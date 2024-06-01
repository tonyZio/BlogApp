<%-- 
    Document   : login
    Created on : May 18, 2024, 12:09:13 AM
    Author     : tonyo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Iniciar Sesión</title>
        <link rel="stylesheet" href="./css/estilo.css">
        <script src="./js/LoginUser.js"></script>
    </head>
    <body>
        <header>
            <h1>Iniciar Sesión</h1>
            <nav>
                <a href="index.jsp">Blogs Públicos</a>
            </nav>
        </header>
        <main>
            <form id ="form-login" action="#" method="post">
                <label for="email">Correo Electrónico:</label>
                <input type="email" id="email" name="email" required>

                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required>

                <button type="submit">Ingresar</button>
            </form>
        </main>
        <footer>
            <p>&copy; tonyco</p>
        </footer>
    </body>
</html>
