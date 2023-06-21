import socket
import threading
#import ujson
import json

from Carta import Carta
from Partida import Partida

# Definir la dirección IP y el puerto para el servidor
IP = "192.168.56.1"
PORT = 8000

# Crear un objeto de socket
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Vincular el objeto de socket al IP y puerto
server_socket.bind((IP, PORT))

# Esperar conexiones entrantes
server_socket.listen()

# Lista de clientes conectados
clientes = [None, None, None, None]
max_clientes = 4
semaphore = threading.Semaphore(max_clientes)

partida = Partida(15)

# Función para manejar las conexiones entrantes


def manejar_cliente(cliente, direccion):
    semaphore.acquire()  # Intenta adquirir el semáforo
    # Añadir el socket del cliente a la lista de clientes
    playerIndex = -1
    for i in range(4):
        if clientes[i] == None:
            playerIndex = i
            clientes[i] = cliente
            break

    print(f"{direccion} se ha conectado.")

    for i, cli in enumerate(clientes):
        if cli != None and i != playerIndex:
            DataFinal = ','.join(str(b != None) for b in clientes)
            DataFinal = DataFinal + "\r\n"
            cli.sendall(DataFinal.encode('utf-8'))
    while True:
        try:
            # Recibir el mensaje del cliente
            mensaje = cliente.recv(1024)
            data = {}
            DataFinal = ""

            if mensaje.decode() == "PasarTurno":
                partida.pasarTurno(clientes)
                turnoP1 = False
                turnoP2 = False
                turnoP3 = False
                turnoP4 = False

                if partida.turno_actual == 0:
                    turnoP1 = True
                elif partida.turno_actual == 1:
                    turnoP2 = True
                elif partida.turno_actual == 2:
                    turnoP3 = True
                elif partida.turno_actual == 3:
                    turnoP4 = True

                DataFinal = "PasarTurno,"+str(turnoP1)+","+str(turnoP2)+","+str(turnoP3)+","+str(turnoP4)
                DataFinal = DataFinal + "\r\n"
                for c in clientes:
                    if c != None:
                        c.sendall(DataFinal.encode('utf-8'))
                print("PasarTurno")

            if mensaje.decode() == "iniciarPartida":
                DataFinal = "iniciarPartida"
                DataFinal = DataFinal + "\r\n"
                for cli in clientes:
                    if cli != None:
                        cli.sendall(DataFinal.encode('utf-8'))

            if mensaje.decode() == "jugadorIndex":
                DataFinal = str(playerIndex)
                DataFinal = DataFinal + "\r\n"
                cliente.sendall(DataFinal.encode('utf-8'))

            if mensaje.decode() == "statusJugadores":
                DataFinal = ','.join(str(b != None) for b in clientes)
                DataFinal = DataFinal + "\r\n"
                cliente.sendall(DataFinal.encode('utf-8'))

            if mensaje.decode() == "desconeccion":
                clientes[playerIndex] = None
                cliente.close()
                for i, cli in enumerate(clientes):
                    if cli != None and i != playerIndex:
                        DataFinal = ','.join(str(b != None) for b in clientes)
                        DataFinal = DataFinal + "\r\n"
                        cli.sendall(DataFinal.encode('utf-8'))

            print(mensaje.decode())
            print(DataFinal)
        except:
            clientes[playerIndex] = None
            cliente.close()
            for i, cli in enumerate(clientes):
                if cli != None and i != playerIndex:
                    DataFinal = ','.join(str(b != None) for b in clientes)
                    DataFinal = DataFinal + "\r\n"
                    cli.sendall(DataFinal.encode('utf-8'))
            semaphore.release()  # Libera el semáforo
            print(f"{direccion} se ha desconectado.")
            break


def esperar_conexiones():
    while True:
        cliente, direccion = server_socket.accept()
        thread_cliente = threading.Thread(
            target=manejar_cliente, args=(cliente, direccion))
        thread_cliente.start()


esperar_conexiones()
