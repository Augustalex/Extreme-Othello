package gameServer.open.message.parser.strategies;

import gameServer.gameEntities.Player;
import gameServer.gameEntities.PlayerDetails;
import gameServer.message.parser.IMessageParser;
import hostDetails.Host;

import java.util.List;

/**
 * Parses a Message containing Player Details.
 *
 * The format of the message should be as follows (to be valid):
 * "IP", "192.168.1.2", "PORT", "1998",
 *  "NAME", "augustalex", "COLOR", "100",
 *  "200", "255", "END"
 */
public class PlayerDetailsParser implements IMessageParser<PlayerDetails> {

    private List<String> input;

    private enum MessageIndices{
        IP_LABEL_INDEX(0), IP_INDEX(1),
        PORT_LABEL_INDEX(2), PORT_INDEX(3),
        NAME_LABEL_INDEX(4), NAME_INDEX(5),
        COLOR_LABEL_INDEX(6), COLOR_RED_INDEX(7),
        COLOR_GREEN_INDEX(8), COLOR_BLUE_INDEX(9);

        private int index;

        private MessageIndices(int index){
            this.index = index;
        }

        public int value(){
            return this.index;
        }
    }

    public static int getSizeOfMessageEndInclusive(){
        return 11;
    }

    @Override
    public PlayerDetailsParser setInput(List<String> input){
        this.input = input;
        return this;
    }

    @Override
    public boolean isValid() {
        return (
                getInput().size() >= 9
                        && getInput().get(MessageIndices.PORT_LABEL_INDEX.value()).equals("PORT")
                        && getInput().get(MessageIndices.IP_LABEL_INDEX.value()).equals("IP")
                        && getInput().get(MessageIndices.NAME_LABEL_INDEX.value()).equals("NAME")
                        && getInput().get(MessageIndices.COLOR_LABEL_INDEX.value()).equals("COLOR")
        );
    }

    @Override
    public PlayerDetails parse() {
        PlayerDetails playerDetails = new PlayerDetails();
        playerDetails.player = new Player()
                .setRed(getInteger(getInput(), MessageIndices.COLOR_RED_INDEX))
                .setGreen(getInteger(getInput(), MessageIndices.COLOR_GREEN_INDEX))
                .setBlue(getInteger(getInput(), MessageIndices.COLOR_BLUE_INDEX))
                .setName(getString(getInput(), MessageIndices.NAME_INDEX));

        playerDetails.connectionDetails = new Host(
                getString(getInput(), MessageIndices.IP_INDEX),
                getInteger(getInput(), MessageIndices.PORT_INDEX)
        );

        return playerDetails;
    }

    public List<String> getInput(){
        if(this.input == null)
            System.out.println("Input is null in PlayerDetailsParser.");
        return this.input;
    }

    private int getInteger(List<String> input, MessageIndices indexEnum){
        int index = indexEnum.value();
        String inputString = input.get(index);
        int integer = (int)Double.parseDouble(inputString);
        return integer;
    }

    private String getString(List<String> input, MessageIndices indexEnum){
        return input.get(indexEnum.value());
    }
}
