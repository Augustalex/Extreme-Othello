package gameServer.open.message.parser.strategies;

import gameServer.gameEntities.PlayerDetails;
import gameServer.message.parser.IMessageParser;
import javafx.util.Pair;

import java.util.List;

/**
 *  Parses a LogOn reqeust message.
 *  The format is: (where # means an inclusion of other
 *  message. That is including the END signifier.)
 *  LOGON
 *  #PlayerDetailsMessage
 *  SPOTS
 *  4
 *  END
 */
public class LogOnMessageParser implements IMessageParser<Pair<PlayerDetails, Integer>> {

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
        return getInput().size() == 15
                && getInput().get(0).equals("LOGON")
                && getInput().get(14).equals("END");
    }

    @Override
    public Pair<PlayerDetails, Integer> parse() {
        int startOfPlayerDetailsMessage = 1;
        int endOfPlayerDetailsMessage = startOfPlayerDetailsMessage + PlayerDetailsParser.getSizeOfMessageEndInclusive() - 1;
        PlayerDetailsParser playerParser = new PlayerDetailsParser()
                .setInput(
                        getInput()
                            .subList(startOfPlayerDetailsMessage, endOfPlayerDetailsMessage)
                );

        PlayerDetails player = playerParser.parse();

        int playerSpotsIndex = endOfPlayerDetailsMessage + 2;
        int numberOfPlayerSpots = Integer.parseInt(getInput().get(playerSpotsIndex));

        return new Pair<>(player, numberOfPlayerSpots);
    }
}
