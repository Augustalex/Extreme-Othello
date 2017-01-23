package gameServer.message.iterator;

import gameServer.message.parser.IMessageParser;

import java.io.InputStream;
import java.util.List;

/**
 * Created by August on 2017-01-19.
 */
public interface IMessageIterator {

    IMessageIterator setMessageExtractor(GameMessageExtractor messageExtractor);

    IMessageIterator setMessageInputStream(InputStream inputStream);

    IMessageIterator nextInput();

    List<String> getInput();

    IMessageIterator attachParsers(IMessageParser... parsers);

    IMessageIterator detachParsers(IMessageParser... parsers);
}
