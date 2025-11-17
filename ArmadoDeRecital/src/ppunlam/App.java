package ppunlam;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class App {
	public static void main(String[] args) {
		List<String> nombresArtistasBase = Artista.cargarNombresArtistasBase("in/artistas-discografica.json");
		
		Map<String, Banda> bandas = new HashMap<>();
		List<Artista> artistas = Artista.cargarArtistas("in/artistas.json",nombresArtistasBase,bandas);
        List<ArtistaExterno> artistasExt = new LinkedList<ArtistaExterno>();
        List<Artista> artistaBase = new LinkedList<Artista>();
        

		for(Artista a: artistas) {
			if(a instanceof ArtistaExterno) {
				System.out.println("Este es un artista externo");
                artistasExt.add((ArtistaExterno) a);
                System.out.println("Artista externo agregado correctamente");

            }
			else {
				System.out.println("Este es un artista base");
                artistaBase.add((Artista) a);
                System.out.println("ArtistaBase agregado correctamente");
			}
			System.out.println(a);
		}
		
		List<Cancion> canciones = Cancion.cargarCanciones("in/recital.json");
		for(Cancion c: canciones) {
			System.out.println(c);
		}

        Artista a = artistas.get(0);
        if(a instanceof ArtistaExterno ext) {
            ext.entrenarArtista(Rol.VOZ_PRINCIPAL);
            System.out.println(ext);

        }
        else
            System.out.println("No es un artista externo");

        /*
        Recital quilmes = new Recital("Quilmes Rock", canciones, artistaBase, artistasExt);

        System.out.println(quilmes);
        System.out.println(quilmes.calcularCosto(artistasExt.get(0)));
        */

        Artista x = artistas.get(0);
        
        Cancion c1= canciones.get(0);

        Banda banda = x.getBandas().get(0);
        
        System.out.println("Artistas que pertenecen a la banda " + banda);
        for (Artista c : banda.getArtistas()) {
            System.out.println(c.getNombre());
        }
        
        System.out.println("Roles requeridos de la cancion " + c1.getTitulo());
        c1.agregarParticipacion(x, Rol.GUITARRA_ELECTRICA);
        c1.rolesFaltantes();
	}

}
