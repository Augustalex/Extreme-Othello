package communication.receiver;

import communication.sender.Package;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Created by August on 2016-10-27.
 */
public class Delivery {

    private BooleanProperty canceled = new SimpleBooleanProperty(false);
    private ObjectProperty<Package> payload = new SimpleObjectProperty<>(null);

    public BooleanProperty canceledProperty(){
        return this.canceled;
    }

    public boolean isCanceled(){
        return this.canceled.get();
    }

    public ObjectProperty<Package> packageProperty(){
        return this.payload;
    }

    public boolean hasPackage(){
        return this.payload.get() != null;
    }

    public Package getPackage(){
        return this.payload.get();
    }
}
