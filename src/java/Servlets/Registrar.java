/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Conexion.Consultas;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Uzías
 */
public class Registrar extends HttpServlet {

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
        
            //Obtener los valores del formulario
            String usuario = request.getParameter("name");
            String correo = request.getParameter("email");
            String pass = request.getParameter("pass");
            
            String redirect = "";
            
            //Validar que no haya usuarios registrados con el usuario y/o email dados
            Consultas con = new Consultas();
            boolean e = con.isEmailSet(correo);
            System.out.println("El valor de usuario en el servlet es de "+usuario);
            System.out.println("El valor de correo en el servlet es de "+correo);
            System.out.println("El valor de contra en el servlet es de "+pass);
            boolean u = con.isUserSet(usuario);
            System.out.println("El valor del boolean en el servlet es: "+u);
            
            if(!u && !e){
               //Si eso no pasó entonces hacer el registro en la BD del usuario
                boolean registro = con.insertarUsuario(usuario, correo, pass);

                if(registro){
                    redirect = "exito.jsp?exito=Usuario_Registrado";
                } else {
                    redirect = "error.jsp?error=Registro_de_Usuario";
                } 
            }
            
            //Redireccionar a la página de error correspondiente:
            if(e){
                redirect = "error.jsp?error=Email_duplicado";
            }
            
            if(u){
                redirect = "error.jsp?error=Nombre_de_Usuario_duplicado";
            } 
            
            response.sendRedirect(redirect);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
