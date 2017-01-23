package gameServer.message.iterator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by August on 2017-01-18.
 */
public class GameMessageExtractor {

    private final Scanner inputScanner;

    public GameMessageExtractor(InputStream inputStream){
        inputScanner = new Scanner(inputStream);
    }

    public List<String> nextGameMessage(){
        List<String> input = new ArrayList<>();
        while(!inputScanner.hasNext()){}

        String next = inputScanner.nextLine();

        for(String word : next.split("\0")) {
            //System.out.println("Split word: " + word);
            input.add(word);
        }

        return input;
    }
}
