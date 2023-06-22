/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Conexion.Consultas;
import Soporte.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Uzías
 */
public class RegistrarTiempo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //Variables a utilizar
            String redirect = "";
            Consultas con = new Consultas();
            
            //Verificar que la sesión esté iniciada
            HttpSession u;
            u = request.getSession();
            Usuario usuario = new Usuario();
            usuario = (Usuario)u.getAttribute("usuario");
            
            if(usuario == null){
                redirect = "error.jsp?error=No_hay_Sesiones_activas";
            } else {
                String tiempo = request.getParameter("t");
                String obs = request.getParameter("o");
                String comb = request.getParameter("c");
                System.out.println("El tiempo fue de "+tiempo+" y el de observacion fue "+obs+" la combinacion fue: "+comb);
                
                //Los valores fueron obtenidos, ahora se deben registrar los valores en la BD
                //Verificar si la combinacion ya está registrada y sino hacer el registro
                int idComb = con.buscarCombByMov(comb);
                boolean insertado = true;
                if(idComb == -1){
                    //Significa que la combinacion no está registrada
                    insertado = con.insertarCombinacion(comb);
                } else {
                    insertado = true;
                }
                
                if(insertado){
                    //La combinacion se ha insertado, el tiempo total y de observación llegan así 00:00.0
                    //Obtener el id del usuario
                    int idUsuario = usuario.getIdUsuario();
                    
                    //Generar el idTIempo para después registrarlos en la tabla de Us_Ti
                    //Obtener el id de la combinacion
                    int idCombinacion = con.buscarCombByMov(comb);
                    char esValido = 'S';
                    
                    //booleanos para las penalizaciones
                    boolean limite = false;
                    boolean inspeccion = false;
                    boolean tardia = false;
                    
                    String minutos = "";
                    String segundos = "";
                    String temp = "";
                    
                    String [] arreglo = tiempo.split(":");
                    //En teoría debería haber solamente 2 posiciones en el arreglo
                    minutos = arreglo[0];
                    segundos = arreglo[1];
                    
                    if(minutos.contains(".")){
                       //En caso de que los minutos se hayan guardado en los segundos
                       temp = minutos;
                       minutos = segundos;
                       segundos = temp;
                    }
                    
                    int min = Integer.parseInt(minutos);
                    
                    String [] arreglo2 = segundos.split("\\.");
                    System.out.println("El valor de segundos es: "+segundos);
                    System.out.println("El tamaño del arreglo es: "+arreglo2.length);
                    String s = arreglo2[0];
                    String ms = arreglo2[1];
                    
                    if(s.length() < 2){
                        temp = s;
                        s = ms;
                        ms = temp;
                    }
                    
                    String segms = s+ms;
                    
                    double seg = Integer.parseInt(segms);
                    System.out.println("El valor de seg es "+seg);
                    
                    //Los valores a almancenar serán "min" y "seg"
                    //Penalizaciones y verificación si es válido el intento o no
                    //Verificar el tiempo de observación
                    String [] observacion = obs.split(":");
                    String obsMin = observacion[0];
                    String obsSeg = observacion[1];
                    
                    if(obsMin.charAt(0) == ' '){
                        obsMin = obsMin.substring(1);
                    }
                    int obsm = Integer.parseInt(obsMin);
                    
                    String [] observacionSegundos = obsSeg.split("\\.");
                    int obss = Integer.parseInt(observacionSegundos[0]);
                    
                    if(obss >= 15 && obss < 17){
                        //Penalizacion de id 2
                        inspeccion = true;
                        min = min+2;
                    }
                    
                    if(min >= 10 && seg > 0){
                        limite = true;
                        esValido = 'N';
                    }
                    
                    if(obss >= 17 || obsm > 0){
                        tardia = true;
                        esValido = 'N';
                    }
                    
                    //Obtener la fecha
                    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String date = sdf.format(new Date());
                    
                    System.out.println("La fecha es "+date);
                    
                    //Guardar el valor del tiempo al fin
                    boolean tiempoGuardado = con.insertarTiempo(date, esValido, idCombinacion, min, seg);
                    if(tiempoGuardado){
                        redirect = "exito.jsp?exito=Tiempo_registrado";
                        //Obtener el id del tiempo registrado para hacer las demás tablas
                        int idTiempo = con.idTiempo(date, idCombinacion);
                        
                        if(idTiempo > 0){
                            //Insertar valor del id en la tabla Ti_Pe y en la tabla Us_Ti
                            //Revisar las penalizaciones
                            if(limite){
                                con.insertarTiPe(idTiempo, 1);
                            }
                            
                            if(inspeccion){
                                con.insertarTiPe(idTiempo, 2);
                            }
                            
                            if(tardia){
                                con.insertarTiPe(idTiempo, 5);
                            }
                            
                            //Insertar el tiempo al usuario
                            boolean tiempoRegistradoUsuario = con.insertarUsTi(idUsuario, idTiempo);
                            
                            if(tiempoRegistradoUsuario){
                                redirect = "exito.jsp?exito=Tiempo_ligado";
                            } else {
                                redirect = "error.jsp?error=Tiempo_no_ligado";
                            }
                        } else {
                            //No encontró el tiempo
                            redirect = "error.jsp?error=Tiempo_NO_encontrado";
                        }
                    } else {
                        redirect = "error.jsp?error=Tiempo_no_registrado";
                    }
                }
            }
            
            response.sendRedirect(redirect);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
