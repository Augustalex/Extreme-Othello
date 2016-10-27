package communication.receiver;

import communication.sender.Package;

/**
 * Created by August on 2016-10-27.
 */
public interface Connection {

    void connect() throws Exception;

    Package receive();

    void close();
}
