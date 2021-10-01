public class Hangers extends Products {
    protected final double TIMEPERHANGER = 0.5; // time in minutes 
    protected int numFinishedProduct; 
    protected int wirePerHanger;
    protected int wireForHangers;
    protected int initialAmount; 
    protected double costSteel; 
    protected double totalCost; 
    protected final int BREAKDOWNCOST;
    protected final int BREAKDOWNTIME;
    protected int breakdownTime;
    protected int breakdowns;
    protected int probability;
    protected double totalTime;
    protected int delayTime;
    protected int delayCost;


    public Hangers(int wirePerHanger, int wireForHangers, double costSteel, int probability) {
        this.wirePerHanger = wirePerHanger;
        this.wireForHangers = wireForHangers;
        this.costSteel = costSteel;
        this.probability = probability;

        numFinishedProduct = 0;
        initialAmount = 0;     
        BREAKDOWNCOST = 1000;
        BREAKDOWNTIME = 120;
    }

    public int numFinishedProduct() {
        return numFinishedProduct;
    }

    public void manufacture(int steelPerWire, int numSteel, ManufacturingSimulation simulation, DrawSimulation drawing) {
        // Create a machine instance and convert all of the raw material into product
        if (Math.random() < probability/100) {
            breakdowns++;
        }
        WireToHanger hangerMachine = new WireToHanger();

        numFinishedProduct += hangerMachine.manufactureHanger(wirePerHanger, simulation.orderSizeHangers, simulation.wireForHangers, simulation, drawing); 
        delayTime = breakdowns * BREAKDOWNTIME;
        delayCost = breakdowns * BREAKDOWNCOST;
        simulation.setDelayHangerMachineCost(delayCost);
        simulation.setDelayTimeHangerMachine(delayTime);
    }

    
    public double calculateCost() {
        totalCost = costSteel * wirePerHanger * numFinishedProduct; 
        return totalCost; 
    }

    //  Time taken to produce hangers, excluding possible time delays in machine errors  
    public double calculateTime() {
        totalTime = TIMEPERHANGER * numFinishedProduct; 
        return totalTime;
    }
}