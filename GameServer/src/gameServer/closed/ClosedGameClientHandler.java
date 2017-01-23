package gameServer.closed;

import gameServer.closed.message.handler.MessageHandlerFacade;

import java.io.IOException;
import java.net.Socket;

/**
 * Handles a socket connection to player involved in a  now closed
 * game. When a player sends a move, this move is broadcast to all
 * other players via the BroadcastChannel class received to the
 * constructor.
 */
public class ClosedGameClientHandler {

    private final Socket client;
    private final ClosedGame game;

    public ClosedGameClientHandler(Socket client, ClosedGame game){
        this.client = client;
        this.game = game;
    }

    public void start(){
        try {
            while(game.gameOnProperty().get()) {
                MessageHandlerFacade.get().handle(
                        client.getOutputStream(),
                        client.getInputStream(),
                        game
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
