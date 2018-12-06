/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.agenda;

/**
 *
 * @author Ezk24
 */
public class Agenda {
    
    // Ejercicio 7. Crea la clase Agenda con los atributos que se especifican.
    
    private static final int MAX_CONTACTOS = 10;
    private int numContactos;
    
    /*Ejercicio 8.Crea el constructor y los métodos get que se especifican. 
     *El método getContactos devolverá una copia del array de contactos
     */

    public Agenda(int numContactos) {
        
        this.numContactos = numContactos;
    }

    public int getNumContactos() {
        
        return numContactos;
    }
    
    public Contacto[] getContactos(){
      
        // Creo un array para la clase Contacto en la que se introducirá un número de contactos indicados en el getNumContactos.
        
        Contacto [] arrayContactos=new Contacto[getNumContactos()];
        
        
        /**Para crear otro array copia del primero debo declarar antes otro array de la clase Contacto*/
        
        Contacto [] contactos =new Contacto[getNumContactos()];
        
        // Para obtener una copia del arrayContactos utilizo el método System.arraycopy():
        
        System.arraycopy(arrayContactos, 0, contactos, 0, getNumContactos());
        
        return contactos;
    }
    
}