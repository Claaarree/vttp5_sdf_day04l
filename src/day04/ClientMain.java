package day04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        //Create a socket to connect to the server
        Socket sock = new Socket ("localhost", 5000);

        System.out.println(">>> Connected to the server\n");
        
        
        Console cons = System.console();

        //Read a message
        String msg = cons.readLine(">>>");

        //Output Stream
        OutputStream os = sock.getOutputStream();
        Writer writer = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(writer);

        //Input Stream
        InputStream is = sock.getInputStream();
        Reader reader = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader);

        //Wirte the messgae to the server
        //Add a new line to the Writer
        bw.write(msg + "/n");
        //Force it in to network
        bw.flush();

        // Read the result back in
        msg = br.readLine();

        System.out.printf(">>> FROM SERVER: %s\n", msg);

        //Close the connection
        sock.close();

    }
    
}
