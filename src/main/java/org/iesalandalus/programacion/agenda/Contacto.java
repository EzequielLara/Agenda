/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.agenda;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ezk24
 */
public class Contacto {
    
    // Ejercicio 3. Crea la clase Contacto con sus atributos correspondientes.
    
    private String nombre, telefono, correo;
    private static final String ER_TELEFONO = "(^[6|9][0-9]{8}$)";
    private static final String ER_CORREO = "/[A-Z0-9._%+-]+@[A-Z0-9-]+.+.[A-Z]{2,4}/igm";
    
    
       
    /** Ejercicio 4. Crea los métodos get y set. Ten en cuenta que el nombre no puede estar vacío
    y que además no podemos modificar el nombre de un contacto ya creado, el teléfono debe
    empezar por 6 o 9 y tener 9 dígitos en total y que el correo debe ser un correo válido.
    Si no se cumple el método set correspondiente deberá lanzar una excepción del tipo IllegalArgumentException
    con el mensaje adecuado. Utiliza dos atributos de clase que no se puedan modificar para guardar las 
    expresiones regulares a validar*/

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        
        /** Declaro el método como privado ya que no se puede modificar el nombre una vez introducido.
          * compruebo que la cadena no sea null ni esté vacía. 
          * Empleo el método isEmpty() que devuelve true si lenght es igual a 0*/
        
        if ((nombre==null)||(nombre.isEmpty()== true)){
            
            throw new IllegalArgumentException("Debe establecer un nombre");
        }
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        
        /** Compilo el patrón del telefono para despues comprobar si coincide
         * con el número introducido, si no coincide lanzo la excepción*/
        
        if(telefono==null){ throw new IllegalArgumentException("No se ha creado ningún teléfono");}
        
        Pattern patronTelefono = Pattern.compile(ER_TELEFONO);
        
        Matcher comprobacionTelefono = patronTelefono.matcher(telefono);
        
       if (comprobacionTelefono.matches()){ 
           
            this.telefono = telefono;
        
       } else{
           
           throw new IllegalArgumentException("El número introducido no corresponde al patrón válido");
       } 
       
       
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        
        // Utilizo la misma lógica aplicada que para el metodo setTelefono().
        
        if(correo==null){ throw new IllegalArgumentException("No se ha creado ninguna dirección de correo");}
        
        Pattern patronCorreo = Pattern.compile(ER_CORREO);
        
        Matcher comprobacionCorreo = patronCorreo.matcher(correo);
        
       if (comprobacionCorreo.matches()){ 
           
            this.correo = correo;
        
       } else{
           
           throw new IllegalArgumentException("La dirección de correo no corresponde al patrón válido");
       } 
        
    }

    
    
    /** Ejercicio 5.Crea el constructor con los parámetros adecuados y que haga el mismo
    control que en el caso anterior.
    
    public Contacto(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }
    
 */
    
    
    
    
}
