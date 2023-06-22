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
public class Sesion extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //Obtener los valores del formulario
            String correo = request.getParameter("email");
            String pass = request.getParameter("pass");
            
            //Verificar que los datos sean correctos
            Consultas con = new Consultas();
            boolean verificado = con.verificaSesion(correo, pass);
            
            if(verificado){
                //Aquí se creará la sesión
                int idU = con.idUsuario(correo, pass);
                String colores = con.obtenerColores(idU);
                String nombre = con.obtenerNombre(idU);
                Usuario usu = new Usuario(idU, nombre, correo, pass, colores);
                
                //Creando el objeto más importante
                HttpSession sesionUsuario = request.getSession(true);
                //Asignamos el objeto del usuario a la sesión
                sesionUsuario.setAttribute("usuario", usu);
                //Tiempo de vida
                sesionUsuario.setMaxInactiveInterval(3000 * 3600);
                
                //Redireccionando a éxito
                response.sendRedirect("exito.jsp?exito=Inicio_de_Sesion_Exitoso");
            } else {
                response.sendRedirect("error.jsp?error=Inicio_de_Sesion_Incorrecto_Verifique_Datos");
            }
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
