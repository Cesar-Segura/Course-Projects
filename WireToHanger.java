public class WireToHanger extends Machines{
    protected int numHangers;
    protected boolean isWorking;
    protected int wireForHangers;

    public WireToHanger() {
        wireForHangers = 0;
    }

    //produce Hangers based on input
    
    // // Setter
    // public void setWireForHangers(int wireForHangers) {
    //     this.wireForHangers = wireForHangers;
    // }

    // // Getter 
    // public int getWireForHangers() {
    //     return wireForHangers;
    // }

    public int manufactureHanger (int wirePerHanger, int orderSizeHangers, int wireForHangers, ManufacturingSimulation simulation, DrawSimulation drawing) {
        // Return amount of hangers created
        for (int i = 0; i < wireForHangers; i += wirePerHanger) {
            numHangers++;
        }

        // draw all hangers manufactured
        // for(int j = 1; j <= numHangers + notebook.numFinishedProduct(); j++) {
        //     if (j % 2 == 1) {
        //         drawing.makeHanger(wirePerHanger);
        //     }
        //     else {
        //         drawing.makeNotebook(simulation.WIREPERNOTEBOOK, simulation.PAPERPERNOTEBOOK);
        //     }
        // }

        return (numHangers);

    }
}
