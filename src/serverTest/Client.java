package serverTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by August on 2016-10-10.
 */
public class Client implements Runnable {

    public void run() {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 8080);

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream());

            System.out.println("Client: WRITING");
            output.write("I am not your client any, more.\n");
            output.flush();

            System.out.println("Client: READING");
            String inputLine = input.readLine();
            System.out.println("Client: " + inputLine);

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            try {
                if(socket != null)
                    socket.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
