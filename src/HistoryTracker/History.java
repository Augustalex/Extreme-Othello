package HistoryTracker;

import HistoryTracker.exceptions.NoHistoryException;

/**
 * Created by August on 2016-10-10.
 */
public class History{

    private static final int maxHistorySize = 1000;

    private HistoryCall[] history = new HistoryCall[History.maxHistorySize];

    private int cursor = 0;
    private int historySize = 0;

    public void store(HistoryCall call){
        if(this.cursor == History.maxHistorySize)
            this.cursor /= 2;

        this.history[this.cursor] = call;
        System.out.println("Call stored at " + this.cursor);

        this.cursor++;
        this.historySize++;
    }

    public HistoryCall get(){
        if(this.cursor < 0)
            throw new NoHistoryException();

        return this.history[this.cursor - 1];
    }

    public void revertTo(int index){
        if(index >= this.historySize || index < 0)
            throw new IllegalArgumentException();

        System.out.println("Reverted from " + this.cursor + " to " + index);

        this.cursor = index;
    }

    public int getCursorPosition(){
        return this.cursor;
    }

}
