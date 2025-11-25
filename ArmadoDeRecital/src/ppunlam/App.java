package ppunlam;

import java.util.HashMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class App {
	public static void main(String[] args) {
        // menuRecital();

        // ----------------------------- CARGAMOS LOS ARTISTAS -----------------------------

        List<String> nombresArtistasBase = Artista.cargarNombresArtistasBase("in/artistas-discografica.json");
        Map<String, Banda> bandas = new HashMap<>();
        List<Artista> artistas = Artista.cargarArtistas("in/artistas.json", nombresArtistasBase, bandas);
        List<ArtistaExterno> artistasExt = new LinkedList<ArtistaExterno>();
        List<Artista> artistaBase = new LinkedList<Artista>();

        // ----------------------------- LISTA DE ARTISTAS EXTERNOS -----------------------------


        for (Artista a : artistas) {
            if (a instanceof ArtistaExterno) {
                artistasExt.add((ArtistaExterno) a);
                // Artista externo agregado correctamente

            } else {
                //Este es un artista base
                artistaBase.add((Artista) a);
                // ArtistaBase agregado correctamente
            }
        }


    System.out.println("--------------------------------------------------\n");

        // ----------------------------- CARGAMOS LAS CANCIONES DEL RECITAL -----------------------------

        List<Cancion> canciones = Cancion.cargarCanciones("in/recital.json");


        Recital quilmes = new Recital("Quilmes Rock", canciones, artistaBase, artistasExt);
        menuRecital(quilmes);

	}

	
	public static void menuRecital(Recital recital){
		Scanner sc = new Scanner(System.in);
		int opcion;
		
		do {
            System.out.println("----------------------------------------------");
            System.out.println("               MENÚ DEL RECITAL");
            System.out.println("----------------------------------------------");
            System.out.println("1) Ver roles faltantes para una cancion");
            System.out.println("2) Ver roles faltantes para todas las canciones");
            System.out.println("3) Contratar artistas para una cancion");
            System.out.println("4) Contratar artistas para todas las canciones");
            System.out.println("5) Entrenar artista seleccionado");
            System.out.println("6) Listar artistas contratados");
            System.out.println("7) Listar canciones y su estado . ");
            System.out.println("8) [PROLOG]");
            System.out.println("9) Salir");
            System.out.println("----------------------------------------------");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            
            System.out.println();
            
            switch (opcion) {
            case 1:{
            	
            	Cancion c;
            	sc.nextLine();
            	do {
            		System.out.println("Seleccione el nombre de la cancion:");
            		String nombre=sc.nextLine();
            		c = recital.getCanciones().stream()
                			.filter(a -> a.getTitulo().equalsIgnoreCase(nombre))
                		    .findFirst()
                		    .orElse(null);
            		 if (c == null) {
            	            System.out.println("No existe esa cancion, seleccione una distinta.");
            	        }
            	}while(c==null);
            	
            	
            	
                
                System.out.println("Roles requeridos de la cancion '" + c.getTitulo() + "'");
                System.out.println(c.rolesFaltantes());
                System.out.println("\n--------------------------------------------------\n");
            }
                break;
            case 2: {
                System.out.println("Roles requeridos del recital ");
                System.out.println(recital.rolesFaltantes());
            }
                break;
            case 3:{
            	Cancion c;
            	sc.nextLine();
            	do {
            		System.out.println("Seleccione el nombre de la cancion:");
            		String nombre=sc.nextLine();
            		c = recital.getCanciones().stream()
                			.filter(a -> a.getTitulo().equalsIgnoreCase(nombre))
                		    .findFirst()
                		    .orElse(null);
            		 if (c == null) {
            	            System.out.println("No existe esa cancion, seleccione una distinta.");
            	        }
            	}while(c==null);
            	c.contratarArtistas(recital.getArtistaExternos(), recital.getArtistasBase());
            }
                break;
            case 4: {
                System.out.println("Contratamos artistas para todo el recital: ");
                recital.contratarArtistasRecital();
            }
                break;
            case 5:{
            	ArtistaExterno artistaSel;
            	Rol rol;
            	sc.nextLine();
            	do {
            		System.out.println("Seleccione el nombre del artista:");
            		String nombre=sc.nextLine();
            		artistaSel = recital.getArtistaExternos().stream()
                			.filter(a -> a.getNombre().equalsIgnoreCase(nombre))
                		    .findFirst()
                		    .orElse(null);
            		 if (artistaSel == null) {
            	            System.out.println("No existe ese artista, seleccione uno distinto.");
            	        }
            	}while(artistaSel==null);
            	
            	
            	
            	do {
            		System.out.println("Seleccione el rol a asignar:");
            		String nombre=sc.nextLine();
            		rol=Rol.pasarStringRol(nombre);
            		 if (rol == null) {
            	            System.out.println("No existe ese rol, seleccione uno distinto.");
            	        }
            	}while(rol==null);
                
                artistaSel.entrenar(rol);
                System.out.println(artistaSel);

            }
                break;
            case 6: {
                Map<Artista, Integer> artistasContratados = recital.listarArtistasContratados();
                for (Artista a : artistasContratados.keySet()) {
                    System.out.println(a + "Costo total: " + artistasContratados.get(a));
                    System.out.println("\n--------------------------------------------------\n");
                }
            }
                break;
            case 7:
                recital.listarCanciones();
                break;

                case 8: {
                    System.out.println("Consulta en prolog: \n");

                    PrologIntegracion prolog = new PrologIntegracion();

                    int entrenamientos = prolog.calcularEntrenamientosMinimos(recital);

                    if (entrenamientos >= 0) {
                        System.out.println("\n Entrenamientos mínimos necesarios (roles que ningun artista base puede cubrir): " + entrenamientos);
                    } else {
                        System.out.println("\n Error al calcular entrenamientos");
                    }

                    System.out.println("\n--------------------------------------------------\n");
                }
                break;
            case 9:
            	System.exit(0);
            default:
                System.out.println("Opción inválida. Intente nuevamente.");
                break;
        }

        System.out.println();
            
		} while (opcion != 9);
	}
}