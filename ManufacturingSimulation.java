//************************************************************************
// File: ManufacturingSimulation.java         Final Project
//
// Description  :  The main simulation file
//  
// This object stores code for the simulate() method 
// that runs our main program and starts to read in the file.
//
//************************************************************************

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ManufacturingSimulation {
    //  Costs of raw materials and probability of machines breaking 
    private double costSteel;
    private double costPaper; 
    private int probBreak;

    // Raw Materials
    protected int numSteel;
    protected int numPaper; 
    protected int totalSteel; 
    protected int totalPaper; 

    //  Wire Made for Products
    protected int wireForHangers;
    protected int wireForNotebooks;

    // Constants
    protected final int STEELPERWIRE = 1;
    protected final int WIREPERHANGER = 2; 
    protected final int WIREPERNOTEBOOK = 3; 
    protected final int PAPERPERNOTEBOOK = 3;

    //  Orders
    protected int orderSizeHangers;
    protected int orderSizeNotebooks; 

    protected int delayTimeHangers;
    protected int delayTimeNotebooks;
    protected int delayCostHanger;
    protected int delayCostNotebook;

    // Class constructor. Default values
    public ManufacturingSimulation() {
        costPaper = 0;
        costSteel = 0; 
        probBreak = 0;
        numSteel = 0;
    }

    public static void main(String[] args) throws FileNotFoundException {
        //USER SET VARIABLE
        boolean debug = false;

        // Read in the input file
        if (args.length != 1) {
            System.out.println("Error with input");
            System.exit(1);
        }
        // Create a new simulation
        ManufacturingSimulation simulation = new ManufacturingSimulation();
        // Create hanger and notebook
        simulation.getInputs(args[0]);

        // Create a drawing instance
        DrawSimulation drawing = new DrawSimulation(simulation.numSteel, simulation.numPaper);
        drawing.init();

        SteelToWire wireMachine = new SteelToWire(simulation.WIREPERHANGER, simulation.WIREPERNOTEBOOK, simulation.orderSizeHangers, simulation.orderSizeNotebooks);
        wireMachine.manufactureWire(simulation.STEELPERWIRE, simulation.numSteel, simulation, drawing);
        Hangers hanger = new Hangers(simulation.WIREPERHANGER, simulation.wireForHangers, simulation.costSteel, simulation.probBreak);
        Notebooks notebook = new Notebooks(simulation.WIREPERNOTEBOOK, simulation.numPaper, simulation.PAPERPERNOTEBOOK,  simulation.costPaper,  simulation.wireForNotebooks, simulation.probBreak); 

        if(debug){
            simulation.test(hanger, notebook);
        }

        hanger.manufacture(simulation.STEELPERWIRE, simulation.totalSteel, simulation, drawing);
        notebook.manufacture(simulation.PAPERPERNOTEBOOK, simulation.totalPaper, simulation, drawing, hanger);

        //print amount of finished product.
        System.out.print("Number of hangers produced: ");
        System.out.println(hanger.numFinishedProduct());
        System.out.print("Number of notebooks produced: ");
        System.out.println(notebook.numFinishedProduct()); 
        System.out.print("Total cost of steel: ");
        System.out.println(hanger.calculateCost());
        System.out.print("Total cost of paper: ");
        System.out.println(notebook.calculateCost());
        System.out.println("Total cost of breakdowns: " + (simulation.delayCostHanger + simulation.delayCostNotebook));
        System.out.println("Total delay from breakdowns: " + (simulation.delayTimeHangers +simulation.delayTimeNotebooks) + " minutes");
    }

    public void getInputs(String simulationFile) throws FileNotFoundException {
        // getInputs reads in the file and creates an array from the inputs
         System.out.println("Simulating " + simulationFile);

         // Read in the simulation arguments from the file
         File f = new File( simulationFile );
         Scanner s = new Scanner( f );
         init(s);
    }

    public void init(Scanner s){
        // initalizes the manufacturing line by reading the lines in the file 
        // and setting the class variables 
        costSteel = s.nextDouble();
        costPaper = s.nextDouble();
        probBreak = s.nextInt();
        numSteel = s.nextInt();
        numPaper = s.nextInt(); 
        orderSizeHangers = s.nextInt(); 
        orderSizeNotebooks = s.nextInt(); 

        totalSteel = numSteel; 
        totalPaper = numPaper; 
    }

    public void test(Hangers hanger, Notebooks notebook){
        // Prints the output of the text file inputs!
        System.out.println(costSteel);
        System.out.println(probBreak);
        System.out.println(numSteel);
        System.out.println(numPaper); 
        System.out.println(STEELPERWIRE);
        System.out.println(orderSizeHangers);
        System.out.println(orderSizeNotebooks); 
    }

    public void setNumSteel(int numSteel) {
        //updates the amount of steel leftover after creating wire from it
        this.numSteel = numSteel;
    }


    public void incrementTotalSteel(int totalSteel) {
        totalSteel =+ totalSteel;
    }


    public void setWireForHangers(int wireForHangers) {
        this.wireForHangers = wireForHangers;
    }

    public void setWireForNotebooks(int wireForNotebooks) {
        this.wireForNotebooks = wireForNotebooks;
    }

    public int getWireForHangers() {
        return wireForHangers;
    }


    public int wireForNotebooks() {
        return wireForNotebooks;
    }

    public void setDelayTimeHangerMachine(int delayTime) {
        this.delayTimeHangers = delayTime;
    }

    public int getDelayTimeHangerMachine() {
        return delayTimeHangers;
    }

    public void setDelayTimeNotebookMachine(int delayTime) {
        this.delayTimeNotebooks = delayTime;
    }

    public int getDelayTimeNotebookMachine() {
        return delayTimeNotebooks;
    }
    
    public void setDelayHangerMachineCost(int delayCost) {
        this.delayCostHanger = delayCost;
    }

    public int setDelayHangerMachineCost() {
        return delayCostHanger;
    }

    public void setDelayNotebookMachineCost(int delayCost) {
        this.delayCostNotebook = delayCost;
    }

    public int setDelayNotebookMachineCost() {
        return delayCostNotebook;
    }

}