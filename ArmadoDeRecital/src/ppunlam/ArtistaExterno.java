package ppunlam;

import java.util.List;

public class ArtistaExterno extends Artista{
	public ArtistaExterno(String nombre, List<Rol> roles, List<Banda> bandas, int costo, int maxCanciones) {
		super(nombre, roles, bandas, costo, maxCanciones);
    }

    public void entrenarArtista(Rol rol){
        if(!roles.contains(rol)){
            if(this.actualCanciones == 0) {
                roles.add(rol);
                this.costo = (int) ((double) this.costo * 1.5);
                System.out.println("Entrenamiento exitoso! " + this.nombre + " agrego el rol de " + rol
                + " y su costo aumento un 50%");
            }
            else
                System.out.println("El artista ya esta contratado para alguna cancion");
        }
        else
            System.out.println("El artista ya posee ese rol");
    }

}
