
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/*
 *  @author Enrique Albors
 * Clase que usará el usuario para la comunicación con el hilo
 * */
public class Cliente {

	public static void main(String[] args) {
		
		//Inicialización de las clases y variables
		DataInputStream in;
		DataOutputStream out;
		final String noUsr = "El usuario indicado no existe";
		final String noMensaje = "mensajes a leer : ";
		Scanner usuario = new Scanner(System.in);
		Scanner opcion = new Scanner(System.in);
		Scanner teclado = new Scanner(System.in);

		try {
			
			Socket socket = new Socket();
			//se configura la ip y el puerto con la que trabajará
			InetSocketAddress addr = new InetSocketAddress("localhost", 5000);
			socket.connect(addr);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			String opc = "";
			
			//Indicarle al server nombre usuario 
			String mensaje = in.readUTF();
			System.out.println("Mensaje recibido : " + mensaje);
			String nombre = usuario.nextLine();
			out.writeUTF(nombre);

			//Indicar al usuario que no existe
			String mensaje2 = in.readUTF();
			System.out.println("Mensaje recibido : " + mensaje2);
			if (mensaje2.equalsIgnoreCase(noUsr)) {
				opc = "3";
			}

			while (!opc.equals("3")) {
				// System.out.println(in.readUTF());
				opc = "";
				opc = opcion.nextLine();
				switch (opc) {
				case "1"://Opción para leer los mensajes enviados al usuario
					out.writeUTF("1");//se le indica la opción elegida al servidor
					String msgs = in.readUTF();
					if (msgs.equalsIgnoreCase(noMensaje)) {
						System.out.println("no tiene mensajes");
					} else {
						System.out.println(msgs);
					}
					break;
				case "2"://Opción para crear mensajes
					out.writeUTF("2");//se le indica la opción elegida al servidor

					//se le indica al servidor a que usuario hay que enviarle el mensaje
					String mensaje3 = in.readUTF();//mensaje del servidor , preguntando por el nombre del remitente
					System.out.println(mensaje3);// lo que llega por server para introducir nombre
					String nombreUsuario = teclado.nextLine();//mensaje del servidor , preguntando por el nombre del remitente
					out.writeUTF(nombreUsuario);//se le pasa el nombre del remitente al servidor

					teclado = new Scanner(System.in);// se resetea el teclado
					
					//Se le indicará al servidor el cuerpo del mensaje a enviar 
					String mensaje4 = in.readUTF();
					System.out.println(mensaje4);// lo que llega por server mensaje a enviar
					String mensajeEnviar = teclado.nextLine();
					out.writeUTF(mensajeEnviar);

					System.out.println("Mensaje Enviado");

					break;
				case "3":
					out.writeUTF("3");
					socket.close();
					break;
				default:
					System.out.println("opcion no valida");
					break;
				}
			}
			// se desconecta el cliente
			socket.close();
			System.out.println("Desconectado");

			//Capatación de excepciones
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}