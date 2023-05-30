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
import com.google.gson.Gson;
import cr.ac.una.arboretum_karauz_aavila_dazofeifa.model.Jugador;

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
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al conectarse al host " + host + ":" + puerto);
            e.printStackTrace();
        }
    }

    private void recibirArchivoJSON() throws IOException {
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
//        StringBuilder contenido = new StringBuilder();
//        String linea;
//        while ((linea = reader.readLine()) != null) {
//            contenido.append(linea);
//        }
//
//        // Decodificar el contenido recibido como JSON
//        String contenidoDecodificado = contenido.toString();
//        JSONObject jsonObject = (JSONObject) JSONValue.parse(contenidoDecodificado);
        System.out.println("1");

        String jsonData = in.readLine();

// Analizar el JSON utilizando Gson
        Gson gson = new Gson();
        System.out.println("2");
        Jugador datos = gson.fromJson(jsonData, Jugador.class);
        System.out.println("3");

// Utilizar los datos recibidos
        System.out.println("Nombre: " + datos.getNombre());
        System.out.println("Puntos: " + datos.getPuntos());

        // Guardar el contenido decodificado en un archivo JSON
//        try ( FileWriter fileWriter = new FileWriter("src/main/java/cr/ac/una/arboretum_karauz_aavila_dazofeifa/service/informacion_recibida.json")) {
//            fileWriter.write(jsonObject.toJSONString());
//        }

        System.out.println("Archivo JSON recibido y guardado.");
    }

    public void recibirRespuesta() {
        try {
            // Leer la respuesta del servidor
            String respuesta = in.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void actualizarInfo() {
        ManejoJSON informacion = new ManejoJSON();
        jsonObject = informacion.cargarJSON();

    }
}
