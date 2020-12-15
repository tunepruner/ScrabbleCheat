package com.tunepruner.main;

import com.sun.org.apache.xpath.internal.objects.XString;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {
    public static final int REQUESTED_LENGTH = 7;
    private static List<String> listOfWords = new ArrayList<>();
    private Map<Character, Integer> letterPointage = new HashMap<>();


    public static void main(String[] args) throws IOException {
        importWholeDictionary(listOfWords);
        String regex = "(?=.*g)(?=.*b)(?=.*h)(?=.*p)(?=.*r)(?=.*d)(?=.*o)";/**/
//        String lettersOnBoard = ""

        /*
         * User enters preferred connecting letter,
         * then enters jackpot indexes in positive or negative values,
         * relative to the connecting letter.
         *
         * And then enters secondary connecting letter options in the same way.
         *
         * User enters all 7 (or less if end of game) characters.
         * Program know which are most valuable.
         *
         * Program returns collection of possible plays.
         * They don't necessarily use all 7 characters,
         * but highest value possible, counting points of all letters used,
         * even taking into account the jackpot indexes entered by user.
         * */

        ArrayList<String> finalList = new ArrayList<>();
        listOfWords
                .stream()
                .filter(Main::evaluateRegex)
                .filter(Main::lookForRepeatCharacters)
                .filter(string -> testForLength(string, REQUESTED_LENGTH))
                .forEach(finalList::add);
        System.out.println(finalList);
    }


    private static void importWholeDictionary(List<String> listToAddDictionaryTo) throws IOException {
        File file = new File("EnglishWords.txt");
        try {
            file.createNewFile();
        } catch (
                IOException e) {
            System.out.println("That file already exists");
        }
        BufferedReader bufReader = new BufferedReader(new FileReader(file.getAbsolutePath()));
        String line;
        line = bufReader.readLine();

        try {
            while (line != null) {
                listToAddDictionaryTo.add(line);
                line = bufReader.readLine();
            }
        } catch (IOException f) {
            f.printStackTrace();
        }
        bufReader.close();

        System.out.printf("There are %d words total in this dictionary.\n", listToAddDictionaryTo.size());
    }

    private static boolean evaluateRegex(String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(regex);
        return matcher.find();
    }

    private static boolean lookForRepeatCharacters(String string) {
        char[] charArray = string.toCharArray();
        boolean noRepetitions = true;
        for ( char value : charArray ) {
            int repetitions = 0;
            char compareFrom = Character.toUpperCase(value);
            for ( char c : charArray ) {
                char compareWith = Character.toUpperCase(c);
                if (compareFrom == compareWith) repetitions++;
                if (repetitions > 1) {
                    noRepetitions = false;
                    break;
                }
            }
        }
        return noRepetitions;
    }

    private static boolean testForLength(String string, int requestedLength) {
        return string.length() <= requestedLength;
    }

}

