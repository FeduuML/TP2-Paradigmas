package ppunlam;

import java.util.List;

public class App {
	public static void main(String[] args) {
		List<String> nombresArtistasBase = Artista.cargarNombresArtistasBase("in/artistas-discografica.json");
		
		List<Artista> artistas = Artista.cargarArtistas("in/artistas.json",nombresArtistasBase);
		for(Artista a: artistas) {
			if(a instanceof ArtistaExterno) {
				System.out.println("Este es un artista externo");
			}
			else {
				System.out.println("Este es un artista base");
			}
			System.out.println(a);
		}
		
		List<Cancion> canciones = Cancion.cargarCanciones("in/recital.json");
		for(Cancion c: canciones) {
			System.out.println(c);
		}
	}
}
