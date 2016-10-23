package boardGameLibrary.viewModel.animation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Created by August on 2016-10-22.
 */
public interface Animator {

    void setInTime(int millis);

    void setOutTime(int millis);

    void playAnimation();

    void playAnimationThen(EventHandler<ActionEvent> eventHandler);
}
