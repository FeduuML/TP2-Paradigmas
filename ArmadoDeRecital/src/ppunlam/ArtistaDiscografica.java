package ppunlam;

import org.json.simple.JSONArray;

public class ArtistaDiscografica {
	public static void mostrar(JSONArray listaArtistasContratados) {
		for(Object o : listaArtistasContratados) {
			String nombre = (String) o;
	        System.out.println("Nombre: " + nombre);
	        System.out.println("----------------------------");
		}
	}
}
