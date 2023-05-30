/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import cr.ac.una.arboretum_karauz_aavila_dazofeifa.model.ManejoJSON;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

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
    private JSONObject jsonObject;

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
            recibirArchivoJSON();
            // Leer la respuesta del servidor
            String respuesta = in.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);

            // Recibir el archivo JSON
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al conectarse al host " + host + ":" + puerto);
            e.printStackTrace();
        }
    }

    private void recibirArchivoJSON() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
        StringBuilder contenido = new StringBuilder();
        String linea;
        while ((linea = reader.readLine()) != null) {
            contenido.append(linea);
        }

        // Decodificar el contenido recibido como JSON
        String contenidoDecodificado = contenido.toString();
        JSONObject jsonObject = (JSONObject) JSONValue.parse(contenidoDecodificado);

        // Guardar el contenido decodificado en un archivo JSON
        try (FileWriter fileWriter = new FileWriter("src/main/java/cr/ac/una/arboretum_karauz_aavila_dazofeifa/service/informacion_recibida.json")) {
            fileWriter.write(jsonObject.toJSONString());
        }

        System.out.println("Archivo JSON recibido y guardado.");
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

    public void actualizarInfo() {

        ManejoJSON informacion = new ManejoJSON();
        jsonObject = informacion.cargarJSON();

    }

}
