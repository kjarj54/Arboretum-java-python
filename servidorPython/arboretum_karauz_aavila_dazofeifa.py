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
