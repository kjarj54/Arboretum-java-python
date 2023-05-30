
class menejoJSON:
    
    def envioJSON():
        #envio del json
        with open('infotmacion.json', 'rb') as archivo:
            contenido = archivo.read()
            
        