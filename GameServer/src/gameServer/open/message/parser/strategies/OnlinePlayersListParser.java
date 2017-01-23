package gameServer.open.message.parser.strategies;

import gameServer.gameEntities.PlayerDetails;
import gameServer.message.parser.IMessageParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses a list of Players that are online in the server.
 * The message format is:
 * (Where # denotes an insert of other message format, and . . . denotes
 * the that a repeating insert may be included a variable amount of times)
 * ONLINEPLAYERS
 * #PlayerDetailsMessage
 * #PlayerDetailsMessage
 *          . . .
 *  END
 *
 *  Note that each PlayerDetailsMessage is complete and all each contains
 *  their own "END" tag in their message. Which means that this message
 *  contains to "END" ends at the END if there are at least one player online.
 */
public class OnlinePlayersListParser implements IMessageParser<List<PlayerDetails>> {
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
                getInput().size() > 1
                && getInput().get(getInput().size()-1).equals("END")
                && ((getInput().size() > 2 && getInput().get(getInput().size()-2).equals("END"))
                        || getInput().size() == 2)
                );
    }

    @Override
    public List<PlayerDetails> parse() {
        List<PlayerDetails> players = new ArrayList<>();

        PlayerDetailsParser playerParser = new PlayerDetailsParser();
        int playerDetailsMessageSize = PlayerDetailsParser.getSizeOfMessageEndInclusive();
        //The first and last word of the input is not any PlayerDetailsObject.
        int numberOfPlayersInMessage = (getInput().size() - 2) / playerDetailsMessageSize;

        int start = 1; //As first word is not a playerDetails object.
        int processedPlayers = 0;
        while(processedPlayers < numberOfPlayersInMessage){
            players.add(
                    playerParser.setInput(
                        getInput().subList(
                                start+(processedPlayers*playerDetailsMessageSize),
                                start+((processedPlayers + 1)*playerDetailsMessageSize))
                    ).parse()
            );
            processedPlayers += 1;
        }

        return players;
    }
}
