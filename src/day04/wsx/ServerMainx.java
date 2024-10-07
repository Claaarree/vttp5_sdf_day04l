package day04.wsx;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMainx {
    public static void main(String[] args) throws IOException {
        int port = 3000;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        System.out.printf("Listening in on port %d\n", port);

        //Create new server
        ServerSocket server = new ServerSocket(port);

        while (true) {
            System.out.println("Waiting for connection...");

            //Establish connection with client
            Socket connection = server.accept();

            System.out.println("Got a client connection");            

            //Get input from client
            InputStream is = connection.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            
            //Getting file from client
            String filename = dis.readUTF();
            Long fileSize = dis.readLong();
            System.out.println(fileSize);
         

            //Need a FileOutputStream to create the received file
            FileOutputStream fos = new FileOutputStream(filename);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            byte [] buff = new byte[4 * 1024];
            int bytesRead = 0;
            int bytesReceived = 0;

            System.out.printf("Receiving file %s of size %d\n", filename, fileSize);

        while (bytesReceived < fileSize) {
            // Number of bytes read
            bytesRead = dis.read(buff);
            bytesReceived += bytesRead;
            
            // Write to local file
            bos.write(buff, 0, bytesRead);
            
            System.out.printf("> %d Recv %d of %d\n", bytesRead, bytesReceived, fileSize);
        }
        bos.flush();
        fos.flush();
        bos.close();
        fos.flush();
         

            

        connection.close();

        }
        
        

        
        
    }
    
}
