# Regex Notes
## Day 1
### 1. Basic Syntax
#### a. Character Matching
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
#### b. Short Hand
#### c. Negations
### 3. Quantifiers
#### a. Short Hand
#### b. Exact
#### c. Lazy Matching
### 4. Groups
Groups can be used to treat sequences of characters as a single unit. This can be achieved by placing paranthesis around a particular pattern. These groups can then have additional quantifiers used on them.

EX: Web Addresses:
Below is a list of web addresses, some with and without a www in front.  If we only wanted to match `.com` addresses we could use the following regex to accomplish this  The `(w{3}.)?` would make the `www` an optional group to match both cases:
`(w{3}\.)?[\w\d-]+.com`

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
