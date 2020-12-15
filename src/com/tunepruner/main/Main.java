package com.tunepruner.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static final int REQUESTED_LENGTH = 7;
    private static final String useAsManyOfTheseAsPoss = "gophrbi";
    private static final String useOnlyOneOfThese = "dents";
    private static String negationRegex = "";
    private static String inclusionRegex = "(?=.*g)(?=.*b)(?=.*h)(?=.*p)";/*(?=.*r)(?=.*d)(?=.*o)*/
    private static final List<String> listOfWords = new ArrayList<>();
    private Map<Character, Integer> letterPointage = new HashMap<>();


    public static void main(String[] args) throws IOException {
        importWholeDictionary(listOfWords);

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

        List<String> finalList = listOfWords
                .stream()
                .filter(string -> matchesRegex(useAsManyOfTheseAsPoss, string))
                .filter(Main::hasNoRepetitions)
                .filter(string -> isNotTooLong(REQUESTED_LENGTH, string))
                .filter(string -> hasOnlyOneOfTheseLetters(useOnlyOneOfThese, string))
                .collect(Collectors.toList());
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

    private static boolean matchesRegex(String regex, String stringToEvaluate) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringToEvaluate);
        return matcher.find();
    }

    private static boolean hasNoRepetitions(String stringToEvaluate) {
        char[] charArray = stringToEvaluate.toCharArray();
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

    private static boolean isNotTooLong(int requestedLength, String stringToEvaluate) {
        return stringToEvaluate.length() <= requestedLength;
    }

    private static boolean hasOnlyOneOfTheseLetters(String useOnlyOneOfThese, String stringToEvaluate) {
        boolean hasOnlyOne = true;
        for ( int i = 0; i < useOnlyOneOfThese.length(); i++ ) {
            String charToUse = ((Character)useOnlyOneOfThese.charAt(i)).toString();
            boolean contains = stringToEvaluate.contains(charToUse);
            if (contains) hasOnlyOne = hasNoRepetitions(stringToEvaluate);
        }
        return hasOnlyOne;
    }
}

