package gameServer.open.message.parser.strategies;

import gameServer.message.parser.IMessageParser;
import hostDetails.Host;

import java.util.List;

/**
 * The ServerDetailsMessage format is as follows:
 * IP
 * 127.0.0.1
 * PORT
 * 2001
 * END
 *
 * With the IP and Port numbers here just being an example.
 */
public class ServerDetailsMessageParser implements IMessageParser<Host> {

    private List<String> input;

    @Override
    public List<String> getInput() {
        return this.input;
    }

    @Override
    public IMessageParser setInput(List<String> input) {
        this.input = input;
        return this;
    }

    @Override
    public boolean isValid() {
        return(
                getInput().size() == 5
                && getInput().get(0).equals("IP")
                && getInput().get(2).equals("PORT")
                && getInput().get(4).equals("END")
                );
    }

    @Override
    public Host parse() {
        return new Host(
                getInput().get(1),
                Integer.parseInt(getInput().get(3))
        );
    }
}
