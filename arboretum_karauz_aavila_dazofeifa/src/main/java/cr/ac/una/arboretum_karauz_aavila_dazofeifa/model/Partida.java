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
public class Partida {
    private List<Carta> mazo_general;
    private List<Jugador> jugadores;
    private double duracion;
    private Jugador turno;

    public Partida(double duracion, Jugador turno) {
        this.mazo_general = new ArrayList<>();
        this.jugadores = new ArrayList<>();
        this.duracion = duracion;
        this.turno = turno;
    }
    
    
}
