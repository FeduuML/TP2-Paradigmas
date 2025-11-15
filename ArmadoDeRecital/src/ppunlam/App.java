package ppunlam;

import org.json.simple.JSONArray;

public class App {
	public static void main(String[] args) {
		JSONArray arrayArtistas = LectorJSON.cargarArray("in/artistas.json");
		Artista.mostrar(arrayArtistas);
		
		JSONArray arrayCanciones = LectorJSON.cargarArray("in/recital.json");
		Recital.mostrar(arrayCanciones);
		
		JSONArray arrayArtistasContratados = LectorJSON.cargarArray("in/artistas-discografica.json");
		ArtistaDiscografica.mostrar(arrayArtistasContratados);
	}
}
