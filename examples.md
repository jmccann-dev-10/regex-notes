### Literal String
- regex: `Hello`
- matches: `Hello`
- notes:  Will also match the first few characters of `HelloTheRestOfThisDoesnNotMatch`.  This is a problem if we only want to match a single word that is exactly "Hello"

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

