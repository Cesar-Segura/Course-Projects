public class SteelToWire extends Machines{
    protected int wireForHangers;
    protected int wireForNotebooks;
    protected boolean enoughWire;
    protected boolean isWorking;
    protected int orderSizeHangers;
    protected int orderSizeNotebooks;
    protected int wirePerHanger;
    protected int wirePerNotebook;
    protected int orders; // will this be stored back in machines? can i use it there?
    protected int steelNeeded;

    //Constructor
    public SteelToWire(int numWirePerHanger, int numWirePerNotebook, int orderSizeHangers, int orderSizeNotebooks) {    
        this.orderSizeHangers = orderSizeHangers;
        this.orderSizeNotebooks = orderSizeNotebooks;
        wirePerHanger = numWirePerHanger;
        wirePerNotebook = numWirePerNotebook;
        wireForHangers = 0;
        wireForNotebooks = 0;
        enoughWire = false;
    }

    public void placeOrder(int steelPerWire, int numSteel, ManufacturingSimulation simulation, DrawSimulation drawing) { 
        // rounds order size up to the nearest 50, since steel can only be ordered in groups of 50 units
        int steelOrderSize = (steelNeeded + 49) / 50 * 50;
        System.out.println("true");
        //drawing.drawCounters(steelOrderSize + numSteel, simulation.numPaper, wirePerHanger, wirePerHanger, wirePerHanger);
        // updates numSteel (raw material available) and totalSteel (all purchased steel)
        simulation.setNumSteel(numSteel + steelOrderSize);
        simulation.incrementTotalSteel(simulation.totalSteel + steelOrderSize);
    }

    //produce wire based on input
    public void manufactureWire(int steelPerWire, int numSteel, ManufacturingSimulation simulation, DrawSimulation drawing) {
        int wireNeeded = orderSizeHangers * wirePerHanger + orderSizeNotebooks * wirePerNotebook;
        steelNeeded = ((orderSizeHangers * wirePerHanger + orderSizeNotebooks * wirePerNotebook) - (wireForHangers + wireForNotebooks)) * steelPerWire;
        
        // if we dont have enough steel to make all the wire we need
        if (numSteel < steelNeeded) {
            orders++;
            placeOrder(steelPerWire, numSteel, simulation, drawing);
        }
        
        // Split wire produced among hangers and notebooks
        for (int i = 1; i <= steelNeeded; i += steelPerWire) {
            if (enoughWire == false) {
                if ((i % ((wirePerHanger + wirePerNotebook) * steelPerWire)) == 0) {
                    if ((wireForNotebooks / wirePerNotebook) < orderSizeNotebooks) {
                        wireForNotebooks++;
                    }
                    else {
                        wireForHangers++;
                    }
                }
                else if ((i % ((wirePerHanger + wirePerNotebook) * steelPerWire) <= (wirePerHanger * steelPerWire))) {
                    if ((wireForHangers / wirePerHanger) < orderSizeHangers) {
                        wireForHangers++;
                    }
                    else {
                        wireForNotebooks++;
                    }

                }
                else {
                    if ((wireForNotebooks / wirePerNotebook) < orderSizeNotebooks) {
                        wireForNotebooks++;
                    }
                    else {
                        wireForHangers++;
                    }
                }

                if (wireForHangers + wireForNotebooks == wireNeeded) {
                    enoughWire = true;
                }
            }
        }

        // System.out.println("in wire machine wireforhangers " + wireForHangers);
        // System.out.println("wirefornotebooks " + wireForNotebooks);

        int wireProduced = wireForHangers + wireForNotebooks;

        // update wireForHangers and wireForNotebooks in main
        simulation.setWireForHangers(wireForHangers);
        simulation.setWireForNotebooks(wireForNotebooks);
    
        // Subtract from total steel.
        simulation.setNumSteel(numSteel - wireProduced * steelPerWire);

        // draw wire made
        for(int i = 0; i < wireProduced; i++) {
            drawing.makeWire(steelPerWire);
        }
    }
}
