package HistoryTracker;

import router.Router;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by August on 2016-10-10.
 */
public class HistoryCall{

    private Method method;
    private Object context;
    private Object[] arguments;

    public HistoryCall(Method method, Object context, Object[] arguments){
        this.method = method;
        this.context = context;
        this.arguments = arguments;
    }

    public void invoke() throws InvocationTargetException, IllegalAccessException {
        this.method.invoke(this.context, this.arguments);
    }
}
