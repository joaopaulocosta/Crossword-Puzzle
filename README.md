# Crossword-Puzzle
A Crossword grid is provided to you, along with a set of words (or names of places) which need to
be filled into the grid.
The cells in the grid are initially, either + signs or - signs.
Cells marked with a + have to be left as they are. Cells marked with a - need to be filled up with an
appropriate character.
## Input Format
The input contains lines, each with characters (which will be either + or - signs).
After this follows a set of words (typically nouns and names of places), separated by semi-colons (;).
## Constraints
There will be no more than ten words. Words will only be composed of upper-case A-Z characters. There
will be no punctuation (hyphen, dot, etc.) in the words.
## Output Format
Position the words appropriately in the grid, and then display the grid as the output. So,
your output will consist of lines with characters each.

### Sample Input 0
+-++++++++<br/>
+-++++++++<br/>
+-++++++++<br/>
+-----++++<br/>
+-+++-++++<br/>
+-+++-++++<br/>
+++++-++++<br/>
++------++<br/>
+++++-++++<br/>
+++++-++++<br/>
LONDON;DELHI;ICELAND;ANKARA

### Sample Output 0
+L++++++++<br/>
+O++++++++<br/>
+N++++++++<br/>
+DELHI++++<br/>
+O+++C++++<br/>
+N+++E++++<br/>
+++++L++++<br/>
++ANKARA++<br/>
+++++N++++<br/>
+++++D++++<br/>

### Sample Input 1
+-++++++++<br/>
+-++++++++<br/>
+-------++<br/>
+-++++++++<br/>
+-++++++++<br/>
+------+++<br/>
+-+++-++++<br/>
+++++-++++<br/>
+++++-++++<br/>
++++++++++<br/>
AGRA;NORWAY;ENGLAND;GWALIOR

### Sample Output 1
+E++++++++<br/>
+N++++++++<br/>
+GWALIOR++<br/>
+L++++++++<br/>
+A++++++++<br/>
+NORWAY+++<br/>
+D+++G++++<br/>
+++++R++++<br/>
+++++A++++<br/>
++++++++++<br/>
