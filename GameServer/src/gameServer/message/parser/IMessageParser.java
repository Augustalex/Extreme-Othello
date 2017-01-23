package gameServer.message.parser;

import java.util.List;

/**
 * Parses a set of strings to a certain Object.
 *
 * Includes a method to check the input so that it matches
 * the specified parsing rules.
 * @param <T>
 */
public interface IMessageParser<T> {

    List<String> getInput();

    IMessageParser setInput(List<String> input);

    boolean isValid();

    T parse();
}
