/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.agenda;

import javax.naming.OperationNotSupportedException;

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
        
        
        /**Para crear otro array copia del primero debo declarar antes otro array de la clase Contacto 
         * pero como este array guardará todos los contactos creados su límite será el indicado por MAX_CONTACTOS*/
        
        Contacto [] contactos =new Contacto[MAX_CONTACTOS];
        
        // Para obtener una copia del arrayContactos utilizo el método System.arraycopy():
        
        System.arraycopy(arrayContactos, 0, contactos, 0, getNumContactos());
        
        return contactos;
    }
    
    /**Ejercicio 9.Crea el método anadir para añadir un contacto a la agenda de forma que ésta
     * se quede ordenada por orden de inserción y sin que admita contactos repetidos.
     * Apóyate en los métodos privados buscarPrimerIndiceComprobandoExistencia e IndiceNoSuperaTamano. 
     * El método debe informar de todos los posibles errores mediante la excepcion OperationNotSupportedException:
     * ya existe ese contacto, el array está lleno, etc.*/
    
    
    private int buscarPrimerIndiceComprobandoExistencia(Contacto contacto)throws OperationNotSupportedException{
        
        int indice=-1;
        boolean posicionLibre=false;
        
        for(int i=0;i<getContactos().length && !posicionLibre;i++)
        {
            if (getContactos()[i]==null)
            {
                posicionLibre=true;
                indice=i;
            }
            else if (getContactos()[i].equals(contacto))
            {
                throw new OperationNotSupportedException("Ya existe un contacto con esos datos.");
            }               
        }
        
        return indice;
    }
    
    private boolean indiceNoSuperaTamano(int i){
        
        if(i>=getContactos().length){
            return true;
        }else
            return false;
    }
    
    public void anadir(Contacto contacto)throws OperationNotSupportedException{
       int i;
       
     //Creamos el contacto:
     
        contacto= new Contacto(contacto.getNombre(),contacto.getTelefono(), contacto.getCorreo());
        
     //Buscamos donde insertar el contacto en el Array de contactos
        try
        {
            i=buscarPrimerIndiceComprobandoExistencia(contacto);
        }
        catch(OperationNotSupportedException e)
        {
            throw new OperationNotSupportedException("Ya existe un contacto con esos datos.");
        }
        
        if (i!=-1)
            getContactos()[i]=contacto;
        else
            throw new OperationNotSupportedException("El array de libros está lleno.");
    }
    
    /**Ejercicio 10. Crea el método buscar que recibirá el nombre del contacto y devolverá el contacto. 
     * Apóyate en el método privado buscarIndiceCliente.*/
    
    
    private int buscarIndiceCliente(String nombre)
    {
        int indice=-1;
        
        for(int i=0;i<getContactos().length;i++)
        {
            /** Para comparar el nombre introducido debo acceder primero a la poscion
              *  del array y luego al atributo nombre para comparar ambos*/
            
            if (getContactos()[i]!=null && getContactos()[i].getNombre().equalsIgnoreCase(nombre))
                indice=i;            
        }
        
        return indice;
    }
    
    public Contacto buscar(String nombre) throws OperationNotSupportedException{
    
        int i;
    
        // No necesito crear un objeto, basta con introducir una palabra para realizar la búsqueda.
        
        i=buscarIndiceCliente(nombre);
        
        if (i==-1){
            
            throw new OperationNotSupportedException("El contacto buscado no se encuentra en la agenda");
            
        }else
        {
        
            return getContactos()[i];
        }
    }
    
    /** Ejercicio 11. Crea el método borrar que borrará un contacto de la lista dejando la misma compactada
     * (los contactos válidos al principio y los nulos al final). 
     * Apóyate en el método desplazarUnaPosicionHaciaIzquierda. 
     * El método debe informar de los posibles errores mediante la excepcion OperationNotSupportedException. */
    
    private void desplazarUnaPosicionHaciaIzquierda(int indice){
        
        // Este metodo desplaza a la derecha los contactos nulos dejando a la izquierda los contáctos válidos.
    
        for (int i = indice; i < getContactos().length - 1 && getContactos()[i] != null; i++) 
            {
                getContactos()[i] = getContactos()[i+1];
	    }
    
    }
    
    public void borrar(String nombre) throws OperationNotSupportedException{
    
      int i;
      i=buscarIndiceCliente(nombre);
      
      
      if (indiceNoSuperaTamano(i)==true){
          
          getContactos()[i]= null;
          
      }else{
      
          throw new OperationNotSupportedException("La agenda contiene un máximo de "+ getContactos().length + "contactos. Está intentando acceder a un contacto inexistente");
      
      }  
    
    }
}