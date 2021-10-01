//************************************************************************
// File: Products.java     Assignment: Final Project
//
//
// Class: Products
// Dependencies: none
//
//  This class stores the methods and objects for the three products
//  that we are producing. This is a parent class for product child 
//  classes and will serve as the default.
// 
//************************************************************************
 
 
public abstract class Products {
    protected int numFinishedProduct;
    protected int initialAmount;
    protected int numSteel;
    protected double cost; 


    public Products() {
        numFinishedProduct = 0;
        initialAmount = 0;
        numSteel = 0;
    }

    // increase or decrease the total amount of product variable
    public void incrementProducts (int productIncrement) {
        numFinishedProduct += productIncrement;
    }

    public void initRawMaterials(int steel) {
        numSteel = steel;
    }

    //Two methods that subclasses can implement themselves
    public void manufacture(){};
    public void setProductInfo(){}; 
   
    //Returns the amount of time production took
    public void time() {
    }
  
    //Returns the cost of producing items
    public void cost() {
    }
  
    //Returns number of items produced
    public int numProduced() {
        return 0;
    }
 }