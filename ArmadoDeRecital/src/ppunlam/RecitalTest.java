package ppunlam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RecitalTest {

    Recital recital;
    Artista base1, base2;
    ArtistaExterno ext1, ext2;
    Cancion c1, c2;

    @BeforeEach
    void setUp() {
        base1 = new Artista("Juan",
                List.of(Rol.GUITARRA_ELECTRICA),
                List.of(new Banda("B1")),
                100, 5);

        base2 = new Artista("Pedro",
                List.of(Rol.BATERIA),
                List.of(new Banda("B1")),
                120, 5);

        ext1 = new ArtistaExterno("Laura",
                new LinkedList<>(List.of(Rol.VOZ_PRINCIPAL)),
                List.of(new Banda("B2")),
                200, 5);

        ext2 = new ArtistaExterno("Mario",
                new LinkedList<>(),               // sin roles
                List.of(new Banda("B3")),
                150, 5);

        //  Canciones 
        c1 = new Cancion("Cancion 1",
                new LinkedList<>(List.of(
                        Rol.GUITARRA_ELECTRICA,
                        Rol.BATERIA,
                        Rol.VOZ_PRINCIPAL
                )));

        c2 = new Cancion("Cancion 2",
                new LinkedList<>(List.of(
                        Rol.BATERIA,
                        Rol.COROS
                )));

        recital = new Recital(
                "Rock Fest",
                new LinkedList<>(List.of(c1, c2)),
                new LinkedList<>(List.of(base1, base2)),
                new LinkedList<>(List.of(ext1, ext2))
        );
    }

    // 1) Roles faltantes de UNA canción
    @Test
    void testRolesFaltantesCancion() {
        HashMap<Rol, Integer> faltantes = c1.rolesFaltantes();

        assertEquals(3, faltantes.size());
        assertEquals(1, faltantes.get(Rol.GUITARRA_ELECTRICA));
        assertEquals(1, faltantes.get(Rol.BATERIA));
        assertEquals(1, faltantes.get(Rol.VOZ_PRINCIPAL));
    }

    // 2) Roles faltantes de todo el recital

    @Test
    void testRolesFaltantesRecital() {
        HashMap<Rol, Integer> faltantes = recital.rolesFaltantes();

        assertEquals(4, faltantes.size());
        assertEquals(1, faltantes.get(Rol.GUITARRA_ELECTRICA));
        assertEquals(2, faltantes.get(Rol.BATERIA));
        assertEquals(1, faltantes.get(Rol.VOZ_PRINCIPAL));
        assertEquals(1, faltantes.get(Rol.COROS));
    }


    // 3) Contratar artistas para UNA canción

    @Test
    void testContratarArtistasCancion() {
        c1.contratarArtistas(recital.getArtistaExternos(), recital.getArtistasBase());

        assertEquals(3, c1.participaciones.size());
    }

    // 4) Contratar artistas para TODAS las canciones
    @Test
    void testContratarArtistasRecital() {
        recital.contratarArtistasRecital();

        assertEquals(3, c1.participaciones.size());
        assertEquals(2, c2.participaciones.size());
    }

    // 5) Entrenar artista externo
    @Test
    void testEntrenarArtista() {
        assertFalse(ext2.getRoles().contains(Rol.COROS));

        ext2.entrenar(Rol.COROS);

        assertTrue(ext2.getRoles().contains(Rol.COROS));
        assertEquals(225, ext2.getCosto());  
    }

    // 6) Listar artistas contratados
    @Test
    void testListarArtistasContratados() {
        recital.contratarArtistasRecital();
        Map<Artista, Integer> contratados = recital.listarArtistasContratados();

        assertTrue(contratados.size() >= 3);
    }

    // 7) Listar canciones y estado
    @Test
    void testListarCanciones() {
        // solo verificamos que no explote
        assertDoesNotThrow(() -> recital.listarCanciones());
    }
    
    
 //  Artista no puede ser contratado si llegó al máximo de canciones

 @Test
 void testArtistaNoDisponiblePorMaxCanciones() {
     base1.setActualCanciones(5);  // se llego al limite
     
     c1.contratarArtistas(recital.getArtistaExternos(), recital.getArtistasBase());
     

     boolean base1Contratado = c1.participaciones.stream()
         .anyMatch(p -> p.getArtista().equals(base1));
     
     assertFalse(base1Contratado, "base1 no debería ser contratado (máximo alcanzado)");
 }


 // No se puede entrenar si el artista ya tiene el rol
 @Test
 void testNoEntrenarRolExistente() {
     ext1.entrenar(Rol.VOZ_PRINCIPAL);  // Ya lo tiene
     
     // El costo NO debería cambiar
     assertEquals(200, ext1.getCosto());
 }


 // TEST: Artista externo sin rol necesita entrenamiento

 @Test
 void testExternoSinRolNecesitaEntrenamiento() {
     // ext2 no tiene roles
     Cancion c3 = new Cancion("Cancion 3", 
         new LinkedList<>(List.of(Rol.PIANO)));
     
     c3.contratarArtistas(recital.getArtistaExternos(), recital.getArtistasBase());
     
     // ext2 debería ser entrenado para PIANO
     assertTrue(ext2.getRoles().contains(Rol.PIANO));
 }


 // TEST: No se puede agregar el mismo artista dos veces a una canción

 @Test
 void testNoAgregarArtistaDosVeces() {
     c1.agregarParticipacion(base1, Rol.GUITARRA_ELECTRICA);
     
     // Intentar agregar de nuevo
     assertThrows(IllegalArgumentException.class, () -> {
         c1.agregarParticipacion(base1, Rol.BATERIA);
     });
 }

}


