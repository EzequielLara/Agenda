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
    
    private static final int MAX_CONTACTOS = 20;
    private int numContactos;
    
    /** Declaro como estático el atributo Contacto ya que pertenecerá a la clase agenda
      *y todos los objetos contacto perteneceran al mismo array contactos
      */
    private static Contacto[] contactos;
    
    /*Ejercicio 8.Crea el constructor y los métodos get que se especifican. 
     *El método getContactos devolverá una copia del array de contactos
     */

    public Agenda() {
        
      /**el constructor inicializa el array de contactos y el numero de contactos
        *que irá aumentado según se añadan contactos y disminuira según se borren contactos
        */  
     contactos = new Contacto[MAX_CONTACTOS];
     numContactos=0;
    }

    public int getNumContactos() {
        
        return numContactos;
    }
    
    public Contacto[] getContactos(){
        
        Contacto [] contacto =new Contacto[MAX_CONTACTOS];
        
        contacto=contactos.clone();
        
        return contacto;
    }
    
    /**Ejercicio 9.Crea el método anadir para añadir un contacto a la agenda de forma que ésta
     * se quede ordenada por orden de inserción y sin que admita contactos repetidos.
     * Apóyate en los métodos privados buscarPrimerIndiceComprobandoExistencia e IndiceNoSuperaTamano. 
     * El método debe informar de todos los posibles errores mediante la excepcion OperationNotSupportedException:
     * ya existe ese contacto, el array está lleno, etc.*/
    
    
    private int buscarPrimerIndiceComprobandoExistencia(Contacto contacto)throws OperationNotSupportedException{
        // Se inicializa índece a -1 ya que 0 corresponde a una posición de array.
        int indice=-1;
        // Esta variable controlará si una posición del array es nula o esta llena.
        boolean posicionLibre=false;
        // Recorre la lista de contactos hasta que encuentre una posicion libre
        for(int i=0;i<contactos.length && !posicionLibre;i++)
        {
            if (contactos[i]==null)
            {
                posicionLibre=true;
                indice=i;
            }
            // Si encuentra un contacto con igual contenido lanzará excepción para avisar.
            else if (contactos[i].equals(contacto))
            {
                throw new OperationNotSupportedException("Ya existe un contacto con esos datos.");
            }               
        }
        
        return indice;
    }
    
    private boolean indiceNoSuperaTamano(int i){
        
        //valida si el indice introducido está dentro del array o no.
        if(i<=contactos.length){
            return true;
        }else
            return false;
    }
    
    public void anadir(Contacto contacto)throws OperationNotSupportedException{
       int i;
       
        
     /**Buscamos donde insertar el contacto en el Array de contactos. 
       *Debemos encontrar una posición vacía y para ello utilizo el método buscarPrimerIndiceComprobandoExistencia()
       */
        try
        {
            i=buscarPrimerIndiceComprobandoExistencia(contacto);
        }
        catch(OperationNotSupportedException e)
        {
            throw new OperationNotSupportedException("Ya existe un contacto con esos datos.");
        }
        /**También comprobamos que el indice se encuentra dentro del array.
         * Y si es así añadimos el contacto al array en esa posción i y ademas
         * aumentamos numeroContactos en 1 al haber creado un contacto nuevo para el array
         */
        if (indiceNoSuperaTamano(i)){
            
               contactos[i]=contacto;
               numContactos++;
            
        }else{
            throw new OperationNotSupportedException("La agenda está completa, no se puede añadir el contacto.");
        }
    }
    
    /**Ejercicio 10. Crea el método buscar que recibirá el nombre del contacto y devolverá el contacto. 
     * Apóyate en el método privado buscarIndiceCliente.*/
    
    
    private int buscarIndiceCliente(String nombre)
    {
        int indice=-1;
        
        for(int i=0;i<contactos.length;i++)
        {
            /** Para comparar el nombre introducido debo acceder primero a la poscion
              *  del array y luego al atributo nombre para comparar ambos*/
            
            if (contactos[i]!=null && contactos[i].getNombre().equalsIgnoreCase(nombre))
                indice=i;            
        }
        
        return indice;
    }
    
    public Contacto buscar(String nombre) throws OperationNotSupportedException{
    
        int i;
    
        /** No necesito crear un objeto, basta con introducir una palabra para realizar la búsqueda 
          *y el método me devolverá un indice con su posición.
          */
        
        i=buscarIndiceCliente(nombre);
        
        if (i==-1){
            
            throw new OperationNotSupportedException("El contacto buscado no se encuentra en la agenda");
            
        }else
        {
        
            return contactos[i];
        }
    }
    
    /** Ejercicio 11. Crea el método borrar que borrará un contacto de la lista dejando la misma compactada
     * (los contactos válidos al principio y los nulos al final). 
     * Apóyate en el método desplazarUnaPosicionHaciaIzquierda. 
     * El método debe informar de los posibles errores mediante la excepcion OperationNotSupportedException. */
    
    private void desplazarUnaPosicionHaciaIzquierda(int indice){
        
        /** Este for recorre el array desde el índice introducido por parámetro hasta la primera posición ocupada a su derecha.
         * Si no estuviese ocupada se pararia el recorrido.
         * Una vez encontrado, copia en la posición indice el contenido de esta última posición.
         */
       
        for (int i = indice; i < contactos.length && contactos[i] != null; i++) 
            {
              
               contactos[i]=contactos[i+1];
	    }
        
    
    }
    
    public void borrar(String nombre) throws OperationNotSupportedException{
    
        /**El método realiza una busqueda del nombre para ver que índice del array le pertenece.
         * Una vez encontrado desplaza las posiciones a la izquierda, quedando de este modo
         * el contacto sobreescrito (borrado) por el contacto siguiente. 
         * Si el índice pertenece al array y no es nulo se decrementa el atributo numContacto.
         */
      int i;
      
      i=buscarIndiceCliente(nombre);
      
      if (indiceNoSuperaTamano(i)==true){
          
          
          numContactos--;
        
          desplazarUnaPosicionHaciaIzquierda(i);
         
        }else{
      
          throw new OperationNotSupportedException( "Está intentando acceder a un contacto inexistente");
      
        }  
    
    }
}