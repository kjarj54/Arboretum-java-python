import socket
import threading
import ujson
import json

from Carta import Carta
from Partida import Partida

# Definir la dirección IP y el puerto para el servidor
IP = "localhost"
PORT = 8000

# Crear un objeto de socket
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Vincular el objeto de socket al IP y puerto
server_socket.bind((IP, PORT))

# Esperar conexiones entrantes
server_socket.listen()

# Lista de clientes conectados
clientes = []

partida = Partida(15)

# Función para manejar las conexiones entrantes


def manejar_cliente(cliente, direccion):
    # Añadir el socket del cliente a la lista de clientes
    playerIndex = -1
    clientes.append(cliente)
    if len(clientes) > 0 :
        playerIndex = len(clientes)

    print(f"{direccion} se ha conectado.")
    while True:
        try:
            # Recibir el mensaje del cliente
            mensaje = cliente.recv(1024)
            data = {}
            DataFinal = ""

            if mensaje.decode() == "PasarTurno":
                partida.pasarTurno()
                data = { 'nombre': 'John', 'puntos': 30 }
                DataFinal = json.dumps(data)
                DataFinal = DataFinal + "\r\n"
                for c in clientes:
                    # Envía el tamaño del JSON
                    c.sendall(DataFinal.encode('utf-8'))  # Envía el JSON
                print("PasarTurno")

            if mensaje.decode() == "jugadorIndex":
                DataFinal = str(playerIndex)
                DataFinal = DataFinal + "\r\n"
                print("jugadorIndex")
                cliente.sendall(DataFinal.encode('utf-8'))
            print(DataFinal)
            print(mensaje.decode())
            # envio del json
            # with open('informacion.json', 'rb') as archivo:
            #     contenido = archivo.read()

            # contenido_codificado = ujson.dumps(ujson.loads(contenido))
            # for c in clientes:
                # Envía el tamaño del JSON
                # c.sendall(DataFinal.encode('utf-8'))  # Envía el JSON
                # json_dataConver = json.loads(json_data)
                # print(json_dataConver['nombre'] + " " + json_dataConver['puntos'])
            # c.sendall(contenido_codificado)
            # c.sendall(mensajeEditado.encode())
            # if not mensaje:
            #     clientes.remove(cliente)
            #     cliente.close()
            #     print(f"{direccion} se ha desconectado.")
            #     break
            # Enviar el mensaje a todos los clientes

            # cliente.close()
            # print(f"{direccion} se ha desconectado.")
        except:
            # Si hay algún error, el cliente se ha desconectado
            clientes.remove(cliente)
            cliente.close()
            print(f"{direccion} se ha desconectado.")
            break
# Función para esperar conexiones entrantes


def esperar_conexiones():
    while True:
        cliente, direccion = server_socket.accept()
        # Crear un hilo para manejar las conexiones entrantes de este cliente
        thread_cliente = threading.Thread(
            target=manejar_cliente, args=(cliente, direccion))
        thread_cliente.start()


# Iniciar la función para esperar conexiones entrantes
esperar_conexiones()
