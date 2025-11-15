package ppunlam;

import java.util.LinkedList;
import java.util.List;

public class Recital {
	String nombre;
	List<Cancion> canciones = new LinkedList<>();
	
	
	public void agregarCancion(Cancion cancion) throws RuntimeException{
		if(!canciones.contains(cancion))
			canciones.add(cancion);
		else
			throw new RuntimeException("La cancion ya esta en el recital");
	}
	
	
}
