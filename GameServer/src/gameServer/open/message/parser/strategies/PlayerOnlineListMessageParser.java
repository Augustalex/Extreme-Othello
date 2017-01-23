package gameServer.open.message.parser.strategies;

import gameServer.gameEntities.PlayerDetails;
import gameServer.message.parser.IMessageParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by August on 2017-01-19.
 */
public class PlayerOnlineListMessageParser implements IMessageParser<List<PlayerDetails>> {

    private static final int PLAYER_DETAILS_MESSAGE_LENGTH = 11;

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
        return getInput().size() > 1
                && getInput().get(0).equals("ONLINEPLAYERS");
    }

    @Override
    public List<PlayerDetails> parse() {
        List<PlayerDetails> output = new ArrayList<>();
        PlayerDetailsParser playerDetailsParser = new PlayerDetailsParser();

        List<String> input = getInput();

        int end = input.size() - 1; //The last word is "END" for the end of "ONLINEPLAYERS" message.

        int index = 1; //PlayerDetails message starts after the first String.
        while(index < end){
            List<String> nextPlayerMessage = input.subList(index, index+PLAYER_DETAILS_MESSAGE_LENGTH);
            PlayerDetails nextPlayer = playerDetailsParser.setInput(nextPlayerMessage).parse();
            output.add(nextPlayer);
        }

        return output;
    }
}
