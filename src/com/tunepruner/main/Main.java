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
    private static final String onlyFromThisSet = "gophrbi";/*(?=.*g)(?=.*h)(?=.*p)(?=.*r)(?=.*o)(?=.*b)(?=.*i)*/
    public static String getOnlyFromThisSet() { return onlyFromThisSet; }
    private static final String useExactlyOneOfThese = "dents";
    public static String getUseExactlyOneOfThese() { return useExactlyOneOfThese; }
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
                .map(String::toLowerCase)
                .filter(Main::hasNoRepeatedCharacters)
                .filter(string -> prohibitAllOtherChars(onlyFromThisSet, string))
                .filter(string -> usesExactlyOneOfThese(useExactlyOneOfThese, string))
                .filter(string -> isNotTooLong(6, string))
                .collect(Collectors.toList());
        System.out.println(finalList);
        System.out.println(finalList.size());
    }


    public static void importWholeDictionary(List<String> listToAddDictionaryTo) throws IOException {
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

    public static boolean hasNoRepeatedCharacters(String stringToEvaluate) {
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

    public static boolean isNotTooLong(int requestedLength, String stringToEvaluate) {
        return stringToEvaluate.length() == requestedLength;
    }

    public static boolean usesExactlyOneOfThese(String useOnlyOneOfThese, String stringToEvaluate) {
        boolean hasAtLeastOne = false;
        boolean hasOnlyOne = false;
        for ( int i = 0; i < useOnlyOneOfThese.length(); i++ ) {
            String charToUse = ((Character) useOnlyOneOfThese.charAt(i)).toString();
            if (stringToEvaluate.contains(charToUse)) {
                if (hasAtLeastOne) break;
                else hasAtLeastOne = true;
                useOnlyOneOfThese = useOnlyOneOfThese.replace(charToUse, "");
            }
            if (hasAtLeastOne) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(useOnlyOneOfThese).append(stringToEvaluate);
                hasOnlyOne = hasNoRepeatedCharacters(stringBuilder.toString());
            }
        }
        return hasOnlyOne;
    }


    public static boolean prohibitAllOtherChars(String charsToInvert, String stringToEvaluate) {
        String charsToAvoidString = "\\.\\/\\'abcdefghijklmnopqrstuvwxyz1234567890-";
        List<Character> charsToAvoid = new ArrayList<>();
        for ( int i = 0; i < charsToAvoidString.length(); i++ ) {
            charsToAvoid.add(charsToAvoidString.charAt(i));
        }
        for ( int i = 0; i < useExactlyOneOfThese.length(); i++ ) {
            charsToAvoid.remove(charsToAvoid.indexOf(useExactlyOneOfThese.charAt(i)));
        }

        for ( int i = 0; i < charsToInvert.length(); i++ ) {
            char charToRemove = charsToInvert.charAt(i);
            int indexOfUnwantedChar = charsToAvoid.indexOf(charToRemove);
            charsToAvoid.remove(indexOfUnwantedChar);
        }
        charsToAvoidString = "";
        StringBuilder stringBuilder = new StringBuilder();
        for ( int i = 0; i < charsToAvoid.size(); i++ ) {
            stringBuilder.append(charsToAvoid.get(i));
        }
        charsToAvoidString = stringBuilder.toString();
        String charsToAvoidRegex = String.format("^[^%s]+$", charsToAvoidString);
//        System.out.println("charsToAvoidRegex = " + charsToAvoidRegex);
        Pattern pattern = Pattern.compile(charsToAvoidRegex);
        Matcher matcher = pattern.matcher(stringToEvaluate);
//        System.out.println("stringToEvaluate = " + stringToEvaluate);
        boolean foundMatch = matcher.find();
        return foundMatch;
    }
}

