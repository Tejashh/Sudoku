
// Group Members: Foram Patel, Harshil Patel, Tejash Contractor
// single class

//include the necessary library
import java.util.ArrayList;

//start of the class
public class Single {
  
 //single algorithm that checks if there is only one number in the candidate list of each cell
 //if there is onyl one number, thats the number to be filled in that cell
 public static void singleAlgo() {

  //loop through all the cells and run the algorithm in it
  for (int x = 0; x < 9; x++) {
   for (int y = 0; y < 9; y++) {
    if (x < 3) {
     //create a new array list
     ArrayList<String> canList = new ArrayList<String>();
     ArrayList<String> cannot_List = new ArrayList<String>();
     ArrayList<String> SubGridVal = new ArrayList<String>();
     canList = Sudoku.CandidateList(x, y, cannot_List);
     
     // get the text of the particular cell
     if (y < 3) {
      for (int k = 0; k < 3; k++) {
       for (int j = 0; j < 3; j++) {
        SubGridVal.add(Sudoku.gridButtons1[k][j].getText());
       }
      }

      canList.removeAll(SubGridVal); // remove numbers from the Candidate list
  
      // if there in one candidate in any cell of the row 1, place the text at that position
      if (canList.size() == 1 && Sudoku.gridButtons1[x][y].getText().equals(" ")) {
       String single = canList.get(0);
       Sudoku.gridButtons1[x][y].setText(single);
       Sudoku.Row_Array[x][y] = single;
        Sudoku.checkWon();
      }
     }
     // get the text of the particular cell
     if (y >= 3 && y < 6) {
      for (int k = 0; k < 3; k++) {
       for (int j = 0; j < 3; j++) {
        SubGridVal.add(Sudoku.gridButtons2[k][j].getText());
       }
      }

      canList.removeAll(SubGridVal); // remove numbers from the Candidate list
    
      // if there in one candidate in any cell of the row 2, place the text at that position
      if (canList.size() == 1  && Sudoku.gridButtons2[x][y-3].getText().equals(" ")) {
       String single = canList.get(0);
       Sudoku.gridButtons2[x][y-3].setText(single);
       Sudoku.Row_Array[x][y] = single;
        Sudoku.checkWon();
      }
     }
     // get the text of the particular cell
     if (y >= 6 && y < 9) {
      for (int k = 0; k < 3; k++) {
       for (int j = 0; j < 3; j++) {
        SubGridVal.add(Sudoku.gridButtons3[k][j].getText());
       }
      }

      canList.removeAll(SubGridVal); // remove numbers from the Candidate list

      // if there in one candidate in any cell of the row 3, place the text at that position
      if (canList.size() == 1  && Sudoku.gridButtons3[x][y-6].getText().equals(" ")) {
       String single = canList.get(0);
       Sudoku.gridButtons3[x][y-6].setText(single);
       Sudoku.Row_Array[x][y] = single;
        Sudoku.checkWon();
      }
     }
    } // end of row 1, 2, 3 and x < 3
    
    if (x >= 3 && x < 6) {
     ArrayList<String> canList = new ArrayList<String>();
     ArrayList<String> cannot_List = new ArrayList<String>();
     ArrayList<String> SubGridVal = new ArrayList<String>();
     canList = Sudoku.CandidateList(x, y, cannot_List);

     // get the text of the particular cell
     if (y < 3) {
      for (int k = 0; k < 3; k++) {
       for (int j = 0; j < 3; j++) {
        SubGridVal.add(Sudoku.gridButtons4[k][j].getText());
       }
      }

      canList.removeAll(SubGridVal); // remove numbers from the Candidate list
 
      // if there in one candidate in any cell of the row 4, place the text at that position
      if (canList.size() == 1  && Sudoku.gridButtons4[x-3][y].getText().equals(" ")) {
       String single = canList.get(0);
       Sudoku.gridButtons4[x-3][y].setText(single);
       Sudoku.Row_Array[x][y] = single;
        Sudoku.checkWon();
      }
     }
     // get the text of the particular cell
     if (y >= 3 && y < 6) {
      for (int k = 0; k < 3; k++) {
       for (int j = 0; j < 3; j++) {
        SubGridVal.add(Sudoku.gridButtons5[k][j].getText());
       }
      }

      canList.removeAll(SubGridVal); // remove numbers from the Candidate list
   
      // if there in one candidate in any cell of the row 5, place the text at that position
      if (canList.size() == 1  && Sudoku.gridButtons5[x-3][y-3].getText().equals(" ")) {
       String single = canList.get(0);
       Sudoku.gridButtons5[x-3][y-3].setText(single);
       Sudoku.Row_Array[x][y] = single;
        Sudoku.checkWon();
      }
     }
     // get the text of the particular cell
     if (y >= 6 && y < 9) {
      for (int k = 0; k < 3; k++) {
       for (int j = 0; j < 3; j++) {
        SubGridVal.add(Sudoku.gridButtons6[k][j].getText());
       }
      }

      canList.removeAll(SubGridVal); // remove numbers from the Candidate list

      // if there in one candidate in any cell of the row 6, place the text at that position
      if (canList.size() == 1  && Sudoku.gridButtons6[x-3][y-6].getText().equals(" ")) {
       String single = canList.get(0);
       Sudoku.gridButtons6[x-3][y-6].setText(single);
       Sudoku.Row_Array[x][y] = single;
        Sudoku.checkWon();
      }
     }
    } // end of row 4,5,6 nad x >=3 x<6
    if (x >= 6 && x < 9) {
     ArrayList<String> canList = new ArrayList<String>();
     ArrayList<String> cannot_List = new ArrayList<String>();
     ArrayList<String> SubGridVal = new ArrayList<String>();
     canList = Sudoku.CandidateList(x, y, cannot_List);

     // get the text of the particular cell
     if (y < 3) {
      for (int k = 0; k < 3; k++) {
       for (int j = 0; j < 3; j++) {
        SubGridVal.add(Sudoku.gridButtons7[k][j].getText());
       }
      }

      canList.removeAll(SubGridVal); // remove numbers from the Candidate list

      // if there in one candidate in any cell of the row 7, place the text at that position
      if (canList.size() == 1&& Sudoku.gridButtons7[x-6][y].getText().equals(" ")) {
       String single = canList.get(0);
       Sudoku.gridButtons7[x-6][y].setText(single);
       Sudoku.Row_Array[x][y] = single;
        Sudoku.checkWon();
      }
     }
     // get the text of the particular cell
     if (y >= 3 && y < 6) {
      for (int k = 0; k < 3; k++) {
       for (int j = 0; j < 3; j++) {
        SubGridVal.add(Sudoku.gridButtons8[k][j].getText());
       }
      }

      canList.removeAll(SubGridVal); // remove numbers from the Candidate list

      // if there in one candidate in any cell of the row 8, place the text at that position
      if (canList.size() == 1 && Sudoku.gridButtons8[x-6][y-3].getText().equals(" ")) {
       String single = canList.get(0);
       Sudoku.gridButtons8[x-6][y-3].setText(single);
       Sudoku.Row_Array[x][y] = single;
        Sudoku.checkWon();
      }
     }
     
     // get the text of the particular cell
     if (y >= 6 && y < 9) {
      for (int k = 0; k < 3; k++) {
       for (int j = 0; j < 3; j++) {
        SubGridVal.add(Sudoku.gridButtons9[k][j].getText());
       }
      }

      canList.removeAll(SubGridVal); // remove numbers from the Candidate list

      // if there in one candidate in any cell of the row 9, place the text at that position
      if (canList.size() == 1 && Sudoku.gridButtons9[x-6][y-6].getText().equals(" ")) {
       String single = canList.get(0);
       Sudoku.gridButtons9[x-6][y-6].setText(single);
       Sudoku.Row_Array[x][y] = single;
        Sudoku.checkWon();
      }
     }
    } // end of row 4,5,6 nad x >=3 x<6
   }
  }
  
 }//
 
 
}//end of class
