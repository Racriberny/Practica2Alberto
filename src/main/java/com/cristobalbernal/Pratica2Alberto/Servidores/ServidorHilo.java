package com.cristobalbernal.Pratica2Alberto.Servidores;

import java.io.*;
import java.net.Socket;

public class ServidorHilo extends Thread {
    private final Socket socket;

    public ServidorHilo(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){

        DataInputStream inputStream = null;
        DataOutputStream outputStream = null;
        try {

            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());

            String ruta = inputStream.readUTF();

            File rutaDelArchivoFile = new File(ruta);

            if(rutaDelArchivoFile.exists()){
                outputStream.writeBoolean(true);

                BufferedReader br = new BufferedReader(new FileReader(ruta));

                String lineas;
                StringBuilder contenidoDelArchivo = new StringBuilder();

                while((lineas = br.readLine()) != null){
                    contenidoDelArchivo.append(lineas).append("\r\n");
                }

                br.close();

                byte[] contenidoFichero = contenidoDelArchivo.toString().getBytes();

                outputStream.writeInt(contenidoFichero.length);

                for (byte fichero : contenidoFichero) {
                    outputStream.writeByte(fichero);
                }

                socket.close();

            }else{
                outputStream.writeBoolean(false);
            }


        } catch (IOException iOE) {
            System.out.println(iOE.getMessage());
        } finally {
            try {
                assert inputStream != null;
                assert outputStream != null;
                inputStream.close();
                outputStream.close();
            } catch (IOException iOE) {
                System.out.println(iOE.getMessage());
            }
        }
    }
}
