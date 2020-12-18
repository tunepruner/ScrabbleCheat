package com.tunepruner.main;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ParameterizedTest1 {
    static final String[] strings = {"One"};

    @ParameterizedTest
//    @ValueSource(strings = {"Meat", "Beer", "iiii", "Bull", "1432"})
    @MethodSource("generateMutationsThreeCharactersLong")
    void isNotTooLongShouldWorkRegardlessOfCharTypes(String string) {
        assertTrue(Main.isNotTooLong(4, string));
    }

    public static Stream<String>/*String[]*/ generateMutationsThreeCharactersLong() {
        String[] stringArray = new String[18276];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int stringArrayIndex = 0;

        Character[] charArray = new Character[3];


        for ( int j = 0; j < alphabet.length(); j++ ) {
            charArray[0] = alphabet.charAt(j);
            if (charArray[2] != null)
                stringArray[stringArrayIndex++] = GenerateStringMutations.charArrayBackToString(charArray);
            for ( int k = 0; k < alphabet.length(); k++ ) {
                charArray[1] = alphabet.charAt(k);
                if (charArray[2] != null)
                    stringArray[stringArrayIndex++] = GenerateStringMutations.charArrayBackToString(charArray);
                for ( int l = 0; l < alphabet.length(); l++ ) {
                    charArray[2] = alphabet.charAt(l);
                    stringArray[stringArrayIndex++] = GenerateStringMutations.charArrayBackToString(charArray);
                }
            }
        }

        List<String> list = Arrays.asList(stringArray);
        return list.stream();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Me", "Moo", "i", "iii", "iiiii", "Steer", "Cattle"})
    void isNotTooLongShouldDenyAnyOtherLength(String string) {
        assertFalse(Main.isNotTooLong(4, string));
    }

}