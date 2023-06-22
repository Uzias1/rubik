<%-- 
    Document   : editPerfil
    Created on : 8/06/2023, 08:31:49 PM
    Author     : Uzías
--%>

<%@page import="Soporte.Usuario"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">        
        <title>Editar datos</title>
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
        <%

        usuario = request.getSession();
        u = (Usuario)usuario.getAttribute("usuario");
        //Evitamos que lleguen a esta página sin tener una sesión iniciada
        if(u == null){
            response.sendRedirect("error.jsp?error=No_hay_Sesiones_activas");
        }
        %>
        <main>
            <div class="container-fluid mt-2 p-4">
                <h1 class="text-center"> Edicion del perfil </h1>
                
                <form action="Modificar" method="post">
                    <fieldset>
                        <legend class="text-center" > Apariencia </legend>
                        <div class="form-group row m-2">
                            <label for="colores" class="form-label"> Seleccione una paleta de colores </label>
                            <select class="form-select" id="colores" name="colores">
                                <option> Default </option>
                                <option> Claro </option>
                                <option> Oscuro </option>
                            </select>
                        </div>
                    </fieldset>
                    
                    <fieldset>
                        <legend class="text-center"> Datos de la cuenta </legend>
                        
                        <div class="form-group row m-2">
                            <label for="name"> Nombre </label>
                            <input class="form-control" type="text" id="name" name="name" value="<%=u.getNombre()%>" required="required"/>
                            <br>
                            <label for="email"> Correo </label>
                            <input class="form-control" type="email" id="email" name="email" value="<%=u.getCorreo()%>" required="required"/>
                            <br>
                            <label for="passA"> Contraseña actual </label>
                            <input class="form-control" type="password" id="passA" name="passA" required="required"/>
                            <br>
                            <label for="passN"> Contraseña nueva </label>
                            <input class="form-control" type="password" id="passN" name="passN" />
                            <br>
                            <label for="passN2"> Confirmar contraseña </label>
                            <input class="form-control" type="password" id="passN2" name="passN2" />
                            <br>
                        </div>
                        
                        <div class="form-group row mt-4">
                            <p class="text-center">
                                <button class="btn btn_primario" type="submit"> Confirmar </button>
                                <button class="btn btn_secundario" type="reset"> Cancelar </button>
                            </p>
                        </div>
                    </fieldset>
                </form>
            </div>
        </main>
        
        <jsp:include page="Componentes/footer.jsp" />
    </body>
</html>
