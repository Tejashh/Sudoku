//
// Group Members: Foram Patel, Harshil Patel, Tejash Contractor
// GridLayout class

//include the neccessary libraries
import java.awt.GridLayout;

public class MyGridLayout extends GridLayout {

 private static final long serialVersionUID = 1L;

 GridLayout grid;
 //create the 3 x 3  grids 
 public static GridLayout getLayout() {

  return (new GridLayout(3, 3, 3, 3));
 }
 
 public static GridLayout getLayout2() {

  return (new GridLayout(11,1,3,3));
 }
 
 //set the grid layout
 public void setLayout(GridLayout tempGrid) {
  
  grid = tempGrid;
 }
} //end of the class