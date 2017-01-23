package gameServer.closed.message.parser.strategies;

import gameServer.message.parser.IMessageParser;

import java.util.List;

/**
 * Used for stopping a game (i.e. game is over.)
 * The format is as follows:
 * STOP
 * END
 */
public class StopMessageParser implements IMessageParser<Boolean> {

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
        return(getInput().size() == 1 && getInput().get(0).equals("STOP"));
    }

    @Override
    public Boolean parse() {
        return this.isValid();
    }
}
