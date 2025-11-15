package ppunlam;

import java.util.LinkedList;
import java.util.List;

public class Banda {
	String nombre;
	List<Artista> artistas = new LinkedList<>();
	
	public Banda(String nombre){
		this.nombre=nombre;
	}
	
	public void agregarArtista(Artista artista) throws RuntimeException{
		if(!artistas.contains(artista))
			artistas.add(artista);
		else
			throw new RuntimeException("El artista ya esta en la banda");
	}
}
