package cr.ac.una.arboretum_karauz_aavila_dazofeifa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimaryController {

    @FXML
    private Button btnenviar;
    @FXML
    private Button btnRecibir;

    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void onActionbtnEnviar(ActionEvent event) {
        enviarMensaje();
    }

    @FXML
    private void onActionBtnRecibir(ActionEvent event) {
        recibirMensaje();
    }

    public void enviarMensaje() {
        String host = "localhost";
        int puerto = 8000;

        try {
            // Crear un socket para conectarse al servidor
            Socket socket = new Socket(host, puerto);
            System.out.println("Conectado al servidor " + host + ":" + puerto);
            System.out.println("Introduce un mensaje");

            // Crear un objeto PrintWriter para enviar mensajes al servidor
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Crear un objeto BufferedReader para recibir mensajes del servidor
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Pedir al usuario que introduzca un mensaje por consola
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            String mensaje = stdin.readLine();

            // Enviar el mensaje al servidor
            out.println(mensaje);
            // Leer la respuesta del servidor
            String respuesta = in.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);

            // Cerrar la entrada estándar, el objeto PrintWriter, el objeto BufferedReader y el socket
            stdin.close();
            out.close();
            in.close();
            socket.close();
            System.out.println("Cerrando conexion al servidor " + host + ":" + puerto);

        } catch (IOException e) {
            System.err.println("Error de entrada/salida al conectarse al host " + host + ":" + puerto);
        }
    }

    public void recibirMensaje() {
        // Definir la dirección IP y el puerto del servidor
        String IP = "localhost";
        int puerto = 8000;

        try {
            // Crear un socket para conectarse al servidor
            Socket socket = new Socket(IP, puerto);
            System.out.println("Conectado al servidor " + IP + ":" + puerto);

            // Crear un lector para recibir los mensajes del servidor
            BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Esperar a recibir un mensaje del servidor y mostrarlo en la consola
            String mensaje = lector.readLine();
            
            System.out.println("Respuesta del servidor: " + mensaje);

            // Cerrar el socket cuando se termina de recibir los mensajes
            socket.close();
            System.out.println("Conexion cerrada con el servidor " + IP + ":" + puerto);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
