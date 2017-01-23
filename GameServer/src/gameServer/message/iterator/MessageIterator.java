package gameServer.message.iterator;

import gameServer.message.parser.IMessageParser;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Iterates over messages in a Message Extractor. Message parser may be attached
 * in the class so that their input is updated every time the extractor is iterated
 * upon (i.e. user fires "nextInput" method).
 */
public class MessageIterator implements IMessageIterator{

    private Map<IMessageParser, InputListener> parserSubscribers = new HashMap<>();
    private ObjectProperty<List<String>> input = new SimpleObjectProperty<>();

    private GameMessageExtractor messageExtractor;

    public MessageIterator(){}

    public MessageIterator(GameMessageExtractor extractor){
        this.setMessageExtractor(extractor);
    }

    public MessageIterator(InputStream inputStream){
        this.messageExtractor = new GameMessageExtractor(inputStream);
    }

    @Override
    public IMessageIterator setMessageExtractor(GameMessageExtractor messageExtractor) {
        this.messageExtractor = messageExtractor;
        return this;
    }

    @Override
    public IMessageIterator setMessageInputStream(InputStream inputStream) {
        this.messageExtractor = new GameMessageExtractor(inputStream);
        return this;
    }

    @Override
    public IMessageIterator nextInput() {
        this.input.set(this.messageExtractor.nextGameMessage());
        return this;
    }

    @Override
    public List<String> getInput() {
        return this.input.get();
    }

    @Override
    public MessageIterator attachParsers(IMessageParser... parsers){
        for(IMessageParser parser : parsers){
            parserSubscribers.put(parser, new InputListener(parser));
            input.addListener(parserSubscribers.get(parser));
            parser.setInput(getInput());
        }

        return this;
    }

    @Override
    public MessageIterator detachParsers(IMessageParser... parsers){
        for(IMessageParser parser : parsers)
            input.removeListener(parserSubscribers.remove(parser));

        return this;
    }

    private class InputListener implements javafx.beans.value.ChangeListener<List<String>> {

        private final IMessageParser parser;

        public InputListener(IMessageParser parser){
            this.parser = parser;
        }

        @Override
        public void changed(ObservableValue<? extends List<String>> observable, List<String> oldValue, List<String> newValue) {
            this.parser.setInput(newValue);
        }
    }
}
