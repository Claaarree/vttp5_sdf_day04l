package day04.wsx;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMainx {
    public static void main(String[] args) throws UnknownHostException, IOException {
        //Setting up file to upload
        String dirPath = "";
        if (args.length > 0) {
            dirPath = args[0];
        }
        String filename = "abc.txt";

        //Reading the file to upload
        File dirPathfilename = new File (dirPath + File.separator + filename);
        FileInputStream fis = new FileInputStream(dirPathfilename);
        BufferedInputStream bis = new BufferedInputStream(fis);

        
    
       //Connect to server
        Socket sock = new Socket("localhost", 5000);

        OutputStream os = sock.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);

        //Sending file to server
        dos.writeUTF(dirPathfilename.getName());
        dos.writeLong(dirPathfilename.length());
        
        // Create a 4K buffer
        byte[] buff = new byte[4 * 1024];
        int bytesRead = 0;
        int sendBytes = 0;
        

        while ((bytesRead = bis.read(buff)) > 0) {
            dos.write(buff, 0, bytesRead);
            sendBytes += bytesRead;
            System.out.printf(" > %d Send %d of %d\n", bytesRead, sendBytes, dirPathfilename.length());
        
        } 
        bis.close();

        dos.flush();
        os.flush();
        dos.close();
        os.close();

        sock.close();
    }
}