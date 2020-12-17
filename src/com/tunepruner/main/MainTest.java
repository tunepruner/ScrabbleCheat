package com.tunepruner.main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    public static final String GOPHER = "gopher";
    public static final String DROP = "drop";
    public static final String HOPS = "hops";

    //    @Test
//    void prohibitAllOtherCharsTest() {
//        String onlyFromThisSet = Main.getOnlyFromThisSet();
//        assertTrue(Main.prohibitAllOtherChars(onlyFromThisSet, GOPHER));
//        assertTrue(Main.prohibitAllOtherChars(onlyFromThisSet, DROP));
//        assertTrue(Main.prohibitAllOtherChars(onlyFromThisSet, HOPS));
//    }
//    @Test
//    void usesExactlyOneOfTheseTest() {
//        String useExactlyOneOfThese = Main.getUseExactlyOneOfThese();
//        assertFalse(Main.usesExactlyOneOfThese(useExactlyOneOfThese, "bighorns"));
//        assertTrue(Main.usesExactlyOneOfThese(useExactlyOneOfThese, GOPHER));
//        assertTrue(Main.usesExactlyOneOfThese(useExactlyOneOfThese, DROP));
//        assertTrue(Main.usesExactlyOneOfThese(useExactlyOneOfThese, HOPS));
//    }
    @Test
    void test1DoesntExceedNumberOfEachCharacter() {
        assertTrue(Main.doesntExceedNumberOfEachCharacter("aiohek", "eebrhit", "her"));
    }

    @Test
    void test2DoesntExceedNumberOfEachCharacter() {
        assertTrue(Main.doesntExceedNumberOfEachCharacter("aiohek", "eebrhit", "here"));
    }

    @Test
    void test3DoesntExceedNumberOfEachCharacter() {
        assertFalse(Main.doesntExceedNumberOfEachCharacter("aiohek", "eebrhit", "greece"));
    }

    @Test
    void test4DoesntExceedNumberOfEachCharacter() {
        String onlyFromThisSet = "gophrbi";
        assertFalse(Main.doesntExceedNumberOfEachCharacter("aiohek", onlyFromThisSet, "bighorns"));
    }

    @Test
    void test5DoesntExceedNumberOfEachCharacter() {
        String onlyFromThisSet = "gophrbi";
        assertTrue(Main.doesntExceedNumberOfEachCharacter("aiohek", onlyFromThisSet, GOPHER));
    }

    @Test
    void test6DoesntExceedNumberOfEachCharacter() {
        String onlyFromThisSet = "gophrbi";
        assertTrue(Main.doesntExceedNumberOfEachCharacter("aiohek", onlyFromThisSet, DROP));
    }

    @Test
    void test7DoesntExceedNumberOfEachCharacter() {
        String onlyFromThisSet = "gophrbi";
        assertTrue(Main.doesntExceedNumberOfEachCharacter("aiohek", onlyFromThisSet, HOPS));
    }

    @Test
    void test1UseExactlyOneOfThese() {
        assertTrue(Main.usesExactlyOneOfThese("igbets", "big"));
    }
}