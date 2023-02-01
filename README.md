# Regex Notes
## Day 1
### 1. Basic Syntax
RegEx is an extremely powerful tool for matching strings found in applications.  Almost all languages have support for RegEx, although they often use different engines when interpreting the RegEx patterns.

Most The most common delimiter used is the `/`, followed by any additional flags to fine tune the pattern matching.  Many examples found on the internet will use this delimiter to indicate a regex pattern.  This is not the only delimiter that can be used, but it is the most common.  For the sake of discussion, I'm going to leave the delimiter off of most examples.  Unless otherwise specified, it should be implied all of the examples are bookended with `/` and `/g`.

The `g` flag, tells RegEx to match as many times as it can possible.

|Example|Implied delimeter|
|:---|:---|
|`find me`|`/find me/g`|
|`[a-z]`|`/[a-z]/g`|
#### a. Literal Character Matching
Any character specified inside of the regex can be found by just typing it in.  If you've ever used the find feature on any text editor before, this works about the same.  This is literal string matching inside of RegEx, and any combination of letters can be used.  Using the letter `a` will find any occurance of the letter `a`, even if it's found inside of another word like "c**a**n" or "b**a**n**a**n**a**".  Any further combination of letters will search for that exact match as well.  Case sensitivity is on by default in RegEx.

|Example|Highlight|Notes|
|:--|:--|:--|
|`a`|**a** b**a**n**a**n**a** is very t**a**sty|Matches any `a` found in the sentence|
|`bat`|Batman threw a **bat**arang at the lamp|Able to match the `bat` in `bat`arang, but ignores `Bat`man because of the capitalization.|


#### c. Meta Characters
Several characters perform special functions in RegEx by default. A few examples are as follows.

|Char|Notes|
|:---|:---|
|`.`|Works as a wild card, will find any character in a string except for new line characters|
|`\|`|Works as an `or` in patterns and will match either everything to the left or everything to the right of the pipe.
|`\`|Used to escape meta characters|

This list is far from exhaustive.  There are plenty of others, and we'll build our volcabulary as we go.

### 2. Classes
#### a. Match Specified Characters
Character classes are made using the square brackets with a number of characters included inbetween.  They function very similarly to the pipe, where it will match one character from any of the ones listed in the square brackets.

|Example|Equivilent|Notes|
|:--|:--|:--|
|`[aF5]` | `a|F|5`| these characters are between the brackets and it can match any of them once
|`[fc]ar`| `far|car` | either an `f` or `c` can be matched, as long as they are followed by the letters `ar`.  The class can only be matched once in this case as well, so `fcar` would not match, nor would `cfar`

___EX: Finding Cat in the Hat___

__“Now! Now! Have no fear. Have no fear!” said the cat. “My tricks are not bad,” said the Cat in the Hat.__

If we wanted to find all instances of the word `cat` and `hat` in the above excerpt from the Cat in the Hat, we could use the following regex: `[CHc]at`

Both the capital and lowercase `C` need to be included.
#### b. Ranges
Character classes also support ranges of characters as well.  The syntax for ranges are specified as the lower character to start from, a hyphen, and then the character to end.  More than one range can be used in the same character class, and the ranges are inclusive to both the lower and upper bounds of the range.  As an example, `[d-f]` will match the characters `d`, `e`, and `f`.

|Example|Match|Notes|
|:--|:--|:--|
|`[a-z]`|any lower case character once|this is the syntax for the range of lower case characters
|`[a-zA-Z]`|any lower case or upper case character|this example has two ranges `a-z` and `A-Z`
|`[A-z]`|any lower case or upper case character plus `[`, `\`, `]`, `^`, `_`, and `` ` ``| Uppercase `Z` is character code 90, and the lowercase a is character code 97, the other characters specified are the characters between the two on an ascii table (91-96)
|`[z-a]`|nothing|the range has to start from a smaller character code to a larger, since `z` is 122, there are no characters codes both larger than 122 and smaller than 97 at the same time.

#### c. Shorthand
There are serveral shorthand tokens supported in RegEx for common character classes
These can be used in place of the squre brackets but lack the flexibility of specifying your own.  If all of the characters can be captured, they can easily be used by themselves, but if there is a character that shouldn't be added then it's better to make your own character class.  Each of these are represented by a backslash and a lowercase alphabet character:

|Shorthand|Equivilent Character Class|Notes
|:--|:--|:--|
|`\d`|`[0-9]`|Any ***D***igit|
|`\w`|`[A-Za-z0-9_]`|Any ***W***ord character (letter, digit, or underscore)|
|`\s`|`[\t\r\n ]`|Any Whitespace character (tab, new line, carriage return, or ***S***pace)|

These can also be combined with other characters inside of a character class to expand them further

___EX: Finding negative and positive numbers___

__12<br>
-14<br>
52<br>
60<br>
-2<br>
456<br>
25__

For the above example, we can use `\d` to select any single digit character.  If we place a hyphen infront of it (`-\d`), we might asssume this would help us find the negative numbers as well, but only the `-1` and `-2` light up.  If we wrap character combination into square brackets (`[-\d]`), expanding the shorthand inside of the character class, then all of the numbers and hyphens light up in this case.  At this point we're still only grabbing single characters in our patterns, but we'll address that in the section on quantifiers.

#### d. Negations
Character classes and the shorthands all have negations built into RegEx, which is really just a fancy way of saying "do the opposite".  For character classes, placing a carrot (`^`) at the beginning of the character class will tell RegEx to match any character not included.  One note is the carrot needs to be at the beginning of the character class, otherwise RegEx will match a literal carrot inside of the string instead.

|Example|Meaning|
|:--|:--|
|`[d-f]`|Match `d`, `e`, or `f` in a string|
|`[1a-z^]`|Match any lower case character, `1`, or `^`|
|`[^dg1]`|Match any character as long as it's not a `d`, `g`, or `1`|
|`[^a-z]`|Match any character as long as it's not a lower case character|

The shorthand tokens above also have negations as well, they are represented with a backslash and uppercase character to do the exact opposite of the lowercase couterpart.

|Shorthand|Equivilent Character Class|Notes
|:--|:--|:--|
|`\D`|`[^0-9]`|Match anything not a digit|
|`\W`|`[^A-Za-z0-9_]`|Match anything not a word character (letter, digit, or underscore)|
|`\S`|`[^\t\r\n ]`|Match anything not a whitespace character (tab, new line, carriage return, or Space)|

### 3. Quantifiers
So far we've seen how to match characters, combinations, and patterns, but sometimes we need to group a pattern multiple times or use a combination.  Take the example for finding numbers above.  We were able to write RegEx to match the individual numbers `4` and `6` and `5`, but what if we wanted to match `456`?  This is where quantifiers come in handy, and allow us to pattern match multiple times within the same group.

#### a. Specific
If we know how many times a pattern should match in a given string, we can use the syntax of a single number between two curly braces (`{num}`) immediately following a pattern to tell RegEx to match the pattern exactly as many times as specified between the curly braces. As an example, using the RegEx `\d{3}` will now match any three digit number, but ignore number combinations less than or more than 3 digits in length.

Most of the time we don't really know the exact length, so fortuantely the quanitifier syntax also supports both a minimum length (by placing a comma after the number), and min/max length (by placing an additional number after the comma).

|Pattern|Match|Ignore|Meaning|
|:--|:--|:--|:--|
|`[a-z]{3}`|`and`, `but`|`And`, `funny`, `do`|Any lowercase character, three times exactly|
|`[a-z]{3,}`|`and`, `funny`|`And`, `do`|Any lowercase character, three times or more|
|`fu{1,5}n`|`fun`, `fuuun`|`fn`, `fuuuuuun`|matches the letter `u` between 1 and 5 times, as long as it's bookended by the letters `f` and `n`.

___EX: Find the phone numbers___

__555-9564<br>
555.4814<br>
555-2763<br>
5557990__

We now have enough RegEx to match all of the phone numbers above.  It could be really easy to just crate a character class to match any of the character and try to match the length (`[\d.-]{7,8}`) but this would allow specific combinations to go through we wouldn't want:
|Matches|Reason|
|:--|:--|
|`55579847`|eight digits|
|`3-768490`|probably a serial number|
|`5559284.`|also grabs the punctuation|

1. Some of the examples are punctuated with either a hypen or period, we can use a character class to target either of those `[-.]`.  Because one example doesn't have this, we'll also need to specify a min/max range on this as well to match one or zero times `[-.]{0,1}`

2. The last four digits can be any random combination, so we can use the shorthand `\d` for any number, and place `{4}` immediately after to target four digit combinations

3. This leaves the only thing we need to target now is the first three digits.  If we were using real phone numbers, these first three digits could be just about any combination of digits so it might make sense to make a pattern similar to the previous step (`\d{3}`).  Since all of the phone numbers are fake in the above examples, it would be better to be more specific than less specific, so we'll use `5{3}` instead.

Final result: `5{3}[-.]{0,1}\d{4}`

#### b. Shorthand
There are three shorthand quantifiers that can be used.  These are extermely common and can be used instead of specific lengths, but are not easily modified.

|Sh|Alt|Exp|Meaning|
|:--|:--|:--|:--|
|`*`|`{0,}`|`[a-z]*`|Match and lowercase character zero or more times, as many times as possible|
|`+`|`{1,}`|`[a-z]+`|Match lowercase character one or more times, as many times as possible|
|`?`|`{0,1}`|`colou?r`|Match either `color` or `colour`, this makes the `u` optional|

#### c. Lazy Matching
Take into consideration the following string:

 `This is some <span>html</span> if we have more than one match in the same <span>space</span> we can have some unusual results.`

 If we use the regex `<span>.*<.span>`, we might expect to get two hits above.  Because quantifiers are greedy by default, the `*` and `+` quantifiers will match as big of a hit as possible.  Since all of the characters in the opening and closing span tags can be matched with the wildcard character, this means the regex will match everything from the first occurrence  `<span>` tag to the last occurrence of the closing `</span>` tag as a single group.

 There is more than one way to fix this problem.  The most simple way is to be more specific with our pattern, but we can also alternatively make the greedy match lazy by adding a `?` after the `*`

|Sh|Exp|Meaning|
|:--|:--|:--|:--|
|`+?`|`[a-z]+?`|Match one or more times, as few times as possible|
|`*?`|`[a-z]*?`|Match zero or more times, as few times as possible| 

### 4. Groups
Groups can be used to treat sequences of characters as a single unit. This can be achieved by placing paranthesis around a particular pattern. These groups can then have additional quantifiers used on them.

EX: Web Addresses:
Below is a list of web addresses, some with and without a www in front.  If we only wanted to match `.com` addresses we could use the following regex to accomplish this  The `(w{3}\.)?` would make the `www.` an optional group to match both cases:
`(w{3}\.)?[\w-]+.com`

|web address|match|
|:---|:---|
|www.dev-10.com|yes|
|google.com|yes|
|www.amazon.com|yes|
|mongodb.com/|yes|
|www.wizard.gov/|no|
## Day 2
### Anchors
### Advanced Groups
#### a. Capturing Groups
#### b. Non-Capturing Groups
#### c. Assertions
### Flags
