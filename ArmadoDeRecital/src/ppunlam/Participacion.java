package ppunlam;

public class Participacion {
	private Artista artista;
	private Cancion cancion; 
	private String rol;
	
	public Participacion(Artista artista, Cancion cancion, String rol) {
		this.artista = artista;
		this.cancion = cancion;
		this.rol = rol;
	} 
	
	public Boolean verificarMaxCanciones(Artista a) {
		if(a.getActualCanciones() >= a.getMaxCanciones())
			return false; // no puede
		else
			return true; 
	}
	
	
	
	
	
}
