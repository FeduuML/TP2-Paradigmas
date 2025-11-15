package ppunlam;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Artista {
	String nombre;
	List<Rol> roles = new LinkedList<>();
	List<Banda> bandas =new LinkedList<>();
	double costo;
	int actualCanciones;
	int maxCanciones;
		
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
		
	public static void mostrar(JSONArray listaArtistas) {
		for(Object o : listaArtistas) {
			JSONObject artista = (JSONObject) o;
			
			String nombre = (String) artista.get("nombre");
		    JSONArray roles = (JSONArray) artista.get("roles");
		    JSONArray bandas = (JSONArray) artista.get("bandas");
		    Long costo = (Long) artista.get("costo");
		    Long maxCanciones = (Long) artista.get("maxCanciones");
		    
			System.out.println("Nombre: " + nombre);
	        System.out.println("Costo: " + costo);
	        System.out.println("Max Canciones: " + maxCanciones);
	        System.out.println("Roles: " + roles);
	        System.out.println("Bandas: " + bandas);
	        System.out.println("----------------------------");
		}
	}
}