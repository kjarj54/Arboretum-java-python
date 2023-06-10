/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import cr.ac.una.arboretum_karauz_aavila_dazofeifa.model.ManejoJSON;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import com.google.gson.Gson;
import com.jfoenix.controls.JFXCheckBox;
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
    public Thread hiloEspera;
    public boolean continuarHilo = true;


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

    public String jugadorIndex() {
        try {
            String mensaje = "jugadorIndex";
            out.print(mensaje);
            out.flush();

            String respuesta = in.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);
            return respuesta;
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al conectarse al host " + host + ":" + puerto);
            e.printStackTrace();
        }
        return "-1";
    }

    public void desconeccion() {
        String mensaje = "desconeccion";
        out.print(mensaje);
        out.flush();
    }
    public void statusJugadores(JFXCheckBox cbxPlayer1, JFXCheckBox cbxPlayer2, JFXCheckBox cbxPlayer3, JFXCheckBox cbxPlayer4) {
        try {
            String mensaje = "statusJugadores";
            out.print(mensaje);
            out.flush();
            
            String respuesta;
            respuesta = in.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);

            String[] valores = respuesta.split(",");
            boolean[] clientes = new boolean[valores.length];
            for (int i = 0; i < valores.length; i++) {
                clientes[i] = Boolean.parseBoolean(valores[i]);
            }

            // Mostrar los valores recibidos
            for (boolean cliente : clientes) {
                System.out.println("Valor recibido: " + cliente);
            }

            if (clientes != null) {
                cbxPlayer1.setSelected(clientes[0]);
                cbxPlayer2.setSelected(clientes[1]);
                cbxPlayer3.setSelected(clientes[2]);
                cbxPlayer4.setSelected(clientes[3]);
            }
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al conectarse al host " + host + ":" + puerto);
            e.printStackTrace();
        }
    }

    public void esperar(JFXCheckBox cbxPlayer1, JFXCheckBox cbxPlayer2, JFXCheckBox cbxPlayer3, JFXCheckBox cbxPlayer4) {
        hiloEspera = new Thread(() -> {
            try {
                String respuesta;
                while (continuarHilo) {
                    respuesta = in.readLine();
                    System.out.println("Respuesta del servidor: " + respuesta);

                    String[] valores = respuesta.split(",");
                    boolean[] clientes = new boolean[valores.length];
                    for (int i = 0; i < valores.length; i++) {
                        clientes[i] = Boolean.parseBoolean(valores[i]);
                    }

                    // Mostrar los valores recibidos
                    for (boolean cliente : clientes) {
                        System.out.println("Valor recibido: " + cliente);
                    }

                    if (clientes != null) {
                        cbxPlayer1.setSelected(clientes[0]);
                        cbxPlayer2.setSelected(clientes[1]);
                        cbxPlayer3.setSelected(clientes[2]);
                        cbxPlayer4.setSelected(clientes[3]);
                    } else if ("IniciarPartida".equals(respuesta)) {
                        System.out.println("Iniciar Partida");
                        break;
                    }
                }
            } catch (IOException e) {
                System.err.println("Error de entrada/salida al conectarse al host " + host + ":" + puerto);
                e.printStackTrace();
            }
        });

        hiloEspera.start();
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
        Jugador datos = gson.fromJson(jsonData, Jugador.class
        );
        System.out.println(
                "3");

// Utilizar los datos recibidos
        System.out.println(
                "Nombre: " + datos.getNombre());
        System.out.println(
                "Puntos: " + datos.getPuntos());

        // Guardar el contenido decodificado en un archivo JSON
//        try ( FileWriter fileWriter = new FileWriter("src/main/java/cr/ac/una/arboretum_karauz_aavila_dazofeifa/service/informacion_recibida.json")) {
//            fileWriter.write(jsonObject.toJSONString());
//        }
        System.out.println(
                "Archivo JSON recibido y guardado.");
    }

    public void recibirRespuesta() {
        try {
            // Leer la respuesta del servidor
            String jsonData = in.readLine();

            // Analizar el JSON utilizando Gson
            Gson gson = new Gson();
            System.out.println("2");
            Jugador datos = gson.fromJson(jsonData, Jugador.class
            );
            System.out.println(
                    "3");

            // Utilizar los datos recibidos
            System.out.println(
                    "Nombre: " + datos.getNombre());
            System.out.println(
                    "Puntos: " + datos.getPuntos());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void actualizarInfo() {
        ManejoJSON informacion = new ManejoJSON();
        jsonObject = informacion.cargarJSON();

    }
}
