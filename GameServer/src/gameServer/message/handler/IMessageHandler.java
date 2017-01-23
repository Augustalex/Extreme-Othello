package gameServer.message.handler;

import gameServer.message.parser.IMessageParser;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * Interface for handling an OpenGameServer Client message.
 *
 */
public interface IMessageHandler<T extends IMessageParser> {

    static void sendMessage(OutputStream outputStream, List<String> message){
        PrintStream output = new PrintStream(outputStream);

        StringBuilder builder = new StringBuilder();
        for(String word : message)
            builder.append(word+"\0");

        output.println(builder.toString());
        output.flush();
    }

    void handle(OutputStream output, T parser);
}
