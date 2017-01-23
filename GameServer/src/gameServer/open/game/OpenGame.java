package gameServer.open.game;

import gameServer.closed.ClosedGameServer;
import gameServer.gameEntities.PlayerDetails;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by August on 2017-01-17.
 */
public class OpenGame implements IOpenGame {

    private String id = "";
    private Map<String, PlayerDetails> allPlayerDetails = new HashMap<>();
    private int port = ((Supplier<Integer>)() -> {
        int start = 2051;
        int max = 2500;

        Random random = new Random(System.currentTimeMillis());
        return random.nextInt(max-start) + start;
    }).get();

    private final int playerSpots;

    public OpenGame(int playerSpots){
        this.playerSpots = playerSpots;
    }

    @Override
    public PlayerDetails[] getPlayerDetails() {
        return new PlayerDetails[0];
    }

    @Override
    public IOpenGame addPlayer(PlayerDetails playerDetails) {
        if(allPlayerDetails.size() == 8) //is already full
            return this;

        allPlayerDetails.put(playerDetails.connectionDetails.address, playerDetails);

        if(allPlayerDetails.size() == 8) //is now full
            this.start().start();
        return this;
    }

    @Override
    public IOpenGame removePlayer(String playerIP) {
        allPlayerDetails.remove(playerIP);
        return this;
    }

    @Override
    public ClosedGameServer start() {
        return new ClosedGameServer(
                Arrays.stream(allPlayerDetails.entrySet().stream().toArray(PlayerDetails[]::new)).collect(Collectors.toList())
        ).start();
    }

    @Override
    public IOpenGame setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getPort() {
        return this.port;
    }

    @Override
    public String getIP() {
        return "127.0.0.1";
    }

    @Override
    public boolean isFull() {
        return allPlayerDetails.size() > 8;
    }
}
