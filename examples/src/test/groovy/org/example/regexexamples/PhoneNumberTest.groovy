package org.example.regexexamples

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

@CompileStatic
class PhoneNumberTest {

    static RegexTester regexTester;

    @BeforeAll
    static void init() throws IOException {
        regexTester = new RegexTester("./data/phone-numbers");
    }

    @Test
    void findNumbersWithParenthesis() {
        String regex = /REGEX HERE/
        List<String> areaCodeWithParenthesis = regexTester.findAll(regex)

        assertEquals(19, areaCodeWithParenthesis.size())
    }

    @Test
    void findNumbersWithoutParenthesis() {
        String regex = /REGEX HERE/
        List<String> areaCodeWithoutParenthesis = regexTester.findAll(regex)

        assertEquals(79, areaCodeWithoutParenthesis.size())
    }

    /* HINT: You may find this link useful - https://www.allareacodes.com/area-code-map.htm */
    @Test
    void findNumbersFromGreaterMilwaukeeArea() {
        String regex = /REGEX HERE/
        List<String> southEastWisconsinNumbers = regexTester.findAll(regex)

        assertEquals(10, southEastWisconsinNumbers.size())
    }

    @Test
    void findNumbersFromTwinCitiesArea() {
        String regex = /REGEX HERE/
        List<String> twinCitiesNumbers = regexTester.findAll(regex)

        assertEquals(7, twinCitiesNumbers.size())
    }

    @Test
    void findWellFormedNumbers_CHALLENGE() {
        String regex = /REGEX HERE/
        List<String> wellFormedNumbers = regexTester.findAll(regex)

        assertEquals(98, wellFormedNumbers.size())
    }
}
