package ppunlam;

public class Participacion {
	private Artista artista;
	private Rol rol;
	
	public Participacion(Artista artista, Rol rol) {
		this.artista = artista;
		this.rol = rol;
	} 
	
	public Boolean verificarMaxCanciones(Artista a) {
		if(a.getActualCanciones() >= a.getMaxCanciones())
			return false; // no puede
		else
			return true; 
	}

	public Artista getArtista() {
		return artista;
	}

	public Rol getRol() {
		return rol;
	}

	
	
	
	
	
}
