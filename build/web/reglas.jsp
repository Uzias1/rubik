<%-- 
    Document   : reglas
    Created on : 8/06/2023, 09:26:30 PM
    Author     : Uzías
--%>

<%@page import="Soporte.Usuario"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">        
        <title>Reglas</title>
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
                <h1 class="text-center p-2"> Las reglas que tomamos en cuenta para el desarrollo de la aplicacion son: </h1>
                <div class="alert alert-warning">
                    <h4 class="alert-heading">Tiempo límite</h4>
                    <p class="oscuro">
                        El tiempo limite que tendrá el jugador por intento será de 10 minutos.
                    </p>
                </div>
                <div class="alert alert-warning">
                    <h4 class="alert-heading">Inspección</h4>
                    <p class="oscuro">
                        Habrá un máximo de 15 segundos para inspeccionar el puzzle, en caso contrario se agregarán 2 segundos al tiempo final.
                    </p>
                </div>
                <div class="alert alert-warning">
                    <h4 class="alert-heading"> Tocar después de parar tiempo </h4>
                    <p class="oscuro">
                        En caso de que se toque el puzzle después de parar el tiempo se agregarán 2 segundos al tiempo final.
                    </p>
                </div>
                <div class="alert alert-warning">
                    <h4 class="alert-heading"> Falta de movimiento </h4>
                    <p class="oscuro">
                        Si hace falta <strong>un movimiento</strong> para la resolución del puzzle se agregarán 2 segundos.
                    </p>
                </div>
                <div class="alert alert-danger">
                    <h4 class="alert-heading"> Descalificación </h4>
                    <p class="oscuro">
                        Si el jugador tarda 17 segundos o más en hacer la inspección del puzzle se anulará el intento.
                    </p>
                </div>
                <div class="alert alert-danger">
                    <h4 class="alert-heading"> Falta de movimientos </h4>
                    <p class="oscuro">
                        Si al puzzle le hacen falta más de 2 movimientos para llegar a la resolución se considerará que el puzzle no fue resuelto.
                    </p>
                </div>
                <div class="alert alert-info">
                    <h4 class="alert-heading"> Reglas oficiales </h4>
                    <p class="oscuro">
                        Para más información acerca de las reglas oficiales de la organización mundial revise el siguiente enlace: <a href="https://www.worldcubeassociation.org/regulations/translations/spanish-american/#article-A-speedsolving">Reglas</a>
                    </p>
                </div>
                <div class="row">
                    <p class="text-center">
                        <a class="btn btn_primario" href="index.jsp"> ¡Estoy listo! </a>
                    </p>
                </div>
            </div>
        </main>
        
        <jsp:include page="Componentes/footer.jsp" />
    </body>
</html>
