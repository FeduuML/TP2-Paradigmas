package ppunlam;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Artista {
	String nombre;
	List<Rol> roles = new LinkedList<>();
	List<Banda> bandas =new LinkedList<>();
	double costo;
	int actualCanciones;
	int maxCanciones;
	
	
	
	
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artista other = (Artista) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
}
