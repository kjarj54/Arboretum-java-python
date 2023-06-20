from Carta import Carta
from Jugador import Jugador
from Celda import Celda
from Tablero import Tablero

class Partida:
    def __init__(self, duracion_partida):
        self.mazo_general = []
        self.jugadores = []
        self.duracion = duracion_partida
        self.turno_actual = 0

    def crearCartas(self):
        mazoAzul = []
        for i in range(1, 9):
            mazoAzul.append(Carta("Azul", i))
        
        mazoRojo = []
        for i in range(1, 9):
            mazoRojo.append(Carta("Rojo", i))

        mazoVerde = []
        for i in range(1, 9):
            mazoVerde.append(Carta("Verde", i))

        mazoAmarillo = []
        for i in range(1, 9):
            mazoAmarillo.append(Carta("Amarillo", i))

        mazoRosado = []
        for i in range(1, 9):
            mazoRosado.append(Carta("Rosado", i))

        mazoMorado = []
        for i in range(1, 9):
            mazoMorado.append(Carta("Morado", i))

        mazoNaranja = []
        for i in range(1, 9):
            mazoNaranja.append(Carta("Naranja", i))

        mazoCeleste = []
        for i in range(1, 9):
            mazoCeleste.append(Carta("Celeste", i))

        mazoCafe = []
        for i in range(1, 9):
            mazoCafe.append(Carta("Cafe", i))

        mazoAqua = []
        for i in range(1, 9):
            mazoAqua.append(Carta("Aqua", i))

        self.mazo_general.append()
    
    def pasarTurno(self, clientes):
        while True:
            self.turno_actual = (self.turno_actual + 1) % len(clientes)
            if clientes[self.turno_actual] != None:
                break
         
            
    
            
    
