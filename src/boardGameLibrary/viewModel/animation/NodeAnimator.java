package boardGameLibrary.viewModel.animation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Created by August on 2016-10-22.
 */
public class NodeAnimator{
/*
    private Node in;
    private Node out;

    private Duration outTime = Duration.millis(220);
    private Duration inTime = Duration.millis(500);

    public NodeAnimator(Node outGoing, Node inGoing){
        this.out = outGoing;
        this.in = inGoing;
    }

    public void setInTime(int millis){
        this.inTime = Duration.millis(millis);
    }

    public void setOutTime(int millis){
        this.outTime = Duration.millis(millis);
    }

    public void playAnimation(){
        final Timeline outTimeline = animateScaleOutX(this.out, this.outTime);
        outTimeline.setOnFinished(e -> {
            final Timeline inTimeline = animateScaleInX(this.in, this.inTime);
            inTimeline.play();
        });

        outTimeline.play();
    }

    public void playAnimationThen(EventHandler<ActionEvent> onFinished){
        final Timeline outTimeline = animateScaleOutX(this.out, this.outTime);

        outTimeline.setOnFinished(e -> {
            final Timeline inTimeline = animateScaleInX(this.in, this.inTime);

            inTimeline.play();
            inTimeline.setOnFinished(onFinished);
        });

        outTimeline.play();

    }

    private Timeline animateScaleOutX(Node node, Duration duration) {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(true);

        node.scaleXProperty().set(1);
        final KeyValue kv = new KeyValue(node.scaleXProperty(), 0);
        final KeyFrame kf = new KeyFrame(duration, kv);
        timeline.getKeyFrames().add(kf);

        return timeline;
    }

    private Timeline animateScaleInX(Node node, Duration duration){
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);

        node.scaleXProperty().set(0);
        final KeyValue destinationKeyValue = new KeyValue(node.scaleXProperty(), 1);
        final KeyFrame endFrame = new KeyFrame(duration, destinationKeyValue);
        timeline.getKeyFrames().add(endFrame);

        return timeline;
    }*/
}
