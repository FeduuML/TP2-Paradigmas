package ppunlam;
import alice.tuprolog.*;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

import java.util.Set;

public class PrologIntegracion {

    private Prolog engine;

    public PrologIntegracion() {
        engine = new Prolog();
    }

    /**
     * Construye y carga la base de conocimiento en Prolog
     */
    public void cargarHechos(
            List<MiembroBase> miembrosBase,
            List<ArtistaExterno> artistasExternos,
            Recital recital,
            int costoEntrenamientoBase
    ) throws Exception {

        StringBuilder pl = new StringBuilder();

        // ----- HECHOS: ROLES REQUERIDOS POR EL RECITAL -----

        Set<Rol> rolesTotales = recital.getCanciones().stream()
                .flatMap(c -> c.getRolesRequeridos().stream())
                .collect(java.util.stream.Collectors.toSet());

        for (Rol rol : rolesTotales) {
            pl.append("rol_requerido(")
                    .append(rol.name().toLowerCase())
                    .append(").\n");
        }

        // ----- HECHOS: ROLES QUE CUBRE CADA MIEMBRO BASE -----

        for (MiembroBase m : miembrosBase) {
            for (Rol rol : m.getRoles()) {
                pl.append("puede(")
                        .append(m.getNombre().toLowerCase())
                        .append(", ")
                        .append(rol.name().toLowerCase())
                        .append(").\n");
            }
        }

        // ----- HECHOS: ARTISTAS EXTERNOS SIN EXPERIENCIA -----

        for (ArtistaExterno a : artistasExternos) {
            pl.append("externo(")
                    .append(a.getNombre().toLowerCase())
                    .append(", ")
                    .append(costoEntrenamientoBase)
                    .append(").\n");
        }

        // ----- REGLAS PROLOG: ENTRENAR = costo base -----

        pl.append("\n");
        pl.append("% Un externo puede cubrir cualquier rol si lo entrenás\n");
        pl.append("puede_entrenado(X, Rol, Costo) :- externo(X, Costo).\n");

        // ----- REGLAS: CUANTOS ENTRENAMIENTOS SE NECESITAN -----

        pl.append("\n");
        pl.append("% Rol está cubierto si lo puede alguien o si lo puede entrenado\n");
        pl.append("cubierto(R) :- puede(_, R).\n");
        pl.append("cubierto(R) :- puede_entrenado(_, R, _).\n");

        pl.append("\n");
        pl.append("% Contar roles no cubiertos por miembros base\n");
        pl.append("roles_faltantes(Count) :- findall(R, (rol_requerido(R), \\+ puede(_,R)), L), length(L, Count).\n");

        engine.clearTheory();
        engine.setTheory(new Theory(pl.toString()));
    }

    /**
     * Ejecuta la consulta principal: cuántos entrenamientos mínimos necesito
     */
    public int consultarEntrenamientosMinimos() throws Exception {
        SolveInfo info = engine.solve("roles_faltantes(X).");

        if (info.isSuccess()) {
            return Integer.parseInt(info.getVarValue("X").toString());
        }

        return -1;
    }
}






