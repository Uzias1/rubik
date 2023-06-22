<%-- 
    Document   : tiempos
    Created on : 8/06/2023, 08:27:52 PM
    Author     : Uzías
--%>

<%@page import="Soporte.MetodosGenerales"%>
<%@page import="Conexion.Consultas"%>
<%@page import="Soporte.Usuario"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">        
        <title>Estadisticas | Tiempos</title>
        <%
            HttpSession usuario;
            Usuario u = new Usuario();

            usuario = request.getSession();
            u = (Usuario)usuario.getAttribute("usuario");

            if (u == null) {
                response.sendRedirect("inicio_sesion.jsp");
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
            <div class="text-center container-fluid mt-4">
                <h1>Tiempos de armado registrados para: <%= u.getNombre() %></h1>
            </div>
            
            <div class="container-fluid table-responsive mt-4">
                <table class="table table-striped tablaStats">
                    <thead class="">
                        <tr class="align-middle">
                            <th scope="col"> Id </th>
                            <th scope="col"> Tiempo total </th>
                            <th scope="col"> Fecha de registro </th>
                            <th scope="col"> Penalizaciones </th>
                            <th scope="col"> Tiempo penalizado </th>
                            <th scope="col"> Válido </th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        Consultas con = new Consultas();
                        int [] ids = con.obtenerIdTiempos(u.getIdUsuario());
                        if(ids != null){
                            int leng = con.contarTiempos(u.getIdUsuario());
                            for (int i = 0; i < leng; i++) {
                                    //Variables necesarias
                                    String tiempo = con.obtenerMinutos(ids[i])+":"+con.obtenerSegundos(ids[i]);
                                    tiempo = tiempo.replaceAll("\\.","");
                                    MetodosGenerales gen = new MetodosGenerales();
                                    int tiempoLength = tiempo.length();
                                    tiempoLength = tiempoLength - 2;
                                    tiempo = gen.intoString(tiempo, ".", tiempoLength);
                                    String fecha = con.obtenerFecha(ids[i]);
                                    
                                    String penalizaciones = "";
                                    String tiempoP = "0";
                                    //Arreglo de penalizaciones
                                    int[] idp = con.obtenerPenalizaciones(ids[i]);
                                    if(idp == null){
                                        //Do nothing
                                    } else {
                                        
                                        for (int j = 0; j < idp.length; j++) {
                                                penalizaciones += con.obtenerNombreP(idp[j])+" ";
                                            }
                                        //Verificar las penalizaciones que contienen penalizacion de tiempo
                                        int tiempoPenalizado = 0;
                                        for (int j = 0; j < idp.length; j++) {
                                                if(idp[j] == 2 || idp[j] == 3 || idp[j] == 4){
                                                    tiempoPenalizado += 2;
                                                }
                                            }
                                        tiempoP = tiempoPenalizado+"";
                                    }
                                    
                                    String valido = con.obtenerValidez(ids[i]);
                        %>
                        <tr class="align-middle">
                            <th scope="row"> <%=ids[i]%> </th>
                            <td class="fs-3"> <%=tiempo%> </td>
                            <td> <%=fecha%> </td>
                            <td> <%=penalizaciones%> </td>
                            <td> <%=tiempoP%> </td>
                            <td> <%=valido%> </td>
                        </tr>
                        <%}}%>
                    </tbody>
                </table>
            </div>
        </main>
        
        <jsp:include page="Componentes/footer.jsp" />
    </body>
</html>
