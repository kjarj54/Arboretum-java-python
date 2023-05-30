/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.arboretum_karauz_aavila_dazofeifa.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author kevin
 */
public class ManejoJSON {

    private static final String JSON_FILE_PATH = "src/main/java/cr/ac/una/arboretum_karauz_aavila_dazofeifa/service/informacion.json";// ruta del archivo JSON
    private JSONObject jsonObject;

    public ManejoJSON() {
        jsonObject = cargarJSON();//Llama al metodo para cargar el json
    }

    public void introducirDatosJugador(String nombre, int puntos) {
        jsonObject.put("nombre", nombre);
        jsonObject.put("puntos", puntos);
        guardarJSON(jsonObject);
    }

    public void introducirDatosTablero() {
        // Agregar lógica para introducir datos de tablero en el JSON
    }

    public void introducirDatosCartas() {
        // Agregar lógica para introducir datos de cartas en el JSON
    }

    public void limpiarJSON() {
        jsonObject.clear();//deja limpio el JSON tener cuidado con este metodo
        guardarJSON(jsonObject);
    }

    public JSONObject cargarJSON() {
        JSONObject loadedJSON = new JSONObject();

        try (FileReader fileReader = new FileReader(JSON_FILE_PATH)) {
            // Crea un objeto JSONParser para analizar el contenido del archivo JSON
            JSONParser jsonParser = new JSONParser();
            // Analiza el contenido del archivo utilizando el JSONParser y lo asigna al objeto loadedJSON
            loadedJSON = (JSONObject) jsonParser.parse(fileReader);
        } catch (FileNotFoundException e) {
            System.out.println("El archivo JSON no existe. Se creará uno nuevo.");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return loadedJSON;
    }

    private void guardarJSON(JSONObject jsonObject) {
        try (FileWriter fileWriter = new FileWriter(JSON_FILE_PATH)) {//Comprueba que el archivo exista
            fileWriter.write(jsonObject.toString());//Escribe el JSON con el atributo jsonObject
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
