package boardGameLibrary.viewModel;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.Node;
import javafx.scene.layout.Region;

/**
 * Created by August on 2016-10-21.
 */
public class ViewDimensionBinder {

    public static void adaptWidthTo(Node binder, ReadOnlyDoubleProperty changingProperty){
        changingProperty.addListener(e -> {
            binder.maxWidth(changingProperty.get());
            binder.minWidth(changingProperty.get());
        });
    }

    public static void adaptHeightTo(Node binder, ReadOnlyDoubleProperty changingProperty){
        changingProperty.addListener(e -> binder.maxHeight(changingProperty.get()));
    }

    public static void fixedBindTo(Region binder, Region container){
        binder.minWidthProperty().bind(container.widthProperty());
        binder.maxWidthProperty().bind(container.widthProperty());

        binder.minHeightProperty().bind(container.heightProperty());
        binder.maxHeightProperty().bind(container.heightProperty());
    }

    public static void squareBindTo(Region binder, Region container){
        if(container.widthProperty().get() > container.heightProperty().get()){
            bindTwoToOneDimensions(binder, container.heightProperty());
        }
        else{
            bindTwoToOneDimensions(binder, container.widthProperty());
        }
    }

    public static void bindTwoToOneDimensions(Region binder, ReadOnlyDoubleProperty bindTo){
        bindOneToOneDimension(binder.minWidthProperty(), binder.maxWidthProperty(), bindTo);
        bindOneToOneDimension(binder.minHeightProperty(), binder.maxHeightProperty(), bindTo);
    }

    public static void bindOneToOneDimension(DoubleProperty minDimension, DoubleProperty maxDimension, ReadOnlyDoubleProperty bindTo){
        minDimension.unbind();
        minDimension.bind(bindTo);

        maxDimension.unbind();
        maxDimension.bind(bindTo);
    }
}
