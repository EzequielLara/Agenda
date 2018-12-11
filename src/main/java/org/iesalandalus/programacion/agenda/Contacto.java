/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.agenda;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ezk24
 */
public class Contacto {
    
    // Ejercicio 3. Crea la clase Contacto con sus atributos correspondientes.
    
    private String nombre, telefono, correo;
    
    // Las siguientes declaraciones de constantes contendrán los patrones para validar telefono y correo.
    
    private static final String ER_TELEFONO = "(^[6|9][0-9]{8}$)";
    private static final String ER_CORREO = "[.a-zA-Z0-9]+@[.a-zA-Z0-9]+.[a-zAZ]{2,4}";


          
    /** Ejercicio 4. Crea los métodos get y set. Ten en cuenta que el nombre no puede estar vacío
      *y que además no podemos modificar el nombre de un contacto ya creado, el teléfono debe
      *empezar por 6 o 9 y tener 9 dígitos en total y que el correo debe ser un correo válido.
      *Si no se cumple el método set correspondiente deberá lanzar una excepción del tipo IllegalArgumentException
      *con el mensaje adecuado. Utiliza dos atributos de clase que no se puedan modificar para guardar las 
      *expresiones regulares a validar
      */
  
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        
        /** Declaro el método como privado ya que no se puede modificar el nombre una vez introducido.
          * Compruebo que la cadena no sea null ni esté vacía. 
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
        
        /** Compilo el patrón del teléfono con Pattern para después comprobar si coincide
          * con el número introducido, si no coincide lanzo la excepción
          */
        
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
    control que en el caso anterior.*/
    
    public Contacto(String nombre, String telefono, String correo) {
        
       setNombre(nombre);
       setTelefono(telefono);
       setCorreo(correo);
    }
    
    
    /** Ejercicio 6. Crea los demás métodos que se muestran en el diagrama de clases,
       *teniendo en cuenta que el método toString nos devolverá una cadena con las iniciales
       *del nombre y encerrado entre corchetes el teléfono y el correo separados por comas.
       *También debes tener en cuenta que un contacto será igual que otro si sus nombres, 
       *ignorando mayúsculas y minúsculas, son iguales.
       */
    
 
    private String getIniciales(){
        
      /** Para extraer las iniciales debo identificar antes el número de palabras introducidas. 
       * Puede que se ingrese solo el nombre, un nombre compuesto, nombre + un apellido
       * o nombre y varios apellidos. Una vez identificadas las cadenas tendré que seleccionar
       * la primera letra de cada cadena e introducirlas como String en una variable a 
       * la que llamare "iniciales" y será la que devolverá el método*. 
       * Como las iniciales no se podrán cambiar a lo largo del programa declaro el método como privado
       * 1º tengo que transformar la cadena nombre en array con el método split.
       * 2º Con un for puedo usar cada palabra por separo para extraerles con una subcadena la letra que quiera
       * 3º Concatenarlas para quedarme tan solo con las iniciales
       */
      
       // Declaro e inicializo variable:
       
       String iniciales="";
       
       // Lanzo un aviso en el caso de que se solicite iniciales de un nombre null:
       
       if(this.nombre==null||this.nombre.isEmpty()){throw new IllegalArgumentException("Debe especificar antes un nombre para obtener las iniciales");} 
       
       //Transformo la cadena nombre en un array:
      
       String[] arrayNombre = this.nombre.split(" ");
      
       /** Con el bucle y el método substring estraigo la primera letra de cada palabra
         * y las voy concatenendo con el método concat()*/
       
       for(int i=0; i<arrayNombre.length; i++){
            
        iniciales = iniciales.concat(arrayNombre[i].substring(0,1));
       
        }
       
  //Por último paso todo el contenido a mayúsculas. 
    return iniciales.toUpperCase();
    
    }
    

    @Override
    public String toString() {
        return "Contacto: " + getIniciales() + " [" + telefono + "," + correo + "]";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.nombre);
        hash = 17 * hash + Objects.hashCode(this.telefono);
        hash = 17 * hash + Objects.hashCode(this.correo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contacto other = (Contacto) obj;
        // Uso el método toUpperCase para que la comparación no dependa de si se introducen los nombres con mayúsculas o minúsculas.
        if (!Objects.equals(this.nombre.toUpperCase(), other.nombre.toUpperCase())) {
            return false;
        }
        if (!Objects.equals(this.telefono, other.telefono)) {
            return false;
        }
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        return true;
    }
    
    
}
