<%-- 
    Document   : error
    Created on : 14/06/2023, 02:17:48 PM
    Author     : Uzías
--%>

<%@page import="Soporte.Usuario"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <title>Exito</title>
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
            <div class="container-fluid pt-4">
                <div class="alert alert-success">
                    <h4 class="alert-heading"> ¡Operación exitosa! </h4>
                    <p class="text-center text-break fs-4 fw-light oscuro">
                        Mientras se encontraba navegando por nuestro sistema ha llegado hasta esta página, eso significa que la transacción que realizó fue un éxito y no debe preocuparse, a continuación se mostrará la transacción que provocó:
                    </p>
                    <% String exito = request.getParameter("exito"); %>
                    <p class="text-center fs-4 oscuro"><%=exito%></p>
                    <p class="text-center fs-6 text-break fw-light oscuro">Nota: en caso de que el código de éxito sea <strong>"null"</strong> se trata de una redirección general.</p>
                </div>
                <%
                    if(exito.equals("Usuario_Registrado")){
                        //Imprimir botón de iniciar sesión%>
                        <div class="container-fluid pt-4">
                            <p class="text-center">
                                <a href="inicio_sesion.jsp" class="btn btn_primario">¡Inicia sesión ahora!</a>
                            </p>
                        </div>
                <%
                    }
                %>
            </div>
        </main>
        
        <jsp:include page="Componentes/footer.jsp" />
    </body>
</html>
