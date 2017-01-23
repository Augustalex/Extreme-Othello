package communication;

import javafx.collections.ListChangeListener;

/**
 * Adds a listener for a single fire event.
 *
 * In other words, the event listener will be removed
 * after being fired only once.
 * @param <T>
 */
public class OneTimeListChangeListener<T> implements ListChangeListener<T> {

    private ListChangeListener<? super T> listChangeListener;

    public OneTimeListChangeListener(ListChangeListener<? super T> listChangeListener){
        this.listChangeListener = listChangeListener;
    }

    @Override
    public void onChanged(Change<? extends T> c) {
        this.listChangeListener.onChanged(c);
        c.getList().removeListener(this.listChangeListener);
    }
}
