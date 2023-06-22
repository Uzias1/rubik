/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Soporte;

/**
 *
 * @author Uzías
 */
public class Usuario {
    public Usuario(){
        
    }
    
    private int idUsuario;
    private String Nombre;
    private String correo;
    private String pass;
    private String colores;
    
    public Usuario(int idUsuario, String Nombre, String correo, String pass, String colores){
        this.idUsuario = idUsuario;
        this.Nombre = Nombre;
        this.correo = correo;
        this.pass = pass;
        this.colores = colores;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getColores() {
        return colores;
    }

    public void setColores(String colores) {
        this.colores = colores;
    }
}
