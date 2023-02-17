package learn.regex

import learn.regex.domain.RegexTester
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertTrue

class PhonebookTest {

    static RegexTester regexTester;

    @BeforeAll
    static void init() throws IOException {
        regexTester = new RegexTester("./data/phonebook");
    }

    @Test
    void findEntriesFromNJ() {
        String regex = /.*NJ.*/
        List<String> phonebookEntryInNewJersey = regexTester.findAll(regex)

        assertEquals(20, phonebookEntryInNewJersey.size())
    }

    @Test
    void findAddressesWithExactZipCodes() {
        String regex = /\b(?<=~ ).*\d{5}-\d{4}/
        List<String> addresses = regexTester.findAll(regex)

        assertEquals(471, addresses.size())
    }

    @Test
    void findNamesOfPeopleWhomLiveOnMainStreet() {
        String regex = /^[A-Za-z, ]+(?=:[\dA-Za-z~ -]+Main)/
        List<String> names = regexTester.findAll(regex)

        assertEquals(30, names.size())
        names.forEach( name -> {
            String[] nameArray = name.split(", ");
            char firstNameLetter = nameArray[0].charAt(0),
                lastNameLetter = nameArray[1].charAt(0)
            assertTrue(firstNameLetter > 64 && firstNameLetter < 91)
            assertTrue(lastNameLetter > 64 && lastNameLetter < 91)
            assertTrue(nameArray[0].length() > 1)
            assertTrue(nameArray[1].length() > 1)
        })

    }

    @Test
    void findFirstNamesOfPeopleWithTheLastNameSmith() {
        String regex = /\b(?<=Smith, )[A-Z][a-z]+/
        List<String> states = regexTester.findAll(regex)

        assertEquals(3, states.size())
        states.forEach(name -> {
            char firstNameLetter = name.charAt(0)
            assertTrue(firstNameLetter > 64 && firstNameLetter < 91)
            assertEquals(name.size(), name.trim().size())
        })

    }

    @Test
    void findAddressesWithInvalidPhoneNumbers() {
        // Some of the phone numbers start with a 0, we need the addresses so we can send them a card in the mail to update their phone numbers

        String regex = /\b(?<=0\d{2}-\d{3}-\d{4} ~ ).*/
        List<String> addressesWithInvalidPhoneNumbers = regexTester.findAll(regex)

        assertEquals(101, addressesWithInvalidPhoneNumbers.size())
        addressesWithInvalidPhoneNumbers.forEach(address -> assertTrue(address.matches("\\d+ [A-Z].*\\d{4,5}\$")))
    }

}
