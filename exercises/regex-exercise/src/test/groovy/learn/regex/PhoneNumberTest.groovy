package learn.regex

import groovy.transform.CompileStatic
import learn.regex.domain.RegexTester
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
        String regex = /(?=\().*/
        List<String> phoneNumbersWithParenthesis = regexTester.findAll(regex)

        assertEquals(19, phoneNumbersWithParenthesis.size())
    }

    @Test
    void findPhoneNumbersWithoutParenthesis() {
        String regex = /(?:\d{3}[.-]?){2}\d{4}/
        List<String> phoneNumbersWithoutParenthesis = regexTester.findAll(regex)

        assertEquals(79, phoneNumbersWithoutParenthesis.size())
    }

    /* HINT: You may find this link useful - https://www.allareacodes.com/area-code-map.htm */
    @Test
    void findPhoneNumbersFromSouthEasternWisconsin() {
        String regex = /\(?(262|414)[. )-]{0,2}\d{3}[.-]?\d{4}/
        List<String> southEastWisconsinNumbers = regexTester.findAll(regex)

        assertEquals(10, southEastWisconsinNumbers.size())
    }

    @Test
    void findPhoneNumbersFromTwinCitiesArea() {
        String regex = /(612|651).*/
        List<String> twinCitiesNumbers = regexTester.findAll(regex)

        assertEquals(7, twinCitiesNumbers.size())
    }

    @Test
    void findWellFormedPhoneNumbers_CHALLENGE() {
        String regex = /\(?\d{3}(\) |[.-])?\d{3}[.-]?\d{4}/
        List<String> wellFormedNumbers = regexTester.findAll(regex)

        assertEquals(98, wellFormedNumbers.size())
    }
}
