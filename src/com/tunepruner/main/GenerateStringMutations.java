package com.tunepruner.main;


import org.junit.jupiter.params.provider.Arguments;

import java.util.*;
import java.util.stream.Stream;

public class GenerateStringMutations {
    public static void main(String[] args) {
//        String[] strings = generateMutationsThreeCharactersLong();
//        for ( String string : strings ) {
//            System.out.println(string);
//        }
    }
    public static Stream<Arguments>/*String[]*/ generateMutationsThreeCharactersLong() {
        String[] stringArray = new String[18276];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int stringArrayIndex = 0;

        Character[] charArray = new Character[3];


        for ( int j = 0; j < alphabet.length(); j++ ) {
            charArray[0] = alphabet.charAt(j);
            if (charArray[2] != null) stringArray[stringArrayIndex++] = charArrayBackToString(charArray);
            for ( int k = 0; k < alphabet.length(); k++ ) {
                charArray[1] = alphabet.charAt(k);
                if (charArray[2] != null) stringArray[stringArrayIndex++] = charArrayBackToString(charArray);
                for ( int l = 0; l < alphabet.length(); l++ ) {
                    charArray[2] = alphabet.charAt(l);
                    stringArray[stringArrayIndex++] = charArrayBackToString(charArray);
                }
            }
        }

        Stream<String> stream = Arrays.stream(stringArray);
        return Stream.of(Arguments.of(stream));
    }

    public static String charArrayBackToString(Character[] charArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for ( Character character : charArray ) {
            if (character != null) stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }
}
