package ppunlam;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

public class Recital {
	private String nombre;
	private List<Cancion> canciones = new LinkedList<>();
    private List<Artista> artistasBase = new LinkedList<Artista>();
    private List<ArtistaExterno> artistasExternos =  new LinkedList<ArtistaExterno>();

    public Recital(String nombre, List<Cancion> canciones, List<Artista> artistasBase, List<ArtistaExterno> artistaExternos) {
        this.nombre = nombre;
        this.canciones = canciones;
        this.artistasBase = artistasBase;
        this.artistasExternos = artistaExternos;
    }

    public void agregarCancion(Cancion cancion) throws RuntimeException{
		if(!canciones.contains(cancion))
			canciones.add(cancion);
		else
			throw new RuntimeException("La cancion ya esta en el recital");
	}
	
	public static void mostrar(JSONArray listaCanciones) {
		for(Object o : listaCanciones) {
			JSONObject cancion = (JSONObject) o;
			
			String titulo = (String) cancion.get("titulo");
		    JSONArray rolesRequeridos = (JSONArray) cancion.get("rolesRequeridos");
		    
			System.out.println("Titulo: " + titulo);
	        System.out.println("Roles requeridos: " + rolesRequeridos);
	        System.out.println("----------------------------");
		}
	}

    public int calcularCosto(Artista candidato) {
        for(Artista a :  artistasBase){
            for(Banda b : candidato.getBandas()){
                if(a.getBandas().contains(b)){
                    return (int)(candidato.getCosto() * 0.5);
                }
            }
        }
        return candidato.getCosto();
    }
    
    public HashMap<Rol,Integer> rolesFaltantes() {
    	Map<Rol, Integer> rolesRequeridos = new HashMap<>();

        for (Cancion c : canciones) {
            Map<Rol, Integer> rolesXCancionReq = c.rolesFaltantes();

            for (Map.Entry<Rol, Integer> e : rolesXCancionReq.entrySet()) {
                rolesRequeridos.merge(e.getKey(), e.getValue(), Integer::sum);
            }
        }

        return new HashMap<>(rolesRequeridos);
    }

public void contratarArtistasRecital(){
        for(Cancion c : canciones){
            System.out.println("CANCION A CONTRATAR: " + c.getTitulo());
            c.contratarArtistas(artistasExternos, artistasBase);
        }
}

public Map<Artista, Integer> listarArtistasContratados(){
        Map<Artista, Integer> artistasContratados = new HashMap<>();
        for(Cancion c : canciones){
            for(Participacion p : c.participaciones){
                if(!artistasContratados.containsKey(p.getArtista())) {
                    artistasContratados.put(p.getArtista(), p.getArtista().getCosto());
                }
                else{
                    artistasContratados.put(p.getArtista(), artistasContratados.get(p.getArtista()) + p.getArtista().getCosto());
                }
            }
        }

        return artistasContratados;
}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cancion> getCanciones() {
		return canciones;
	}

	public List<Artista> getArtistasBase() {
		return artistasBase;
	}

	public List<ArtistaExterno> getArtistaExternos() {
		return artistasExternos;
	}
}
