package ppunlam;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class Recital {
	String nombre;
	List<Cancion> canciones = new LinkedList<>();
    List<Artista> artistasBase = new LinkedList<Artista>();
    List<ArtistaExterno> artistaExternos =  new LinkedList<ArtistaExterno>();

    public Recital(String nombre, List<Cancion> canciones, List<Artista> artistasBase, List<ArtistaExterno> artistaExternos) {
        this.nombre = nombre;
        this.canciones = canciones;
        this.artistasBase = artistasBase;
        this.artistaExternos = artistaExternos;
    }

    public void agregarCancion(Cancion cancion) throws RuntimeException{
		if(!canciones.contains(cancion))
			canciones.add(cancion);
		else
			throw new RuntimeException("La cancion ya esta en el recital");
	}
	
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

    public int calcularCosto(Artista candidato) {
        for(Artista a :  artistasBase){
            for(Banda b : candidato.getBandas()){
                if(a.getBandas().contains(b)){
                    return (int)(candidato.getCosto() * 0.5);
                }
            }
        }
        return candidato.getCosto();
    }
}
