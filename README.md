# Sub-Dictionary-Records
Sub-Dictionary:
In this part, you are required to write a program that will accept a text file as input and creates a sub-dictionary that includes most of
the words found in the input file based on a number of specific rules.

The input file may have zero or more words separated by whitespaces. Most words consist of alphabetic characters, but some words
may consist of certain punctuation characters at specific positions within the words. The following items specify the punctuation
characters and where they can appear within a word:
• “?” (Question mark): only appears at the end of a word; for instance: why not?
• “:” (colon): only appears at the end of a word; for instance: the rules are: ….
• “,” (comma): only appears at the end of a word; for instance: However, ….
• “=”(equal sign): which can appear by itself in the middle of an equation; for instance: x = y
• “;”(semi-colon): only appears at the end of a word; for instance: violin; what else …
• “!”(Exclamation mark): only appears at the end of a word; for instance: That is fantastic!
• “.”(Period): only appears at the end of a word; for instance: These times were good.
• Digits: 0 to 9, which may appear as a number (i.e. 1927); or as a part of a word (i.e. hi5)
• Single characters: A, B, T, etc.
• “’” (apostrophe): which can only appear in front of t, ll, re, d, nt, ve, m, n, and s; for instance: I’m … or It’s …

The words recorded in the sub-dictionary must satisfy the following rules:
• A word is recorded only once in the sub-dictionary, even if that word appears multiple times in the input file. For instance,
if the word “Hello” appears in the input file 15 times, it is recorded only once in the sub-dictionary.
• All words must be recorded only in UPPER CASE. For instance, the word Hello must be recorded as HELLO.
• Words cannot be recorded with any of the punctuation; for instance, “fantastic!” must be recorded only as “FANTASTIC”.
• No numbers or words that have digits anywhere (i.e. 1927, hi5 or b4that) can be recorded in the sub-dictionary.
• No single characters (i.e. k, M, t, etc.), with the exception of A and I, can be recorded in the dictionary. (Notice that “a”
and “i” are allowed but need to be recorded as A and I respectively).
• All words with “’t” , “’ll” “’re” , “’d”, “’nt” , “ve”, “’m” , “’n” or “’s” (or their upper case versions) must be recorded without
the “’t” , “’ll” “’re” , “’d”, “’nt” , “ve”, “’m” , “’n” or “’s”. For instance, It’s, will need to be recorded as IT.
• The words in the sub-dictionary are grouped based on their initial letters, with each group sorted alphabetically. When
displayed, each group must be preceded by the initial letter representing the group (similar to real dictionaries). For
example, the group of words that start with the letter “K” should be preceded by the lines
K
= =
• Finally, the first line in a sub-dictionary must indicate the number of word entries in the sub-dictionary. For example:
This sub-dictionary includes 447 entries

For instance, given the following file PersonOfTheCentury.txt[1] (which is provided with the assignment), your program will
create the output file SubDictionary.txt (which is also provided with the assignment). You should notice that this input file is just
one example and your program must be able to work with any input file. In fact, the marker will actually test your program with
other input files. For immediate illustration, partial images of the input and output files are shown below. 

• You MUST use the ArrayList class to implement what is needed. In specific, when you read the input file, all data must
be stored in one, or more, ArrayList objects before finally being stored in the output file.
• Your program should allow the user to enter the name of the input file at run-time. The name of the output files is always
assumed to be SubDictionary.txt.
• You MUST NOT use any other built-in Java classes/packages in this assignment. For instance, you are NOT allowed to
use other classes such as List, Map, HashMap, etc. In fact, here are all the needed imports for your program:
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

Finally, as a side note, and in honor/respect of this great scientist, you may notice the word MC² in the output file, which
was kept in the text and the output in purpose, in spite of the rule stating that words with digits must not be recorded!

Cell Phone Records:
In this part, you are required to write a program, using your own-created linked lists, that manipulates a set of records of cell phones
and performs some operations on these records.

1. The CellPhone class has the following attributes: a serialNum (long type), a brand (String type), a year (int type, which
indicates manufacturing year) and a price (double type). It is assumed that brand name is always recorded as a single word (i.e.
Motorola, SonyEricsson, Panasonic, etc.). It is also assumed that all cellular phones follow one system of assigning serial numbers,
regardless of their different brands, so no two cell phones may have the same serial number.
You are required to write the implementation of the CellPhone class. Beside the usual mutator and accessor methods (i.e.
getPrice(), setYear()) the class must have the following:
- Parameterized constructor that accepts four values and initializes serialNum, brand, year and price to these passed values;
- Copy constructor, which takes two parameters, a CellPhone object and a long value. The newly created object will be
assigned all the attributes of the passed object, with the exception of the serial number. serialNum is assigned the value passed
as the second parameter to the constructor. It is always assumed that this value will correspond to the unique serial number
rule;
- clone() method. This method will prompt the user to enter a new serial number, then creates and returns a clone of the
calling object with the exception of the serial number, which is assigned the value entered by the user;
- Additionally, the class should have a toString() and an equals() methods. Two cell phones are equal if they have the
same attributes, with the exception of the serial number, which could be different.

2. The file Cell_Info.txt, which one of its versions is provided with this assignment, has the information of various cell
phone objects. The file may have zero or more records. The information stored in this file is always assumed to be correct and
following the unique serial number rule.

3. The CellList class has the following:
- An inner class called CellNode. This class has the following:
i. Two private attributes: an object of CellPhone and a pointer to a CellNode object;
ii. A default constructor, which assigns both attributes to null;
iii. A parameterized constructor that accepts two parameters, a CellPhone object and a CellNode object, then initializes
the attributes accordingly;
iv. A copy constructor;
v. A clone() method;
vi. Other mutator and accessor methods.
- A private attribute called head, which should point to the first node in this list object;
- A private attribute called size, which always indicates the current size of the list (how many nodes are in the list);
- A default constructor, which creates an empty list;
- A copy constructor, which accepts a CellList object and creates a copy of it;
- A method called addToStart(), which accepts one parameter, an object from CellPhone class. The method then creates
a node with that passed object and inserts this node at the head of the list;
- A method called insertAtIndex(), which accepts two parameters, an object from CellPhone class, and an integer
representing an index. If the index is not valid (a valid index must have a value between 0 and size-1), the method must
throw a NoSuchElementException and terminate the program. If the index is valid, then the method creates a node with
the passed CellPhone object and inserts this node at the given index. The method must properly handle all special cases;
- A method called deleteFromIndex(), which accepts one integer parameter representing an index. Again, if the index is
not valid, the method must throw a NoSuchElementException and terminate the program. Otherwise; the node pointed by
that index is deleted from the list. The method must properly handle all special cases;
- A method called deleteFromStart(), which deletes the first node in the list (the one pointed by head). All special cases
must be properly handled.
- A method called replaceAtIndex(), which accepts two parameters, an object from CellPhone class, and an integer
representing an index. If the index is not valid, the method simply returns; otherwise the object in the node at the passed index
is to be replaced by the passed object;
- A method called find(), which accepts one parameter of type long representing a serial number. The method then searches
the list for a node with a cell phone with that serial number. If such an object is found, then the method returns a pointer to
that node where the object is found; otherwise, the method returns null. The method must keep track of how many iterations
were made before the search finally finds the phone or concludes that it is not in the list;
- A method called contains(), which accepts one parameter of type long representing a serial number. The method returns
true if an object with that serial number is in the list; otherwise, the method returns false;
(m) A method called showContents(), which displays the contents of the list, in a similar fashion to what is shown in Figure
2 below.
- A method called equals(), which accepts one parameter of type CellList. The method returns true if the two lists contain
similar objects; otherwise the method returns false. Recall that two CellPhone objects are equal if they have the same
values with the exception of the serial number, which can, and actually is expected to be, different.

Finally, here are some general rules that you must consider when implementing the above methods:
-Whenever a node is added or deleted, the list size must be adjusted accordingly;
-All special cases must be handled, whether or not the method description explicitly states that;
-All clone() and copy constructors must perform a deep copy; no shallow copies are allowed;
-If any of your methods allows a privacy leak, you must clearly place a comment at the beginning of the method 1) indicating
that this method may result in a privacy leak 2) exp

4. Now, you are required to write a public class called CellListUtilization. In the main() method, you must do the
following:
- Create at least two empty lists from the CellList class;
- Open the Cell_Info.txt file, and read its contents line by line. Use these records to initialize one of the CellList
objects you created above. You can simply use the addToStart() method to insert the read objects into the list. However,
the list should not have any duplicate records, so if the input file has duplicate entries, which is the case in the file provided
with the assignment for instance, your code must handle this case so that each record is inserted in the list only once;
- Show the contents of the list you just initialized;
- Prompt the user to enter a few serial numbers and search the list that you created from the input file for these values. Make
sure to display the number of iterations performed;
- Following that, you must create enough objects to test each of the constructors/methods of your classes. The details of this
part are left as open to you. You can do whatever you wish as long as your methods are being tested including some of the
special cases. 
