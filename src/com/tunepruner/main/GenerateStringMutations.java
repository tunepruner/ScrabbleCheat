package com.tunepruner.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateStringMutations {
    public static void main(String[] args) {
        String[] strings = generateMutationsThreeCharactersLong();
    }
    public static String[] generateMutationsThreeCharactersLong() {
        String[] stringArray = new String[18278];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int stringArrayIndex = 0;

        char[] charArray = new char[3];


        for ( int j = 0; j < alphabet.length(); j++ ) {
            charArray[0] = alphabet.charAt(j);
            if (charArray.length >2) stringArray[stringArrayIndex++] = charArrayBackToString(charArray);
            for ( int k = 0; k < alphabet.length(); k++ ) {
                charArray[1] = alphabet.charAt(k);
                if (charArray.length >2) stringArray[stringArrayIndex++] = charArrayBackToString(charArray);
                for ( int l = 0; l < alphabet.length(); l++ ) {
                    charArray[2] = alphabet.charAt(l);
                    stringArray[stringArrayIndex++] = charArrayBackToString(charArray);
                }
            }
        }

        return stringArray;
    }

    private static String charArrayBackToString(char[] charArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for ( char character : charArray ) {
            stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }
}
