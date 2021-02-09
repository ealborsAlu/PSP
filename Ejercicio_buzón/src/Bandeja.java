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

	//método sincronizado para saber si exite un usuario o no
	//se le pasa un nombre de usuario y devuelve un boolean
	public synchronized boolean usuarioExiste(String nomUsr) {
		boolean OK = true;
		if (!mensajes.containsKey(nomUsr)) {
			System.out.println("Usuario inexistente");
			OK = false;
		}

		return OK;
	}
	
	//método sincronizado para la lectura de mensajes
	//se le pasa un nombre de usuario y devuelve su valor
	public synchronized String leer(String nomUsr) {
		String mensaje="";
		mensaje = mensajes.get(nomUsr);
		
		return mensaje;
		
	}
	
	//método sincronizado para borrar los mensajes una vez leido
	public synchronized void borrar(String nomUsr) {
		mensajes.put(nomUsr , "");
		
	}
	//método sincronizado para redactar mensajes
	//se le pasa un nombreusuario y el cuerpo del mensaje 
	public synchronized void redacta(String nomUsr , String texto) {
		mensajes.put(nomUsr , mensajes.get(nomUsr) + "\n " + (" - " +texto));
	}

}
