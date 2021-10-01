/**********************************************************************
*   File: Machines.java        Assignment: Final Project
*
*   Class: Machines
*   Dependencies: none
*
*   This class stores the methods and objects for manufacturing the
*   three products, hangars, notebooks, and ice cream. This parent
*   class will serve as the default for child classes.
*     
**********************************************************************/
 
abstract class Machines {
    protected int probability;
    protected int breakdowns;
    protected int orders;
    protected final int BREAKDOWNCOST;
    protected final int BREAKDOWNTIME;
    protected final int ORDERTIME;
  

    public Machines() {
        breakdowns = 0;
        orders = 0;
        BREAKDOWNCOST = 1000;
        BREAKDOWNTIME = 120;
        ORDERTIME = 45;

    }

    public void setProbability(int probBreak) {
        probBreak = this.probability; 
        isWorking();
    }

    public int probability() {
        return probability; 
    }

    public int manufacture() {
        return 0;
    }

    public void placeOrder() {
        
    }

    public boolean isWorking() {
        if (Math.random() < probability/100) {
            breakdowns++;
            return false;
        }
        else {
            return true;
        }
    }

    public int costBreakdown() {
        return (breakdowns * BREAKDOWNCOST);
    }

    public int timeBreakdown() {
        return (breakdowns * BREAKDOWNTIME + orders * ORDERTIME);
    }
}