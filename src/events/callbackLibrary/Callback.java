package events.callbackLibrary;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by August on 2016-10-10.
 */
public abstract class Callback {

    protected Method method;
    protected Object context;
    protected Object[] arguments;

    public abstract void invoke() throws InvocationTargetException, IllegalAccessException;
}
