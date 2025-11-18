package ppunlam;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Cancion {
	String titulo;
	List<Rol> rolesRequeridos = new LinkedList<>();
	List<Participacion> participaciones = new LinkedList<Participacion>();
    Map<Rol, Integer> rolesMap = new HashMap<>();
	
	public Cancion(String titulo, List<Rol> rolesRequeridos) {
		this.titulo = titulo;
		this.rolesRequeridos = rolesRequeridos;
	}
/*
    public void listToMap() {
        for (Rol rol : this.rolesRequeridos) {
            if (!this.rolesMap.containsKey(rol)) {
                rolesMap.put(rol, 1);
            }
            else{
                rolesMap.put(rol, rolesMap.get(rol) + 1);
            }
        }
        System.out.println("Roles requeridos de la cancion ");
        System.out.println(rolesMap.toString());
    }

 */
	
	public static List<Cancion> cargarCanciones(String path) {
	    List<Cancion> canciones = new LinkedList<>();

	    JSONArray array = LectorJSON.cargarArray(path);

	    for (Object o : array) {
	        JSONObject jsonCancion = (JSONObject) o;
	        canciones.add(jsonToCancion(jsonCancion));
	    }

	    return canciones;
	}
	
	public void agregarParticipacion(Artista artista, Rol rol) throws IllegalArgumentException{

		if (!artista.tieneRol(rol)) {
	        throw new IllegalArgumentException(
	            "El artista " + artista.getNombre() + " no puede cumplir el rol " + rol
	        );
	    }
		
		for (Participacion part : participaciones) {
	        if (part.getArtista().getNombre()==artista.getNombre()) {
	            throw new IllegalArgumentException(
	                "El artista " + artista.getNombre() + " ya tiene un rol en esta canción."
	            );
	        }
	    }

	    
	    int cantRolOcupado= (int)participaciones.stream().filter(p -> p.getRol() == rol).count();
	    int cantRolTotal=(int) rolesRequeridos.stream().filter(r -> r == rol).count();
	    
	    if(cantRolOcupado>=cantRolTotal) {
	    	throw new IllegalArgumentException("La canción no requiere el rol " + rol);
	    }
	    
	    Participacion p = new Participacion(artista, rol);
	    participaciones.add(p);
	}
	
	public HashMap<Rol,Integer> rolesFaltantes() {
		Map<Rol, Integer> rolesReq = new HashMap<>();
		
		for (Rol rol : rolesRequeridos.stream().distinct().toList()) {
			int cantRolOcupado= (int)participaciones.stream().filter(p -> p.getRol() == rol).count();
		    int cantRolTotal=(int) rolesRequeridos.stream().filter(r -> r == rol).count();
		    if(cantRolOcupado<cantRolTotal) {
		    	rolesReq.put(rol, cantRolTotal-cantRolOcupado);
		    }
		}
		return new HashMap<>(rolesReq);
	}

    public void contratarArtistas(List<ArtistaExterno> artistasExt, List<Artista> artistaBase) {
       Map<Rol,Integer> rolesFaltantes = rolesFaltantes();
       List <Artista> todosArtistas = new LinkedList<>();
       todosArtistas.addAll(artistasExt);
       todosArtistas.addAll(artistaBase);
        rolesFaltantes.forEach((rol, cant) -> {
            while(rolesFaltantes.get(rol) > 0) {
                Artista art = seleccionarArtista(todosArtistas, rol);
                System.out.println(art.getNombre() + " fue contratado para ser " + rol);
                rolesFaltantes.put(rol, rolesFaltantes.get(rol) - 1);
                agregarParticipacion(art, rol);
                art.setActualCanciones(art.getActualCanciones() + 1);
                todosArtistas.remove(art);
            }
        });
    }


    public Artista seleccionarArtista(List<Artista> artistasDisp, Rol rol){
        //artistas que tienen el rol
        System.out.println("\n=== Artistas disponibles ===");
        artistasDisp.forEach(a -> System.out.println(a.getNombre() + " -> Roles: " + a.getRoles()));
        System.out.println("Buscando rol: " + rol);

        List<Artista> artistasConRol = artistasDisp.stream()
                .filter(a -> !this.participaciones.contains(a))
                .filter(a -> a.getRoles().contains(rol))
                .filter(a -> a.getActualCanciones() < a.getMaxCanciones())
                .toList();

        System.out.println("Encontrados CON rol, sin participaciones y con canciones dispnibles : " + artistasConRol.size());


        // artistas que NO tienen el rol
        List<Artista> artistasSinRol = artistasDisp.stream()
                .filter(a -> !this.participaciones.contains(a))
                .filter(a -> !a.getRoles().contains(rol))
                .filter(a -> a.getActualCanciones() < a.getMaxCanciones())
                .filter(a -> a instanceof ArtistaExterno)
                .toList();
        System.out.println("Encontrados SIN rol, sin participaciones y con canciones dispnibles: " + artistasSinRol.size());

        if(artistasConRol.isEmpty()) {
            if(artistasSinRol.isEmpty()) {
                return null; // excepcion...
            }
            Artista a2 = Collections.min(artistasSinRol);
            ((ArtistaExterno) a2).entrenar(rol);
            return a2;
        }

        Artista a1 = Collections.min(artistasConRol);

        if(artistasSinRol.isEmpty()) {
            return a1;
        }

        Artista a2 = Collections.min(artistasSinRol);

        if(a1.getCosto() < ((int)a2.getCosto() * 1.5)) {
            System.out.println(a1.getNombre() + " VA SIN ENTRENAR");
            return a1;
        }
        else{
            ((ArtistaExterno) a2).entrenar(rol);
            return a2;
        }

    }



	public static Cancion jsonToCancion(JSONObject json) {		
		String titulo = (String) json.get("titulo");
		
	    JSONArray rolesRequeridosArray = (JSONArray) json.get("rolesRequeridos");
	    List<Rol> roles = new LinkedList<>();
	    for(Object r : rolesRequeridosArray) {
	    	//System.out.println("Mira aca: "+ Rol.pasarStringRol(r) " " + r);
	    	
	    	roles.add(Rol.pasarStringRol(r));
	    }
	    
	    return new Cancion(titulo, roles);
	}
	@Override
	public String toString() {
        return "Titulo: " + titulo + "\nRoles requeridos: " + rolesRequeridos + "\n";
    }
	
	public String getTitulo() {
		return titulo;
	}
}
