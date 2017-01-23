package gameServer.open.message.parser.strategies;

import gameServer.gameEntities.PlayerDetails;
import gameServer.message.parser.IMessageParser;
import javafx.util.Pair;

import java.util.List;

/**
 * Created by August on 2017-01-19.
 */
public class JoinGameMessageParser implements IMessageParser<Pair<String, PlayerDetails>> {
    private List<String> input;

    private static final int gameIdLabelIndex = 0;
    private static final int gameIdIndex = 1;

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
                input.size() >= 2
                && input.get(gameIdLabelIndex).equals("GAMEID")
        );
    }

    /**
     * Returns a pair of GameId and the Player that wants to join the
     * game (that is, the PlayerDetails of that player).
     * @return
     */
    @Override
    public Pair<String, PlayerDetails> parse() {
        return new Pair<>(
                input.get(gameIdIndex),
                new PlayerDetailsParser()
                        .setInput(getInput().subList(1,getInput().size()))
                        .parse()
        );
    }
}
