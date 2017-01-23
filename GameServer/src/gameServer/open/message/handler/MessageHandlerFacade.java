package gameServer.open.message.handler;

import gameServer.message.handler.IMessageHandler;
import gameServer.message.iterator.IMessageIterator;
import gameServer.message.iterator.MessageIterator;
import gameServer.message.parser.IMessageParser;
import gameServer.open.game.OpenGameDirectory;
import gameServer.open.message.handler.strategies.JoinGameMessageHandler;
import gameServer.open.message.handler.strategies.LeaveGameMessageHandler;
import gameServer.open.message.handler.strategies.LogOnMessageHandler;
import gameServer.open.message.parser.strategies.JoinGameMessageParser;
import gameServer.open.message.parser.strategies.LeaveGameMessageParser;
import gameServer.open.message.parser.strategies.LogOnMessageParser;
import gameServer.open.message.parser.strategies.PlayerDetailsParser;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Imposes the strategy pattern to handle different Message handlers
 * depending on the message contents. Thus hides the message handling
 * implementations from the Servers client handler (which uses this class).
 */
public class MessageHandlerFacade {

    private LogOnMessageParser logOnMessageParser = new LogOnMessageParser();
    private JoinGameMessageParser joinGameMessageParser = new JoinGameMessageParser();
    private LeaveGameMessageParser leaveGameMessageParser = new LeaveGameMessageParser();

    private final static class MessageHandlerFactoryHolder{
        public final static MessageHandlerFacade instance = new MessageHandlerFacade();
    }

    private MessageHandlerFacade(){}

    public static MessageHandlerFacade get(){
        return MessageHandlerFactoryHolder.instance;
    }

    public void handle(InputStream inputStream, OutputStream outputStream, OpenGameDirectory openGameDirectory) {
        IMessageIterator messageIterator = new MessageIterator(inputStream).nextInput();
        messageIterator.attachParsers(getAllParsers());

        System.out.println("Handling message on OpenServer.");
        System.out.println("Message is: " + messageIterator.getInput());
        if (joinGameMessageParser.isValid())
            new JoinGameMessageHandler(openGameDirectory).handle(outputStream, joinGameMessageParser);
        else if (logOnMessageParser.isValid())
            new LogOnMessageHandler(openGameDirectory).handle(outputStream, logOnMessageParser);
        else if (leaveGameMessageParser.isValid())
            new LeaveGameMessageHandler(openGameDirectory).handle(outputStream, leaveGameMessageParser);
        else
            throw new RuntimeException("No matching handlers for client message: " + messageIterator.getInput());
    }

    private IMessageParser[] getAllParsers(){
        return new IMessageParser[]{logOnMessageParser, joinGameMessageParser, leaveGameMessageParser};
    }
}
