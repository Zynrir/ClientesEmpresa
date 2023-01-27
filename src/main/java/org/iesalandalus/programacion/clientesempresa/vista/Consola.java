package org.iesalandalus.programacion.clientesempresa.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	
	private Consola() {
		
	}
	public static void mostrarMenu() {
          System.out.println("0. Borrar cliente");
          System.out.println("1. Buscar cliente");
          System.out.println("2. Insertar cliente");
          System.out.println("3. Mostrar cliente");
          System.out.println("4. Mostrar clientes por fecha de nacimiento");
          System.out.println("5. Salir");
    }
	public static Opcion elegirOpcion() { 
	   int opcion;
		do {
	    opcion = Entrada.entero();
	    }while(opcion<0 || opcion>5);
	    	
	    switch (opcion) {
	        case 0:
	            return Opcion.BORRAR;
	        case 1:
	            return Opcion.BUSCAR;
	        case 2:
	            return Opcion.INSERTAR;
	        case 3:
	            return Opcion.MOSTRAR_TODOS;
	        case 4:
	            return Opcion.MOSTRAR_FECHA;
	        case 5:
	            return Opcion.SALIR;
	        default:
	            return Opcion.INSERTAR;}
	    	
	}
	 public static Cliente leerCliente() {
	        String dni, nombre, correo, telefono;
	        LocalDate fechaNacimiento;
	        Cliente cliente = null;
	       do { 
	        // Leemos el DNI
	        System.out.print("Introduce el DNI: ");
	        dni = Entrada.cadena();

	        // Leemos el nombre
	        System.out.print("Introduce el nombre: ");
	        nombre = Entrada.cadena();
	       
	        
	        System.out.print("Introduce el correo: ");
	        correo = Entrada.cadena();
	        
	        System.out.print("Introduce el telefono: ");
	        telefono = Entrada.cadena();
	        
	        // Leemos la fecha de nacimiento
	        System.out.print("Introduce la fecha de nacimiento (dd/MM/yyyy): ");
	        
	        
	            fechaNacimiento = leerFechaNacimiento();
	            try {
					cliente = new Cliente(dni,nombre, correo, telefono, fechaNacimiento);
				} catch (IllegalArgumentException e) {
					
				}
	       }while(cliente == null);
	        // Creamos y devolvemos el objeto cliente
	        return cliente;
	    }
	// public Cliente leerClienteDni() throws Exception {
	//        System.out.println("Introduce el DNI del cliente: ");
//	        String dni = Entrada.cadena();

	  //      for (Cliente cliente : coleccionClientes) {
	    //        if (cliente.getDni().equals(dni)) {
	        //        return cliente;
	      //      }
	        //}
	        //throw new IllegalArgumentException("No se ha encontrado ningún cliente con ese DNI");
    //}
	
public static LocalDate leerFechaNacimiento() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate fecha = null;
    while (fecha == null) {
        try {
            System.out.print("Introduce una fecha de nacimiento (dd/mm/yyyy): ");
            String fechaIntroducida = Entrada.cadena();
            fecha = LocalDate.parse(fechaIntroducida, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("La fecha introducida no es válida. Por favor, vuelve a intentarlo.");
        }
    }
    return fecha;
	}
}
	

