package learn.regex

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
    void findPhoneNumbersWithParenthesis() {
        String regex = /REGEX HERE/
        List<String> phoneNumbersWithParenthesis = regexTester.findAll(regex)

        assertEquals(19, phoneNumbersWithParenthesis.size())
    }

    @Test
    void findPhoneNumbersWithoutParenthesis() {
        String regex = /REGEX HERE/
        List<String> phoneNumbersWithoutParenthesis = regexTester.findAll(regex)

        assertEquals(79, phoneNumbersWithoutParenthesis.size())
    }

    /* HINT: You may find this link useful - https://www.allareacodes.com/area-code-map.htm */
    @Test
    void findPhoneNumbersFromGreaterMilwaukeeArea() {
        String regex = /REGEX HERE/
        List<String> southEastWisconsinNumbers = regexTester.findAll(regex)

        assertEquals(10, southEastWisconsinNumbers.size())
    }

    @Test
    void findPhoneNumbersFromTwinCitiesArea() {
        String regex = /REGEX HERE/
        List<String> twinCitiesNumbers = regexTester.findAll(regex)

        assertEquals(7, twinCitiesNumbers.size())
    }

    @Test
    void findWellFormedPhoneNumbers_CHALLENGE() {
        String regex = /REGEX HERE/
        List<String> wellFormedNumbers = regexTester.findAll(regex)

        assertEquals(98, wellFormedNumbers.size())
    }
}
