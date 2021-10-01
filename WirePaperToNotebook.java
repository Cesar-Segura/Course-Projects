//Work on cost and time to produce 

public class WirePaperToNotebook extends Machines{
    //produce Notebooks based on supply of Paper and Wire
    protected int numNotebooks; 
    protected double wire;
    protected double notebookPaper; 
    protected int wireForNotebooks;
    //protected double leftoverWire;
    //protected double leftoverPaper;

    //     public void placeOrder(int steelPerWire, int numSteel, ManufacturingSimulation simulation, DrawSimulation drawing) { 
    //     // rounds order size up to the nearest 50, since steel can only be ordered in groups of 50 units
    //     int steelOrderSize = (steelNeeded + 49) / 50 * 50;

    //     // updates numSteel (raw material available) and totalSteel (all purchased steel)
    //     simulation.setNumSteel(numSteel + steelOrderSize);
    //     simulation.incrementTotalSteel(simulation.totalSteel + steelOrderSize);
    // }

    public int manufactureNotebook(int wirePerNotebook, int paperPerNotebook, int wireForNotebooks, int numPaper) {
        numNotebooks = 0; 
        wire = ((double) wireForNotebooks) / ((double) wirePerNotebook); 
        notebookPaper = ((double) numPaper) / ((double) paperPerNotebook); 

        // if (numPaper < paperPerNotebook * orderSizeNotebook) {
        //     orders++;
        //     placeOrder(steelPerWire, numSteel, simulation, drawing);
        // }

        //check to see if there is an uneven amount of wire per notebook or paper per notebook
        //if there is enough wire for 10 notebooks but only enough paper to make 9 notebooks, 
        //there will only be 9 notebooks made and 1 wire leftover 
        // however many wires i need for a hanger to go to the hanger machine, and then however
        // many wires i need for notebooks to go there for sharing
        // draw one hanger then one
        // wirePerHanger 
        if (wire != notebookPaper) {
            if (wire < notebookPaper) {
                numNotebooks = (int) wire; 
            } else {
                numNotebooks = (int) notebookPaper; 
            }
        } else {
            numNotebooks = (int) notebookPaper; 
        }
        return numNotebooks;
    }

    //Setter
    public int notebooksManufactured() {
        return numNotebooks; 
    }
    
}  
