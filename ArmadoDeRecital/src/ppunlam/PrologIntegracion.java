package ppunlam;

import alice.tuprolog.*;
import java.util.*;

public class PrologIntegracion {

    private Prolog engine;

    public PrologIntegracion() {
        engine = new Prolog();
    }

    public int calcularEntrenamientosMinimos(Recital recital) {
        try {

            StringBuilder teoria = new StringBuilder();

            // Reglas

            //Un artista base sabe un rol si está en su lista de roles
            teoria.append("sabe_rol(artista_base(_, Roles), Rol) :- \n");
            teoria.append("    member(Rol, Roles).\n\n");

           // Un artista contratado no sabe ningún rol
            teoria.append("sabe_rol(artista_contratado(_), _) :- \n");
            teoria.append("    fail.\n\n");

            //Se necesita entrenamiento para un rol si ningún artista lo sabe
            teoria.append("entreno_necesario(Rol, Artistas) :- \n");
            teoria.append("    \\+ (member(A, Artistas), sabe_rol(A, Rol)).\n\n");

            //Contar todos los entrenamientos necesarios
            teoria.append("entrenamientos_necesarios(Artistas, Roles, E) :- \n");
            teoria.append("    findall(1, (member(R, Roles), entreno_necesario(R, Artistas)), L), \n");
            teoria.append("    length(L, E).\n\n");

            engine.setTheory(new Theory(teoria.toString()));


            Set<Rol> rolesRequeridos = new HashSet<>();
            for (Cancion c : recital.getCanciones()) {
                rolesRequeridos.addAll(c.rolesRequeridos);
            }

            StringBuilder listaArtistas = new StringBuilder("[");
            List<Artista> todosArtistas = new ArrayList<>();
            todosArtistas.addAll(recital.getArtistasBase());
            todosArtistas.addAll(recital.getArtistaExternos());

            for (int i = 0; i < todosArtistas.size(); i++) {
                Artista a = todosArtistas.get(i);

                if (a instanceof ArtistaExterno) {
                    listaArtistas.append("artista_contratado('")
                            .append(a.getNombre().toLowerCase())
                            .append("')");
                } else {
                    StringBuilder roles = new StringBuilder("[");
                    List<Rol> listaRoles = a.getRoles();
                    for (int j = 0; j < listaRoles.size(); j++) {
                        roles.append(listaRoles.get(j).name().toLowerCase());
                        if (j < listaRoles.size() - 1) {
                            roles.append(", ");
                        }
                    }
                    roles.append("]");

                    listaArtistas.append("artista_base('")
                            .append(a.getNombre().toLowerCase())
                            .append("', ")
                            .append(roles.toString())
                            .append(")");
                }

                if (i < todosArtistas.size() - 1) {
                    listaArtistas.append(", ");
                }
            }
            listaArtistas.append("]");

            // lista de roles requeridos:
            StringBuilder listaRoles = new StringBuilder("[");
            List<Rol> roles = new ArrayList<>(rolesRequeridos);
            for (int i = 0; i < roles.size(); i++) {
                listaRoles.append(roles.get(i).name().toLowerCase());
                if (i < roles.size() - 1) {
                    listaRoles.append(", ");
                }
            }
            listaRoles.append("]");

            // ejecutar consulta
            String consulta = String.format("entrenamientos_necesarios(%s, %s, E).",
                    listaArtistas.toString(),
                    listaRoles.toString());

            System.out.println("Ejecutando consulta prolog:");
            System.out.println(consulta);
            System.out.println();

            SolveInfo solucion = engine.solve(consulta);

            if (solucion.isSuccess()) {
                int entrenamientos = Integer.parseInt(solucion.getVarValue("E").toString());
                return entrenamientos;
            } else {
                System.err.println("La consulta no tuvo éxito");
                return -1;
            }

        } catch (Exception e) {
            System.err.println("Error al calcular entrenamientos: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
}