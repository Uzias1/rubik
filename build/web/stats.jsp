<%-- 
    Document   : stats
    Created on : 8/06/2023, 07:57:35 PM
    Author     : Uzías
--%>

<%@page import="Conexion.Consultas"%>
<%@page import="Soporte.MetodosGenerales"%>
<%@page import="Soporte.Usuario"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">        
        
        <!--
        <link href="css/bootstrap.min.css" rel="stylesheet"> -->
        <title>Estadisticas | Home</title>
        <%
            HttpSession usuario;
            Usuario u = new Usuario();

            usuario = request.getSession();
            u = (Usuario)usuario.getAttribute("usuario");

            if (u == null) {
            //Si no hay sesion, entonces redireccionar
            response.sendRedirect("error.jsp?error=Inicia_sesion_primero");
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
            Consultas con = new Consultas();
            int [] ids = con.obtenerIdTiempos(u.getIdUsuario());
            /*System.out.println("El valor de los ids es:");
            for (int i = 0; i < ids.length; i++) {
                    System.out.println(ids[i]);
                }*/
            String mejorTiempo = "Sin registros";
            String peorTiempo = "Sin registros";
            String promedioTiempo = "Sin registros";
            
            int minutos = 0;
            int segundos = 0;
            int ms = 0;
            
            int peorMinuto = -1;
            int contpeorMint = 0;
            double peorSegundo = -1.0;
            
            int mejorMinuto = 0;
            int contmejorMin = 0;
            double mejorSegundo = 50.0;
            
            if(ids != null){
                for (int i = 0; i < ids.length; i++) {
                        minutos += con.obtenerMinutos(ids[i]);
                        
                        //System.out.println("Encontró el minuto: "+con.obtenerMinutos(ids[i]));
                        segundos += con.obtenerSegundos(ids[i])/10;
                        ms += con.obtenerSegundos(ids[i])%10;
                        System.out.println("Los ms son "+ms);
                       // System.out.println("Encontró el segundo: "+con.obtenerSegundos(ids[i]));
                        
                        if(peorMinuto <= con.obtenerMinutos(ids[i])){
                            peorMinuto = con.obtenerMinutos(ids[i]);
                            contpeorMint++;
                        }
                        
                        if(mejorMinuto >= con.obtenerMinutos(ids[i])){
                            mejorMinuto = con.obtenerMinutos(ids[i]);
                            contmejorMin++;
                        }
                    //System.out.println("El iterador vale: "+i);
                }
                //Promedio de los minutos
                minutos = minutos/ids.length;
                //Promedio de los segundos
                segundos = segundos/ids.length;
                //Promedio de los ms
                ms = ms/ids.length;
                
                //Construir el tiempo promedio**************************
                String tiempo = (minutos+":"+segundos+"."+ms);
                MetodosGenerales gen = new MetodosGenerales();
                //String tiempo2 = tiempo.replaceAll("\\.","");
                /*
                int tiempoLength = tiempo.length();
                tiempoLength = tiempoLength;
                tiempo = gen.intoString(tiempo, ".", tiempoLength);*/
                
                promedioTiempo = tiempo;
                //***********************************************************
                
                //Se tiene el peor minuto
                //Hay que consultar todos los tiempos que tengan el peor minuto para sacar el peor tiempo
                //System.out.println("El peor minuto es "+peorMinuto+ " y el conteo es de "+contpeorMint);
                int [] idspeores = new int[contpeorMint]; 
                idspeores = con.obtenerIdTiempobyMinutos(peorMinuto, contpeorMint, ids);
                
                if(idspeores != null){
                    for (int i = 0; i < contpeorMint; i++) {
                            if(peorSegundo <= con.obtenerSegundos(idspeores[i])){
                                peorSegundo = con.obtenerSegundos(idspeores[i]);
                            }
                    }
                    //crear el String de peor tiempo
                    String tmpTIempo = peorMinuto + ":" + peorSegundo;
                    peorTiempo = tmpTIempo.replaceAll("\\.", "");
                    tmpTIempo = gen.intoString(peorTiempo, ".", tmpTIempo.length()-3);
                    peorTiempo = tmpTIempo;
                } else {
                    peorTiempo = "Problemas con la obtención";
                }
                
                //Obtención del mejor tiempo ######################################
                int [] idsmejores = new int[contmejorMin];
                idsmejores = con.obtenerIdTiempobyMinutos(minutos, contmejorMin, ids);
                
                if(idsmejores != null){
                    for (int i = 0; i < contmejorMin; i++) {
                            if(mejorSegundo >= con.obtenerSegundos(idsmejores[i])){
                                mejorSegundo = con.obtenerSegundos(idsmejores[i]);
                            }
                    }
                    
                    //Crear el String
                    String tmpMejor = mejorMinuto+":"+mejorSegundo;
                    mejorTiempo = tmpMejor.replaceAll("\\.", "");
                    tmpMejor = gen.intoString(mejorTiempo, ".", tmpMejor.length()-3);
                    mejorTiempo = tmpMejor;
                } else {
                    mejorTiempo = "Problemas con la obtencion";
                }
            }
        %>
        <!-- Agregando morris js -->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>

        <div class="container text-center mt-4">
            <div class="row">
                <div class="col col-md-4 mt-4">
                    <h1 class="text-center"> Estadisticas básicas </h1>
                    <h4 class="text-center mt-4"> Mejor tiempo: </h4>
                    <p class="text-center"> <span class="tiempos"> <%=mejorTiempo%> </span> </p>

                    <h4 class="text-center"> Peor tiempo: </h4>
                    <p class="text-center"> <span class="tiempos"> <%=peorTiempo%> </span> </p>

                    <h4 class="text-center"> Tiempo promedio: </h4>
                    <p class="text-center"> <span class="tiempos"> <%=promedioTiempo%> </span> </p>

                    <br>
                    <p class="text-center">
                        <a class="btn btn_primario" href="tiempos.jsp" > Consultar tiempos </a>
                    </p>
                </div>

                <div class="col col-md-8 mt-4 container-fluid">
                    <h1 class="text-center"> Porcentaje superior al promedio </h1>
                    <div id="grafica" class="mt-4">
                        <script>
                                <%
                                    int superior = 0;
                                    int inferior = 0;
                                if(ids != null){
                                    for (int i = 0; i < ids.length; i++) {
                                       //Variables necesarias
                                        System.out.println("EL tiempo promedio es: "+promedioTiempo);
                                        //Separar el tiempo en minutos y segundos
                                        String [] tiempoSeparado = promedioTiempo.split(":");
                                        String minuto = tiempoSeparado[0];
                                        String segms = tiempoSeparado[1];
                                        //Separar los seg de los ms
                                        String [] segundosms = segms.split("\\.");
                                        //Minutos: minuto || Segundos: segundosms[0] || Ms: segundosms[1]
                                        //Pasar a enteros
                                        int m = Integer.parseInt(minuto);
                                        int s = Integer.parseInt(segundosms[0]);
                                        int MS = Integer.parseInt(segundosms[1]);
                                        
                                        System.out.println("El valor a comparar es de "+con.obtenerMinutos(ids[i])+ " minutos y "+con.obtenerSegundos(ids[i]));
                                        if(con.obtenerMinutos(ids[i]) > m ){
                                            //Superior seguro
                                            superior++;
                                        } else if(con.obtenerMinutos(ids[i]) == m){
                                            //Verificar los segundos
                                            if(con.obtenerSegundos(ids[i])/10 > s){
                                                superior++;
                                            }else if(con.obtenerSegundos(ids[i])/10 == s){
                                                if(con.obtenerSegundos(ids[i])%10 >= MS){
                                                    superior++;
                                                }else {
                                                    inferior++;
                                                }
                                            } else {
                                                inferior++;
                                            }
                                        } else {
                                            inferior++;
                                        }
                                        
                                        
                                        System.out.println("La variable minutos vale "+minutos);
                                        System.out.println("La variable segundos vale "+segundos);
                                    }
                                }
                                %>
                                    Morris.Donut({
                                    element: 'grafica',
                                    resize: true,
                                    data: [
                                      {value: <%= superior%>, label: 'Superior'},
                                      {value: <%= inferior%>, label: 'Inferior'}
                                    ],
                                    backgroundColor: '#FFFFFF',
                                    labelColor: '#000000',
                                    colors: [
                                      '#D1071E',
                                      '#02B629'
                                    ]
                                  });
                        </script>
                    </div>
                    <p class="text-center"> <a class="btn btn_secundario" href="index.jsp"> Practicar </a> </p>
                </div>
            </div>
        </div>
        <jsp:include page="Componentes/footer.jsp" />
    </body>
</html>
