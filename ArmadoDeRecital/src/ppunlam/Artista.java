package ppunlam;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Artista {
	String nombre;
	List<String> roles = new LinkedList<>();
	List<String> bandas = new LinkedList<>();
	int costo;
	int actualCanciones = 0;
	int maxCanciones;
	
	public Artista(String nombre, List<String> roles, List<String> bandas, int costo, int maxCanciones) {
		this.nombre = nombre;
		this.roles = roles;
		this.bandas = bandas;
		this.costo = costo;
		this.maxCanciones = maxCanciones;
	}

	public static List<Artista> cargarArtistas(String path) {
	    List<Artista> artistas = new LinkedList<>();

	    JSONArray array = LectorJSON.cargarArray(path);

	    for (Object o : array) {
	        JSONObject jsonArtista = (JSONObject) o;
	        artistas.add(jsonToArtista(jsonArtista));
	    }

	    return artistas;
	}

	public static Artista jsonToArtista(JSONObject json) {		
		String nombre = (String) json.get("nombre");
		
	    JSONArray rolesArray = (JSONArray) json.get("roles");
	    List<String> roles = new LinkedList<>();
	    for(Object r : rolesArray) {
	    	roles.add((String) r);
	    }
	    
	    JSONArray bandasArray = (JSONArray) json.get("bandas");
	    List<String> bandas = new LinkedList<>();
	    for(Object r : bandasArray) {
	    	bandas.add((String) r);
	    }
	    
	    int costo = ((Long) json.get("costo")).intValue();
	    int maxCanciones = ((Long) json.get("maxCanciones")).intValue();
	    
	    return new Artista(nombre, roles, bandas, costo, maxCanciones);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artista other = (Artista) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
	@Override
	public String toString() {
        return "Nombre: " + nombre + "\nRoles: " + roles + "\nBandas: " + bandas + "\nCosto: " + costo + "\nMaxCanciones: " + maxCanciones + "\n";
    }
}