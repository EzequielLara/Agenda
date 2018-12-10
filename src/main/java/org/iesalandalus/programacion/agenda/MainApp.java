package org.iesalandalus.programacion.agenda;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.utilidades.Entrada;

public class MainApp {
    private static final String ERROR="La acción no se ha podido ejecutar satisfactoriamente.";
    private static final String EXITO="Acción realizada con éxito";
    private static Agenda agenda=new Agenda(15);
   
    
	public static void main(String[] args) throws OperationNotSupportedException {
		System.out.println("Programa para gestionar una agenda de contactos");
                
        /**Crea los diferentes métodos que se indican en el diagrama de clases para permitir
         * que el método main nos muestre un menú que nos permitirá añadir un contacto, buscar un contacto,
         * borrar un contacto, listar todos los contactos no nulos y salir. El menú se repetirá mientras no elijamos
         * la opción salir. En todo caso se debe informar al usuario del resultado de la operación y de los posibles errores.
         */  
        
            int opcion;
            
         do{
             
            mostrarMenu();
            opcion = elegirOpcion();
            ejecutarOpcion(opcion);
            
         }while(opcion != 0);
            System.out.println("");
            System.out.println("Fin de la ejecución del programa Agenda");
    
	}
        
        
        
        private static void mostrarMenu(){
        
            System.out.println("--------------------------------------------------------");
            System.out.println("                 MENÚ DE LA AGENDA                      ");
            System.out.println("--------------------------------------------------------");
            System.out.println("");
            System.out.println("1. Añadir un contacto a la Agenda");
            System.out.println("2. Buscar un contacto");
            System.out.println("3. Borrar un contacto");
            System.out.println("4. Ver la lista de contactos");
            System.out.println("");
            System.out.println("0. Salir del programa");
            System.out.println("");
            System.out.println("----------------------------------------------- ");
        
        }
        
        private static int elegirOpcion(){
            
             int opcion;
             
            // Si deseo que se muestre el mensaje repetidamente hasta que se introduzca un número válido utilizo un do-while
            
             do{
           
                 System.out.println("Indique una de las opciones disponibles");
                 opcion=Entrada.entero();
             
             }while (opcion<0 || opcion>4);
        
            return opcion;
        }
        
        private static void ejecutarOpcion(int opcion) throws OperationNotSupportedException{
            
            
            switch (opcion){
                
                case 1:
                    
                    try{
                        
                         anadirContacto();
                     
                    }catch(OperationNotSupportedException e) 
                    {
                         System.out.println("ERROR:" + e.getMessage());
                    }
                    break;
             
                case 2:
                    
                    try{
                        
                         buscarContacto();
                     
                    }catch(OperationNotSupportedException e) 
                    {
                         System.out.println("ERROR:" + e.getMessage());
                    }
             
                    break;
                    
                case 3:
                    
                    try{
                         borrar();
                     
                    }catch(OperationNotSupportedException e) 
                    {
                         System.out.println("ERROR:" + e.getMessage());
                    }
                     
                    break;
                    
                case 4:
                    
                     listarAgenda();
              
                    break;  
            }
        }
            
            
        private static void anadirContacto() throws OperationNotSupportedException{
            
            // Primero el usuario crea un contacto y después se añade a la agenda.
            
            /** Como no tengo un setnombre creo la variable local nombre 
              *para que el usuario pueda introducir un nombre a través del constructor
              */
 
            String nombre,telefono,correo;
            System.out.println("------------------------------------------------");
            System.out.println("Introduzca los datos del contacto:");
            System.out.println("Introduzca primero el nombre");
            
            nombre=Entrada.cadena();
            
            System.out.println("Introduzca ahora el número de teléfono");
           
            telefono = Entrada.cadena();
            
            System.out.println("Por último introduzca la dirección de correo electronico");
            
            correo = Entrada.cadena();
                          
            try{
                Contacto contactoNuevo = new Contacto(nombre,telefono, correo);        
                agenda.anadir(contactoNuevo);
                System.out.println("--------------------------------------------");
                System.out.println(EXITO +" /CONTACTO CREADO/");
                System.out.println("--------------------------------------------");
                     
            }catch(OperationNotSupportedException e){
                    
                System.out.println(ERROR+ e.getMessage());
            }
            
            
        }
        
        private static void buscarContacto() throws OperationNotSupportedException{
            
            System.out.println("Introduzca el nombre del usuario que desea buscar");
            String nombre = Entrada.cadena();
            
            if (agenda.buscar(nombre)==null){
               
                System.out.println(ERROR + " /Contacto NO encontrado en la agenda/");
                
            }else{
                System.out.println(EXITO + " /CONTACTO ENCONTRADO/");
                System.out.println(agenda.buscar(nombre).toString());
            }
        }
        
        private static void borrar() throws OperationNotSupportedException{
        
            System.out.println("Indique el nombre del contacto a borrar");
            String nombre=Entrada.cadena();
            try{
                
                agenda.borrar(nombre);
                System.out.println("--------------------------------------------");
                System.out.println(EXITO + "/CONTACTO ELIMINADO/");
                System.out.println("--------------------------------------------");
            }catch(OperationNotSupportedException e){
                    
                System.out.println(ERROR + e.getMessage());
           
           }
        }
        
        private static void listarAgenda(){
            
            if(agenda.getContactos()== null){
            
                System.out.println(ERROR + "La agenda no tiene ningun contacto almacenado");
            }
        
            System.out.println("El listado de contactos de la Agenda es el siguiente: ");
            
            // Como se pide que solo se muestren las posiciones con valores creo la variable posicionvacia para controlar el bucle
            boolean posicionvacia=false;
            
            for (int i=0; i<agenda.getContactos().length&&!posicionvacia;i++){
                
                if(agenda.getContactos()[i]==null){
                
                posicionvacia=true;
                }else{
                     
                    System.out.println(agenda.getContactos()[i].toString()); 
                }
            }
        }
    }
    

