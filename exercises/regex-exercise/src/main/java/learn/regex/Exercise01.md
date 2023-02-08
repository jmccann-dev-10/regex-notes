# Exercise 01 - Phone Numbers

## Instructions
1. In the test folder, open the class named `PhoneNumberTest`
2. Read each test name carefully to understand what you will need to find
3. Replacing only the words `REGEX HERE` in each test, write a pattern that will find the correct matching strings
    
    EX: if the task is to find the entire string, change `String regex = /REGEX HERE/` to `String regex = /.*/` 

4. You will probably find it useful to open up the corresponding file named `phone-numbers` inside of the `data` folder when writing the tests
5. The rest of the unit test is written for you, if you found a correct pattern, the assertions will match up.
5. Don't be alarmed the unit tests are written in `Groovy`, as valid `Java` still works perfectly fine in `Groovy`.  We chose to use `Groovy` because it made the `Regex` patterns easier to read.  The unit tests are still using `JUnit 5`, and run exactly the same.
6. More than one pattern can be used to solve the issue, try playing around with the patterns if you have a success.

<details>
<summary>Hint #1</summary>
There's seven valid area codes for the Twin Cities, the only ones in the file are `612`, and `651`
</details>

<details>
<summary>Hint #2</summary>
If the count for the Twin Cities area codes is off, try using a break point to see what's getting returned.  Are you being specific enough?
</details>