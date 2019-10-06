/**
* This application takes words that the user inputs
* It creates a simple world search puzzle
* Displays the puzzle and the words need to search for
* @author Linh Duong, Vy Hoang
* @version December 3, 2018
*
*/

import java.util.*;

public class WordPuzzle
{
//constants
static final int MINIMUM_SIZE=5;
static final int MAXIMUM_SIZE=10;
static Scanner input = new Scanner(System.in);
static Random random = new Random();

public static void main(String[] args)
{

    char[][] words; //declare a 2D character array which is called words

    int size;   //declare the type

    String[] word; //declare a list of words which is called word

    displayPurpose();
    size = getSize(); //getSize();
    words = new char[size][size]; //allocate memory space to the names array
    word = fillWithWord (words, size); //call the method ask user to input words
    fillUnusedElements(words, size);//call a method to fill the unused elements with random characters
    displayPuzzle (words, size);//call method to display the puzzle
    displayWord(word);//call method to display the words

}//of main


/**
 * This method displays the purpose of the program
 *
 */
private static void displayPurpose()
{

    System.out.println("***************************************************************************");
    System.out.println("                 This program creates a simple word puzzle                       ");
    System.out.println("***************************************************************************");

}//for displayPurose()


/**
 * This method takes size of the puzzle from the user which is >= 5 or <= 10 and returns it
 * @return size
 */
private static int getSize()
{
    int size;
    do{
    System.out.printf("What is the size of the puzzle you would like to create (>=%d and <= %d): ", MINIMUM_SIZE, MAXIMUM_SIZE);
    size = input.nextInt();
    input.nextLine(); //flush the input buffer
    if(size < MINIMUM_SIZE || size > MAXIMUM_SIZE)
      {
        System.out.printf("******Invalid size, MUST be >= %d and <=%d%n",MINIMUM_SIZE, MAXIMUM_SIZE);
      }

    }while (size < MINIMUM_SIZE || size > MAXIMUM_SIZE);

    return size;
}//getSize


/**
 * This method takes a String(word) from the user and then 'saves' that String in a row of the
 * character array. So each row now has a word in it. The length of the word should be <= size
 * If the row size > word length there are just null characters at the positions do not have character
 * @param words the two dimensional array of characters
 * @return word
 */

private static String[] fillWithWord(char[][] words, int size)
{
    String[] word = new String [size];
    String inputWord;

    boolean checkInputWord;
    int randomColumn;
    System.out.println("---------------------------------------------------------------------------");
    System.out.printf("Enter %d words, each word no more than %d characters long%n ", size, size);
    System.out.println("---------------------------------------------------------------------------");
    //iterate through each row of the character array
    //maximum of size word can be input
    for(int i= 0; i < size; i++)
    {
        //ask for a name from the user- ensure is <= size, since that is the space allocated for each name

        do{
        	String wordPattern= "[a-zA-Z]+";
        	//prompt for and input a word from the user
            System.out.printf("Please input word %d to add to the puzzle: ", i+1);
            inputWord = input.nextLine();
            checkInputWord = inputWord.matches(wordPattern);
            inputWord = inputWord.toUpperCase( );
            //the word can only be saved if it can be accommodated in the row, word size <= row size(COLUMNS)
            if (inputWord.length() > size){
                System.out.printf("The word cannot be more than %d characters.%n", size);
            }//if

            if(!checkInputWord)
            {
              System.out.println("--Invalid input---The word cannot contain spaces, numbers or special characters");
            }//if
            word[i] = inputWord;
        }while(inputWord.length() > size ||!checkInputWord);

        randomColumn = random.nextInt (size +1 - inputWord.length()); //set the position of the word in random position
        for(int j= 0; j < inputWord.length ();j++){
            words[i][randomColumn]= inputWord.charAt(j); //this method gets the character at random position
            randomColumn ++; //This line places the next character in the next position
         }//for j
       }//for i
        return word;
     }//for fillWithWord

private static void fillUnusedElements(char[][] words, int size)
{   // an array that can store the characters
    char[] randomCharacters ={'A','B','D','F','G','H','J','K','L','N','O','P','Q','V','W','X','Y','Z','U','C','M', 'E','S', 'R', 'Z', 'T', 'I'};
    int randomNumber;
    int sizeRandom = randomCharacters.length;
    //iterate through all rows in the 2d array
    for(int i= 0; i < size; i++)
    {
        //iterate through all columns in each row
        for(int j= 0; j < size; j++)
        {
            randomNumber = random.nextInt (sizeRandom);

            //if the element is the null character, replace it with a random character in the list above
            if (words[i][j] == '\u0000')
            words[i][j] = randomCharacters[randomNumber];
        }//for j
    }//for i
}//fillUnusedElements

/**
 * This method displays the characters in the 2D array
 * @param words the two dimensional array of characters
 * @size the number of rows and columns since it is a square array
 */

private static void displayPuzzle(char[][] words, int size)
{
    System.out.println("--------------------------Puzzle-----------------------------");
    for(int i=0; i < words.length; i++)
    {
        for(int j=0; j < words[i].length; j++)
        {
          System.out.printf ("%c     ", words[i][j]); //display the puzzle
        }
          System.out.println (); //start display of new row on different line
    }//for i
}//displayPuzzle

/**
 * This method displays the words that the player needs to search for
 * @param word words that user inputs
 */

private static void displayWord(String[] word)
{
    System.out.println("-----------------------Word to Search-------------------------");

    for(String inputWord: word)
    {
          System.out.println (inputWord);
    }//for

 }//displayWord

}
