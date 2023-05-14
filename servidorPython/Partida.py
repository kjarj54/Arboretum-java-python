from Carta import Carta
from Jugador import Jugador
from Celda import Celda
from Tablero import Tablero

class Partida:
    def __init__(self, duracion_partida):
        self.mazo_general = []
        self.jugadores = []
        self.duracion = duracion_partida
        self.turnoP1 = True
        self.turnoP2 = False
        self.turnoP3 = False
        self.turnoP4 = False

    def crearCartas(self):
        mi_carta1 = Carta("corazones", 1)
        mi_carta2 = Carta("corazones", 2)
        mi_carta3 = Carta("corazones", 3)
        mi_carta4 = Carta("corazones", 4)
        mi_carta5 = Carta("corazones", 5)
        mi_carta6 = Carta("corazones", 6)
        mi_carta7 = Carta("corazones", 7)
        mi_carta8 = Carta("corazones", 8)
    
    def pasarTurno(self):
        if(self.turnoP1):
            self.turnoP2 = True
            self.turnoP1 = False
        elif(self.turnoP2):
            self.turnoP3 = True
            self.turnoP2 = False
        elif(self.turnoP3):
            self.turnoP4 = True
            self.turnoP3 = False
        elif(self.turnoP4):
            self.turnoP1 = True
            self.turnoP4 = False
        print("TurnoP1" + str(self.turnoP1) + " | TurnoP2" + str(self.turnoP2) + " | TurnoP3" + str(self.turnoP3) + " | TurnoP4" + str(self.turnoP4))

        
    
            
    
            
    
