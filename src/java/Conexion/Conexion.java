/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Uzías
 */
public class Conexion {
    public static Connection obtenerConexion(){
        //Inicializar objetos necesarios
        Connection con = null;
        //Usuario registrado en mysql en el dispositivo
        String user = "root";//"b91a29fdd9a704";//"uhltze43t3xnfmyw";
        //Contraseña registrada
        String password = "n0m3l0";////n0m3l0;
        //Direccion de la base de datos, en este caso no está deployada en algun servidpor, por eso es localhost
        String url = "jdbc:mysql://localhost:3306/rubik";// jdbc:mysql://us-cdbr-east-02.cleardb.com:3306/heroku_9ad0f74b62bb348?characterEncoding=utf8
        //Try catch necesario en caso de que la conexion falle
        try {
            //nombre de la clase 
            Class.forName("com.mysql.jdbc.Driver");
            //establecer el objeto de la conexion
            con = DriverManager.getConnection(url, user, password);
            //si el objeto no es nulo, es decir, se realizó la conexion
            if (con != null) {
                System.out.println("Conexion a la BD exitosa");
            }else{
                System.out.println("Fallo conexion BD UnU");
            }
            //imprimir la excepcion si hace falta
        } catch (Exception ex) {
            System.out.println("Falló la conexión");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }finally{
            return con;
        }
    }
}
