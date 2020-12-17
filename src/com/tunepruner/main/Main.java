package com.tunepruner.main;

import java.lang.*;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    /*TODO Using pencil and paper, sketch out my strategy. TODO Then create a test plan, with all the scenarios I want to test.*/
    /*TODO Watch AND WORK ALONG with Mosh Algorithms course, at least for the String Algorithms part. It would be very helpful to see how a pro works with this stuff, and I think this would be the most helpful course for this project in general.
    /*TODO Finish rewatching the Streams section of Mosh's Java course. I need to understand more functions than filter, forEach, and collect to be able to say that I have a grasp of streams.
    /*TODO Watch regex course. It would help to be able to explain why regex might NOT be the best solution for this project!*/
    /*TODO Watch JUnit course or read some guide.*/
    /*TODO Watch refactoring course. It will give me some help reworking control flow. I could certainly improve with that */

    public static final int REQUESTED_LENGTH = 7;
    private static final String onlyFromThisSet = "iiiiibu";/*(?=.*g)(?=.*h)(?=.*p)(?=.*r)(?=.*o)(?=.*b)(?=.*i)*/

    public static String getOnlyFromThisSet() {
        return onlyFromThisSet;
    }

    private static final String useExactlyOneOfThese = "goetsp";

    public static String getUseExactlyOneOfThese() {
        return useExactlyOneOfThese;
    }

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
                .filter(string -> usesExactlyOneOfThese(useExactlyOneOfThese, string))
                .filter(string -> doesntExceedNumberOfEachCharacter(useExactlyOneOfThese, onlyFromThisSet, string))
//                .filter(string -> prohibitAllOtherChars(onlyFromThisSet, string))
//                .filter(string -> isNotTooLong(6, string))
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

    /*TODO this method needs to be changed to "doesntExceedNumberOfEachCharacter",
     *  and then rewritten.*/

    public static boolean doesntExceedNumberOfEachCharacter(String useExactlyOneOfThese, String onlyFromThisString, String stringToEvaluate) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(useExactlyOneOfThese).append(onlyFromThisString);
        onlyFromThisString = stringBuilder.toString();
        boolean doesntExceedNumber = true;

        //Moves from strings to maps to allow counting of each character's repetition
        Map<Character, Integer> onlyFromThisHashMap = convertStringToHashMap(onlyFromThisString);
        Map<Character, Integer> mapToEvaluate = convertStringToHashMap(stringToEvaluate);;
        char[] charArray = stringToEvaluate.toCharArray();

        //Main logic of method.
        for ( int j = 0; j < stringToEvaluate.length(); j++ ) {
            char charToEvaluate = stringToEvaluate.charAt(j);
            if (!onlyFromThisHashMap.containsKey(charToEvaluate)) doesntExceedNumber = false;
            else if (mapToEvaluate.get(charToEvaluate) > onlyFromThisHashMap.get(charToEvaluate)) doesntExceedNumber = false;
        }
        return doesntExceedNumber;
    }

    public static boolean isNotTooLong(int requestedLength, String stringToEvaluate) {
        return stringToEvaluate.length() == requestedLength;
    }

    public static boolean usesExactlyOneOfThese(String useOnlyOneOfThese, String stringToEvaluate) {
        HashMap<Character, Integer> onlyFromThisHashMap = convertStringToHashMap(useOnlyOneOfThese);
        HashMap<Character, Integer> mapToEvaluate = convertStringToHashMap(stringToEvaluate);

        int numberOfMatches = 0;
        boolean hasOnlyOne = true;

        for ( int i = 0; i < useOnlyOneOfThese.length(); i++ ) {
            String charToUse = ((Character) useOnlyOneOfThese.charAt(i)).toString();
            if (numberOfMatches > 1) {
                hasOnlyOne = false;
            }else if (mapToEvaluate.containsKey(charToUse)) {
                numberOfMatches++;
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
            try {
                charsToAvoid.remove(indexOfUnwantedChar);
            } catch (Exception e) {
            }
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

    private static HashMap<Character, Integer>
    convertStringToHashMap(String stringToEvaluate) {
        HashMap<Character, Integer> newMap = new HashMap<>();
        for ( int i = 0; i < stringToEvaluate.length(); i++ ) {
            char compareWith = stringToEvaluate.charAt(i);
            if (newMap.containsKey(compareWith))
                newMap.replace(compareWith, newMap.get(compareWith) + 1);
            else {
                newMap.putIfAbsent(compareWith, 1);
            }
        }
        return newMap;
    }
}