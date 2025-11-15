package ppunlam;

import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Cancion {
	String titulo;
	List<String> rolesRequeridos = new LinkedList<>();
	
	public Cancion(String titulo, List<String> rolesRequeridos) {
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

	public static Cancion jsonToCancion(JSONObject json) {		
		String titulo = (String) json.get("titulo");
		
	    JSONArray rolesRequeridosArray = (JSONArray) json.get("rolesRequeridos");
	    List<String> roles = new LinkedList<>();
	    for(Object r : rolesRequeridosArray) {
	    	roles.add((String) r);
	    }
	    
	    return new Cancion(titulo, roles);
	}
	
	@Override
	public String toString() {
        return "Titulo: " + titulo + "\nRoles requeridos: " + rolesRequeridos + "\n";
    }
}
