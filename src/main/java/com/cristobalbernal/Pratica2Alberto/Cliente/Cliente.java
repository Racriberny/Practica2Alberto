package com.cristobalbernal.Pratica2Alberto.Cliente;

import com.cristobalbernal.Pratica2Alberto.Lib.Lib;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
    private static final String DIRECCION = "localhost";
    private static final int PUERTP = 1500;


    public static void main(String[] args) {
        try {
            Socket sc = new Socket(DIRECCION, PUERTP);

            DataInputStream inputStream = new DataInputStream(sc.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(sc.getOutputStream());

            String ruta = pedirDatos();

            assert ruta != null;
            outputStream.writeUTF(ruta);

            boolean existe = inputStream.readBoolean();

            if(existe){
                //Guardamos la longitud del archivo!!!
                int longitudDelArchivo = inputStream.readInt();

                byte[] contenidoDelArchivo = new byte[longitudDelArchivo];

                for (int i = 0; i < longitudDelArchivo; i++) {
                    contenidoDelArchivo[i] = inputStream.readByte();
                }

                String contenidoFichero = new String(contenidoDelArchivo);
                //Aqui se imprime el contenido del archivo!!!
                System.out.println(contenidoFichero);

            }else{
                //Mensaje de error si no existe!!!
                System.out.println("Error, no existe el fichero");
            }

            sc.close();

        } catch (IOException iOE) {
            System.out.println(iOE.getMessage());
        }
    }
    public static String pedirDatos(){
        boolean valido = false;
        String ruta = null;
        while (!valido){
            System.out.println("Introduce la ruta del archivo:");
            ruta = Lib.leerLinea();
            if (ruta.length() > 1){
                valido = true;
            }
        }
        return ruta;
    }
}
