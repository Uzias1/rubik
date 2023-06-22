<%-- 
    Document   : index
    Created on : 7/06/2023, 07:00:35 PM
    Author     : Uzías
--%>

<%@page import="Soporte.Usuario"%>
<%@page import="Conexion.Consultas"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Agregando bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <title>Timer</title>
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
        <!-- Cargar funciones js -->
        <script src="js/mezclas.js"></script>
        <% Consultas obj = new Consultas();
           String comb = obj.generarCombinacion();
           int max = obj.maxIdComb();
           int idComb = obj.buscarCombByMov(comb);
        %>
        <main>
            
            <div class="container-fluid">
                <div class="row m-2">
                    <div>
                        <h3 class="text-center">Combinacion:</h3>
                    </div>
                    <div class="alert alert-light text-center combina">
                        <p class="fs-3">
                            <span id="combinacion"><%=obj.buscarCombById(idComb)%></span>
                            <button class="btn btn_secundario" id="mezclar" onclick="scramblestring(0)"> <img class="img-fluid" src="img/flecha.png" width="20" height="20"/> </button>
                        </p>
                    </div>
                </div>


                <div class="row m-2 display-1 text-center ">
                    <div class=" timer reloj col text-end">
                        <p id="tiempoTranscurrido" class="text-center" name="tiempo">

                        </p>
                    </div>

                </div>
                            <div class="row text-center fs-6">
                                <p>Tiempo de observacion:</p>
                            </div>
                <div class="row text-center" id="contenedorMarcas" name="observacion">

                </div>
                <form action="RegistrarTiempo" method="post">
                    <div class="row m-2">
                            <input type="hidden" name="c" id="c" />
                            <input type="hidden" name="o" id="o" />
                            <input type="hidden" name="t" id="t" />
                            <div>
                                <p class="text-center">
                                    <button class="btn m-2 btn_primario" id="btnIniciar">Comenzar observacion</button>
                                    <button class="btn m-2 btn_primario" id="btnMarca" disabled>Comenzar armado</button>
                                    <button class="btn m-2 btn_primario" id="btnDetener" disabled>Terminar intento</button>
                                    <button type="submit" class="btn m-2 btn_secundario" id="btnGuardar" disabled>Guardar tiempo</button>
                                </p>
                            </div>
                    </div>
                </form>
            </div>
            
        </main>
                            
        <jsp:include page="Componentes/footer.jsp" />
        
        <!-- Cargar funciones js -->
        <script src="js/funciones.js"></script>
    </body>
</html>
