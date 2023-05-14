/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.arboretum_karauz_aavila_dazofeifa.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ANTHONY
 */
public class Jugador {
    private String nombre;
    private Tablero tablero;
    private List<Carta> mazo;
    private int puntos;

    public Jugador(String nombre, int puntos) {
        this.nombre = nombre;
        this.tablero = null;
        this.mazo = new ArrayList<>();
        this.puntos = puntos;
    }
    
    
}
