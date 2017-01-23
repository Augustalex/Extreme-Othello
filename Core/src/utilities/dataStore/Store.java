package utilities.dataStore;

import java.io.Serializable;
import java.util.List;

/**
 * Created by August on 2016-10-25.
 */
public interface Store<T> extends Serializable {

    T get(String objectId);

    void store(T object);

    void remove(T object);

    List<T> getStoreCopy();

    int size();
}
