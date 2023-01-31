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

#### c. Short-Hand
There are serveral short-hand tokens supported in RegEx for common character classes
These can be used in place of the squre brackets but lack the flexibility of specifying your own.  If all of the characters can be captured, they can easily be used by themselves, but if there is a character that shouldn't be added then it's better to make your own character class.  Each of these are represented by a backslash and a lowercase alphabet character:

|Short-Hand|Equivilent Character Class|Notes
|:--|:--|:--|
|`\d`|`[0-9]`|Any ***D***igit|
|`\w`|`[A-Za-Z0-9_]`|Any ***W***ord character (letter, digit, or underscore)|
|`\s`|`[\t\r\n ]`|Any Whitespace character (tab, new line, carriage return, or ***S***pace)|

These can also be combined with other characters inside of a character class to expand them further

___EX: Finding negative and positive numbers___

__"12 -14 52 60 -2 456 25"__

For the above example, we can use `\d` to select any single digit character.  If we place a hyphen infront of it (`-\d`), we might asssume this would help us find the negative numbers as well, but only the `-1` and `-2` light up.  If we wrap character combination into square brackets (`[-\d]`), expanding the short hand inside of the character class, then all of the numbers and hyphens light up in this case.  At this point we're still only grabbing single characters in our patterns, but we'll address that in the section on quantifiers.

#### d. Negations
### 3. Quantifiers
#### a. Short Hand
#### b. Exact
#### c. Lazy Matching
### 4. Groups
Groups can be used to treat sequences of characters as a single unit. This can be achieved by placing paranthesis around a particular pattern. These groups can then have additional quantifiers used on them.

EX: Web Addresses:
Below is a list of web addresses, some with and without a www in front.  If we only wanted to match `.com` addresses we could use the following regex to accomplish this  The `(w{3}.)?` would make the `www` an optional group to match both cases:
`(w{3}\.)?[\w-]+.com`

address|match
|:---|:---|
www.dev-10.com|yes
google.com|yes
www.amazon.com|yes
mongodb.com/|yes
www.wizard.gov/|no
## Day 2
### Advanced Groups
#### a. Capturing Groups
#### b. Non-Capturing Groups
#### c. Assertions
### Anchors
### Flags
