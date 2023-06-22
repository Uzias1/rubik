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
public class MetodosGenerales {
    public MetodosGenerales(){
        
    }
    
    public String addChar(String cadena, int posicion, String letra) {
        
        String[] array = new String[cadena.length() + 1];
        String[] cadenaArray = cadena.split("");
        String res = "La posición se encuentra fuera de la longitud de la cadena";
        if(posicion < cadena.length()) {
            for(int i = 0; i < cadena.length(); i++) {
            String letraCadena = cadenaArray[i];
            if(i == posicion) {
                array[i] = letra;
                array[i+1] = letraCadena;
                i+=1;
            } else {
                array[i] = letraCadena;
            }
            
            res = String.join("", array);
            }
        }
        
        return res;
    }
    
    public String intoString(String textoReal, String textoInsert, int pos){
        StringBuilder stringBuilder= new StringBuilder(textoReal);
        stringBuilder.insert(pos,textoInsert);
        return stringBuilder.toString();
    }
}
