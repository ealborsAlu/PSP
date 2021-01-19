package Ejercicio_buzón_hilos;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/* @author Enrique Albors
 * Clase encargada para desarrollar el esquema de comunicaciones 
 * */
public class server extends Thread {
	
	// Atributos de la clase 
	DataInputStream in;
	DataOutputStream out;
	Socket sc;
	HashMap<String, String> mensajes;

	// metodo constructor
	public server(Socket s, DataInputStream in, DataOutputStream out, HashMap<String, String> mensajes) {
		this.sc = s;
		this.in = in;
		this.out = out;
		this.mensajes = mensajes;
	}

	// método run del thread
	@Override
	public void run() {
		boolean salir = true;

		try {
			System.out.println("Servidor Iniciado");// se indica que el servidor ha sido creado

			while (salir) {
				System.out.println("Cliente conectado");// se indica por pantalla del la conexion correcta

				in = new DataInputStream(sc.getInputStream());
				out = new DataOutputStream(sc.getOutputStream());

				// Cliente indica nombre
				out.writeUTF("Indica tu nombre ");
				String mensaje = in.readUTF();// Se queda a la espera del cliente
				System.out.println("Nombre: " + mensaje);// se muestra el mensaje enviado por el cliente
				String nombreUsuario = mensaje;// guardo el nombre del usuario

				if (!mensajes.containsKey(mensaje)) {
					System.out.println("El usuario indicado no existe");// se indica
					out.writeUTF("El usuario indicado no existe");
					nombreUsuario = "";
					salir = false;

				}

				mensaje = "";

				// Opciones del menú que se le pasa al cliente
				mensaje = "Elige una de estas dos opciones \n 1.Leer mensaje \n 2.Escribir mensaje \n 3.Salir ";
				out.writeUTF(mensaje);// se envian los mensajes al usuario
				while (salir) {// cuando se le pase server la opcion de salir , esta cerrará el bucle

					String opcion = in.readUTF();//la opcion queda a la espera de que el cliente elija
					
					switch (opcion) {
					case "1"://Para la impresión de mensajes 
						System.out.println("leer");
						if (mensajes.containsKey(nombreUsuario)) {

							String msg = "mensajes a leer : " + mensajes.get(nombreUsuario);
							if (mensajes.get(nombreUsuario) != null) {//si hay mensajes 
								out.writeUTF(msg);//se envian los mensajes que ha solicitado el cliente
								mensajes.put(nombreUsuario, "");//se "borran" los mensajes 
								out.flush();//se vacia el buffer
							}

						} else {//si no hay menasjes
							System.out.println("No hay nada");
							out.writeUTF("");
						}

						break;
					case "2"://Para enviar codigo a otro cliente 

						out.writeUTF("Enviar mensaje : \n   Escribe el nombre del remitente");//se solicita el nombre del usuario a enviar
						String usuarioEnviar = in.readUTF();//el server queda a la espera de la información
						out.writeUTF("Escribe el cuerpo del mensaje");//se solicita el cuerpo del texto
						String mensajeEnviar = in.readUTF();
						//con los datos obtenidos por el cliente , se accede al Hashmap por el nombre del usuario ,se obtienen
						//los mensajes por imprimir y se le añade el nuevo
						mensajes.put(usuarioEnviar, mensajes.get(usuarioEnviar) + "\n " + (" - " + mensajeEnviar));

						break;
					case "3":
						System.out.println("Cerrando conexion");
						sc.close();
						System.out.println("Conexion cerrada");
						salir = false;
						break;
					}

				}
				in.close();
				out.close();
				sc.close();// se cierra el cliente
				System.out.println("Cliente " + nombreUsuario + " desconectado");

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
