package ppunlam;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Artista implements Comparable<Artista>{
	String nombre;
	List<Rol> roles = new LinkedList<>();
	List<Banda> bandas = new LinkedList<>();
	int costo;
	int actualCanciones = 0;
	int maxCanciones;
	
	public Artista(String nombre, List<Rol> roles, List<Banda> bandas, int costo, int maxCanciones) {
		this.nombre = nombre;
		this.roles = roles;
		this.bandas = bandas;
		this.costo = costo;
		this.maxCanciones = maxCanciones;
	}

	public static List<Artista> cargarArtistas(String path, List<String> nombresArtistasBase, Map<String, Banda> bandas) {
	    List<Artista> artistas = new LinkedList<>();

	    JSONArray array = LectorJSON.cargarArray(path);

	    for (Object o : array) {
	        JSONObject jsonArtista = (JSONObject) o;
	        artistas.add(jsonToArtista(jsonArtista,nombresArtistasBase,bandas));
	    }

	    return artistas;
	}

	public static Artista jsonToArtista(JSONObject json, List<String> nombresArtistasBase, Map<String, Banda> repo) {		
		String nombre = (String) json.get("nombre");
		
	    JSONArray rolesArray = (JSONArray) json.get("roles");
	    List<Rol> roles = new LinkedList<>();
	    for(Object r : rolesArray) {
	    	roles.add(Rol.pasarStringRol(r));
	    }
	    
	    JSONArray bandasArray = (JSONArray) json.get("bandas");
	    List<Banda> bandas = new LinkedList<>();
	    Banda b = null;
	    for(Object r : bandasArray) {
	    	String nombreBanda = (String) r;
	    	b = getOrCreateBanda(repo,nombreBanda);
	    	bandas.add(b);
	    }
	    
	    int costo = ((Long) json.get("costo")).intValue();
	    int maxCanciones = ((Long) json.get("maxCanciones")).intValue();
	    
	    for(String a: nombresArtistasBase) {
	    	if(a.equals(nombre)) {
	    		Artista artista = new Artista(nombre, roles, bandas, costo, maxCanciones);
	    		for (Banda banda : bandas) {
	    		    banda.agregarArtista(artista);
	    		}
	    		return artista;
	    	}
	    }
	    Artista artista = new ArtistaExterno(nombre, roles, bandas, costo, maxCanciones);
	    for (Banda banda : bandas) {
	        banda.agregarArtista(artista);
	    }
		return artista;
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
	
	public static Banda getOrCreateBanda(Map<String, Banda> repo, String nombre) {
	    return repo.computeIfAbsent(nombre, n -> new Banda(n));
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

    public List<Rol> getRoles() {
        return roles;
    }

    public List<Banda> getBandas() {
        return bandas;
    }
    
    public boolean tieneRol(Rol r) {
    	return roles.contains(r);
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

    @Override
    public int compareTo(Artista o) {
        return (Integer.compare(this.costo, o.costo));
    }
}