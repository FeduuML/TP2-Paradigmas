package ppunlam;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Recital {
	public static void mostrar(JSONArray listaCanciones) {
		for(Object o : listaCanciones) {
			JSONObject cancion = (JSONObject) o;
			
			String titulo = (String) cancion.get("titulo");
		    JSONArray rolesRequeridos = (JSONArray) cancion.get("rolesRequeridos");
		    
			System.out.println("Titulo: " + titulo);
	        System.out.println("Roles requeridos: " + rolesRequeridos);
	        System.out.println("----------------------------");
		}
	}
}
