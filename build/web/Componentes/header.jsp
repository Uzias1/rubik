<%-- 
    Document   : header
    Created on : 7/06/2023, 07:01:12 PM
    Author     : Uzías

    
<div class="d-flex mb-3">
  <div class="me-auto p-2">Flex item</div>
  <div class="p-2">Flex item</div>
  <div class="p-2">Flex item</div>
</div>
--%>

<%@page import="Soporte.Usuario"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
    <!-- Cargar el script para bootstrap -->
    <script src="js/bootstrap.bundle.min.js"></script>
    
    <!-- Barra de navegacion -->
    <header>
        <nav class="navbar navbar-expand-lg barra">
            <div class="container-fluid">
                <a class="navbar-brand" href="index.jsp"> 
                    <img src="img/cubo.png" class="d-inline-block align-text-middle" width="50" height="50">
                    Speedcube
                </a>
                <button class="navbar-toggler cfuente" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item"> <a class="nav-link" href="inicio.jsp"> Inicio </a> </li>
                        <li class="nav-item"> <a class="nav-link" href="index.jsp"> Timer </a> </li>
                        <!-- Verificar si hay sesion para cambiar los links -->
                        <%
                            HttpSession usuario;
                            Usuario u = new Usuario();
                            
                            usuario = request.getSession();
                            u = (Usuario)usuario.getAttribute("usuario");
                            
                            if (u == null) {
                        %>
                        </ul>
                        <span class="navbar-text">
                            <a class="nav-link" href="inicio_sesion.jsp"> Iniciar sesion &nbsp;</a>
                        </span>
                        <span class="navbar-text">
                            <a class="nav-link" href="registro.jsp"> Registrarse </a>
                        </span>
                        <%} else {
                        %>
                        <li class="nav-item"> <a class="nav-link" href="stats.jsp"> Estadisticas </a> </li>
                        </ul>  
                        <span class="navbar-text">
                            <a class="nav-link" href="editPerfil.jsp"> Perfil &nbsp;</a>
                        </span>
                        <span class="navbar-text">
                            <a class="nav-link" href="cerrarSesion"> Cerrar sesión </a>
                        </span>
                        <%
                        }%>
                    
                </div>  
            </div>
        </nav>
    </header>

