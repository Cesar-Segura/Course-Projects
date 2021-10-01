import java.awt.*;

public class DrawSimulation {
    private int speed;
    private int steel;
    private int paper;
    private int wire;
    private int hangers;
    private int notebooks;
    private static double size;
    private static int xSize;
    private static int ySize;
    private static int FRAME_T;

    public DrawSimulation(int numSteel, int numPaper){
        steel = numSteel;
        paper = numPaper;
        wire = 0;
        hangers = 0;
        notebooks = 0;

        speed = 5;
        size = 50;
        xSize = 500;
        ySize = 500;
        FRAME_T = 20;
    }

    public void init() {
        setupCanvas(xSize, ySize);
        StdDraw.enableDoubleBuffering();
    }


    //Manufacturing one wire from steel
    public void makeWire(int steelPerWire) {
        double cx = size/2;
        double cy = ySize/4;
        // cx and cy specify the center of the picture

        //Draw the steel going into the machine
        for (int i = 0; i < steelPerWire; i++){
            cx = size/2;
            steel--;
            while (cx < (xSize/4)) {
                cx += speed;
                StdDraw.clear();
                drawBackground();
                // Draw Steel
                StdDraw.picture(cx, cy, "Steel.png", size, size);
                drawMachines();
                drawRawMaterials();
                drawCounters(steel, paper, wire, hangers, notebooks);
                StdDraw.show();
                StdDraw.pause(FRAME_T);
            }
        }
        //Draw the wire coming out of the machine
        while (cx < xSize/2) {
            cx += speed;
            StdDraw.clear();
            // Draw Background
            drawBackground();
            // Draw Hanger
            StdDraw.picture(cx, cy, "Wire.jpeg", size, size);
            // Draw Machines
            drawMachines();
            drawRawMaterials();
            drawCounters(steel, paper, wire, hangers, notebooks);
            StdDraw.show();
            StdDraw.pause(FRAME_T);
        }
        wire++;
        StdDraw.clear();
        // Draw Background
        drawBackground();
        // Draw Machines
        drawMachines();
        drawRawMaterials();
        drawCounters(steel, paper, wire, hangers, notebooks);
        StdDraw.show();
        StdDraw.pause(FRAME_T);
    }

    public void makeHanger(int wirePerHanger){
        double cx = xSize/2;
        double cy = ySize/4;
        // cx and cy specify the center of the picture

        //Draw the wire going into the machine
        for (int i = 0; i < wirePerHanger; i++){
            wire--;
            cx = xSize/2;
            while (cx < ((double)3/4) * xSize) {
                cx += speed;
                StdDraw.clear();
                drawBackground();
                // Draw Steel
                StdDraw.picture(cx, cy, "Wire.jpeg", size, size);
                drawMachines();
                drawRawMaterials();
                drawCounters(steel, paper, wire, hangers, notebooks);
                StdDraw.show();
                StdDraw.pause(FRAME_T);
            }
        }
        //Draw the Hanger coming out of the machine
        while (cx < (xSize - (size/2))) {
            cx += speed;
            StdDraw.clear();
            // Draw Background
            drawBackground();
            // Draw Hanger
            StdDraw.picture(cx, cy, "Hanger.png", size, size);
            // Draw Machines
            drawMachines();
            drawRawMaterials();
            drawCounters(steel, paper, wire, hangers, notebooks);
            StdDraw.show();
            StdDraw.pause(FRAME_T);
        }
        hangers++;
        StdDraw.clear();
        // Draw Background
        drawBackground();
        // Draw Machines
        drawMachines();
        drawRawMaterials();
        drawCounters(steel, paper, wire, hangers, notebooks);
        StdDraw.show();
        StdDraw.pause(FRAME_T);
    }

    public void makeNotebook(int wirePerNotebook, int paperPerNotebook) {
        double cx = xSize/2;
        double cy = ySize/4;
        // cx and cy specify the center of the picture

        //Draw the wire going into the machine
        for (int i = 0; i < wirePerNotebook; i++){
            cx = xSize/2;
            cy = ySize/4;
            wire--;
            while (cx < ((double)3/4) * xSize) {
                cx += speed;
                cy += speed * 2;
                StdDraw.clear();
                drawBackground();
                // Draw Steel
                StdDraw.picture(cx, cy, "Wire.jpeg", size, size);
                drawMachines();
                drawRawMaterials();
                drawCounters(steel, paper, wire, hangers, notebooks);
                StdDraw.show();
                StdDraw.pause(FRAME_T);
            }
        }

        //Draw the paper going into the machine
        for (int i = 0; i < paperPerNotebook; i++){
            cx = xSize/2;
            cy = ((double)3/4) * ySize;
            paper--;
            while (cx < ((double)3/4) * xSize) {
                cx += speed;
                StdDraw.clear();
                // Draw Background
                drawBackground();
                // Draw Hanger
                StdDraw.picture(cx, cy, "Paper.png", size, size);
                // Draw Machines
                drawMachines();
                drawRawMaterials();
                drawCounters(steel, paper, wire, hangers, notebooks);
                StdDraw.show();
                StdDraw.pause(FRAME_T);
            }
        }

        // Draw the created notebook
        while (cx < (xSize - (size/2))) {
            cx += speed;
            StdDraw.clear();
            // Draw Background
            drawBackground();
            // Draw Hanger
            StdDraw.picture(cx, cy, "Notebook.png", size, size);
            // Draw Machines
            drawMachines();
            drawRawMaterials();
            drawCounters(steel, paper, wire, hangers, notebooks);
            StdDraw.show();
            StdDraw.pause(FRAME_T);
        }
        notebooks++;
        StdDraw.clear();
        // Draw Background
        drawBackground();
        // Draw Machines
        drawMachines();
        drawRawMaterials();
        drawCounters(steel, paper, wire, hangers, notebooks);
        StdDraw.show();
        StdDraw.pause(FRAME_T);
    }

    public static void setupCanvas(final int XSIZE, final int YSIZE) {
        // Sets up the canvas
        StdDraw.setCanvasSize(XSIZE, YSIZE);
        StdDraw.setXscale(0, XSIZE);
        StdDraw.setYscale(0, YSIZE);
    }

    public static void drawBackground(){
        // Draw Background
        StdDraw.picture(xSize/2, ySize/2, "GrayBackground.jpeg", xSize, ySize);
        // Draw Title Text
        StdDraw.text(xSize/2, ((double)9/10) * ySize, "Product Manufacturing Simulation");
    }

    public static void drawMachines(){
        // Draw Machines
        // Steel to Wire Machine
        StdDraw.picture(xSize/4, ySize/4, "Machine.png", size, size);
        // Wire to Hanger Machine
        StdDraw.picture(((double)3/4) * xSize, ySize/4, "Machine.png", size, size);
        // PaperWire to Notebook Machine
        StdDraw.picture(((double)3/4) * xSize, ((double)3/4) * ySize, "Machine.png", size, size);
    }

    public static void drawRawMaterials(){
        // Draw Raw Steel
        StdDraw.picture(size/2, ySize/4, "Steel.png", size, size);
        // Draw Paper
        StdDraw.picture(xSize/2, ((double)3/4) * ySize, "Paper.png", size, size);
        // Draw Wire
        StdDraw.picture(xSize/2, ySize/4, "Wire.jpeg", size, size);
        // Draw Hanger
        StdDraw.picture(xSize - (size/2), ySize/4, "Hanger.png", size, size);
        // Draw Notebook
        StdDraw.picture(xSize - (size/2), ((double)3/4) * ySize, "Notebook.png", size, size);
    }

    public void drawCounters(int steel, int paper, int wire, int hangers, int notebooks) {
        Font font = new Font("sans serif", Font.BOLD, 10);
        StdDraw.setFont(font);
        // Draw counter for steel
        StdDraw.text(size/2, (ySize/4) - (size/2) - 10, "Count: " + steel);
        // Draw counter for paper
        StdDraw.text(xSize/2, ((double)3/4) * ySize - (size/2) - 10, "Count: " + paper);
        // Draw counter for wire
        StdDraw.text(xSize/2, (ySize/4) - (size/2) - 10, "Count: " + wire);
        // Draw counter for hanger
        StdDraw.text(xSize - (size/2), ySize/4 - (size/2) - 10, "Count: " + hangers);
        // Draw counter for notebooks
        StdDraw.text(xSize - (size/2), ((double)3/4) * ySize - (size/2) - 10, "Count: " + notebooks);
    }
}
