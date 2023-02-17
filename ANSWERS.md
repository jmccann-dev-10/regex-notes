# PhoneNumberTest Regex Solutions:
## findPhoneNumbersWithParenthesis

1. Fastest Solution: `/\(/`

This will work, but only because the formatting is pretty linear across the board.  None of the numbers with parenthesis are anything other than phone numbers, so only targeting an opening or closing parenthesis will give you the correct answer.

If you put a break point on line 25 and run this test, you'll see you have only a list of parenthesis though, so this solution clearly has draw backs.

2. Better Solution: `/\(.*/`

This solution is taking advantage of the exact same thing as the fastest solution: the only numbers in the list starting with an open parenthesis are phone numbers.  Since we also know the String only contains the phone number, this pattern will give us the entire string, giving us the full phone number.

Having knowledge about the material ahead of time is useful when building out patterns.  If we know we have a static file (which we do), then writing something this loose is perfectly fine.  I might be a little reluctant to use a pattern like this if the data were changing every day.  Especially because I can see two lines in the file that are either typos or not phone numbers, so I'll dub this the 'bull in an antique parlor' solution.

You may remember I mentioned we should be careful when using the combination `.*` because the engine will automatically jump to the end of the line and work backward until it finds the next part of the pattern.  Sometimes this can be faster and sometimes this can be slower depending on two things: the length of our string, and where the last character we want to match in our string lives.

Take for example this Sentence:

`This sentence is just used as an example to show speed.`

The above sentence has 54 characters in it.  If we wanted to match everything up until the last `s` (first letter of `speed` in this case) and we used the pattern `^.*s`, the solution would be really fast.  Why?  Because there's only 5 characters after the last `s`, so there's only five characters the engine would have to give back.

If we wanted to target the first `s` in the sentence, using the `.*` would be much more slow because the first `s` is the fourth character in, meaning `.*` would cause the engine to work backward through about 50 characters, but `.*?` would start from the beginning and match until it found the first `s`.

General rule:
* More characters after the pattern than inside the pattern?  Lazy load will be better.
* More character inside the pattern than after? Greedy load will be better.

Most solutions will probably be better to lazy load in real life, which is why we should always be careful when considering using `.*`.  In this particular case, the `.*` will be better because it will automatically give us the entire rest of the string.

3. Best Solution: `/\(\d{3}\) \d{3}-\d{4}/` or `/\([\d) -]*?-\d{4}/`

This solution is slower, but this solution is more accurate and gives the same answer.  With the previous solution, we're making the assumption all of the examples with paranthesis will be fine as long as they start with a paranthesis (which they are).  If we weren't 100% sure every solution matches the pattern, being more specific would be better which both of these are.

---

## findPhoneNumbersWithoutParenthesis
1. Good Solution: `/^\d{3}[-.]?\d{2}.*/`

You may be tempted to try looking for all of the solutions without a paranthesis `^[^(].*$`.  Unfortunately there's two lines we need to take account for that we need to exclude:

* `8582-92-1174`  - #26
* `90-46671637`   - #50

Here are three three examples we need to target:

* `856-750-7942`  - #1
* `612.463.2653`  - #8
* `6128050959`    - #2

Because the out of place characters are early in string (`-` in character 3 and character 5), we can specifically grab the first few characters and match on those.

Starting from the beginning of the strings, we can see all of the good patterns start with three digits, so if we start with `^\d{3}` we've now managed to exclude `90-46671637` because it starts with only 2 digits.  One down, one to go!

Some of the patterns have punctuation in them at character 4, and some don't, so we'll need to make those optional as part of the pattern we're matching on: `[.-]?`.

The next three characters in all of the patterns are digits, but we only need 2 of them to get the pattern we're really looking for.  Why?  Since the next character in the other patern we don't want is also a digit one digit won't exclude it from our pattern, but the next character after that is a hyphen.  So if we use `\d{2}` as the next part of the pattern, all three of the good examples match and now both of the bad ones get excluded.

After that we just need to grab the rest of the string with `.*`: `^\d{3}[.-]?\d{2}.*`

2. Better Solution: `/\d{3}[-.]?\d{3}[-.]?\d{4}/`

Just like before, this is just more specific.  We don't have to worry about trying to exclude patterns if our pattern is exact to what we want.

3. Even Better Solution: `/^(\d{3}[.-]?){2}\d{4}/`

 Since there's a part of the pattern that occurs twice in a row (three digits followed by a hypen or period), we can group this together and ask the engine to look for this twice.  So this pattern `\d{3}[.-]?`, I'll just wrap in paranthesis and put a quantifier immediately after it `(\d{3}[.-]?){2}`.  I could also add a `?:` to the start of the group to make it non-capturing, but it doesn't affect much since we're only searching for solutions here.

 You may have also noticed I added an anchor to the beginnning of the pattern, this is to speed up the solution by quite a bit.  Everytime there is a group, the engine is going to jump backward for every character it has to match on.  Adding an anchor to the pattern can help avoid backtracking too much, as it will reduce significantly the amount of places the engine will even consider looking at.

 https://www.loggly.com/blog/five-invaluable-techniques-to-improve-regex-performance/
 https://www.regular-expressions.info/catastrophic.html

---

## findPhoneNumbersFromSouthEasternWisconsin
1. Shortest Solution: `/(262|414).*/`

If we follow the link provided, all of the specific area codes in the south-west side to Wisconsin are only `262` or `414`, so we can target that at the beginning of the pattern and grab everything immediately after.  This works perfectly fine, but if we had those digits in the middle of the phone number, this solution is a little lacking.

2. Better Solution: `/\(?(262|414)[. )-]{0,2}\d{3}[.-]?\d{4}/`

This solution is a little more exact.  It's definitely making a few assumptions but the assumptions are fairly safe here to make.  There's four possible combinations that could possibly come after the area code before the next set of digits: `) `, `.`, `-`, or nothing.  What are the chances we would ever get a three digit code followed by only one of those combinations and then three more digits?  Probably not likely if it's not a phone number, so I feel pretty safe making a character class to look for any of those characters between 0 or 2 times maximum: `[. )-]{0,2}`

If you're feeling like Darkwing Duck and being a little dangerous, `.{0,2}` would probably also be fine, since the rest of the pattern is so specific.  We might get some combinations unexpected, but the liklyhood of there being anything other than the above characters bookended by digits is pretty low.  Ultimately, while being a little dangerous, probably still pretty safe.

---

## findPhoneNumbersFromTwinCitiesArea
1. Shortest Solution: `/^.?(612|651).*/`

If you do a quick google for `twin cities area codes` you get seven different possibilities: `320`, `507`, `612`, `651`, `715`, `763`, or `952`.  I know that my file only had `612` and `651`, so I'm going to only include those, but having them all in there is probably better, but I'm going to short-cut the solutions.

You may have been tempted to just use the same solution above as a starting point, but line 97 has `448-456-7612` which ends in 612. So bad news is the shorter Wisconsin solution isn't going to work directly here, but the good news is we can use an anchor to fix this. So if we just add `^.?` that will take care of all lines that may or may not start with an open paranthesis, and then is immediately followed by `612` or `651`.

2. Better Solution: `/\(?(612|615)[. )-]{0,2}\d{3}[.-]?\d{4}/`
It may not be any surprise at this point, but the more specific solution works just as well here as it did with Wisconsin solution.  This is why I think it's better.  The more specific I am, the more likely I'll get the matches I want without matching things I don't want.

---

## findWellFormedPhoneNumbers_CHALLENGE
Solution: `/^.?\d{3}.{0,2}\d{3}[.-]?\d{4}/` or `/^\(?\d{3}[ .)-]{0,2}\d{3}[.-]?\d{4}/` or `/^\(?\d{3}(\) |[.-])?\d{3}[.-]?\d{4}/`

Like the phone numbers with paranthesis exercise, I can easily build a solution out that would find what I'm looking for by trying to excluded the patterns that don't match.  Because the exercise name is indicating we should find 'well formed phone numbers', I think this means the pattern is more important than the end result.

Assuming the phone numbers are American, looking for two groups of three and one group of four is going to need to be part of my solution.  The rest of the surrounding parts of the pattern are just looking for available punctuation.  Ultimately all of the individual parts of the solution have been compared in previous examples here, so this shouldn't be too difficult to read at this point.

---

# VIN Regex Solutions:

For these next few, there's fewer possible solutions that will get us to the right answer, so there will be fewer options here from me.  All of my soltuions will the full match, and not just a partial, although partials are still an option :)

## findValidVins
Solution: `/^[^OIQ]{17}$/`

Looking at the documentation on VINs, the only letters excluded are `O`, `I`, or `Q` and are 17 characters in length.  If we wanted to also get more specific, we could target only the valid letters since technically this solution will pick up lower case letters and puncutation.  A more accurate alternative is to use a character class with ranges: `[A-HJ-NPR-Z0-9]`.  Both are good, I'm going to keep using `[^OIQ]` since it's shorter and easier to read.

---

## findInvalidVins
Solution: `/^.*?[OIQ].*$/`

Fortunately, I didn't include anything with lengths greater or less than 17 characters, so the only thing we need to look for is the inclusion of an `O`, `I`, or `Q`.

If we wanted to include something like that, I probably would add something like
`\b(.*?[OIQ].*|[^OIQ\s]{1,16}|[^OIQ\s]{18,})\b`.  That one is a bit of a mouthful, and I didn't want to make these super challenging.  This is practice, after all.

---

## findValidVinsMadeInJapan
Solution: `/^J[^OIQ]{16}/`

Same rules as above: 17 characters, can't include `O`, `I`, or `Q`.  Knowing that the first three characters in a VIN indicate the country and factory the vehicle was manufactured in, we can find any VIN starting with `J` means it was made in Japan.

---

## findValidVinsMadeIn2002
Solution: `/^[^OIQ]{9}2[^OIQ]{6}/`

Same rules as above: 17 characters, can't include `O`, `I`, or `Q`.  In this case, the 10th character indicates the year the vehicle was built.  For vehicles built in 2002, the 10th characters needs to be a `2`.

---

## findCanadianMadeHondaVinsMadeFrom2003To2013_CHALLENGE
Solution: `/^2H[GHKJNU][^OIQ]{6}[3-9A-D][^OIQ]{6}/`

This one is a little more involved.  There are a lot of VINs that indicate a Honda, but for a Honda made in Canada, they need to start with 2H, and then be followed by either a `G`, `H`, `K`, `J`, `N`, or `U` so the first part of the pattern must start with `^2H[GHJNU]`

10th character is still the year made, and the range starts at 3 for 2003, goes up through 9 for 2009, then loops through the letters A for 2010 to D for 2013.  So we can solve this with a character range: `[3-9A-D]`

There should only be  6 characters after the 10th, and 6 characters after the 3rd and before the 10th so we can puncutate these with `[^OIQ]{6}`

* `2HNYD182X4H51069` - 2004 MDX
* `2HNYD2H39DH58932` - 2013 MDX
* `2HKYF18683H54096` - 2003 Pilot
* `2HGES16625H64867` - 2005 Civic
* `2HKYF18453H56718` - 2003 Pilot

# Refactor the Person Class

Minimum I was able to get this completed in was four.  I used the find and replace function on IntelliJ to fix this (`CTRL + R`) and turned on Regex (button looks like `[.*]`) and case sensitivity (button looks like `[Cc]`):

|Step|Find|Replace|Notes|
|--:|:--|:--|:--|
|1|`p(rotected\|rivate)`|`public`|Sweeping deleration to fix all of the methods at once, this also changes all of the field properties to public, but we can fix that in the next step|
|2|`public(?=.*;)`|`private`|Fixes all of the field variables. Lookahead is necessary (no matter what order) I did these in to avoid making the class private as well, so I elected to do this one second to avoid|
|3|`getH`|`h`|Fixes the name of properties for `getHairColor` to `hairColor`|
|4|`Get`|_(nothing)_|removes the `GET` from `getGet` and `setGet`|

If IntelliJ supported conditional replacements, I might have been able to conditionally replace, but it doesn't look like that's a supported feature here.

# Phonebook Regex Tests

These tests are a little more tricky for the most part because many of them have more assertions built in, narrowing the possible regex solutions possible.  I'll highlight all of the additional assertions in each of these.


---

## findEntriesFromNJ

Regex: `/.*NJ.*/`

The state I'm looking for is within 15 characters of the end of every entry, so backtracking will be faster on matches and doesn't add much extra to non-matches, making this probably the best solution here.

---

## findAddressesWithExactZipCodes

Regex: `/\b(?<=~ ).*\d{5}-\d{4}/`

The thing to target on this one is going to be the delimeter.  Each entry follows a pattern - `Last Name, First: phone number ~ address`.  Targeting the `~` and space is a good way to find where the addresses start in each entry, but we don't want that included, so we'll use a lookbehind to find the start of the part of the string we want: `(?<=~ )`

I'm pretty sure the author (*cough* ***me*** *cough*), when asking for _Exact_ zip codes was looking for the _zip+4_ code.  These aren't really exact but they help the USPS locate addresses more efficiently, and help to reduce misdeliveries by specifying the part of the delivery route the address exists on.  So going with that, I _think_ the author's intention (*cough* ***still me*** *cough*) to only incude zip codes that are 9 digits long.  Since they're part of the capture group, we don't need a lookahead: `\d{5}-\d{4}`

You may be wondering why I also have a word boundry anchor at the beginning of the pattern as well (since the lookbehind also works as a lookbehind as well).  Short reason is make it more efficient.  The lookbehind will always backtrack to search for the pattern, and will make this check on every character in the string until it finds a match to the pattern.  To give you a quick math on this, if you have a string 10 characters long, and none of them match the pattern in the lookbehind, regex is going to check the lookbehind on every character, making a total of 20 steps for that one word (one time to check, and one time to backtrack).  The longer the string, typically means twice as many steps to search through (unless found).  The word boundry is also not capturing (like the lookbehind) and doesn't so it's safe to put infront of the lookbehind as a primary anchor to reduce the amount of times the actual lookbehind is checked.

---

## findNamesOfPeopleWhomLiveOnMainStreet

Regex: `/^[A-Za-z, ]+(?=:[\dA-Za-z~ -]+Main)/`

Okay, so little note here, Dylan and I made a mistake with the regex on this one, so I'll explain after.  Here's the breakdown:

So in addition to finding the correct count here, the solution must have both the first name and the last name, and the comma delimiter in it.  Additionally, the first letter for both  of these must be an upper case character.  Since we have a limited set of characters we want for this, a character class is going to be amazing here for the upper case letters, lower case letters, the comma, and the space.  We'll want to grab as many as possible so putting a `+` immediately after will give us as many characters as fit very quickly:  `[A-Za-z, ]+`

Now comes the lookahead we'll want to find but not capture the rest of the information.  We know the next character after is going to be a `:`, followed by any number of characters up to the words `Main Street`, but because there's a limited character set, we can be more specific here and use a character class to speed up the pattern matching by a lot: `(?=:[\dA-Za-z~ -]Main)`

So there we go!  30 entires exactly and all unit tests pass.  So what was the mistake then?  If you open up the text file and look at line 931, one of the names is Irish: O'Keefe.  Which means the character class for the names should also contain an apostrophy as well. Depending on how long you struggled with this, you might have figure this out.  Since no one messaged me on this, I'm assuming one of two things happened:

1. You got the _actual_ correct count (31), and the unit tests failed
2. You got the right wrong count (30), and the unit tests passed

If either of these happened, I think you can be forgiven for making the same mistake two seasoned devs made, especially when one of them has an Irish last name and designed all of these tests.  If you figured out our mistake and didn't say anything, then all I have to say is "Hey!  What gives?"

---

## findFirstNamesOfPeopleWithTheLastNameSmith

Regex: `/\b(?<=Smith, )[A-Z][a-z]+/`

We're only looking for one name here, as long as the last name is Smith.  In addition to only finding 3, we need the first letter to be a captial letter and no extra spaces around any of the solutions.

I'll build the character class to get a single name first.  All of the names start with a single Upper case character with one or more lower case characters (no abbreviations, or Irish first names!) : `[A-Z][a-z]+`

The only other thing we need is the lookbehind for `Smith` and we'll add the comma and space immediately after to also not capture those: `(?<=Smith, )`.  It's really important to include the comma and space here because everything in the lookbehind has to be included up to the point where we want to start capturing.

Adding the word boundary anchor to the start reduces the amount of back tracking performed by the engine speeding the entire regex solution up by about about half.

If you got a different count that I did, your regex might be too loose.  If you tried something like `(?=Smith.{2})\w+` you would have definitely gotten the street `Smith Harbors (line 205)` and matched on `arbors`.  Being more specific in the case helps a lot with tracking down matches here again.

---

## findAddressesWithInvalidPhoneNumbers

Regex: `/\b(?<=0\d{2}-\d{3}-\d{4} ~ ).*/`

Okay, last exercise.  I hope you had fun with these because I did.  In this one we want to look for phone numbers that start with 0, and only capture the address after the `~`.  In addition, I was a big old meanie head and threw in some additonal regex as part of the requirements that will verify your capture was correct with no extra frou-frou added, but doesn't really help you to find the correct answer in the solution.  If you thought this one was a little less challenging here, you may be right.

In the other exercises I purposfully made the last test the most tricky to do.  In this exercise, I still made the exercise tricky, but not in the same sense as the other exercises.  Finding a correct solution up to this point is not particularlly difficult. Being the sneaky little hobbitses I am, I tried making the best solution not so obvious.

All of the phone numbers have a uniform pattern this time (unlike last time) where they start with three digits, a hyphen, three digits, a hyphen, and four digits.  The first one is the one we care about, as that needs to be a fixed `0`.  So part of our solution needs to include: `0\d{2}-\d{3}\d{4}`

After the phone number and before the address (where we want to start capturing) there's a pattern of a `~` and one space on both sides.  If we add that to our lookbehind, then we'll be in the right spot to always capture everything we're looking for, so we'll add that to the pattern above and wrap the whole thing in a lookbehind. `(?<=`__0\d{2}-\d{3}-\d{4}__` ~ )`

Once again, I put a word boundry to the beginning to speed up the regex.

After we've found where the capture needs to starts everything until the end of the string is what we want to grab, so finishing the pattern with `.*` is going to net us to the fastest and shortest result.

If you used the regex given as part of your solution, kudos to you for have a very specific answer!

If you realized the regex I had was designed as a redherring to tempt you into using it as part of the solution, even better!

If you did something else but still got the right answer, then you still deserve a pat on the back!

Which ever way, you did an amazing job and I hope you learned something while having fun doing it!