package org.iesalandalus.programacion.clientesempresa.modelo.dominio;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {
	private static final String ER_CORREO = "\\w+[\\.\\w]*@\\w+[\\.\\w]*\\.\\w{2,5}\\b\\s?";
	private static final String ER_DNI = "(\\\\d{8})([A-Za-z])";
	private static final String ER_TELEFONO = "\\d{9}";
	public static final String FORMATO_FECHA = "dd/MM/yyyy";
	private String nombre;
	private String dni;
	private String correo;
	private String telefono;
	private LocalDate fechaNacimiento;
	
	
	private static String formateaNombre(String nombre) {
	    nombre = nombre.trim();
	    nombre = nombre.replaceAll("\s\s+", " ");
	    String[] nombreArray = nombre.split("\\s+");
	    StringBuilder formateado = new StringBuilder();
	    for (String s : nombreArray) {
	        formateado.append(Character.toUpperCase(s.charAt(0))).append(s.substring(1).toLowerCase()).append(" ");
	    }
	    return formateado.toString().trim();
	}
	
	private static boolean comprobarLetraDni(String dni) {
	    final String LETRA_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";
	    Pattern pattern = Pattern.compile(ER_DNI);
	    Matcher matcher = pattern.matcher(dni);
	    if (matcher.matches()) {
	        int numero = Integer.parseInt(matcher.group(1));
	        char letra = matcher.group(2).toUpperCase().charAt(0);
	        int resto = numero % 23;
	        char letraEsperada = LETRA_DNI.charAt(resto);
	        return letra == letraEsperada;
	    }
	    return false;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if(nombre == null) {
			throw new NullPointerException("El nombre no puede estar vacio");
		}
		if (nombre.trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre no puede estar mal escrito");
		}
		this.nombre = formateaNombre(nombre);
	}

	public String getDni() {
		return dni;
	}

	private void setDni(String dni) {
		if(dni == null) {
			throw new NullPointerException("El dni no puede estar vacio");
		} 
		if (comprobarLetraDni(dni)) {
			throw new IllegalArgumentException("El dni esta mal escrito");
		}
			
		this.dni = dni;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		if(correo == null) {
			throw new NullPointerException("El correo no puede estar vacio");
		}
		if(correo.matches(ER_CORREO)) {
			throw new IllegalArgumentException("El correo esta mal escrito");
		}
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if(telefono == null) {
			throw new NullPointerException("El telefono no puede estar vacio");
		}
		if(telefono.matches(ER_TELEFONO)) {
			throw new IllegalArgumentException("El telefono esta mal escrito");
		}
		this.telefono = telefono;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		if(fechaNacimiento == null) {
			throw new NullPointerException("La fecha no puede estar vacia");
		}
		this.fechaNacimiento = fechaNacimiento;
	}

	public Cliente(Cliente cliente) {
		setNombre(cliente.getNombre());
		setDni(cliente.getDni());
		setCorreo(cliente.getCorreo());
		setTelefono(cliente.getTelefono());
		setFechaNacimiento(cliente.getFechaNacimiento());
	}


	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni);
	}

	public Cliente(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) {
		setNombre(nombre);
		setDni(dni);
		setCorreo(correo);
		setTelefono(telefono);
		setFechaNacimiento(fechaNacimiento);
	}	
	
	public String getIniciales() {
        String[] palabras = nombre.split(" ");
        String iniciales = "";
        for (String palabra : palabras) {
            iniciales += palabra.substring(0, 1);
        }
        return iniciales;
    }

	@Override
	public String toString() {
		return "Cliente [nombre=" + "(" + getIniciales() + ")" + nombre + ", dni=" + dni + ", correo=" + correo + ", telefono=" + telefono
				+ ", fechaNacimiento=" + fechaNacimiento + "]";
	}
}
