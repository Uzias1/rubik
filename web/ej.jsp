<%-- 
    Document   : ej
    Created on : 14/06/2023, 10:27:06 AM
    Author     : Uzías
--%>

<%@page import="Soporte.Usuario"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <title>Equipo</title>
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
            <div class="container-fluid">
                <div class="row">
                    <h1 class="text-center p-2 fs-1 mt-2">Conoce al equipo de trabajo:</h1>
                </div>
                <div class="row">
                    <div class="container-fluid ">
                        <img src="img/me.jpeg" width="240" height="320" class="img-fluid rounded mx-auto d-block">
                    </div>
                    <div class="container-fluid pt-2">
                        <p class="text-center fs-5">García Lucero Uzías</p>
                    </div>
                </div>
                <div class="row">
                    <h2 class="text-center">Aportación del "equipo":</h2>
                    <p class="text-center p-3">
                        <a class="btn btn_primario" href="videos/AP.pdf"> Ver aportación </a>
                    </p>
                </div>
                <div class="row">
                    <h3 class="text-center"> Proceso de creación del proyecto (humor): </h3>
                    <div class="container-fluid">
                        <p class="text-center">
                            <video src="videos/yo.mp4" class="object-fill-xl-contain" width="250" height="400" controls></video>
                            <video src="videos/gpt.mp4" class="object-fill-xl-contain" width="250" height="400" controls></video>
                        </p> 
                    </div>
                </div>
            </div>
        </main>
        
        <jsp:include page="Componentes/footer.jsp" />
    </body>
</html>
