import socket
import threading

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
    clientes.append(cliente)
    print(f"{direccion} se ha conectado.")
    while True:
        try:
            # Recibir el mensaje del cliente
            mensaje = cliente.recv(1024)

            if mensaje.decode() == "PasarTurno":
                partida.pasarTurno()
                print("Entra")

            mensaje_decodificado = mensaje.decode() + "Hola Mundo\r\n"
            print(mensaje_decodificado)

            for c in clientes:
                c.sendall(mensaje_decodificado.encode())
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
