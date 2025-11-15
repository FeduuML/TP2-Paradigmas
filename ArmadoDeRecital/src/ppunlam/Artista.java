package ppunlam;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Artista {
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
