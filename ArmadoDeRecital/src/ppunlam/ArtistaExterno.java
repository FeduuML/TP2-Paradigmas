package ppunlam;

import java.util.List;

public class ArtistaExterno extends Artista implements Comparable<Artista>{
	public ArtistaExterno(String nombre, List<Rol> roles, List<Banda> bandas, int costo, int maxCanciones) {
		super(nombre, roles, bandas, costo, maxCanciones);
    }

    public void entrenar(Rol rol){
        if(!roles.contains(rol)){
            if(this.actualCanciones == 0) {
                roles.add(rol);
                int costoAux = this.costo;
                this.costo = (int) ((double) this.costo * 1.5);
                System.out.println("Entrenamiento exitoso! " + this.nombre + " agrego el rol de " + rol
                + " y su costo original " + costoAux +  " aumento un 50% y ahora es " + this.costo);
            }
            else
                System.out.println("El artista ya esta contratado para alguna cancion");
        }
        else
            System.out.println("El artista ya posee ese rol");
    }

}
