package com.tunepruner.main;

import com.sun.codemodel.internal.JForEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParameterizedTest1 {

    @ParameterizedTest
    @ValueSource(strings = {"Meat", "Beer", "iiii", "Bull", "1432"})
    void isNotTooLongShouldWorkRegardlessOfCharTypes(String string) {
        assertTrue(Main.isNotTooLong(4, string));
    }


    @ParameterizedTest
    @ValueSource(strings = {"Me", "Moo", "i", "iii", "iiiii", "Steer", "Cattle"})
    void isNotTooLongShouldDenyAnyOtherLength(String string) {
        assertFalse(Main.isNotTooLong(4, string));
    }

}