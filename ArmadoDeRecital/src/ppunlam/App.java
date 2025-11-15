package ppunlam;

import java.util.List;

public class App {
	public static void main(String[] args) {
		List<Artista> artistas = Artista.cargarArtistas("in/artistas.json");
		for(Artista a: artistas) {
			System.out.println(a);
		}
		
		/*
		JSONArray arrayCanciones = LectorJSON.cargarArray("in/recital.json");
		Recital.mostrar(arrayCanciones);
		
		JSONArray arrayArtistasContratados = LectorJSON.cargarArray("in/artistas-discografica.json");
		ArtistaDiscografica.mostrar(arrayArtistasContratados);
		*/
	}
}
