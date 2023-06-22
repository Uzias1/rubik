/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Uzías
 */
public class Consultas {
    public Consultas(){
        
    }
    
    public int maxIdUser(){
        boolean bandera = true;
        Connection con = Conexion.obtenerConexion();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String q;
        int result = -1;
        try {
            //Se guarda el query en el string q
            q = "SELECT count(*) FROM Usuarios";
            //Modificacion del objeto ps para hacer la consulta
            ps = con.prepareStatement(q);
            
            //Objeto de resultado
            rs = ps.executeQuery();
            if(rs.next()) {
                result = rs.getInt("count(*)");
                //System.out.println("El entero es "+result);
            }
        } catch(Exception e){
            bandera = false;
        } finally {
            try {
                con.close();
                ps.close();
                q = "";
                rs.close();
            } catch (SQLException ex) {
                bandera = false;
                ex.printStackTrace();
            }
        }
        
        if(bandera)
            return result;
        return -1;
    }
    
    public int maxIdComb(){
        boolean bandera = true;
        Connection con = Conexion.obtenerConexion();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String q;
        int result = -1;
        try {
            //Se guarda el query en el string q
            q = "SELECT count(*) FROM Combinaciones";
            //Modificacion del objeto ps para hacer la consulta
            ps = con.prepareStatement(q);
            
            //Objeto de resultado
            rs = ps.executeQuery();
            if(rs.next()) {
                result = rs.getInt("count(*)");
                //System.out.println("El entero es "+result);
            }
        } catch(Exception e){
            bandera = false;
        } finally {
            try {
                con.close();
                ps.close();
                q = "";
                rs.close();
            } catch (SQLException ex) {
                bandera = false;
                ex.printStackTrace();
            }
        }
        
        if(bandera)
            return result;
        return -1;
    }
    
    public int maxIdTiempos(){
        boolean bandera = true;
        Connection con = Conexion.obtenerConexion();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String q;
        int result = -1;
        try {
            //Se guarda el query en el string q
            q = "SELECT count(*) FROM Tiempos";
            //Modificacion del objeto ps para hacer la consulta
            ps = con.prepareStatement(q);
            
            //Objeto de resultado
            rs = ps.executeQuery();
            if(rs.next()) {
                result = rs.getInt("count(*)");
                //System.out.println("El entero es "+result);
            }
        } catch(Exception e){
            bandera = false;
        } finally {
            try {
                con.close();
                ps.close();
                q = "";
                rs.close();
            } catch (SQLException ex) {
                bandera = false;
                ex.printStackTrace();
            }
        }
        
        if(bandera)
            return result;
        return -1;
    }
    
    public String generarCombinacion(){
        boolean result = true;
        Connection con = Conexion.obtenerConexion();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String q;
        String comb="";
        int max = maxIdComb();
        if(max <= 0){
            result = false;
        } else {
            int idComb = (int)(Math.random()*max+1);;
            try {
                //Hacer el query
                q = "Select Movimientos from Combinaciones where idCombinacion = ?";
                ps = con.prepareStatement(q);
                ps.setInt(1, idComb);
                //System.out.println("EL id a usar será :"+idComb);
                rs = ps.executeQuery();
                
                if(rs.next()){
                    comb = rs.getString("Movimientos");
                }
            } catch (Exception e) {
                result = false;
            } finally {
                //Aunque haya algun fallo con la bd se tienene que cerrar la conexion a la bd para evitar saturarla
                try {
                    con.close();
                    ps.close();
                    q = "";
                    rs.close();
                } catch (SQLException ex) {
                    result = false;
                }
            }
        }
        //System.out.println("La combinacion será: "+comb);
        return comb;
    }
    
    public int buscarCombByMov (String movimientos){
        boolean result = true;
        Connection con = Conexion.obtenerConexion();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String q;
        int id = -1;
        try {
            //Hacer el query
            q = "Select idCombinacion from Combinaciones where Movimientos = ?";
            ps = con.prepareStatement(q);
            ps.setString(1, movimientos);
            //System.out.println("EL id a usar será :"+idComb);
            rs = ps.executeQuery();

            if(rs.next()){
                id = rs.getInt("idCombinacion");
            }
        } catch (Exception e) {
            result = false;
        } finally {
            //Aunque haya algun fallo con la bd se tienene que cerrar la conexion a la bd para evitar saturarla
            try {
                con.close();
                ps.close();
                q = "";
                rs.close();
            } catch (SQLException ex) {
                result = false;
            }
        }
        
        //System.out.println("La combinacion será: "+comb);
        return id;
    }
    
    public String buscarCombById (int id){
        boolean result = true;
        Connection con = Conexion.obtenerConexion();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String q;
        String movimientos = "";
        try {
            //Hacer el query
            q = "Select Movimientos from Combinaciones where idCombinacion = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            //System.out.println("EL id a usar será :"+idComb);
            rs = ps.executeQuery();

            if(rs.next()){
                movimientos = rs.getString("Movimientos");
            }
        } catch (Exception e) {
            result = false;
        } finally {
            //Aunque haya algun fallo con la bd se tienene que cerrar la conexion a la bd para evitar saturarla
            try {
                con.close();
                ps.close();
                q = "";
                rs.close();
            } catch (SQLException ex) {
                result = false;
            }
        }
        
        System.out.println("La combinacion será: "+movimientos);
        return movimientos;
    }
    
    public boolean insertarUsuario(String name, String user, String pass){
        boolean exito = false;
        Connection con = Conexion.obtenerConexion();
        Statement ps = null;
        int id = maxIdUser()+1;
        String q;
        try {
            //Se guarda el query en el string q
            q = "insert into Usuarios values ("+id+
                    ", '"+name+"',"
                    + "'"+user+"',"
                    + "'"+pass+"',"
                    + "'Default'"
                    + ");";
            //Modificacion del objeto ps para hacer la consulta
            ps = con.createStatement();
            //Objeto de resultado
            ps.executeUpdate(q);
            exito = true;
        }catch (Exception ex) {
            System.out.println("Llego a la excepcion");
            ex.printStackTrace();
            exito = false;
        }finally{
            //Aunque haya algun fallo con la bd se tienene que cerrar la conexion a la bd para evitar saturarla
            try {
                con.close();
                ps.close();
                q = "";
            } catch (SQLException ex) {
                exito = false;
            }
        }
        return exito;
    }
    
    public boolean insertarCombinacion(String combinacion){
        boolean exito = false;
        Connection con = Conexion.obtenerConexion();
        Statement ps = null;
        int id = maxIdComb()+1;
        String q;
        try {
            //Se guarda el query en el string q
            q = "insert into Combinaciones values ("+id+
                    ", \""+combinacion+"\""
                    + ");";
            //Modificacion del objeto ps para hacer la consulta
            ps = con.createStatement();
            //Objeto de resultado
            ps.executeUpdate(q);
            exito = true;
        }catch (Exception ex) {
            System.out.println("Llego a la excepcion");
            ex.printStackTrace();
            exito = false;
        }finally{
            //Aunque haya algun fallo con la bd se tienene que cerrar la conexion a la bd para evitar saturarla
            try {
                con.close();
                ps.close();
                q = "";
            } catch (SQLException ex) {
                exito = false;
            }
        }
        return exito;
    }
    
    public boolean insertarTiempo(String fecha, char valido, int idCombinacion, int minutos, double segundos){
        boolean exito = false;
        Connection con = Conexion.obtenerConexion();
        Statement ps = null;
        int id = maxIdTiempos()+1;
        String q;
        try {
            //Se guarda el query en el string q
            q = "insert into Tiempos values ("+id+
                    ", '"+fecha+"',"
                    + "'"+valido+"',"
                    + ""+idCombinacion+","
                    + ""+minutos+","
                    + ""+segundos+""
                    + ");";
            //Modificacion del objeto ps para hacer la consulta
            ps = con.createStatement();
            //Objeto de resultado
            ps.executeUpdate(q);
            exito = true;
        }catch (Exception ex) {
            System.out.println("Llego a la excepcion");
            ex.printStackTrace();
            exito = false;
        }finally{
            //Aunque haya algun fallo con la bd se tienene que cerrar la conexion a la bd para evitar saturarla
            try {
                con.close();
                ps.close();
                q = "";
            } catch (SQLException ex) {
                exito = false;
            }
        }
        return exito;
    }
    
    public boolean insertarTiPe(int idTiempos, int idPenalizacion){
        boolean exito = false;
        Connection con = Conexion.obtenerConexion();
        Statement ps = null;
        String q;
        try {
            //Se guarda el query en el string q
            q = "insert into Ti_Pe values ("+idTiempos+
                    ", "+idPenalizacion+""
                    + ");";
            //Modificacion del objeto ps para hacer la consulta
            ps = con.createStatement();
            //Objeto de resultado
            ps.executeUpdate(q);
            exito = true;
        }catch (Exception ex) {
            System.out.println("Llego a la excepcion");
            ex.printStackTrace();
            exito = false;
        }finally{
            //Aunque haya algun fallo con la bd se tienene que cerrar la conexion a la bd para evitar saturarla
            try {
                con.close();
                ps.close();
                q = "";
            } catch (SQLException ex) {
                exito = false;
            }
        }
        return exito;
    }
    
    public boolean insertarUsTi(int idUsuario, int idTiempos){
        boolean exito = false;
        Connection con = Conexion.obtenerConexion();
        Statement ps = null;
        String q;
        try {
            //Se guarda el query en el string q
            q = "insert into Us_Ti values ("+idUsuario+
                    ", "+idTiempos+""
                    + ");";
            //Modificacion del objeto ps para hacer la consulta
            ps = con.createStatement();
            //Objeto de resultado
            ps.executeUpdate(q);
            exito = true;
        }catch (Exception ex) {
            System.out.println("Llego a la excepcion");
            ex.printStackTrace();
            exito = false;
        }finally{
            //Aunque haya algun fallo con la bd se tienene que cerrar la conexion a la bd para evitar saturarla
            try {
                con.close();
                ps.close();
                q = "";
            } catch (SQLException ex) {
                exito = false;
            }
        }
        return exito;
    }
    
    public boolean isUserSet (String user){
        boolean resultado = false;
        
        if(maxIdUser() == 0) {
            resultado = false;
        } else {
            Connection con = Conexion.obtenerConexion();
            ResultSet rs = null;
            PreparedStatement ps = null;
            String q;
            try {
                //Hacer el query
                q = "Select * from Usuarios where Nombre = ?";
                ps = con.prepareStatement(q);
                ps.setString(1, user);
                //System.out.println("EL id a usar será :"+idComb);
                rs = ps.executeQuery();

                if(rs.next()){
                    //Esto indica que hubo coincidencia
                    resultado = true;
                    System.out.println("El usuario registrado es: "+rs.getString("Nombre"));
                }
            } catch (Exception e) {
                resultado = false;
            } finally {
                //Aunque haya algun fallo con la bd se tienene que cerrar la conexion a la bd para evitar saturarla
                try {
                    con.close();
                    ps.close();
                    q = "";
                    rs.close();
                } catch (SQLException ex) {
                    resultado = false;
                }
            }
        }
        System.out.println("El valor de resultado será : "+resultado);
        return resultado;
    }
    
    public boolean isEmailSet (String email){
        boolean resultado = false;
        
        if(maxIdUser() == 0) {
            resultado = false;
        } else {
            Connection con = Conexion.obtenerConexion();
            ResultSet rs = null;
            PreparedStatement ps = null;
            String q;
            try {
                //Hacer el query
                q = "Select * from Usuarios where Correo = ?";
                ps = con.prepareStatement(q);
                ps.setString(1, email);
                //System.out.println("EL id a usar será :"+idComb);
                rs = ps.executeQuery();

                if(rs.next()){
                    //Esto indica que hubo coincidencia
                    resultado = true;
                }
            } catch (Exception e) {
                resultado = false;
            } finally {
                //Aunque haya algun fallo con la bd se tienene que cerrar la conexion a la bd para evitar saturarla
                try {
                    con.close();
                    ps.close();
                    q = "";
                    rs.close();
                } catch (SQLException ex) {
                    resultado = false;
                }
            }
        }
        
        return resultado;
    }
    
    public boolean verificaSesion (String user, String pass){
        boolean verificado = false;
        Connection con = Conexion.obtenerConexion();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String q;
        try {
            //Hacer el query
            q = "Select idUsuario from Usuarios where Correo = ? AND Pass = ?";
            ps = con.prepareStatement(q);
            ps.setString(1, user);
            ps.setString(2, pass);
            //System.out.println("EL id a usar será :"+idComb);
            rs = ps.executeQuery();

            if(rs.next()){
                //Esto indica que hubo coincidencia
                verificado = true;
            }
        } catch (Exception e) {
            verificado = false;
        } finally {
            //Aunque haya algun fallo con la bd se tienene que cerrar la conexion a la bd para evitar saturarla
            try {
                con.close();
                ps.close();
                q = "";
                rs.close();
            } catch (SQLException ex) {
                verificado = false;
            }
        }
        return verificado;
    }
    
    public int idUsuario (String user, String pass){
        int verificado = -1;
        Connection con = Conexion.obtenerConexion();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String q;
        try {
            //Hacer el query
            q = "Select idUsuario from Usuarios where Correo = ? AND Pass = ?";
            ps = con.prepareStatement(q);
            ps.setString(1, user);
            ps.setString(2, pass);
            //System.out.println("EL id a usar será :"+idComb);
            rs = ps.executeQuery();

            if(rs.next()){
                //Esto indica que hubo coincidencia
                verificado = rs.getInt("idUsuario");
            }
        } catch (Exception e) {
            verificado = -1;
        } finally {
            //Aunque haya algun fallo con la bd se tienene que cerrar la conexion a la bd para evitar saturarla
            try {
                con.close();
                ps.close();
                q = "";
                rs.close();
            } catch (SQLException ex) {
                verificado = -1;
            }
        }
        return verificado;
    }
    
    public int idTiempo (String date, int idCombinacion){
        int verificado = -1;
        Connection con = Conexion.obtenerConexion();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String q;
        try {
            //Hacer el query
            q = "Select idTiempos from Tiempos where fechaRegistro = ? AND idCombinacion = ?";
            ps = con.prepareStatement(q);
            ps.setString(1, date);
            ps.setInt(2, idCombinacion);
            //System.out.println("EL id a usar será :"+idComb);
            rs = ps.executeQuery();

            if(rs.next()){
                //Esto indica que hubo coincidencia
                verificado = rs.getInt("idTiempos");
            }
        } catch (Exception e) {
            verificado = -1;
        } finally {
            //Aunque haya algun fallo con la bd se tienene que cerrar la conexion a la bd para evitar saturarla
            try {
                con.close();
                ps.close();
                q = "";
                rs.close();
            } catch (SQLException ex) {
                verificado = -1;
            }
        }
        return verificado;
    }
    
    public String obtenerColores (int id){
        String color = "";
        Connection con = Conexion.obtenerConexion();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String q;
        try {
            //Hacer el query
            q = "Select Colores from Usuarios where idUsuario = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            //System.out.println("EL id a usar será :"+idComb);
            rs = ps.executeQuery();

            if(rs.next()){
                //Esto indica que hubo coincidencia
                color = rs.getString("Colores");
            }
        } catch (Exception e) {
            color = "";
        } finally {
            //Aunque haya algun fallo con la bd se tienene que cerrar la conexion a la bd para evitar saturarla
            try {
                con.close();
                ps.close();
                q = "";
                rs.close();
            } catch (SQLException ex) {
                color = "";
            }
        }
        return color;
    }
    
    public String obtenerNombre (int id){
        String color = "";
        Connection con = Conexion.obtenerConexion();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String q;
        try {
            //Hacer el query
            q = "Select Nombre from Usuarios where idUsuario = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            //System.out.println("EL id a usar será :"+idComb);
            rs = ps.executeQuery();

            if(rs.next()){
                //Esto indica que hubo coincidencia
                color = rs.getString("Nombre");
            }
        } catch (Exception e) {
            color = "";
        } finally {
            //Aunque haya algun fallo con la bd se tienene que cerrar la conexion a la bd para evitar saturarla
            try {
                con.close();
                ps.close();
                q = "";
                rs.close();
            } catch (SQLException ex) {
                color = "";
            }
        }
        return color;
    }
    
    public int obtenerMinutos (int idTiempos){
        int minutos = -1;
        Connection con = Conexion.obtenerConexion();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String q;
        try {
            //Hacer el query
            q = "Select minutos from Tiempos where idTiempos = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, idTiempos);
            //System.out.println("EL id a usar será :"+idComb);
            rs = ps.executeQuery();

            if(rs.next()){
                //Esto indica que hubo coincidencia
                minutos = rs.getInt("minutos");
            }
        } catch (Exception e) {
            minutos = -1;
        } finally {
            //Aunque haya algun fallo con la bd se tienene que cerrar la conexion a la bd para evitar saturarla
            try {
                con.close();
                ps.close();
                q = "";
                rs.close();
            } catch (SQLException ex) {
                minutos = -1;
            }
        }
        return minutos;
    }
    
    public int[] obtenerIdTiempobyMinutos (int minutos, int contador, int[] arregloids){
        int[] ids = new int[contador];
        int contador2 = 0;
        Connection con = Conexion.obtenerConexion();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String q;
        try {
            //Hacer el query
            q = "select idTiempos from tiempos where minutos = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, minutos);
            //System.out.println("EL id a usar será :"+idComb);
            rs = ps.executeQuery();

            while(rs.next()){
                //Esto indica que hubo coincidencia
                //buscar que los idTiempos pertenezcan al usuario
                if(estaEn(rs.getInt("idTiempos"), arregloids)){
                    ids[contador2] = rs.getInt("idTiempos");
                    //System.out.println("Entro, el valor es de "+minutos+" y el id es de "+ids[contador2]);
                    contador2++;
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            //Aunque haya algun fallo con la bd se tienene que cerrar la conexion a la bd para evitar saturarla
            try {
                con.close();
                ps.close();
                q = "";
                rs.close();
            } catch (SQLException ex) {
                return null;
            }
        }
        return ids;
    }
    
    public double obtenerSegundos (int idTiempos){
        double segundos = -1.0;
        Connection con = Conexion.obtenerConexion();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String q;
        try {
            //Hacer el query
            q = "Select segundos from Tiempos where idTiempos = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, idTiempos);
            //System.out.println("EL id a usar será :"+idComb);
            rs = ps.executeQuery();

            if(rs.next()){
                //Esto indica que hubo coincidencia
                segundos = rs.getDouble("segundos");
            }
        } catch (Exception e) {
            segundos = -1.0;
        } finally {
            //Aunque haya algun fallo con la bd se tienene que cerrar la conexion a la bd para evitar saturarla
            try {
                con.close();
                ps.close();
                q = "";
                rs.close();
            } catch (SQLException ex) {
                segundos = -1.0;
            }
        }
        return segundos;
    }
    
    public String obtenerFecha (int idTiempos){
        String fecha = "";
        Connection con = Conexion.obtenerConexion();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String q;
        try {
            //Hacer el query
            q = "Select fechaRegistro from Tiempos where idTiempos = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, idTiempos);
            //System.out.println("EL id a usar será :"+idComb);
            rs = ps.executeQuery();

            if(rs.next()){
                //Esto indica que hubo coincidencia
                fecha = rs.getString("fechaRegistro");
            }
        } catch (Exception e) {
            fecha = "";
        } finally {
            //Aunque haya algun fallo con la bd se tienene que cerrar la conexion a la bd para evitar saturarla
            try {
                con.close();
                ps.close();
                q = "";
                rs.close();
            } catch (SQLException ex) {
                fecha = "";
            }
        }
        return fecha;
    }
    
    public String obtenerNombreP (int idPenalizaciones){
        String nombre = "";
        Connection con = Conexion.obtenerConexion();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String q;
        try {
            //Hacer el query
            q = "Select Nombre from Penalizaciones where idPenalizaciones = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, idPenalizaciones);
            //System.out.println("EL id a usar será :"+idComb);
            rs = ps.executeQuery();

            if(rs.next()){
                //Esto indica que hubo coincidencia
                nombre = rs.getString("Nombre");
            }
        } catch (Exception e) {
            nombre = "";
        } finally {
            //Aunque haya algun fallo con la bd se tienene que cerrar la conexion a la bd para evitar saturarla
            try {
                con.close();
                ps.close();
                q = "";
                rs.close();
            } catch (SQLException ex) {
                nombre = "";
            }
        }
        return nombre;
    }
    
    public String obtenerValidez (int idTiempos){
        String valido = "";
        Connection con = Conexion.obtenerConexion();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String q;
        try {
            //Hacer el query
            q = "Select valido from Tiempos where idTiempos = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, idTiempos);
            //System.out.println("EL id a usar será :"+idComb);
            rs = ps.executeQuery();

            if(rs.next()){
                //Esto indica que hubo coincidencia
                valido = rs.getString("valido");
            }
        } catch (Exception e) {
            valido = "";
        } finally {
            //Aunque haya algun fallo con la bd se tienene que cerrar la conexion a la bd para evitar saturarla
            try {
                con.close();
                ps.close();
                q = "";
                rs.close();
            } catch (SQLException ex) {
                valido = "";
            }
        }
        return valido;
    }
    
    public int contarPenalizaciones (int idPenalizaciones){
        int penalizaciones = -1;
        Connection con = Conexion.obtenerConexion();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String q;
        try {
            //Hacer el query
            q = "Select count(*) from Ti_Pe where idTiempos = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, idPenalizaciones);
            //System.out.println("EL id a usar será :"+idComb);
            rs = ps.executeQuery();

            if(rs.next()){
                //Esto indica que hubo coincidencia
                penalizaciones = rs.getInt("count(*)");
            }
        } catch (Exception e) {
            penalizaciones = -1;
        } finally {
            //Aunque haya algun fallo con la bd se tienene que cerrar la conexion a la bd para evitar saturarla
            try {
                con.close();
                ps.close();
                q = "";
                rs.close();
            } catch (SQLException ex) {
                penalizaciones = -1;
            }
        }
        return penalizaciones;
    }
    
    public int[] obtenerPenalizaciones (int idTiempos){
        if(contarPenalizaciones(idTiempos) <= 0){
            return null;
        }
        int[] penalizaciones = new int[contarPenalizaciones(idTiempos)];
        int contador = 0;
        Connection con = Conexion.obtenerConexion();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String q;
        try {
            //Hacer el query
            q = "Select idPenalizaciones from Ti_Pe where idTiempos = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, idTiempos);
            //System.out.println("EL id a usar será :"+idComb);
            rs = ps.executeQuery();

            while(rs.next()){
                //Esto indica que hubo coincidencia
                penalizaciones[contador] = rs.getInt("idPenalizaciones");
                contador++;
            }
        } catch (Exception e) {
            return null;
        } finally {
            //Aunque haya algun fallo con la bd se tienene que cerrar la conexion a la bd para evitar saturarla
            try {
                con.close();
                ps.close();
                q = "";
                rs.close();
            } catch (SQLException ex) {
                return null;
            }
        }
        return penalizaciones;
    }
    
    public int contarTiempos (int idUsuario){
        int tiempos = -1;
        Connection con = Conexion.obtenerConexion();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String q;
        try {
            //Hacer el query
            q = "Select count(*) from Us_Ti where idUsuario = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, idUsuario);
            //System.out.println("EL id a usar será :"+idComb);
            rs = ps.executeQuery();

            if(rs.next()){
                //Esto indica que hubo coincidencia
                tiempos = rs.getInt("count(*)");
            }
        } catch (Exception e) {
            tiempos = -1;
        } finally {
            //Aunque haya algun fallo con la bd se tienene que cerrar la conexion a la bd para evitar saturarla
            try {
                con.close();
                ps.close();
                q = "";
                rs.close();
            } catch (SQLException ex) {
                tiempos = -1;
            }
        }
        return tiempos;
    }
    
    public int[] obtenerIdTiempos (int idUsuario){
        if(contarTiempos(idUsuario) <= 0){
            return null;
        }
        int [] arreglo = new int[contarTiempos(idUsuario)];
        int contador = 0;
        Connection con = Conexion.obtenerConexion();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String q;
        try {
            //Hacer el query
            q = "Select idTiempos from Us_Ti where idUsuario = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, idUsuario);
            //System.out.println("EL id a usar será :"+idComb);
            rs = ps.executeQuery();

            while(rs.next()){
                //Esto indica que hubo coincidencia
                arreglo[contador] = rs.getInt("idTiempos");
                contador++;
            }
        } catch (Exception e) {
            return null;
        } finally {
            //Aunque haya algun fallo con la bd se tienene que cerrar la conexion a la bd para evitar saturarla
            try {
                con.close();
                ps.close();
                q = "";
                rs.close();
            } catch (SQLException ex) {
                return null;
            }
        }
        return arreglo;
    }
    
    public boolean modificaUsuario(int id, String nombre, String correo, String pass, String colores){
        Connection con = Conexion.obtenerConexion();
        Statement ps = null;
        String q;
        try {
            q = "update Usuarios set "
                    + "Nombre = '"+nombre+"' "
                    + ", Correo = '"+correo+"' "
                    + ", Pass = '"+pass+"'"
                    + ", Colores = '"+colores+"' "
                    + "where idUsuario = "+id+"";
            ps = con.createStatement();
            ps.executeUpdate(q);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try{
                con.close();
                ps.close();
                q = "";
            } catch(Exception e){
                System.out.println("Fallo en el cierre de la BD");
            }
        }
    }
    
    public boolean estaEn(int i, int[] x){
       // System.out.println("Llego a la funcion");
        for (int j = 0; j < x.length; j++) {
           ///System.out.println("El valor de i es "+i+" y el valor del arreglo actual es de "+x[j]);
            if(i == x[j]){
                
                return true;
            }
        }
        return false;
    }
}
