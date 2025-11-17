package ppunlam;

public enum Rol {
	GUITARRA_ELECTRICA,VOZ_SECUNDARIA,BATERIA,VOZ_PRINCIPAL,BAJO,PIANO,TECLADO,COROS;
	
	public static Rol pasarStringRol(Object r) {
	    if (r == null) return null;

	    switch (r.toString().toLowerCase()) {
	        case "guitarra eléctrica":
	            return Rol.GUITARRA_ELECTRICA;

	        case "voz secundaria":
	            return Rol.VOZ_SECUNDARIA;

	        case "batería":
	            return Rol.BATERIA;

	        case "voz principal":
	            return Rol.VOZ_PRINCIPAL;

	        case "bajo":
	            return Rol.BAJO;

	        case "piano":
	            return Rol.PIANO;

	        case "teclado":
	            return Rol.TECLADO;

	        case "coros":
	            return Rol.COROS;

	        default:
	            return null;
	    }
	}
}
