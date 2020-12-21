package com.tunepruner.main.test;

import com.tunepruner.main.processing.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ParameterizedTest1 {
    static final String[] strings = {"One"};

    @ParameterizedTest
//    @ValueSource(strings = {"Meat", "Beer", "iiii", "Bull", "1432"})
    @MethodSource("generateMutationsThreeCharactersLong")
    void isNotTooLongShouldWorkRegardlessOfCharTypes(String string) {
        Assertions.assertTrue(Main.isNotTooLong(4, string));
    }

    public static Stream<String>/*String[]*/ generateMutationsThreeCharactersLong() {
        return GenerateStringMutations.generateMutationsThreeCharactersLong();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Me", "Moo", "i", "iii", "iiiii", "Steer", "Cattle"})
    void isNotTooLongShouldDenyAnyOtherLength(String string) {
        assertFalse(Main.isNotTooLong(4, string));
    }
}