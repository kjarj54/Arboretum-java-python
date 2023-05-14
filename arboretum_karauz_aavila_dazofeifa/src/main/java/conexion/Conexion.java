/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ANTHONY
 */
public class Conexion {

    String host = "localhost";
    int puerto = 8000;
    Socket socket;
    PrintWriter out;
    BufferedReader in;

    public Conexion() {
        try {
            // Crear un socket para conectarse al servidor
            socket = new Socket(host, puerto);
            System.out.println("Conectado al servidor " + host + ":" + puerto);

            // Crear un objeto PrintWriter para enviar mensajes al servidor
            out = new PrintWriter(socket.getOutputStream(), true);

            // Crear un objeto BufferedReader para recibir mensajes del servidor
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void PasarTurno() {
        try {
            String mensaje = "PasarTurno";

            // Enviar el mensaje al servidor
            out.print(mensaje);
            out.flush();

            // Leer la respuesta del servidor
            String respuesta = in.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al conectarse al host " + host + ":" + puerto);
            e.printStackTrace();
        }
    }

    public void recibirRespuesta() {
        try {
            // Leer la respuesta del servidor
            String respuesta = in.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
