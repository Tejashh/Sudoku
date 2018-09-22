//
// Group members: Foram Patel, Harshil Patel, Tejash Contractor
// FuncListner class

//include all the necessay library definitions
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

// start of the class
public class FuncListener {

 // *****************************************************************************************************************************************************************
 /* Exits the GUI */
 public static void exitMethod() {
  System.exit(0);
 }

 // *****************************************************************************************************************************************************************
 /* Helps To Reset the Grid */
 public static void resetGrid() {
  for (int i = 0; i < Sudoku.Array1.length; i++) {
   for (int j = 0; j < Sudoku.Array1[0].length; j++) {

    Sudoku.Array1[i][j] = " "; // resets the 1st subGrid
    Sudoku.Array2[i][j] = " "; // resets the 2nd subGrid
    Sudoku.Array3[i][j] = " "; // resets the 3rd subGrid
    Sudoku.Array4[i][j] = " "; // resets the 4th subGrid
    Sudoku.Array5[i][j] = " "; // resets the 5th subGrid
    Sudoku.Array6[i][j] = " "; // resets the 6th subGrid
    Sudoku.Array7[i][j] = " "; // resets the 7th subGrid
    Sudoku.Array8[i][j] = " "; // resets the 8th subGrid
    Sudoku.Array9[i][j] = " "; // resets the 9th subGrid

   }
  }

  Sudoku application2 = new Sudoku();
  application2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 }// End of the reset function

 // *****************************************************************************************************************************************************************
 /*
  * Opens the file, Parses the data, and buts the data in a Array list and then
  * checks checks those arrray list or the arrays for further actions.
  */
 public static void openFile(JFileChooser fc) throws FileNotFoundException {

  fc = new JFileChooser();
  int value = fc.showOpenDialog(null);
  if (value == JFileChooser.APPROVE_OPTION) {
   File selectedFile = fc.getSelectedFile();
   @SuppressWarnings("resource")
   Scanner input = new Scanner(selectedFile);
   while (input.hasNext()) {
    String nextLine = input.nextLine();
    String values[] = nextLine.split(" ");
    int int_row = Integer.parseInt(values[0]);
    int int_col = Integer.parseInt(values[1]);
    int int_num = Integer.parseInt(values[2]);

    String row = Integer.toString(int_row);
    String col = Integer.toString(int_col);
    String num = Integer.toString(int_num);

    // set the number
    NumberPosition pos = new NumberPosition();
    pos.setRow(row);
    pos.setCol(col);
    pos.setNumber(num);

    Sudoku.gridInfo.add(pos);

    //find and place the numbers in the required position of the grid
    if (int_row == 1 || int_row == 2 || int_row == 3) {
     if (int_col == 1 || int_col == 2 || int_col == 3) 
      Sudoku.Array1[int_row - 1][int_col - 1] = num;
     
     if (int_col == 4 || int_col == 5 || int_col == 6) 
      Sudoku.Array2[int_row - 1][int_col - 4] = num;
     
     if (int_col == 7 || int_col == 8 || int_col == 9) 
      Sudoku.Array3[int_row - 1][int_col - 7] = num;
     
    } else if (int_row == 4 || int_row == 5 || int_row == 6) {
     if (int_col == 1 || int_col == 2 || int_col == 3) 
      Sudoku.Array4[int_row - 4][int_col - 1] = num;
     
     if (int_col == 4 || int_col == 5 || int_col == 6) 
      Sudoku.Array5[int_row - 4][int_col - 4] = num;
     
     if (int_col == 7 || int_col == 8 || int_col == 9) 
      Sudoku.Array6[int_row - 4][int_col - 7] = num;
     
    } else if (int_row == 7 || int_row == 8 || int_row == 9) {
     if (int_col == 1 || int_col == 2 || int_col == 3) 
      Sudoku.Array7[int_row - 7][int_col - 1] = num;
     
     if (int_col == 4 || int_col == 5 || int_col == 6) 
      Sudoku.Array8[int_row - 7][int_col - 4] = num;
     
     if (int_col == 7 || int_col == 8 || int_col == 9) 
      Sudoku.Array9[int_row - 7][int_col - 7] = num;
    }
   }
  }

  
  /*
   * make a Big Arryay to keep track of future procecedings
   */
  for (int i = 0; i < 9; i++) {
   for (int j = 0; j < 9; j++) {

    // For row 1 
    if (i == 0 || i == 1 || i == 2) {
     if (j == 0 || j == 1 || j == 2) 
      Sudoku.Row_Array[i][j] = Sudoku.Array1[i][j];
      else if (j == 3 || j == 4 || j == 5) 
      Sudoku.Row_Array[i][j] = Sudoku.Array2[i][j - 3];
      else if (j == 6 || j == 7 || j == 8) 
      Sudoku.Row_Array[i][j] = Sudoku.Array3[i][j - 6];
     
    } else if (i == 3 || i == 4 || i == 5) {
     if (j == 0 || j == 1 || j == 2) 
      Sudoku.Row_Array[i][j] = Sudoku.Array4[i - 3][j];
      else if (j == 3 || j == 4 || j == 5) 
      Sudoku.Row_Array[i][j] = Sudoku.Array5[i - 3][j - 3];
      else if (j == 6 || j == 7 || j == 8) 
      Sudoku.Row_Array[i][j] = Sudoku.Array6[i - 3][j - 6];
     
    } else if (i == 6 || i == 7 || i == 8) {
     if (j == 0 || j == 1 || j == 2) 
      Sudoku.Row_Array[i][j] = Sudoku.Array7[i - 6][j];
      else if (j == 3 || j == 4 || j == 5) 
      Sudoku.Row_Array[i][j] = Sudoku.Array8[i - 6][j - 3];
      else if (j == 6 || j == 7 || j == 8) 
      Sudoku.Row_Array[i][j] = Sudoku.Array9[i - 6][j - 6];
    }
   }
  }//end of for
  
  //launch the application
  Sudoku application = new Sudoku();
  application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }// end of function

 // *****************************************************************************************************************************************************************
 /*
  * The code here saves the current grid postion to a text file and the player
  * can load that again to play the Game
  */
 public static void saveFile() {
  int value = 0;
  JFileChooser newFile = new JFileChooser();
  newFile.setDialogTitle("Specify a file to save");

  value = newFile.showSaveDialog(null);
  if (value == JFileChooser.APPROVE_OPTION) {
   File fileToSave = newFile.getSelectedFile();

   try (BufferedWriter outputFile = new BufferedWriter(new FileWriter(fileToSave))) {
    for (int i = 0; i < Sudoku.Row_Array.length; i++) {
     for (int k = 0; k < Sudoku.Row_Array.length; k++) {
      if (!(Sudoku.Row_Array[i][k].equals("0"))) {
       outputFile.write((i + 1) + " " + (k + 1) + " " + Sudoku.Row_Array[i][k]);
       outputFile.newLine();
      }
     }
    }
    outputFile.flush();
    outputFile.close();

   } catch (IOException exception) {
    exception.printStackTrace();
   }
  }
 }// end of saveFile

 // *****************************************************************************************************************************************************************
 /* Prints out the information about on Displaying the Rules */
 public static void displayRules() {
  JOptionPane.showMessageDialog(null,
    "1.) There are 9 3x3 different Grids\n"
      + "2.) Each puzzle consists of a 9x9 grid containing given clues in various places\n"
      + "3.) The object is to fill all empty squares so that"
      + " \nthe numbers 1 to 9 appear exactly once in each row, column and 3x3 box.\n"
      + "4.) So each box will number 1 through 9 each row should have number 1 thru 9\n"
      + "and each column should have number 1 thru 9",
    "Rules", JOptionPane.PLAIN_MESSAGE);

 }

 // *****************************************************************************************************************************************************************
 /* Prints out the information How to Play information */
 public static void howToPlay() {
  JOptionPane.showMessageDialog(null, "1.) The application very easy to use.\n"
    + "2.) You are to Load a puzzle from a File that is .txt file which will load the puzzle "
    + "in the box\n"
    + "3.) If wnat any hints then you click on the Edit buttons and you can get different Hints.\n"
    + "4.) There are numbers provided to you on the right side which you\n click first and then"
    + "click the blank space where you want to keep that number.\n"
    + "5.) You can keep the number where ever you want until you click the different number next time.\n"
    + "6.) If you want to Earse then click the X button.\n"
    + "7.) To Save the Puzzle like the save button under the File option and this will save it in a .txt file.\n",
    "How To Play", JOptionPane.PLAIN_MESSAGE);

 }

 // *****************************************************************************************************************************************************************
 /* Prints out the information about the author */
 public static void aboutTheAuthors() {
  JOptionPane.showMessageDialog(null,
    "Author Name: Harshil Patel\n" + "                       Foram Patel\n"
      + "                       Tejash Contractor\n" + "Course: CS342\n" + "Professor Troy\n"
      + "Project3: Suduko Puzzle\n" + "Last Modified Date: 10/25/2017 ",
    "About", JOptionPane.PLAIN_MESSAGE);

 }// end of function
 
}// end of class

