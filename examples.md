### Literal String
- regex: `Hello`
- matches: `Hello`
- notes:  Will also match the first few characters of `HelloTheRestOfThisDoesnNotMatch`.  This is a problem if we only want to match a single word that is exactly "Hello"
- PROBLEM:  What characters are NOT treated as literals?

### Using the `.`
- regex: `agent.`
- sentence: `Agent Parker, please go to the agents lounge and speak to your supervising agent.`
- matches: `agents` and `agent.`
- notes: The `.` character has special meaning in a regular expression.  It is a while card that acts as a substitute for any character.  In this example the "s" and the "." both match the wildcard.  If we were to also modify the first word (Agent) to start with a lower-case "a", then we would match `agent `.  In this example the space after agent would also be included in the match because of the `.` in our regular expression.

INTERLUDE - quick overview of meta characters

### Character classes
- regex: `[Hh]ello`
- matches twice: `Hello hello`
- notes: Character classes are created by using square brackets and including a number of options.  Think of this like the `.` character but with a limited number of options we can define.

### Character classes continued
- regex: `[HMj]ello`
- matches 3 times: `Hello, Mr. Mello, would you like some to eat some jello while you play your cello?`
- does not match: `hello mello cello`
- notes: Notice that all of the characters in the character class are still case sensitive so the capitalized versions do not match.

### Character classes with ranges
- regex: `[a-gA-G]`
- matches 7 times: `a b c D e F g h`
- does not match `h`
- Using the dash in a character class we can define a range to use.

### Shorthands for common classes
- long form regex: `[A-Za-z0-9_]`
- matches 5 times: `A bC_0` (does not match the space)
- shorthand regex: `\w` for "word" character

### Character class negation
- long regex: `[^A-Za-z0-9_]`
- matches 3 times: `Hello. $agent` (the period, space, and dollar sign)
- shorthand regex: `\W` capitalizing the shorthand gives the inverse or negation of the original.

### Single word
- regex: `\bHello\b`
- matches 3 times: `Hello Hello Hello`
- notes: `\b` matches the boundary between any "word" and "non-word" character such as that between the start of the line and "H", between "o" and a space character, a space charachter and "H", and finally the boundary between "o" and the end of the line.

### Starts with "Hello"
- regex: `^Hello`
- matches: `Hello`
- matches first word: `Hello my friend`
- notes: only matches the first word.

### Lines that start with "Hello"
- regex: `^Hello.*$`
- matches: `Hello There` and `Hello, my name is Inigo Montoya.  You killed my father.  Prepare to die.`
- notes: `.` is a wildcard used to denote any character, `*` means one or many of the previous character,  combined `.*` means any number of any type of character,  
finally `$` matches the end of the line,  therefore `.*$` reads as any number of any character until the end of the line.
- DOES NOT match: ` Hello friends` because of leading whitespace,  `hello Hello` because the first "h" at the beginning is not capitalized.

### Lines that start with a variation of "Hello"
- regex: `^\s*[hH]ello.*$`
- matches: `hello Friend`, `  Hello friend`, and `     hello FrIeNd`
- notes: `\s` is a new character representing any whitespace character, combined with the previous `*` modifier this means that any number of whitespaces can come before "Hello".
also new to this expression is the `[hH]` notatation.  This is called a "character class"  all characters inside the square brackets are treated as one, meaning `[hH]` will match
either a capital or lower-case "h", and `[abcAB]` would match "a", "b", "c", "A", or "B" (note that it would NOT match "C" as it is not included within the brackets)

### Lines that end with "ello" but not "hello" or "Hello"
- regex: `^.*\b[^hH]ello$`
- matches: `This line is mello`,  `I play the cello`
- does NOT match: `ends in hello`, `last word is too long violoncello`
- notes: We have added the `^` to the inside of the charachter class brackets.  When present this inverts the character class, meaning that all characters match 
EXCEPT the ones included in the brackets. Also note the presence of the `\b` here.  Due to it's location in the regex string this indicates we only want to match
if the final word is here a non-"h" character followed by "ello".  Removing `\b` would allow `last word is too long violoncello` to match.

### Only floating point numbers
**Objective**: match all money
```
120
$123.45 (match)
$123,000.45 (match)
123,000.5779
```
- regex: `[0-9,]+\.[0-9]+`
    - This pattern works for the given example but will also match something like `,,,,.12`
- alternative: `\$([0-9]{1,3},?)+\.[0-9]{2}`
    - This pattern is robust but maybe a little bit more than we need for this specific example.
    - notice we first start with a number class `[0-9]` and there can be one to three digits `{1,3}` before we get an optional comma `,?`.
    - We have that whole pattern wrapped in a capture group so that we can confirm it happens one or more times before the period `(___)+\.`
    - last the number should end with 1 or more digits `[0-9]+`
- notes:  Both of these patterns are valid depending on your use-case.  If you just need something quick and dirty for a find-replace, then the first will take you a long way as long as you're aware of its limitations.  The alternative example is much more likely to be added to a long term project due to the increased specificity. Choosing the right tool for the job is important.

### Find and replace domain names
```
https://localhost:5500/api/agent/
https://localhost:5500/api/alias/
http://localhost:5500/api/agency/1/agent
```
- regex: `(https?:\/\/)[a-z]+:?\d*(\/.*$)`
    - first capture group `(https?:\/\/)` the "s" is optional (`s?`) and the slashes have to be escaped `\/\/`
    - domain name `[a-z]+:?\d*` one or more lowercase letters followed by an optional colon and port number
    - second capture group `(\/.*$)` a slash followed by anything until the end of the line
- replace:  `$1mydomain.com$2`
    - notice because we reference the first and second capture groups in our replace, we can just replace the domain name.

### Match quoted words
**Objective** Match the words within the quotations but not the quotations themselves.
```
"this"
"not this"
"'no'" (should not match)
"""this""" (should only match the letters not the inner quotation marks)
```

- Incorrect regex: `"([a-zA-Z]+)"`  While this matches a single word in quotations it also matches the quotations themselves.
- Correct regex: `(?<=")([a-zA-Z]+)(?=")`
    - Now instead of including the quotes in the match we are asserting their location.
    - We use look behind `(?<=")` to assert that a single quote precedes our match.
    - We use look ahead `(?=")` to assert that a single quote comes after our match.

### Southern name change
***A disgruntled journalist wrote a story including many fake double names using "Bob". From a brief reading, we notice that we need to correct "Billy Bob", "Jim Bob", and "Joe Bob".  To complicate things, there is someone named "Bob" so we don't want to match that name when it appears by itself.  The example sentence should only match the last three instances of "Bob"***
```
Uncle Bob doesn't like Jim Bob, Joe Bob, or Billy Bob.
```
- Starter regex:  `\sBob` will match all four.
- Only after "Jim": `(?<Jim)\sBob` 
- After "Jim" or "Joe": `(?<=J(oe|im))\sBob` or `(?<=J\w{2})\sBob`
- Only after "Billy": `(?<=Billy)\sBob` or `(?<=B\w{4})`
- - Finalized regex combination: `(?<=J\w{2}|B\w{4})\sBob`
