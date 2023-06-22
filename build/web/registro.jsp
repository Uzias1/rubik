<%-- 
    Document   : registro
    Created on : 8/06/2023, 07:52:26 PM
    Author     : Uzías
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <title>Registro</title>
        <link href="css/estilos.css" rel="stylesheet">
    </head>
    <body>
        <!-- Cargar el script para bootstrap -->
        <script src="js/bootstrap.bundle.min.js"></script>
        <main>
            <div class="container-fluid m-2 registro">
                <form method="post" action="Registrar">
                    <fieldset> 
                        <legend class="text-center"> Registro </legend>
                        <div class="form-group row m-2">
                            <label class="col-sm-2 col-form-label" for="name"> Nombre: </label>
                            <input class="form-control" type="text" id="name" name="name" placeholder="Nombre_Usuario" required="required">
                        </div>
                        <div class="form-group row m-2">
                            <label class="col-sm-2 col-form-label text-nowrap" for="email"> E-mail: </label>
                            <input class="form-control" type="email" id="email" name="email" placeholder="email@example.com" required="required">
                        </div>
                        <div class="form-group row m-2">
                            <label class="col-sm-2 col-form-label" for="pass"> Contraseña: </label>
                            <input class="form-control" type="password" id="pass" name="pass" required="required">
                        </div>
                        <div class="form-group row m-2 mt-4">
                            <p class="text-center">
                                <button class="btn btn_primario m-2" type="submit"> Registrarse </button>
                                <button class="btn btn_secundario m-2" type="reset"> Reiniciar </button>
                            </p>
                        </div>
                    </fieldset>
                </form>
                <div class="form-group row m-2">
                    <p class="text-center">
                        ¿Ya tienes una cuenta?
                        <a href="inicio_sesion.jsp" class="btn btn_primario">Iniciar sesion</a>
                    </p>
                </div>
            </div>
        </main>
    </body>
</html>
