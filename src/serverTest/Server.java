package serverTest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by August on 2016-10-10.
 */
public class Server implements Runnable{

    public void run() {
        Socket socket = null;
        ServerSocket server = null;
        try {
            server = new ServerSocket(8080);

            socket = server.accept();
            System.out.println("Server: CONNECTION! " + socket.toString());

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream());

            System.out.println("Server: READING");
            String inputLine = input.readLine();
            System.out.println("Server: " + inputLine);
            System.out.println("Server: WRITING");
            output.write("This is to all clients, hello!\n");
            output.flush();


        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                if(socket != null)
                    socket.close();
                if(server != null)
                    server.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}

//Vill ni vara säkra på att de skickas och inte hamnar i en buffer och fastnar där, använd server.flush()