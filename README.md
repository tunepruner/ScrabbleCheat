# ScrabbleCheat
<i>This README was updated as of shaw 9a378828.</i>

The goal of the project is to create 
an algorithm to solve scrabble plays. 
A user will be prompted three times. First: 
to enter the letters currently available on the 
tileboard. Second: to enter one possible connecting 
letter. And third: to enter a string with however many
"Premium Letter Squares" and "Premium Word Squares"
are within range, along with the relative (+ of -) indexes
of each, and their type. The algorithm returns a list of 
possible words, sorted according to their point value.

The project is written in Java 8. My personal goal is to 
use it to practice test driven development and JUnit 5, 
Java streams (and declaritive programming in general),
and regular expressions, all why trying to write cleaner and
cleaner code, and improving my refactoring techniques in 
IntelliJ IDEA. It is also my first time working with a data
set this large (almost 500,000 words in the dictionary
I'm using).   