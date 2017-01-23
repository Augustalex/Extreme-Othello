package gameServer.closed.message.handler;

import gameServer.closed.ClosedGame;
import gameServer.closed.message.handler.strategies.MoveMessageHandler;
import gameServer.closed.message.handler.strategies.StopGameMessageHandler;
import gameServer.closed.message.parser.strategies.MoveMessageParser;
import gameServer.closed.message.parser.strategies.StopMessageParser;
import gameServer.message.handler.IMessageHandler;
import gameServer.message.iterator.MessageIterator;
import gameServer.message.parser.IMessageParser;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by August on 2017-01-21.
 */
public class MessageHandlerFacade {

    private StopMessageParser stopMessageParser = new StopMessageParser();
    private MoveMessageParser moveMessageParser = new MoveMessageParser();

    private final static class MessageHandlerFactoryHolder{
        public final static MessageHandlerFacade instance = new MessageHandlerFacade();
    }

    private MessageHandlerFacade(){}

    public static MessageHandlerFacade get(){
        return MessageHandlerFactoryHolder.instance;
    }

    public void handle(OutputStream output, InputStream input, ClosedGame game){
        MessageIterator messageIterator = new MessageIterator(input);
        messageIterator.attachParsers(stopMessageParser, moveMessageParser);

        if(stopMessageParser.isValid())
            new StopGameMessageHandler(game).handle(output, stopMessageParser);
        else if(moveMessageParser.isValid())
            new MoveMessageHandler(game).handle(output, moveMessageParser);
        else
            throw new RuntimeException("No matching handlers for client message: " + messageIterator.getInput());
    }

    public IMessageParser[] getClosedGameParsers(){
        return new IMessageParser[]{stopMessageParser, moveMessageParser};
    }

}
