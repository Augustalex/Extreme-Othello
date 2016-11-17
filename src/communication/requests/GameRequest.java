package communication.requests;

/**
 * Pre configured Request object that asks for
 * a client to join a game.
 */
public class GameRequest extends Request {

    public GameRequest() {
        super("JOIN GAME", RequestType.QUESTION);
    }
}
