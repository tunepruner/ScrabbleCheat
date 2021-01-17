 # ScrabbleCheat
<i>This README was updated as of shaw 844e67b6072d91e828a870d5029cfd2250b3b964.</i>

The goal of the project is to create 
an algorithm to solve scrabble plays. I was
playing remote scrabble with my sister, and 
couldn't win, so I made this (and then 
continued to lose).

The project is written in Java 8. It gets its data 
from a text file (included in repo) containing almost 500,000 
words. 

Since the main value of this for me is practice, here's a
few words about what I worked with: I've wrote a 
number of algorithms to process the data set in different 
ways in order to help find
the highest scoring words quickly. A few algorithms use
regex, other are coded manually. Everything is processed
using streams. I used JUnit to test those algorithms, 
and used parameterized tests for the first time. And 
despite not being necessary or even useful in this case, it was 
fun to create an algorithm to generate a huge test
dataset and pass it into my parameterized test.

  

