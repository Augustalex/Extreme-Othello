package communication.receiver;

import communication.sender.Package;

/**
 * Created by August on 2016-10-27.
 */
public class VoidConnection implements Connection {
    @Override
    public void connect() throws Exception {

    }

    @Override
    public Package receive() {
        return null;
    }

    @Override
    public void close() {
        System.out.println("Trying to close void connection.");
    }
}
