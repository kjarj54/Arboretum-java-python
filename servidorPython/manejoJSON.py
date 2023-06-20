
class menejoJSON:
    
    def envioJSON():
        #envio del json
        with open('informacion.json', 'rb') as archivo:
            contenido = archivo.read()
            
        