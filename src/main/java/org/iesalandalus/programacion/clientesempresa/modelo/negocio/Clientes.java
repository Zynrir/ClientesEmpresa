package org.iesalandalus.programacion.clientesempresa.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;

public class Clientes {
	private int capacidad;
	private int tamano;
	public Cliente[] coleccionClientes; 

	
	
	public Clientes(int capacidad) {
		tamano =capacidad;
		this.capacidad = capacidad;
		coleccionClientes = new Cliente[tamano];
	}



	public int getCapacidad() {
		return capacidad;
	}



	public int getTamano() {
		return tamano;
	}



	public Cliente[] get() {
		return coleccionClientes;
	}
	
	public Cliente buscar(Cliente cliente) {
        for (int i = 0; i < tamano; i++) {
            if (coleccionClientes[i].equals(cliente)) {
                return new Cliente(coleccionClientes[i]);
            }
        }
        return null;
    }
	
	public void desplazarUnaPosicionHaciaIzquierda(int indice) {
	   if(coleccionClientes[indice+1] != null){
		if (indice < 0 || indice >= coleccionClientes.length) {
	        throw new IllegalArgumentException("El índice especificado no es válido");
	    }
	    Cliente temporal = new Cliente(coleccionClientes[indice]);
	    for (int i = indice; i < coleccionClientes.length - 1; i++) {
	        coleccionClientes[i] = coleccionClientes[i + 1];
	    }
	    if(capacidadSuperada(1)) {
	    	coleccionClientes[coleccionClientes.length-1] = temporal;
	       } else {
	    	    int index=0;
	    while (index < coleccionClientes.length && coleccionClientes[index] != null) {
            index++;
        }
        coleccionClientes[index-1] = temporal;
	       }
	    	   
	   
	}
	}
	
	public void borrar(Cliente cliente) {
        int indice = buscarIndice(cliente);
        if (indice == tamano+1) {
            throw new IllegalArgumentException("El cliente no se encuentra en la colección.");
        }
        coleccionClientes[indice]=null;
        desplazarUnaPosicionHaciaIzquierda(indice);
        capacidad++;
   
    }
    
    

	
	private int buscarIndice(Cliente cliente) {
		for (int i = 0; i < coleccionClientes.length; i++) {
            if (coleccionClientes[i] != null && equals(cliente)) {
                return i;
            }
        }
        return tamano+1;
    }
	
	 public void insertar(Cliente cliente) throws OperationNotSupportedException {
	        if (cliente == null) {
	            throw new IllegalArgumentException("No se puede insertar un cliente nulo");
	        }
	        if (buscarIndice(cliente) != coleccionClientes.length) {
	            throw new OperationNotSupportedException("El cliente ya existe en la colección");
	        }
	       if(capacidadSuperada(1)) {
	    	   throw new OperationNotSupportedException("Ya esta llena la lista");
	       } 
	       int index=0;
	       while (index < coleccionClientes.length && coleccionClientes[index] != null) {
	            index++;
	        }
	        coleccionClientes[index] = cliente;
	        capacidad--;
	    }
	    
	
	private boolean capacidadSuperada(int indice) {
		if(indice > capacidad) {
			return true;
		}
		return false;
		
	}

	private boolean tamanoSuperado(int indice) {
		if(indice > tamano) {
			return true;
		}
		return false;
	}	

}


	
	
