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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Uzías
 */
public class Modificar extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession u;
            u = request.getSession();
            Usuario usuario = new Usuario();
            usuario = (Usuario)u.getAttribute("usuario");
            
            if(usuario == null){
                response.sendRedirect("error.jsp?error=No_hay_Sesiones_activas");
            } else {
                Consultas con = new Consultas();
                //Obtener los datos del formulario
                String nombre = request.getParameter("name");
                String email = request.getParameter("email");
                String passA = request.getParameter("passA");
                String passN = request.getParameter("passN");
                String passN2 = request.getParameter("passN2");
                String colores = request.getParameter("colores");
                
                boolean cambioPass = true;
                boolean error = false;
                boolean match = false;
                
                /// System.out.println("El valor de colores es: "+colores); //Para confirmar que obtiene bien el select 
                
                //Si la nueva contraseña está vacía no cambiar nada
                if(passN.equals("") && passN2.equals("")){
                    cambioPass = false;
                } else {
                    //Verificar que las contraseñas sean iguales
                    if(passN.equals(passN2)){
                        error = false;
                    } else {
                        error = true;
                    }
                }
                
                if(usuario.getPass().equals(passA)){
                    match = true;
                }
                
                //Modificar al usuario
                //Verificando que no se tenga que cambiar la contraseña y sean iguales
                if(!cambioPass && !error && match){
                    con.modificaUsuario(usuario.getIdUsuario(), nombre, email, passA, colores);
                    Usuario mod = new Usuario(usuario.getIdUsuario(), nombre, email, passA, colores);
                    u.setAttribute("usuario", mod);
                }else if(cambioPass && !error && match){
                    con.modificaUsuario(usuario.getIdUsuario(), nombre, email, passN, colores);
                    Usuario mod = new Usuario(usuario.getIdUsuario(), nombre, email, passN, colores);
                    u.setAttribute("usuario", mod);
                }
                
                //Redireccionar al éxito
                response.sendRedirect("exito.jsp?exito=Usuario_modificado");
            }
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
