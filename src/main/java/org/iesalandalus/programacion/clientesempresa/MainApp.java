package org.iesalandalus.programacion.clientesempresa;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
import org.iesalandalus.programacion.clientesempresa.modelo.negocio.Clientes;
import org.iesalandalus.programacion.clientesempresa.vista.Consola;
import org.iesalandalus.programacion.clientesempresa.vista.Opcion;
import org.iesalandalus.programacion.utilidades.Entrada;

public class MainApp {

	int NUM_MAX_CLIENTES;
	static Clientes clientes;
	
	public static void insertarCliente() {
            Cliente nuevoCliente = Consola.leerCliente();
            try {
				clientes.insertar(nuevoCliente);
			} catch (OperationNotSupportedException e) {
			}
            System.out.println("Cliente insertado correctamente.");
    }
	
	 public static void buscarCliente() {
	        System.out.println("Introduce el DNI del cliente a buscar:");
	        String dni = Entrada.cadena();
	        Cliente[] clientesCopia = clientes.get();
	        for(Cliente cliente : clientesCopia) {
	        	if (cliente != null && cliente.getDni().equals(dni)) { 
	        		System.out.println(cliente); }
	        }  
	  }
	 public static void borrarCliente() {
	        System.out.println("Introduce el DNI del cliente que quieres borrar:");
	        String dni = Entrada.cadena();
	        Cliente[] clientesCopia = clientes.get();
	        for(Cliente cliente : clientesCopia) {
	  
	        	if (cliente != null && cliente.getDni().equals(dni)) { 
	        		clientes.borrar(cliente);
	        		System.out.println("El cliente ya ha sido borrado");}
	        }  
	  }
	 public static void mostrarClientes() {
	        Cliente[] clientesCopia = clientes.get();
	        for(Cliente cliente : clientesCopia) {
	        if (cliente != null){
	        	System.out.println(cliente);
	        }
	     }
	 }
	 
	 public static void mostrarClientesfecha() {
	        System.out.println("Introduce la fecha del cliente a buscar:");
	        LocalDate fecha = Consola.leerFechaNacimiento();
	        Cliente[] clientesCopia = clientes.get();
	        for(Cliente cliente : clientesCopia) {
	        	if (cliente != null && cliente.getFechaNacimiento().equals(fecha)) { 
	        		System.out.println(cliente); }
	        }  
	  }
	 public static void ejecutarOpcion(Opcion opcion) {
	        switch(opcion) {
	           case BORRAR:
	                borrarCliente();
	                break; 
	            case BUSCAR:
	                buscarCliente();
	                break;
	            case INSERTAR:
	                insertarCliente();
	                break;
	            case MOSTRAR_TODOS:
	                mostrarClientes();
	                break;
	            case MOSTRAR_FECHA:
	                mostrarClientesfecha();
	                break;
	            case SALIR:
	                System.out.println("Saliendo del programa...");
	                break;
	        }
	    }
	 public static void main(String[] args) {
			// TODO Auto-generated method stub
		 clientes = new Clientes(8);
		 Opcion opcion;
		 do {
		        // Mostrar el menú
		        Consola.mostrarMenu();
		        
		        // Pedir la opción elegida
		        opcion = Consola.elegirOpcion();
		        
		        // Ejecutar la opción elegida
		        ejecutarOpcion(opcion);

		    } while (opcion != Opcion.SALIR);
		}
}