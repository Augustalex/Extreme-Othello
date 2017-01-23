package gameServer.open;

import gameServer.Server;
import gameServer.open.game.OpenGameDirectory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server that contains an OpenGameDirectory. Each client is handled by
 * the OpenGameConnectionHandler class.
 */
public class OpenServer extends Server {

    private static final int serverPort = 1999;

    private OpenGameDirectory openGameDirectory = new OpenGameDirectory();

    public void start(){
        serverOnProperty().set(true);
        new Thread(() -> {
            try{
                ServerSocket server = new ServerSocket(serverPort);
                System.out.println("Waiting for connections.");
                while(serverOnProperty().get())
                    handleClient(server.accept());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void handleClient(Socket socket) throws IOException {
        System.out.println("Got connection to client.");
        executorService.submit(new OpenServerClientHandler(socket, openGameDirectory));
    }

    public static void main(String[] args){
        OpenServer gameServer = new OpenServer();
        System.out.println("Starting server.");
        gameServer.start();
        System.out.println("Server has started.");
        new Thread(() ->{
                OpenServerTester tester = new OpenServerTester("10.10.107.76", serverPort, "1");
                tester.testLogOn();
        }).start();

        new Thread(() ->{
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            OpenServerTester tester = new OpenServerTester("10.10.107.76", serverPort, "2");
            tester.testLogOn();
        }).start();

    }

}
