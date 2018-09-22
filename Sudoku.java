/*
 * Name: Harshil Patel
 *    Foram Patel
 *    Tejash Contractor
 * PROJECT 3: SUDOKU SOLVER
 * Sudoku.java: Create all the JMenu including JMenu Item and also create subArrays using double array
 * Creates Grid and GET Candidate lists which helps us solve the sudoku.
 */

//Different GUI Libraries
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

//Start of the Sudoku class which implements ActionListener and extends JFrame
public class Sudoku extends JFrame implements ActionListener {
 
 private static final long serialVersionUID = 1L;
 
 public static final int GRID_SIZE = 9;
 public static final int SUB_GRID = 3;

 JPanel Grid; //Grid Jpanel
 JPanel SubGrids; //SubGrids JPanel

 JFileChooser fc = new JFileChooser();

 //Initialize JButton array as global
 JButton HelpButton[];
 
 //helpNames String array
 public final String helpNames[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "X", "?" };

 /* The Jbuttons for the SubGrids that is we have nine SubGrids */
 public static JButton gridButtons1[][];
 public static JButton gridButtons2[][];
 public static JButton gridButtons3[][];
 public static JButton gridButtons4[][];
 public static JButton gridButtons5[][];
 public static JButton gridButtons6[][];
 public static JButton gridButtons7[][];
 public static JButton gridButtons8[][];
 public static JButton gridButtons9[][];

 /* The Array list has the Data from the File */
 public static ArrayList<NumberPosition> gridInfo = new ArrayList<>();

 /* Arrays used to store data from the file */
 public static String Array1[][] = { { " ", " ", " " }, { " ", " ", " " }, { " ", " ", " " }, };
 public static String Array2[][] = { { " ", " ", " " }, { " ", " ", " " }, { " ", " ", " " }, };
 public static String Array3[][] = { { " ", " ", " " }, { " ", " ", " " }, { " ", " ", " " }, };
 public static String Array4[][] = { { " ", " ", " " }, { " ", " ", " " }, { " ", " ", " " }, };
 public static String Array5[][] = { { " ", " ", " " }, { " ", " ", " " }, { " ", " ", " " }, };
 public static String Array6[][] = { { " ", " ", " " }, { " ", " ", " " }, { " ", " ", " " }, };
 public static String Array7[][] = { { " ", " ", " " }, { " ", " ", " " }, { " ", " ", " " }, };
 public static String Array8[][] = { { " ", " ", " " }, { " ", " ", " " }, { " ", " ", " " }, };
 public static String Array9[][] = { { " ", " ", " " }, { " ", " ", " " }, { " ", " ", " " }, };

 /* Just the Big Array can be used to save the File */
 public static String Row_Array[][] = new String[9][9];

 int num_To_insert = -1;

 //*****************************************************************************************************************************************************************
 /* Constructor */
 public Sudoku() {
  super("Sudoku");
  
  //Drawing border line for all chells with black line and width 3
  Border outside = new LineBorder(Color.BLACK, 3);

  //Allocating gridButtons1 to gridButtons9 double array
  gridButtons1 = new JButton[SUB_GRID][SUB_GRID];
  gridButtons2 = new JButton[SUB_GRID][SUB_GRID];
  gridButtons3 = new JButton[SUB_GRID][SUB_GRID];
  gridButtons4 = new JButton[SUB_GRID][SUB_GRID];
  gridButtons5 = new JButton[SUB_GRID][SUB_GRID];
  gridButtons6 = new JButton[SUB_GRID][SUB_GRID];
  gridButtons7 = new JButton[SUB_GRID][SUB_GRID];
  gridButtons8 = new JButton[SUB_GRID][SUB_GRID];
  gridButtons9 = new JButton[SUB_GRID][SUB_GRID];

  /*
   * create a panel inside a pannel that is the inner subGrids
   */
  Grid = new JPanel(); //Allocating grid as Grid panel
  for (int row = 0; row < SUB_GRID; ++row) {
   for (int col = 0; col < SUB_GRID; ++col) {

    Grid.setLayout(MyGridLayout.getLayout());
    Grid.setBorder(outside);
    Grid.add(subGrids(row, col));
   }
  }
  
  JPanel GridPanel_Final = new JPanel(new BorderLayout());
  GridPanel_Final.add(Grid, BorderLayout.CENTER);

  /*
   * create a Panel for the buttons on the side
   */
  JPanel HelpPanel = new JPanel(MyGridLayout.getLayout2()); // layout of 1x10
  HelpPanel.setBorder(outside);

  HelpButton = new JButton[helpNames.length]; //HelpButton allocating size of helpNames array
  
  for (int i = 0; i < helpNames.length; i++) {

   HelpButton[i] = new JButton(helpNames[i]);
   HelpButton[i].addActionListener(Sudoku.this);
   HelpPanel.add(HelpButton[i]);
  }

  // set the content pane fot both the JPanel
  getContentPane().add(HelpPanel, BorderLayout.EAST);
  add(Grid);
  // ----------------------------End of Grid
  // Making--------------------------------------------

  // create a menu bar
  JMenuBar bar = new JMenuBar();
  setJMenuBar(bar);
  
  //creating menuOptions JMenu for File menu
  JMenu menuOptions = new JMenu("File");
  menuOptions.setMnemonic('A');
  bar.add(menuOptions); //add menuOptions for bar

  /* Load A File */
  JMenuItem Load = new JMenuItem("Open File"); //Open File buttons
  Load.setMnemonic('A');
  menuOptions.add(Load);
  Load.addActionListener(new ActionListener() {

   public void actionPerformed(ActionEvent e) {
    try {
     FuncListener.openFile(fc);
    } catch (FileNotFoundException e1) {
     // TODO Auto-generated catch block
     e1.printStackTrace();
    }
    setVisible(false);
   }
  }); // end of actionListner

  /* Save A File */
  JMenuItem Save = new JMenuItem("Save File"); //Save File JMunuItem into Sudoku
  Save.setMnemonic('B');
  menuOptions.add(Save);
  Save.addActionListener(new ActionListener() {

   public void actionPerformed(ActionEvent e) {
    FuncListener.saveFile(); //to save file
   }
  }); // end of actionListner

  /* Reset the Grid */
  JMenuItem Reset = new JMenuItem("Reset"); //Reset JMenuItem 
  Reset.setMnemonic('R');
  menuOptions.add(Reset); //adding Reset to menuOptions
  Reset.addActionListener(new ActionListener() {

   public void actionPerformed(ActionEvent e) {
    FuncListener.resetGrid(); //Reseting grid
    setVisible(false); //setting as visible

   }
  }); // end of actionListner

  /* Create a Exit option */
  JMenuItem exit = new JMenuItem("Exit"); //Exit JMenuItem
  exit.setMnemonic('X');
  menuOptions.add(exit); //exit menuOptions to sudoku
  
  exit.addActionListener(new ActionListener() {
   
   //exit buttons actionPerformed
   public void actionPerformed(ActionEvent e) {
    FuncListener.exitMethod();
   }
  }); // end of actionListner

  /* help Options displayed othe menyBar */
  JMenu HelpOptions = new JMenu("Help"); //Help JMenu HelpOptions
  HelpOptions.setMnemonic('H');
  bar.add(HelpOptions); //add HelpOptions to bar

  /* Create Rules Options */
  JMenuItem rules = new JMenuItem("Rules"); //Rules JMenuItem
  rules.setMnemonic('X');
  HelpOptions.add(rules); //adding rules HelpOptions
  rules.addActionListener(new ActionListener() {
   
   //actionPerformed for rules to displayRules 
   public void actionPerformed(ActionEvent e) {
    FuncListener.displayRules();
   }
  }); // end of actionListner

  /* How to use the GUI or How to play the game */
  JMenuItem how_to_play = new JMenuItem("How to Play");  //How to Play JMenuItem 
  how_to_play.setMnemonic('Y');
  HelpOptions.add(how_to_play); //add How to Play buttion into HelpOptions
  rules.addActionListener(new ActionListener() {
   
   //actionPerformed for rules to do howtoPlay
   public void actionPerformed(ActionEvent e) {
    FuncListener.howToPlay();
   }
  }); // end of actionListner

  /* about the authors */
  JMenuItem about = new JMenuItem("About"); //About JMenuItem
  about.setMnemonic('A');
  HelpOptions.add(about); //add About into HelpOptions
  about.addActionListener(new ActionListener() {
   
   //actionPerformed for about to do aboutTheAuthors
   public void actionPerformed(ActionEvent e) {
    FuncListener.aboutTheAuthors();
   }
  }); // end of actionListner

  /* Edit option which will give you hints and gour algorithms */
  JMenu editOptions = new JMenu("Hints"); //Hints JMenu
  editOptions.setMnemonic('M');
  bar.add(editOptions); //add Hits into bar
  
  /*Create the slection for Single AlgoRithm*/
  JMenuItem single = new JMenuItem("Singles"); //Singles JMenuItem
  single.setMnemonic('A');
  editOptions.add(single); //add Singles into Hints
  single.addActionListener(new ActionListener() {

   //ActionPerformed for single
   public void actionPerformed(ActionEvent e) {
    // TO DO ....
    Single.singleAlgo();
   }
  }); // end of actionListner
  
  /*Create the slection for Naked Pairs*/
  JMenuItem NakedPair =  new JMenuItem("Naked Pair"); //Naked Pair JMenuItem
  NakedPair.setMnemonic('B');
  editOptions.add(NakedPair); //add Naked Pair into Hint 
  NakedPair.addActionListener(new ActionListener() {
   //ActionPerformed for NakedPair
   public void actionPerformed(ActionEvent e) {
    // TO DO ....
   }
  }); // end of actionListner
  
  /*Create the slection for Locked Candidate AlgoRithm*/
  JMenuItem LockedCan = new JMenuItem("Locked Candidate"); //Locked Candidates JMenuItem
  LockedCan.setMnemonic('C');
  editOptions.add(LockedCan); //add Locked Condidates into Hints
  LockedCan.addActionListener(new ActionListener() {
   
   //ActionPerformed for LockedCon
   public void actionPerformed(ActionEvent e) {
    // TO DO ....
   }
  }); // end of actionListner
  
  /*Create the slection for Hidden Single Candidate AlgoRithm*/
  JMenuItem hidSin = new JMenuItem("Hidden Single"); //Hidden Single JMenuItem
  hidSin.setMnemonic('H');
  editOptions.add(hidSin); //add Hidden Single into Hints
  hidSin.addActionListener(new ActionListener() {

   //ActionPerformed for Hidden Single
   public void actionPerformed(ActionEvent e) {
    // TO DO ....
   }
  }); // end of actionListner
  
  setSize(400, 330); //SetSize as 400 x 330
  setVisible(true); //Visible the JPanel
 }//end of constructor

 //*****************************************************************************************************************************************************************
 /*
  * create a panle inside panel and then also sets 
  * the border for all the subGrids which are numbered from 
  * 1 to 9 for simplicity
  */
 public JPanel subGrids(int row, int col) {

  SubGrids = new JPanel();
  //Create Border between SubGrids
  Border outside = new LineBorder(Color.BLUE, 3);

  for (int x = 0; x < SUB_GRID; ++x) {
   for (int y = 0; y < SUB_GRID; ++y) {
    SubGrids.setLayout(MyGridLayout.getLayout());
    SubGrids.setBorder(outside);
    if (row == 0 && col == 0) {         
     gridButtons1[x][y] = new JButton(Array1[x][y]);
     gridButtons1[x][y].addActionListener(Sudoku.this);    //row 0 and  col 0 --> Makes the SubGrid1
     SubGrids.add(gridButtons1[x][y]);
    } else if (row == 0 && col == 1) {
     gridButtons2[x][y] = new JButton(Array2[x][y]);
     gridButtons2[x][y].addActionListener(Sudoku.this);   //row 0 and  col 1 --> Makes the SubGrid2
     SubGrids.add(gridButtons2[x][y]);
    } else if (row == 0 && col == 2) {
     gridButtons3[x][y] = new JButton(Array3[x][y]);
     gridButtons3[x][y].addActionListener(Sudoku.this);    //row 0 and  col 2 --> Makes the SubGrid3
     SubGrids.add(gridButtons3[x][y]);
    } else if (row == 1 && col == 0) {
     gridButtons4[x][y] = new JButton(Array4[x][y]);      //row 1 and  col 0 --> Makes the SubGrid4
     gridButtons4[x][y].addActionListener(Sudoku.this);
     SubGrids.add(gridButtons4[x][y]);
    } else if (row == 1 && col == 1) {
     gridButtons5[x][y] = new JButton(Array5[x][y]);
     gridButtons5[x][y].addActionListener(Sudoku.this);    //row 1 and  col 1 --> Makes the SubGrid5
     SubGrids.add(gridButtons5[x][y]);
    } else if (row == 1 && col == 2) {
     gridButtons6[x][y] = new JButton(Array6[x][y]);
     gridButtons6[x][y].addActionListener(Sudoku.this);      //row 1 and  col 2 --> Makes the SubGrid6
     SubGrids.add(gridButtons6[x][y]);
    } else if (row == 2 && col == 0) {
     gridButtons7[x][y] = new JButton(Array7[x][y]);
     gridButtons7[x][y].addActionListener(Sudoku.this);      //row 2 and  col 0 --> Makes the SubGrid7
     SubGrids.add(gridButtons7[x][y]);
    } else if (row == 2 && col == 1) {
     gridButtons8[x][y] = new JButton(Array8[x][y]);
     gridButtons8[x][y].addActionListener(Sudoku.this);    //row 2 and  col 1 --> Makes the SubGrid8
     SubGrids.add(gridButtons8[x][y]);
    } else if (row == 2 && col == 2) {
     gridButtons9[x][y] = new JButton(Array9[x][y]);
     gridButtons9[x][y].addActionListener(Sudoku.this);    //row 2 and  col 2 --> Makes the SubGrid9
     SubGrids.add(gridButtons9[x][y]);
    }
   } // end of for
  } // end of for

  return SubGrids;
 }// end of subGrids
 
 //*****************************************************************************************************************************************************************
 /*
  *The actions Listener here does the Following things:
  *->You can click a button number and place the number in the subGrids.
  *->The Subgrids are handled differently.
  *->You can earse the number you added you cannot erase the number that are loaded from the File 
  *->You can check the duplicates in the row, col and the subgrids.
  *->check for the candidate list for each cell in the subgrids and  the whole Grid
  */
 @Override
 public void actionPerformed(ActionEvent e) {
  // TODO Auto-generated method stub
  JButton temp = (JButton) e.getSource();

  /*
   * get the number which will be used for
   * insert into the subgrids
   */
  for (int k = 0; k < HelpButton.length; k++) {
   if (temp.equals(HelpButton[k])) {
    num_To_insert = k;
    System.out.println("Number to be inserted: " + num_To_insert);
    break;
   }
  }//end for for

  /*
   * From Here we Check the condtion of click a button from the Right Grid and
   * then puttin the number in the subGrids We get the postion of the subgrids and
   * then we get the postions of the button thta was clicked in that subgrid
   * Afterwards we set the text to that button and simuntaneously we also handle
   * the earsing part of the grid only those number can be earsed that were
   * inserted by the user. if the user tries to earse a loaded number then a error
   * message is poped.
   */
  for (int GridPos = 0; GridPos < 9; ++GridPos) {
   for (int x = 0; x < SUB_GRID; ++x) {
    for (int y = 0; y < SUB_GRID; ++y) {

     // if the Sub Grid Position is ZERO
     if (GridPos == 0) {
      if (temp.equals(gridButtons1[x][y])) {

       /* If X was Pressed to earse the position*/
       if ((HelpButton[num_To_insert].getText().equals("X"))) {

        /* Notify the USer you are on an Earse Mode */
        JOptionPane.showMessageDialog(null,
          "You are on a Earse mode, you can erase as many number you want\n"
            + "but you can erase only the numbers you inserted\n"
            + "You will be notified once the EARSE MODE is OFF ",
          "ERASE MODE ON", JOptionPane.PLAIN_MESSAGE);
        
        /*if X was pressses and it was not eqaul to blank at beginning*/
        /*display an error that its cannot be erased becuase its was loaded from file*/
        if (!Array1[x][y].equals(" ")) {
         JOptionPane.showMessageDialog(null,
           "You cannot Earse this positon as it was loaded from a file ", "ERROR",
           JOptionPane.PLAIN_MESSAGE);
        } else {

         /*if the it was earsed from the position set the positon to blank and 
          * also check which postion it was erased so you can update it in the BiG array*/
         gridButtons1[x][y].setText(" ");
         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          Row_Array[x][y] = "0";
         }
        }//end of check
        /*if the button pressed was for help display the cadidate list*/
       } else if ((HelpButton[num_To_insert].getText().equals("?"))) {   

        ArrayList<String> cannot_List = new ArrayList<String>();  //Array list for cannot condidate list
        ArrayList<String> cand_List = new ArrayList<String>();   //array list for candidate list
        
        //if chell is empty
        if (gridButtons1[x][y].getText().equals(" ")) {
         
         /*if the button pressed was poition 00, 01, 02 ... in the gird
          * create the candidate list*/
         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {

          /*call the dunction which returns the arraylist of candidate list*/
          cand_List = CandidateList(x, y, cannot_List);

          /* Also chech the Grid if you can remove elemts from the Candidate List */
          ArrayList<String> SubGridVal = new ArrayList<String>();
          for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
            SubGridVal.add(gridButtons1[i][j].getText());
           }
          }//end of for
          
          cand_List.removeAll(SubGridVal); // remove numbers in the grid from the Candidate list
          
          /*Dispplay the candidate list*/
          JOptionPane.showMessageDialog(null,
            "Your candidate list is or candidates you can you are: \n"
              + cand_List.toString(),
            "Candidate List", JOptionPane.PLAIN_MESSAGE);
         }//end of if (x==0 && .....
        }//end of gridbuttons1[x][y]...
       } else {
        // and is some other button was clicked other then X
        // replace the blank with the number clicked

        if (!Array1[x][y].equals(" ")) {
         JOptionPane.showMessageDialog(null,
           "You cannot insert at this positon as it was loaded from a file ", "ERROR",
           JOptionPane.PLAIN_MESSAGE);
        } else {
         System.out.println("The button was clicked in: " + "Grid pos:" + GridPos + " x: "
           + x + " y " + y);

         /*Get the number which is to be inserted in the grids*/
         String number_clicked = HelpButton[num_To_insert].getText();

         /*Set the flags*/
         int flag1 = 1;
         int flag1_y = 1;
         int flag1_grid = 1;
         
         /*check the position if the row and col has the same value
          * if it has the same value set the flag to flase and then donot
          * enter that number in the grid and throw a mess*/
         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          flag1 = checkRow(number_clicked, 1, x);
          flag1_y = checkCol(number_clicked, 1, y);
         }

         //*check for the grid duplicates*/
         flag1_grid = checkGrid(number_clicked,gridButtons1,1);
         
         /*if all the flags are true only then insert it in the Gird*/
         if (flag1 == 1 && flag1_y == 1 && flag1_grid == 1) {
          gridButtons1[x][y].setText(HelpButton[num_To_insert].getText());
          checkWon();
          if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
            || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
            || x == 2 && y == 1 || x == 2 && y == 2) {
           Row_Array[x][y] = HelpButton[num_To_insert].getText();
          }

         } else { 
          JOptionPane.showMessageDialog(null,
            "Error: This button has already been placed in this row, column or grid! \n",
            " ", JOptionPane.PLAIN_MESSAGE);
         }//end of else
        }//end of  else 
       }// if big else
      }//end of temp.equals...
     }else if (GridPos == 1) { //if the sub Grid Position is ONE
      if (temp.equals(gridButtons2[x][y])) {

       // If X was Pressed to earse the position
       if ((HelpButton[num_To_insert].getText().equals("X"))) {

        /* Notify the USer you are on an Earse Mode */
        JOptionPane.showMessageDialog(null,
          "You are on a Earse mode, you can erase as many number you want\n"
            + "but you can erase only the numbers you inserted\n"
            + "You will be notified once the EARSE MODE is OFF ",
          "ERASE MODE ON", JOptionPane.PLAIN_MESSAGE);

        // if X was pressses and it was not eqaul to blank at beginning
        // display an error that its cannot be erased becuase its was loaded from file
        if (!Array2[x][y].equals(" ")) {
         JOptionPane.showMessageDialog(null,
           "You cannot Earse this positon as it was loaded from a file ", "ERROR",
           JOptionPane.PLAIN_MESSAGE);
        } else {

         // if it was a blank and was not loaded from a file replace it with a blank and
         //also check wihich position it was erased so you can update it in the BIG array
         gridButtons2[x][y].setText(" ");
         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          Row_Array[x][y + 3] = "0";
         }
        }//end of check
        /*if the button pressed was for help display the candidate list*/
       } else if ((HelpButton[num_To_insert].getText().equals("?"))) {

        ArrayList<String> cannot_List = new ArrayList<String>(); //Array List for cannot candidate list
        ArrayList<String> cand_List = new ArrayList<String>(); //array list for candidate list
        
        //if cell is empty
        if (gridButtons2[x][y].getText().equals(" ")) {
         /*if the button pressed was poition 00, 01, 02 ... in the gird
          * create the candidate list*/
         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          //call the function which returns the arraylist of candidate list
          cand_List = CandidateList(x, y + 3, cannot_List);

          /* Also check the Grid if you can remove elements from the Candidate List */
          ArrayList<String> SubGridVal = new ArrayList<String>();
          for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
            SubGridVal.add(gridButtons2[i][j].getText());
           }
          }//end of for
          
          cand_List.removeAll(SubGridVal); // remove numbers from the Candidate list
          
          //Display the candidate list
          JOptionPane.showMessageDialog(null,
            "Your candidate list is or candidates you can you are: \n"
              + cand_List.toString(),
            "Candidate List", JOptionPane.PLAIN_MESSAGE);
         }//end of if(x == 0 && ....
        }//end of gridbutton2[x][y]..

       } else {
        // and is some other button was clicked other then X
        // replace the blank with the number clicked
        if (!Array2[x][y].equals(" ")) {
         JOptionPane.showMessageDialog(null,
           "You cannot insert at this positon as it was loaded from a file ", "ERROR",
           JOptionPane.PLAIN_MESSAGE);
        } else {
         System.out.println("The button was clicked in: " + "Grid pos:" + GridPos + " x: "
           + x + " y " + y);
         String number_clicked = HelpButton[num_To_insert].getText();

         // checck the row 1 for Row_Array if its has number_clicked
         // only if the blank space was cliked in subGrid1.
         int flag2 = 1;
         int flag2_y = 1;
         int flag2_grid = 1;
         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          flag2 = checkRow(number_clicked, 1, x);
          flag2_y = checkCol(number_clicked, 1, y + 3);
         }

         /*check for duplicates in the subGrids*/
         flag2_grid = checkGrid(number_clicked,gridButtons2,1);

         //if all the flag are true only then insert it in the grid
         if (flag2 == 1 && flag2_y == 1 && flag2_grid == 1) {
          gridButtons2[x][y].setText(HelpButton[num_To_insert].getText());
          checkWon();
          if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
            || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
            || x == 2 && y == 1 || x == 2 && y == 2) {
           Row_Array[x][y + 3] = HelpButton[num_To_insert].getText();
          
          }

         } else {
          JOptionPane.showMessageDialog(null,
            "Error: This button has already been placed in this row, column or grid! \n",
            " ", JOptionPane.PLAIN_MESSAGE);
         }//end of else
        }//end of else
       }//if big else
      }//end of temp.equals
     } else if (GridPos == 2) { // if the Sub Grid Position is TWO
      if (temp.equals(gridButtons3[x][y])) {

       /* If X was Pressed to earse the position*/
       if ((HelpButton[num_To_insert].getText().equals("X"))) {

        /* Notify the USer you are on an Earse Mode */
        JOptionPane.showMessageDialog(null,
          "You are on a Earse mode, you can erase as many number you want\n"
            + "but you can erase only the numbers you inserted\n"
            + "You will be notified once the EARSE MODE is OFF ",
          "ERASE MODE ON", JOptionPane.PLAIN_MESSAGE);

        // if X was pressses and it was not eqaul to blank at beginning
        // display an error that its cannot be erased becuase its was loaded from file
        if (!Array3[x][y].equals(" ")) {
         JOptionPane.showMessageDialog(null,
           "You cannot Earse this positon as it was loaded from a file ", "ERROR",
           JOptionPane.PLAIN_MESSAGE);
        } else {

         /*if the it was earsed from the position set the positon to blank and 
          * also check which postion it was erased so you can update it in the BiG array*/
         gridButtons3[x][y].setText(" ");
         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          Row_Array[x][y + 6] = "0";
         }
        }//end of check
        /*if the button pressed was for help display the candidate list*/
       } else if ((HelpButton[num_To_insert].getText().equals("?"))) {

        ArrayList<String> cannot_List = new ArrayList<String>();  //Array list for cannot condidate list
        ArrayList<String> cand_List = new ArrayList<String>();   //array list for candidate list
        
        //if cell is empty
        if (gridButtons3[x][y].getText().equals(" ")) {
         /*if the button pressed was position 00, 01, 02 ... in the gird
          * create the candidate list*/

         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          /*call the function which returns the arrayList of candidate list*/
          cand_List = CandidateList(x, y + 6, cannot_List);

          /* Also chech the Grid if you can remove elemts from the Candidate List */
          ArrayList<String> SubGridVal = new ArrayList<String>();
          for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
            SubGridVal.add(gridButtons3[i][j].getText());
           }
          }// end of for
          cand_List.removeAll(SubGridVal); // remove numbers in the grid from the Candidate list
          
          /*Dispplay the candidate list*/
          JOptionPane.showMessageDialog(null,
            "Your candidate list is or candidates you can you are: \n"
              + cand_List.toString(),
            "Candidate List", JOptionPane.PLAIN_MESSAGE);
         }//end of if (x==0 && .....
        }//end of gridbuttons1[x][y]...

       } else {
        // and is some other button was clicked other then X
        // replace the blank with the number clicked
        if (!Array3[x][y].equals(" ")) {
         JOptionPane.showMessageDialog(null,
           "You cannot insert at this positon as it was loaded from a file ", "ERROR",
           JOptionPane.PLAIN_MESSAGE);
        } else {
         System.out.println("The button was clicked in: " + "Grid pos:" + GridPos + " x: "
           + x + " y " + y);

         String number_clicked = HelpButton[num_To_insert].getText();

         // checck the row 1 for Row_Array if its has number_clicked
         // only if the blank space was cliked in subGrid2 in row 0.
         int flag3 = 1;
         int flag3_y = 1, flag3_grid = 1;
         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          flag3 = checkRow(number_clicked, 1, x);
          flag3_y = checkCol(number_clicked, 1, y + 6);
         }
         
         /*check for duplicates in the subGrids*/
         flag3_grid = checkGrid(number_clicked,gridButtons3,1);

         /*if all the flags are true only then insert it in the Gird*/
         if (flag3 == 1 && flag3_y == 1 && flag3_grid == 1) {
          gridButtons3[x][y].setText(HelpButton[num_To_insert].getText());
          checkWon();
          if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
            || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
            || x == 2 && y == 1 || x == 2 && y == 2) {
           Row_Array[x][y + 6] = HelpButton[num_To_insert].getText();
          }

         } else {
          JOptionPane.showMessageDialog(null,
            "Error: This button has already been placed in this row, column or grid! \n",
            " ", JOptionPane.PLAIN_MESSAGE);
         }//end of else
        }//end of else
       }//if big else
      }//end of temp.equals
     } else if (GridPos == 3) { // if the Sub Grid Position is THREE
      if (temp.equals(gridButtons4[x][y])) {

       /* If X was Pressed to earse the position*/
       if ((HelpButton[num_To_insert].getText().equals("X"))) {

        /* Notify the USer you are on an Earse Mode */
        JOptionPane.showMessageDialog(null,
          "You are on a Earse mode, you can erase as many number you want\n"
            + "but you can erase only the numbers you inserted\n"
            + "You will be notified once the EARSE MODE is OFF ",
          "ERASE MODE ON", JOptionPane.PLAIN_MESSAGE);

        // if X was pressses and it was not eqaul to blank at beginning
        // display an error that its cannot be erased becuase its was loaded from file
        if (!Array4[x][y].equals(" ")) {
         JOptionPane.showMessageDialog(null,
           "You cannot Earse this positon as it was loaded from a file ", "ERROR",
           JOptionPane.PLAIN_MESSAGE);
        } else {

         /*if the it was earsed from the position set the positon to blank and 
          * also check which postion it was erased so you can update it in the BiG array*/
         gridButtons4[x][y].setText(" ");

         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          Row_Array[x + 3][y] = "0";
         }
        }//end of check
        /*if the button pressed was for help display the candidate list*/
       } else if ((HelpButton[num_To_insert].getText().equals("?"))) {

        ArrayList<String> cannot_List = new ArrayList<String>();  //Array list for cannot condidate list
        ArrayList<String> cand_List = new ArrayList<String>();   //array list for candidate list
        
        //if cell is empty
        if (gridButtons4[x][y].getText().equals(" ")) {
         
         /*if the button pressed was position 00, 01, 02 ... in the gird
          * create the candidate list*/
         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          
          /*call the function which returns the arrayList of candidate list*/
          cand_List = CandidateList(x + 3, y, cannot_List);

          /* Also chech the Grid if you can remove elemts from the Candidate List */
          ArrayList<String> SubGridVal = new ArrayList<String>();
          for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
            SubGridVal.add(gridButtons4[i][j].getText());
           }
          }// end of for
          cand_List.removeAll(SubGridVal); // remove numbers in the grid from the Candidate list
          
          /*Dispplay the candidate list*/
          JOptionPane.showMessageDialog(null,
            "Your candidate list is or candidates you can you are: \n"
              + cand_List.toString(),
            "Candidate List", JOptionPane.PLAIN_MESSAGE);
         }//end of if (x==0 && .....
        }//end of gridbuttons1[x][y]...

       } else {
        // and is some other button was clicked other then X
        // replace the blank with the number clicked
        if (!Array4[x][y].equals(" ")) {
         JOptionPane.showMessageDialog(null,
           "You cannot insert at this positon as it was loaded from a file ", "ERROR",
           JOptionPane.PLAIN_MESSAGE);
        } else {
         System.out.println("The button was clicked in: " + "Grid pos:" + GridPos + " x: "
           + x + " y " + y);

         String number_clicked = HelpButton[num_To_insert].getText();

         // checck the row 1 for Row_Array if its has number_clicked
         // only if the blank space was cliked in subGrid2 in row 0.
         int flag4 = 1;
         int flag4_y = 1, flag4_grid = 1;
         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          flag4 = checkRow(number_clicked, 1, x + 3);
          flag4_y = checkCol(number_clicked, 1, y);
         }

         /*check for duplicates in the subGrids*/
         flag4_grid = checkGrid(number_clicked,gridButtons4,1);

         if (flag4 == 1 && flag4_y == 1 && flag4_grid == 1) {
          gridButtons4[x][y].setText(HelpButton[num_To_insert].getText());
          checkWon();
          if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
            || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
            || x == 2 && y == 1 || x == 2 && y == 2) {
           Row_Array[x + 3][y] = HelpButton[num_To_insert].getText();
          }
         } else {
          JOptionPane.showMessageDialog(null,
            "Error: This button has already been placed in this row, column or grid! \n",
            " ", JOptionPane.PLAIN_MESSAGE);
         } //end of else

        }//end of else
       }//if big else
      }//end of temp.equals
     } else if (GridPos == 4) { //// if the Sub Grid Position is FOUR
      if (temp.equals(gridButtons5[x][y])) {

       /* If X was Pressed to earse the position*/
       if ((HelpButton[num_To_insert].getText().equals("X"))) {

        /* Notify the USer you are on an Earse Mode */
        JOptionPane.showMessageDialog(null,
          "You are on a Earse mode, you can erase as many number you want\n"
            + "but you can erase only the numbers you inserted\n"
            + "You will be notified once the EARSE MODE is OFF ",
          "ERASE MODE ON", JOptionPane.PLAIN_MESSAGE);

        // if X was pressses and it was not eqaul to blank at beginning
        // display an error that its cannot be erased becuase its was loaded from file
        if (!Array5[x][y].equals(" ")) {
         JOptionPane.showMessageDialog(null,
           "You cannot Earse this positon as it was loaded from a file ", "ERROR",
           JOptionPane.PLAIN_MESSAGE);
        } else {

         /*if the it was earsed from the position set the positon to blank and 
          * also check which postion it was erased so you can update it in the BiG array*/
         gridButtons5[x][y].setText(" ");

         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          Row_Array[x + 3][y + 3] = "0";
         }
        }//end of check
        /*if the button pressed was for help display the candidate list*/
       } else if ((HelpButton[num_To_insert].getText().equals("?"))) {

        ArrayList<String> cannot_List = new ArrayList<String>();  //Array list for cannot condidate list
        ArrayList<String> cand_List = new ArrayList<String>();   //array list for candidate list
        
        //if cell is empty
        if (gridButtons5[x][y].getText().equals(" ")) {

         /*if the button pressed was position 00, 01, 02 ... in the gird
          * create the candidate list*/
         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          
          /*call the function which returns the arrayList of candidate list*/
          cand_List = CandidateList(x + 3, y + 3, cannot_List);

          /* Also chech the Grid if you can remove elemts from the Candidate List */
          ArrayList<String> SubGridVal = new ArrayList<String>();
          for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
            SubGridVal.add(gridButtons5[i][j].getText());
           }
          }//end of for
          cand_List.removeAll(SubGridVal); // remove numbers in the grid from the Candidate list
          
          /*Dispplay the candidate list*/
          JOptionPane.showMessageDialog(null,
            "Your candidate list is or candidates you can you are: \n"
              + cand_List.toString(),
            "Candidate List", JOptionPane.PLAIN_MESSAGE);
         }
         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          cand_List = CandidateList(x + 3, y + 3, cannot_List);
          JOptionPane.showMessageDialog(null,
            "Your candidate list is or candidates you can you are: \n"
              + cand_List.toString(),
            "Candidate List", JOptionPane.PLAIN_MESSAGE);
         }//end of if (x==0 && .....
        }//end of gridbuttons1[x][y]...

       } else {
        // and is some other button was clicked other then X
        // replace the blank with the number clicked
        if (!Array5[x][y].equals(" ")) {
         JOptionPane.showMessageDialog(null,
           "You cannot insert at this positon as it was loaded from a file ", "ERROR",
           JOptionPane.PLAIN_MESSAGE);
        } else {
         System.out.println("The button was clicked in: " + "Grid pos:" + GridPos + " x: "
           + x + " y " + y);
         String number_clicked = HelpButton[num_To_insert].getText();

         // checck the row 1 for Row_Array if its has number_clicked
         // only if the blank space was cliked in subGrid2 in row 0.
         int flag5 = 1;
         int flag5_y = 1, flag5_grid = 1;
         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          flag5 = checkRow(number_clicked, 1, x + 3);
          flag5_y = checkCol(number_clicked, 1, y + 3);
         }

         /*check for duplicates in the subGrids*/
         flag5_grid = checkGrid(number_clicked,gridButtons5,1);


         /*if all the flags are true only then insert it in the Gird*/
         if (flag5 == 1 && flag5_y == 1 && flag5_grid == 1) {
          gridButtons5[x][y].setText(HelpButton[num_To_insert].getText());
          checkWon();
          if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
            || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
            || x == 2 && y == 1 || x == 2 && y == 2) {
           Row_Array[x + 3][y + 3] = HelpButton[num_To_insert].getText();
          }
         } else {
          JOptionPane.showMessageDialog(null,
            "Error: This button has already been placed in this row, column or grid! \n",
            " ", JOptionPane.PLAIN_MESSAGE);
         }//end of else

        } //end of else
       }// if big else
      }//end of temp.equals..
     } else if (GridPos == 5) { // if the Sub Grid Position is FIVE
      if (temp.equals(gridButtons6[x][y])) {

       /* If X was Pressed to earse the position*/
       if ((HelpButton[num_To_insert].getText().equals("X"))) {

        /* Notify the USer you are on an Earse Mode */
        JOptionPane.showMessageDialog(null,
          "You are on a Earse mode, you can erase as many number you want\n"
            + "but you can erase only the numbers you inserted\n"
            + "You will be notified once the EARSE MODE is OFF ",
          "ERASE MODE ON", JOptionPane.PLAIN_MESSAGE);

        // if X was pressses and it was not eqaul to blank at beginning
        // display an error that its cannot be erased becuase its was loaded from file
        if (!Array6[x][y].equals(" ")) {
         JOptionPane.showMessageDialog(null,
           "You cannot Earse this positon as it was loaded from a file ", "ERROR",
           JOptionPane.PLAIN_MESSAGE);
        } else {

         /*if the it was earsed from the position set the positon to blank and 
          * also check which postion it was erased so you can update it in the BiG array*/
         gridButtons6[x][y].setText(" ");

         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          Row_Array[x + 3][y + 6] = "0";
         }
        }//end of check
        /*if the button pressed was for help display the candidate list*/
       } else if ((HelpButton[num_To_insert].getText().equals("?"))) {

        ArrayList<String> cannot_List = new ArrayList<String>();  //Array list for cannot condidate list
        ArrayList<String> cand_List = new ArrayList<String>();   //array list for candidate list
        
        //if cell is empty
        if (gridButtons6[x][y].getText().equals(" ")) {

         /*if the button pressed was position 00, 01, 02 ... in the gird
          * create the candidate list*/

         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          
          /*call the function which returns the arrayList of candidate list*/
          cand_List = CandidateList(x + 3, y + 6, cannot_List);

          /* Also chech the Grid if you can remove elemts from the Candidate List */
          ArrayList<String> SubGridVal = new ArrayList<String>();
          for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
            SubGridVal.add(gridButtons6[i][j].getText());
           }
          } //end of for
          cand_List.removeAll(SubGridVal); // remove numbers in the grid from the Candidate list
          
          /*Dispplay the candidate list*/
          JOptionPane.showMessageDialog(null,
            "Your candidate list is or candidates you can you are: \n"
              + cand_List.toString(),
            "Candidate List", JOptionPane.PLAIN_MESSAGE);
         }//end of if (x==0 && .....
        }//end of gridbuttons1[x][y]...

       } else {
        // and is some other button was clicked other then X
        // replace the blank with the number clicked
        if (!Array6[x][y].equals(" ")) {
         JOptionPane.showMessageDialog(null,
           "You cannot insert at this positon as it was loaded from a file ", "ERROR",
           JOptionPane.PLAIN_MESSAGE);
        } else {
         System.out.println("The button was clicked in: " + "Grid pos:" + GridPos + " x: "
           + x + " y " + y);
         String number_clicked = HelpButton[num_To_insert].getText();

         // checck the row 1 for Row_Array if its has number_clicked
         // only if the blank space was cliked in subGrid2 in row 0.
         int flag6 = 1;
         int flag6_y = 1, flag6_grid = 1;
         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          flag6 = checkRow(number_clicked, 1, x + 3);
          flag6_y = checkCol(number_clicked, 1, y + 6);
         }

         /*check for duplicates in the subGrids*/
         flag6_grid = checkGrid(number_clicked,gridButtons6,1);

         /*if all the flags are true only then insert it in the Gird*/
         if (flag6 == 1 && flag6_y == 1 && flag6_grid == 1) {
          gridButtons6[x][y].setText(HelpButton[num_To_insert].getText());
          checkWon();
          if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
            || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
            || x == 2 && y == 1 || x == 2 && y == 2) {
           Row_Array[x + 3][y + 6] = HelpButton[num_To_insert].getText();
          }
         } else {
          JOptionPane.showMessageDialog(null,
            "Error: This button has already been placed in this row, column or grid! \n",
            " ", JOptionPane.PLAIN_MESSAGE);
         } //end of else
        } //end of else
       } //if big else
      } //end of temp.equals..
     } else if (GridPos == 6) { // if the Sub Grid Position is SIX
      if (temp.equals(gridButtons7[x][y])) {

       /* If X was Pressed to earse the position*/
       if ((HelpButton[num_To_insert].getText().equals("X"))) {

        /* Notify the USer you are on an Earse Mode */
        JOptionPane.showMessageDialog(null,
          "You are on a Earse mode, you can erase as many number you want\n"
            + "but you can erase only the numbers you inserted\n"
            + "You will be notified once the EARSE MODE is OFF ",
          "ERASE MODE ON", JOptionPane.PLAIN_MESSAGE);

        // if X was pressses and it was not eqaul to blank at beginning
        // display an error that its cannot be erased becuase its was loaded from file
        if (!Array7[x][y].equals(" ")) {
         JOptionPane.showMessageDialog(null,
           "You cannot Earse this positon as it was loaded from a file ", "ERROR",
           JOptionPane.PLAIN_MESSAGE);
        } else {

         /*if the it was earsed from the position set the positon to blank and 
          * also check which postion it was erased so you can update it in the BiG array*/
         gridButtons7[x][y].setText(" ");

         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          Row_Array[x + 6][y] = "0";
         }
        }//end of check
        /*if the button pressed was for help display the candidate list*/
       } else if ((HelpButton[num_To_insert].getText().equals("?"))) {

        ArrayList<String> cannot_List = new ArrayList<String>();  //Array list for cannot condidate list
        ArrayList<String> cand_List = new ArrayList<String>();   //array list for candidate list
        
        //if cell is empty
        if (gridButtons7[x][y].getText().equals(" ")) {
         /*if the button pressed was position 00, 01, 02 ... in the gird
          * create the candidate list*/

         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          
          /*call the function which returns the arrayList of candidate list*/
          cand_List = CandidateList(x + 6, y, cannot_List);

          /* Also chech the Grid if you can remove elemts from the Candidate List */
          ArrayList<String> SubGridVal = new ArrayList<String>();
          for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
            SubGridVal.add(gridButtons7[i][j].getText());
           }
          } //end of for
          cand_List.removeAll(SubGridVal); // remove numbers in the grid from the Candidate list
          
          /*Dispplay the candidate list*/
          JOptionPane.showMessageDialog(null,
            "Your candidate list is or candidates you can you are: \n"
              + cand_List.toString(),
            "Candidate List", JOptionPane.PLAIN_MESSAGE);
         }//end of if (x==0 && .....
        }//end of gridbuttons1[x][y]...
       } else {
        // and is some other button was clicked other then X
        // replace the blank with the number clicked
        if (!Array7[x][y].equals(" ")) {
         JOptionPane.showMessageDialog(null,
           "You cannot insert at this positon as it was loaded from a file ", "ERROR",
           JOptionPane.PLAIN_MESSAGE);
        } else {
         System.out.println("The button was clicked in: " + "Grid pos:" + GridPos + " x: "
           + x + " y " + y);
         String number_clicked = HelpButton[num_To_insert].getText();

         // checck the row 1 for Row_Array if its has number_clicked
         // only if the blank space was cliked in subGrid2 in row 0.
         int flag7 = 1;
         int flag7_y = 1, flag7_grid = 1;
         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          flag7 = checkRow(number_clicked, 1, x + 6);
          flag7_y = checkCol(number_clicked, 1, y);
         }

         /*check for duplicates in the subGrids*/
         flag7_grid = checkGrid(number_clicked,gridButtons7,1);


         /*if all the flags are true only then insert it in the Gird*/
         if (flag7 == 1 && flag7_y == 1 && flag7_grid == 1) {
          gridButtons7[x][y].setText(HelpButton[num_To_insert].getText());
          checkWon();
          if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
            || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
            || x == 2 && y == 1 || x == 2 && y == 2) {
           Row_Array[x + 6][y] = HelpButton[num_To_insert].getText();
          }

         } else {
          JOptionPane.showMessageDialog(null,
            "Error: This button has already been placed in this row, column or grid! \n",
            " ", JOptionPane.PLAIN_MESSAGE);
         } //end of else

        } //end of else
       } //if big else
      }//end of temp.equals..
     } else if (GridPos == 7) { // if the Sub Grid Position is SEVEN
      if (temp.equals(gridButtons8[x][y])) {

       /* If X was Pressed to earse the position*/
       if ((HelpButton[num_To_insert].getText().equals("X"))) {

        /* Notify the USer you are on an Earse Mode */
        JOptionPane.showMessageDialog(null,
          "You are on a Earse mode, you can erase as many number you want\n"
            + "but you can erase only the numbers you inserted\n"
            + "You will be notified once the EARSE MODE is OFF ",
          "ERASE MODE ON", JOptionPane.PLAIN_MESSAGE);

        // if X was pressses and it was not eqaul to blank at beginning
        // display an error that its cannot be erased becuase its was loaded from file
        if (!Array8[x][y].equals(" ")) {
         JOptionPane.showMessageDialog(null,
           "You cannot Earse this positon as it was loaded from a file ", "ERROR",
           JOptionPane.PLAIN_MESSAGE);
        } else {

         /*if the it was earsed from the position set the positon to blank and 
          * also check which postion it was erased so you can update it in the BiG array*/
         gridButtons8[x][y].setText(" ");

         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          Row_Array[x + 6][y + 3] = "0";
         }
        }//end of check
        /*if the button pressed was for help display the candidate list*/
       } else if ((HelpButton[num_To_insert].getText().equals("?"))) {

        ArrayList<String> cannot_List = new ArrayList<String>();  //Array list for cannot condidate list
        ArrayList<String> cand_List = new ArrayList<String>();   //array list for candidate list
        
        //if cell is empty
        if (gridButtons8[x][y].getText().equals(" ")) {

         /*if the button pressed was position 00, 01, 02 ... in the gird
          * create the candidate list*/

         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          
          /*call the function which returns the arrayList of candidate list*/
          cand_List = CandidateList(x + 6, y + 3, cannot_List);

          /* Also chech the Grid if you can remove elemts from the Candidate List */
          ArrayList<String> SubGridVal = new ArrayList<String>();
          for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
            SubGridVal.add(gridButtons8[i][j].getText());
           }
          } //end of for
          cand_List.removeAll(SubGridVal); // remove numbers in the grid from the Candidate list
          
          /*Dispplay the candidate list*/
          JOptionPane.showMessageDialog(null,
            "Your candidate list is or candidates you can you are: \n"
              + cand_List.toString(),
            "Candidate List", JOptionPane.PLAIN_MESSAGE);
         }//end of if (x==0 && .....
        }//end of gridbuttons1[x][y]...

       } else {  // if the Sub Grid Position is EIGHT
        // and is some other button was clicked other then X
        // replace the blank with the number clicked
        if (!Array8[x][y].equals(" ")) {
         JOptionPane.showMessageDialog(null,
           "You cannot insert at this positon as it was loaded from a file ", "ERROR",
           JOptionPane.PLAIN_MESSAGE);
        } else {
         System.out.println("The button was clicked in: " + "Grid pos:" + GridPos + " x: "
           + x + " y " + y);
         String number_clicked = HelpButton[num_To_insert].getText();

         // checck the row 1 for Row_Array if its has number_clicked
         // only if the blank space was cliked in subGrid2 in row 0.
         int flag8 = 1;
         int flag8_y = 1, flag8_grid = 1;
         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          flag8 = checkRow(number_clicked, 1, x + 6);
          flag8_y = checkCol(number_clicked, 1, y + 3);
         }

         /*check for duplicates in the subGrids*/
         flag8_grid = checkGrid(number_clicked,gridButtons8,1);

         /*if all the flags are true only then insert it in the Gird*/
         if (flag8 == 1 && flag8_y == 1 && flag8_grid == 1) {
          gridButtons8[x][y].setText(HelpButton[num_To_insert].getText());
          checkWon();
          if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
            || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
            || x == 2 && y == 1 || x == 2 && y == 2) {
           Row_Array[x + 6][y + 3] = HelpButton[num_To_insert].getText();
          }
         } else {
          JOptionPane.showMessageDialog(null,
            "Error: This button has already been placed in this row, column or grid! \n",
            " ", JOptionPane.PLAIN_MESSAGE);
         } //end of else
        } //end of else
       } //if big else
      } //end of temp.equals..
     } else if (GridPos == 8) {
      if (temp.equals(gridButtons9[x][y])) {

       /* If X was Pressed to earse the position*/
       if ((HelpButton[num_To_insert].getText().equals("X"))) {

        /* Notify the USer you are on an Earse Mode */
        JOptionPane.showMessageDialog(null,
          "You are on a Earse mode, you can erase as many number you want\n"
            + "but you can erase only the numbers you inserted\n"
            + "You will be notified once the EARSE MODE is OFF ",
          "ERASE MODE ON", JOptionPane.PLAIN_MESSAGE);

        // if X was pressses and it was not eqaul to blank at beginning
        // display an error that its cannot be erased becuase its was loaded from file
        if (!Array9[x][y].equals(" ")) {
         JOptionPane.showMessageDialog(null,
           "You cannot Earse this positon as it was loaded from a file ", "ERROR",
           JOptionPane.PLAIN_MESSAGE);
        } else {

         /*if the it was earsed from the position set the positon to blank and 
          * also check which postion it was erased so you can update it in the BiG array*/
         gridButtons9[x][y].setText(" ");

         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          Row_Array[x + 6][y + 6] = "0";
         }
        }//end of check
        /*if the button pressed was for help display the candidate list*/
       } else if ((HelpButton[num_To_insert].getText().equals("?"))) {

        ArrayList<String> cannot_List = new ArrayList<String>();  //Array list for cannot condidate list
        ArrayList<String> cand_List = new ArrayList<String>();   //array list for candidate list
        
        //if cell is empty
        if (gridButtons9[x][y].getText().equals(" ")) {
         /*if the button pressed was position 00, 01, 02 ... in the gird
          * create the candidate list*/

         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          cand_List = CandidateList(x + 6, y + 6, cannot_List);

          /* Also chech the Grid if you can remove elemts from the Candidate List */
          ArrayList<String> SubGridVal = new ArrayList<String>();
          for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
            SubGridVal.add(gridButtons9[i][j].getText());
           }
          } //end of for
          cand_List.removeAll(SubGridVal); // remove numbers in the grid from the Candidate list
          
          /*Dispplay the candidate list*/
          JOptionPane.showMessageDialog(null,
            "Your candidate list is or candidates you can you are: \n"
              + cand_List.toString(),
            "Candidate List", JOptionPane.PLAIN_MESSAGE);
         }//end of if (x==0 && .....
        }//end of gridbuttons1[x][y]...
       } else {
        // and is some other button was clicked other then X
        // replace the blank with the number clicked
        if (!Array9[x][y].equals(" ")) {
         JOptionPane.showMessageDialog(null,
           "You cannot insert at this positon as it was loaded from a file ", "ERROR",
           JOptionPane.PLAIN_MESSAGE);
        } else {
         System.out.println("The button was clicked in: " + "Grid pos:" + GridPos + " x: "
           + x + " y " + y);
         String number_clicked = HelpButton[num_To_insert].getText();

         // checck the row 1 for Row_Array if its has number_clicked
         // only if the blank space was cliked in subGrid2 in row 0.
         int flag9 = 1;
         int flag9_y = 1, flag9_grid = 1;
         if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
           || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
           || x == 2 && y == 1 || x == 2 && y == 2) {
          flag9 = checkRow(number_clicked, 1, x + 6);
          flag9_y = checkCol(number_clicked, 1, y + 6);
         }

         /*check for duplicates in the subGrids*/
         flag9_grid = checkGrid(number_clicked,gridButtons9,1);

         /*Checks if the Flags are all true that is the row, col, subGrids does not contain dubplictaes else throw a error message*/
         if (flag9 == 1 && flag9_y == 1 && flag9_grid == 1) {
          gridButtons9[x][y].setText(HelpButton[num_To_insert].getText());
          checkWon();
          if (x == 0 && y == 0 || x == 0 && y == 1 || x == 0 && y == 2 || x == 1 && y == 0
            || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 0
            || x == 2 && y == 1 || x == 2 && y == 2) {
           Row_Array[x + 6][y + 6] = HelpButton[num_To_insert].getText();
          }

         } else {
          JOptionPane.showMessageDialog(null,
            "Error: This button has already been placed in this row, column or grid! \n",
            " ", JOptionPane.PLAIN_MESSAGE);
         }
        }
       }//end of else
      }// end of if 
     }// end of elseif (GridPos == 8)
    }//end of the third For loop
   }//end of second for loop
  }//end of BIG for the SUBGRIDS
 }//end of ActionListner

 //*****************************************************************************************************************************************************************
 /*
  *  main method
  */
 public static void main(String[] args) {
  Sudoku application1 = new Sudoku();
  application1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 }// end of main

 //*****************************************************************************************************************************************************************
 /*
  * checks for the row col if we have a simialr number
  * returns flase if there is a same number in the row.
  */
 public static int checkRow(String number, int flag, int x) {
  for (int i = 0; i < 9; i++) {
   if (Row_Array[x][i].equals(number)) {
    System.out.println("Row: " + x + " Col: " + i);
    flag = 0;
   }
  }
  return flag;
 }//end of function

 //*****************************************************************************************************************************************************************
 /*
  * checks for the col if we have a similar number
  * returns flase if there is a same number in the col.
  */
 public static int checkCol(String number, int flag, int y) {
  for (int i = 0; i < 9; i++) {
   if (Row_Array[i][y].equals(number)) {
    System.out.println("Row: " + i + " Col: " + y);
    flag = 0;
   }
  }
  return flag;
 }//end of function

 //*****************************************************************************************************************************************
 // checks if the user met the winning condition or not with the help of the updates Row_Array
 public static void checkWon(){
   int m, n, winVal = 0;
   for (m = 0; m < 9; m++){
     for (n = 0; n < 9; n++){
       if (Sudoku.Row_Array[m][n] != " "){
          winVal++;
       }
     }
   }
   if (winVal == 81){
     //pop up the winning box
      JOptionPane.showMessageDialog(null,
          "You won the game!!",
          "CONGRATULATIONS", JOptionPane.PLAIN_MESSAGE);
   }
 }
 //*****************************************************************************************************************************************************************
 /*
  * Creates a Arraylist for the candidate list accordint to row
  * and colmns and removes the values which are not needed
  * and then returns the candidate list.
  */
 public static ArrayList<String> CandidateList(int x, int y, ArrayList<String> cannot_List) {

  for (int i = 0; i < 9; i++) {
   if (!Row_Array[x][i].equals("0")) {
    cannot_List.add(Row_Array[x][i]);
   }
  }//end of for
  
  
  for (int j = 0; j < 9; j++) {
   if (!Row_Array[j][y].equals("0")) {
    cannot_List.add(Row_Array[j][y]);
   }
  }///end of for

  ArrayList<String> num = new ArrayList<String>();
  for (int i = 0; i < 9; i++) {
   num.add(Integer.toString(i + 1));
  }//end of for

  num.removeAll(cannot_List);

  return num;
 }//end of function
 
 //*****************************************************************************************************************************************************************
 /*
  * Check the The Grid with Multiple values if true then set the value to True
  * and throw an error
  */
 public static int checkGrid(String number, JButton [][]SubGrids,int flag) {
  flag = 1;
  
  
  for (int i = 0; i < 3; i++) {
   for (int k = 0; k < 3; k++) {
    if (SubGrids[i][k].getText().equals(number)){
     flag = 0;
    } // end of if
   } // end of for
  } // end of for

  return flag;
 }//end of function
}//end of the Sudoku class
