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

	public static List<Artista> cargarArtistas(String path, List<String> nombresArtistasBase) {
	    List<Artista> artistas = new LinkedList<>();

	    JSONArray array = LectorJSON.cargarArray(path);

	    for (Object o : array) {
	        JSONObject jsonArtista = (JSONObject) o;
	        artistas.add(jsonToArtista(jsonArtista,nombresArtistasBase));
	    }

	    return artistas;
	}

	public static Artista jsonToArtista(JSONObject json, List<String> nombresArtistasBase) {		
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
	    
	    for(String a: nombresArtistasBase) {
	    	if(a.equals(nombre)) {
	    		return new Artista(nombre, roles, bandas, costo, maxCanciones);
	    	}
	    }
	    return new ArtistaExterno(nombre, roles, bandas, costo, maxCanciones);
	}
	
	public static List<String> cargarNombresArtistasBase(String path) {
		List<String> nombresArtistasBase = new LinkedList<>();

	    JSONArray array = LectorJSON.cargarArray(path);

	    for (Object o : array) {
	        String nombre = (String) o;
	        nombresArtistasBase.add(nombre);
	    }

	    return nombresArtistasBase;
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

    public String getNombre() {
        return nombre;
    }

    public List<String> getRoles() {
        return roles;
    }

    public List<String> getBandas() {
        return bandas;
    }

    public int getCosto() {
        return costo;
    }

    public int getActualCanciones() {
        return actualCanciones;
    }

    public int getMaxCanciones() {
        return maxCanciones;
    }
}