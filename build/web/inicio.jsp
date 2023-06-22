<%-- 
    Document   : inicio
    Created on : 8/06/2023, 07:32:11 PM
    Author     : Uzías
--%>

<%@page import="Soporte.Usuario"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <title>Conocenos</title>
        <%
            HttpSession usuario;
            Usuario u = new Usuario();

            usuario = request.getSession();
            u = (Usuario)usuario.getAttribute("usuario");

            if (u == null) {
        %>
        <link href="css/estilos.css" rel="stylesheet">
        <%}else {
            String estilo = u.getColores();
            switch(estilo){
                case "Default":%>
                <link href="css/estilos.css" rel="stylesheet"> <%
                break;
                case "Oscuro":%>
                <link href="css/estilos2.css" rel="stylesheet"><%
                break;
                case "Claro":%>
                <link href="css/estilos3.css" rel="stylesheet"><%
                break;
            }
        }%>
    </head>
    <body>
        <jsp:include page="Componentes/header.jsp" />
        
        <main>
            <div class="container-fluid p-4">
                <div class="container-fluid">
                    <p class="text-center">
                        <a href="ej.jsp"> <img src="img/logo.png" width="100" height="100"> </a>
                    </p>
                </div>
                <div>
                    <h1 class="text-center p-2 fs-1">Timer para speed cubing</h1>
                    <p class="text-center text-break fs-4 fw-light">
                        El speedcubing es una actividad en la que los participantes intentan resolver el cubo de Rubik y otros rompecabezas similares lo más rápido posible. Los speedcubers son aficionados que se dedican a resolver estos rompecabezas en el menor tiempo posible, utilizando técnicas avanzadas y algoritmos específicos.
                    </p>
                    <p class="text-center">
                        <a class="btn btn_primario m-2" href="reglas.jsp">Conoce las reglas</a>
                        <a class="btn btn_primario m-2" href="index.jsp">¡Empieza ahora!</a>
                    </p>
                </div>
            </div>
        </main>
        
        <jsp:include page="Componentes/footer.jsp" />
    </body>
</html>
