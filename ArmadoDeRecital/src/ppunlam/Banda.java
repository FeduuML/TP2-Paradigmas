package ppunlam;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Banda {
	String nombre;
	List<Artista> artistas = new LinkedList<>();
	
	public Banda(String nombre){
		this.nombre = nombre;
	}
	
	public void agregarArtista(Artista artista) throws RuntimeException{
		if(!artistas.contains(artista))
			artistas.add(artista);
		else
			throw new RuntimeException("El artista ya esta en la banda");
	}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Banda banda = (Banda) o;
        return Objects.equals(nombre, banda.nombre) && Objects.equals(artistas, banda.artistas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, artistas);
    }
}
