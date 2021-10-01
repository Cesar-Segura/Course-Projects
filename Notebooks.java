//  This class serves as a child class to Products.java and provides 
//  more detail into the manufacturing process for notebooks in particular.

public class Notebooks extends Products {

    protected final double TIMEPERNOTEBOOK = 1.5; // time in minutes 
    protected int timeCreate; 
    protected int numFinishedProduct;
    protected int numPaper;
    protected int initialAmount; 
    protected int paperPerNotebook; 
    protected int totalSheetsPaper; 
    protected double costPerPaper; 
    protected int wirePerNotebook; 
    protected double totalCost; 
    protected double totalTime; 
    protected int wireForNotebooks;
    protected double costPaper;
    protected int notebookDrawn;
    protected int hangerDrawn;
    protected final int BREAKDOWNCOST;
    protected final int BREAKDOWNTIME;
    protected int breakdownTime;
    protected int breakdowns;
    protected int probability;
    protected int delayTime;
    protected int delayCost;
    
    public Notebooks(int wirePerNotebook, int numPaper, int paperPerNotebook, double costPaper , int wireForNotebooks, int probability) {
        // It takes 120 seconds to produce one notebook.
        this.wirePerNotebook = wirePerNotebook;
        this.numPaper = numPaper;
        this.paperPerNotebook = paperPerNotebook;
        this.costPaper = costPaper;
        this.wireForNotebooks = wireForNotebooks;
        this.probability = probability;
        timeCreate = 120;
        initialAmount = 0;
        numFinishedProduct = 0; 
        notebookDrawn = 0;
        hangerDrawn = 0;
        BREAKDOWNCOST = 1000;
        BREAKDOWNTIME = 120;
    }

    public int numFinishedProduct() {
        return numFinishedProduct;
    }

    public void manufacture(int paperPerNotebook, int totalPaper, ManufacturingSimulation simulation, DrawSimulation drawing, Hangers hanger){
        // Create a machine instance and convert all of the raw material into product
        //take paper as raw input into notebook 
        if (Math.random() < probability/100) {
            breakdowns++;
        }
        
        delayTime = breakdowns * BREAKDOWNTIME;
        simulation.setDelayTimeNotebookMachine(delayTime);
        delayCost = breakdowns * BREAKDOWNCOST;
        simulation.setDelayNotebookMachineCost(delayCost);
        WirePaperToNotebook paperMachine = new WirePaperToNotebook(); 
        numFinishedProduct = paperMachine.manufactureNotebook(wirePerNotebook, paperPerNotebook, wireForNotebooks, numPaper);

        for(int j = 1; j <= numFinishedProduct + hanger.numFinishedProduct(); j++) {                                                     
            if (j % 2 == 1) {
                if (hangerDrawn < hanger.numFinishedProduct()) {
                    drawing.makeHanger(simulation.WIREPERHANGER);
                    hangerDrawn++;
                }
            }

            else {
                if (notebookDrawn < numFinishedProduct) {
                    drawing.makeNotebook(simulation.WIREPERNOTEBOOK, simulation.PAPERPERNOTEBOOK);
                    notebookDrawn++;
                    System.out.println(numFinishedProduct + hanger.numFinishedProduct());
                }
            }
        }
    }
    

    public double calculateCost() {
        totalCost = costPaper * paperPerNotebook *  numFinishedProduct; 
        return totalCost; 
    }

    //  Time taken to produce hangers, excluding possible time delays in machine errors  
    public double calculateTime() {
        totalTime = TIMEPERNOTEBOOK * numFinishedProduct; 
        return totalTime;
    }
}
