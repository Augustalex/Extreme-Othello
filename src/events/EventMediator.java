package events;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.Event;
import javafx.scene.input.MouseEvent;

/**
 * Created by August on 2016-10-15.
 */
public class EventMediator<T> {

    private T event;

    private BooleanProperty hasEvent = new SimpleBooleanProperty(false);

    public void setEvent(T event){
        this.event = event;
        this.hasEventProperty().set(true);
    }

    public T getEvent(){
        this.hasEventProperty().set(false);
        return this.event;
    }

    public BooleanProperty hasEventProperty(){
        return this.hasEvent;
    }
}
