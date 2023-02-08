package learn.regex

import learn.regex.domain.RegexTester
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

class VinTest {

    static RegexTester regexTester;

    @BeforeAll
    static void init() throws IOException {
        regexTester = new RegexTester("./data/vins");
    }

    // HINT: This will probably be useful: https://driving-tests.org/vin-decoder/

    @Test
    void findValidVins() {
        String regex = /REGEX HERE/
        List<String> validVins = regexTester.findAll(regex)

        assertEquals(499, validVins.size())
    }

    @Test
    void findInvalidVins() {
        String regex = /REGEX HERE/
        List<String> invalidVins = regexTester.findAll(regex)

        assertEquals(35, invalidVins.size())
    }

    @Test
    void findValidVinsMadeInJapan() {
        String regex = /REGEX HERE/
        List<String> vehicleVinsMadeInJapan = regexTester.findAll(regex)

        assertEquals(56, vehicleVinsMadeInJapan.size())
    }

    @Test
    void findValidVinsMadeIn2002() {
        String regex = /REGEX HERE/
        List<String> vinsMadeIn2002 = regexTester.findAll(regex)

        assertEquals(21, vinsMadeIn2002.size())
    }

    // HINT: You may want to use this: https://en.wikibooks.org/wiki/Vehicle_Identification_Numbers_(VIN_codes)/Honda/VIN_Codes#Americas

    @Test
    void findCanadianMadeHondaVinsMadeFrom2003To2013_CHALLENGE() {
        String regex = /REGEX HERE/
        List<String> canadianMadeHondasFrom2010To2013 = regexTester.findAll(regex)
        assertEquals(5, canadianMadeHondasFrom2010To2013.size())
    }
}
