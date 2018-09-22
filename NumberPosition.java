//
// Group Members: Foram Patel, Harshil Patel, Tejash Contractor
// Number Position class

public class NumberPosition {
 
 // declare the variables
 public String row;
 public String col;
 public String number;
 
 /*get which grid we want to go*/
 public String getRows() {
  return this.row;
 }
 
 /*set grid number*/
 public void setRow(String row) {
  this.row = row;
 }
 
 
 /*get which subgrid position we want to go*/
 public String getCol() {
  return this.col;
 }
 
 /*set subgrid pos number*/
 public void setCol(String col) {
  this.col = col;
 }
 
 /*get which number to insert*/
 public String getNumber() {
  return this.number;
 }
 
 /*set number*/
 public void setNumber(String number) {
  this.number = number;
 }
 
}
//end of the class
