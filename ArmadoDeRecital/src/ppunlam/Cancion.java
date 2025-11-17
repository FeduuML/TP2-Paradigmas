package ppunlam;

import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Cancion {
	String titulo;
	List<Rol> rolesRequeridos = new LinkedList<>();
	List<Participacion> participaciones = new LinkedList<Participacion>();
	
	public Cancion(String titulo, List<Rol> rolesRequeridos) {
		this.titulo = titulo;
		this.rolesRequeridos = rolesRequeridos;
	}
	
	public static List<Cancion> cargarCanciones(String path) {
	    List<Cancion> canciones = new LinkedList<>();

	    JSONArray array = LectorJSON.cargarArray(path);

	    for (Object o : array) {
	        JSONObject jsonCancion = (JSONObject) o;
	        canciones.add(jsonToCancion(jsonCancion));
	    }

	    return canciones;
	}
	
	public void agregarParticipacion(Artista artista, Rol rol) throws IllegalArgumentException{

		if (!artista.tieneRol(rol)) {
	        throw new IllegalArgumentException(
	            "El artista " + artista.getNombre() + " no puede cumplir el rol " + rol
	        );
	    }
		
		for (Participacion part : participaciones) {
	        if (part.getArtista().getNombre()==artista.getNombre()) {
	            throw new IllegalArgumentException(
	                "El artista " + artista.getNombre() + " ya tiene un rol en esta canción."
	            );
	        }
	    }

	    
	    int cantRolOcupado= (int)participaciones.stream().filter(p -> p.getRol() == rol).count();
	    int cantRolTotal=(int) rolesRequeridos.stream().filter(r -> r == rol).count();
	    
	    if(cantRolOcupado>=cantRolTotal) {
	    	throw new IllegalArgumentException("La canción no requiere el rol " + rol);
	    }
	    
	    Participacion p = new Participacion(artista, rol);
	    participaciones.add(p);
	}
	
	public void rolesFaltantes() {
		for (Rol rolReq : rolesRequeridos.stream().distinct().toList()) {
			int cantRolOcupado= (int)participaciones.stream().filter(p -> p.getRol() == rolReq).count();
		    int cantRolTotal=(int) rolesRequeridos.stream().filter(r -> r == rolReq).count();
		    if(cantRolOcupado<cantRolTotal) {
		    	System.out.println("Se requiere "+ rolReq + " cantidad: " + (cantRolTotal-cantRolOcupado));
		    }
		}
	}

	public static Cancion jsonToCancion(JSONObject json) {		
		String titulo = (String) json.get("titulo");
		
	    JSONArray rolesRequeridosArray = (JSONArray) json.get("rolesRequeridos");
	    List<Rol> roles = new LinkedList<>();
	    for(Object r : rolesRequeridosArray) {
	    	//System.out.println("Mira aca: "+ Rol.pasarStringRol(r) " " + r);
	    	
	    	roles.add(Rol.pasarStringRol(r));
	    }
	    
	    return new Cancion(titulo, roles);
	}
	
	@Override
	public String toString() {
        return "Titulo: " + titulo + "\nRoles requeridos: " + rolesRequeridos + "\n";
    }
	
	public String getTitulo() {
		return titulo;
	}
}
