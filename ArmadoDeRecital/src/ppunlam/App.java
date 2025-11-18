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

        System.out.println("LISTADO DE ARTISTAS\n");

        for (Artista a : artistas) {
            if (a instanceof ArtistaExterno) {
                System.out.println("Este es un artista externo");
                artistasExt.add((ArtistaExterno) a);
                // System.out.println("Artista externo agregado correctamente");

            } else {
                System.out.println("Este es un artista base");
                artistaBase.add((Artista) a);
                // System.out.println("ArtistaBase agregado correctamente");
            }
            System.out.println(a);
        }

        System.out.println("--------------------------------------------------\n");

        // ----------------------------- CARGAMOS LAS CANCIONES DEL RECITAL -----------------------------

        List<Cancion> canciones = Cancion.cargarCanciones("in/recital.json");






        // ----------------------------- LISTA DE CANCIONES DEL RECITAL -----------------------------
        /*
        System.out.println("LISTADO DE CANCIONES\n");
        for (Cancion c : canciones) {
            System.out.println(c);
        }

        System.out.println("--------------------------------------------------\n");

        // ----------------------------- INTENTAMOS ENTRENAR AL ARTISTA BRIAN MAY PARA QUE ADQUIERA EL ROL DE VOZ PRINCIPAL -----------------------------

        Artista BrianMay = artistas.get(0);
        if (BrianMay instanceof ArtistaExterno ext) {
            ext.entrenar(Rol.VOZ_PRINCIPAL);
            System.out.println(ext);
        } else
            System.out.println("'" + BrianMay.getNombre() + "' es un artista base, no puede ser entrenado.\n");

        System.out.println("--------------------------------------------------\n");

        // ----------------------------- MOSTRAMOS LOS MIEMBROS DE LA BANDA QUEEN -----------------------------

        Banda banda = BrianMay.getBandas().get(0);
        System.out.println("Artistas que pertenecen a la banda " + banda);
        for (Artista a : banda.getArtistas()) {
            System.out.println("\t" + a.getNombre());
        }

        System.out.println("\n--------------------------------------------------\n");

        // ----------------------------- CREAMOS EL RECITAL QUILMES ROCK -----------------------------

        Recital quilmes = new Recital("Quilmes Rock", canciones, artistaBase, artistasExt);
        System.out.println("Bienvenido al recital " + quilmes.getNombre());
        // System.out.println(quilmes.calcularCosto(artistasExt.get(0)));

        // ----------------------------- CONSULTAMOS QUE ROLES NOS FALTAN PARA PODER TOCAR TODAS LAS CANCIONES DEL RECITAL -----------------------------


        System.out.println("\n--------------------------------------------------\n");

        System.out.println("Roles requeridos del recital ");
        System.out.println(quilmes.rolesFaltantes());

        System.out.println("\n--------------------------------------------------\n");

        // ----------------------------- CONSULTAMOS QUE ROLES NOS FALTAN PARA PODER TOCAR SOMEBODY TO LOVE (SIN BRIAN MAY) -----------------------------

        Cancion c = canciones.get(0);
        System.out.println("Roles requeridos de la cancion '" + c.getTitulo() + "'");
        System.out.println(c.rolesFaltantes());

        System.out.println("\n--------------------------------------------------\n");

        // ----------------------------- ASIGNAMOS EL ROL DE GUITARRA ELECTRICA A BRIAN MAY -----------------------------
        Map<Rol, Integer> rolesFaltantes = new HashMap<>();
        System.out.println("Roles requeridos de la cancion '" + c.getTitulo() + "'");
        c.agregarParticipacion(BrianMay, Rol.GUITARRA_ELECTRICA);
        rolesFaltantes = c.rolesFaltantes();
        System.out.println(c.rolesFaltantes());
        System.out.println("Artistas contratados ");

        System.out.println(quilmes.listarArtistasContratados());
        System.out.println("\n--------------------------------------------------\n");

        c.contratarArtistas(artistasExt, artistaBase);

        System.out.println("Artistas contratados ");
        System.out.println(quilmes.listarArtistasContratados());
        System.out.println("\n--------------------------------------------------\n");

        System.out.println("Roles requeridos de la cancion '" + c.getTitulo() + "'");
        System.out.println(c.rolesFaltantes());
        System.out.println("\n--------------------------------------------------\n");

        // ----------------------------- VOLVEMOS A CONSULTAR QUE ROLES NOS FALTAN PARA PODER TOCAR TODAS LAS CANCIONES DEL RECITAL  -----------------------------

        System.out.println("Roles requeridos del recital ");
        System.out.println(quilmes.rolesFaltantes());

        System.out.println("\n--------------------------------------------------\n");

        // ----------------------------- VOLVEMOS A CONSULTAR QUE ROLES NOS FALTAN PARA PODER TOCAR TODAS LAS CANCIONES DEL RECITAL  -----------------------------

        System.out.println("Contratamos artistas para todo el recital PROBANDO: ");
        quilmes.contratarArtistasRecital();
        System.out.println("Roles requeridos del recital ");
        System.out.println(quilmes.rolesFaltantes());

        System.out.println("Artistas contratados ");
        Map<Artista, Integer> artistasContratados = quilmes.listarArtistasContratados();
        for(Artista a : artistasContratados.keySet()) {
            System.out.println(a);
            System.out.println("Costo total: " + artistasContratados.get(a));
        }

*/
        /*
        	Roles requeridos del recital 
        	{BATERIA=4, PIANO=1, BAJO=4, VOZ_PRINCIPAL=5, GUITARRA_ELECTRICA=4}
        	Roles requeridos de la cancion Somebody to Love
        	{BATERIA=1, PIANO=1, VOZ_PRINCIPAL=1, BAJO=1}
        */
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
            System.out.println("1) Ver roles faltantes para la canción Somebody to Love");
            System.out.println("2) Ver roles faltantes para todas las canciones");
            System.out.println("3) Contratar artistas para Somebody to love");
            System.out.println("4) Contratar artistas para todas las canciones");
            System.out.println("5) Entrenar artista (Voz principal)");
            System.out.println("6) Listar artistas contratados");
            System.out.println("7) Listar canciones y su estado (No disponible aun). ");
            System.out.println("8) [PROLOG] (no disponinble aun)");
            System.out.println("9) Salir");
            System.out.println("----------------------------------------------");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            
            System.out.println();
            
            switch (opcion) {
            case 1:{
                Cancion c = recital.getCanciones().get(0);
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
                Cancion c = recital.getCanciones().get(0);
                c.contratarArtistas(recital.getArtistaExternos(), recital.getArtistasBase());
            }
                break;
            case 4: {
                System.out.println("Contratamos artistas para todo el recital: ");
                recital.contratarArtistasRecital();
            }
                break;
            case 5:{
                ArtistaExterno david = recital.getArtistaExternos().get(0);
                david.entrenar(Rol.VOZ_PRINCIPAL);
                System.out.println(david);
            }
                break;
            case 6: {
                Map<Artista, Integer> artistasContratados = recital.listarArtistasContratados();
                for (Artista a : artistasContratados.keySet()) {
                    System.out.println(a);
                    System.out.println("Costo total: " + artistasContratados.get(a));
                }
            }
                break;
            case 7:
                // listarCancionesEstado();
                break;
            case 8:
                // Prolog();
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