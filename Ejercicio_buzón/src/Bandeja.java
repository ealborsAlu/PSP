import java.util.HashMap;
/*
 * @author Enrique Albors
 * Clase encargada de gestionar la base de datos de los mensajes
 * */

public class Bandeja {
	//atributos
	HashMap<String, String> mensajes = new HashMap<String, String>();

	//método constructor 
	public Bandeja() {
		
		//mensajes base , un usuario y un mensaje
		mensajes.put("Manuel", "compra pan ");
		mensajes.put("Paco", "te echo de menos");
		mensajes.put("Luisa", "estoy esperandote ");

	}

	//asber 
	public synchronized boolean usuarioExiste(String nomUsr) {
		boolean OK = true;
		if (!mensajes.containsKey(nomUsr)) {
			System.out.println("Usuario inexistente");
			OK = false;
		}

		return OK;
	}
	
	public synchronized String leer(String nomUsr) {
		String mensaje="";
		mensaje = mensajes.get(nomUsr);
		
		return mensaje;
		
	}
	public synchronized void borrar(String nomUsr) {
		mensajes.put(nomUsr , "");
		
	}
	//
	public synchronized void redacta(String nomUsr , String texto) {
		mensajes.put(nomUsr , mensajes.get(nomUsr) + "\n " + (" - " +texto));
	}

}
