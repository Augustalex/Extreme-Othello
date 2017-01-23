package gameServer.open.message.handler;

import gameServer.message.handler.IMessageHandler;
import gameServer.message.parser.IMessageParser;
import gameServer.open.game.OpenGameDirectory;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * Handles an incoming message on a Socket coming from a
 * OpenGameServer (with no other ties but the use of a
 * OpenGameDirectory, which suggest a use with the OpenGameServer).
 * @param <T>
 */
public abstract class MessageHandler<T extends IMessageParser> implements IMessageHandler<T> {

    private OpenGameDirectory gameDirectory;

    public MessageHandler(OpenGameDirectory gameDirectory){
        this.gameDirectory = gameDirectory;
    }

    public OpenGameDirectory getGameDirectory(){
        return this.gameDirectory;
    }
}
