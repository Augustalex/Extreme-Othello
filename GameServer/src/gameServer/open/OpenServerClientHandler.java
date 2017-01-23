package gameServer.open;

import gameServer.open.game.OpenGameDirectory;
import gameServer.open.message.handler.MessageHandlerFacade;

import java.io.IOException;
import java.net.Socket;

/**
 * Handles client connections from OpenGameServer and responds to
 * received messages appropriately.
 */
public class OpenServerClientHandler implements Runnable{

    private Socket socket;
    private final OpenGameDirectory openGameDirectory;

    public OpenServerClientHandler(Socket socket, OpenGameDirectory openGameDirectory){
        this.socket = socket;
        this.openGameDirectory = openGameDirectory;
    }

    @Override
    public void run() {
        try {
            MessageHandlerFacade.get().handle(
                    socket.getInputStream(),
                    socket.getOutputStream(),
                    openGameDirectory
            );
        } catch (IOException e) {
            System.out.println("Could not send/receive client message.");
            e.printStackTrace();
        }

    }


}
