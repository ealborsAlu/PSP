package Ejercicio_buzón_hilos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
/*
 *  @author Enrique Albors
 * Clase encargada de la gestión de  las nuevas conexiones y 
 * asociarles un hilo para su desarrollo
 * */

public class ServerPrincipal {

	public static void main(String[] args) {

		try {
			// Hashmap de clientes con mensajes
			HashMap<String, String> mensajes = new HashMap<String, String>();
			mensajes.put("Manuel", "compra pan ");
			mensajes.put("Paco", "te echo de menos");
			mensajes.put("Luisa", "estoy esperandote ");

			// Se crea el socket del servidor
			ServerSocket server = new ServerSocket();
			System.out.println("Creando enlace (bind)");// mensaje impreso por pantalla para el programador
			InetSocketAddress direccion = new InetSocketAddress("localhost", 5000);
			server.bind(direccion);

			// esta a la espera de que vengan clientes
			while (true) {//se inicia un bucle infirnito 
				Socket s = null;
				try {
					// se queda a la espera de acepta conexiones cliente
					s = server.accept();

					// para recuperar los datos de entrada salida 
					DataInputStream dis = new DataInputStream(s.getInputStream());
					DataOutputStream dos = new DataOutputStream(s.getOutputStream());
					System.out.println("Hilo para nuevo cliente creado");

					// se crea nuevo objeto hilo
					Thread t = new server(s, dis, dos, mensajes);

					// se arranca el hilo
					t.start();
					System.out.println("Hilo creado");
					// tratamiento de excepciones
				} catch (Exception e) {
					s.close();
					e.printStackTrace();
				}
			}
			// tratamiento de excepciones
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
