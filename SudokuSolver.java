//=================================================================
// 
// Author: Cesar Segura           Email: cesar.segura@yale.edu 
// 
// Class: SudokuSolver
// 
// ----------------------------------------------------------------
//      This program is a generic sudoku solver! It accepts input
//      from a file that contains the metrics for a 9x9 sudoku and 
//      solves for all the values in the sudoku. 
//
//=================================================================
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Scanner;


public class SudokuSolver {
    public static void main(String[] args) throws FileNotFoundException {
        //take input from another file when compiling the code 
        if (args.length != 1) {
            usage();
            System.exit(1);
        }


        String sudokuFile = args[0];
        System.out.println("Solving " + sudokuFile);
        
        File f = new File (sudokuFile);
        Scanner s = new Scanner(f);
        int[][] sudoku = new int[9][9];; 
    
        //Read in the array from the external sudoku file 
        for (int c = 0; c < 9; c++) {
            for (int j = 0; j < 9; j++) {
                sudoku[c][j] = s.nextInt(); 
            } 
        }

        //solve the sudoku 
        solve(sudoku);

        //print results 
        System.out.println("Result: ");
        printSudoku(sudoku); 
    }

    
    public static void solve(int sudoku[][]) {
        boolean hasMadeProgress;
        do {

            hasMadeProgress = false;

            int possibleSquares;
            int fillColumn;
            int fillRow;

            //check and solve for each row
            for (int checkNumber = 1; checkNumber <= 9; checkNumber++) {
                for (int checkRow = 0; checkRow <= 8; checkRow++){
                    possibleSquares = 0;
                    fillColumn = 0;
                    fillRow = 0; 
                    for (int checkColumn = 0; checkColumn <= 8; checkColumn++){
                        if(isPossible(sudoku, checkRow, checkColumn, checkNumber)){
                            possibleSquares++;
                            fillColumn = checkColumn;
                            fillRow = checkRow; 
                        }
                    }
                    if(possibleSquares == 1) {
                        hasMadeProgress = true; 
                        sudoku[fillRow][fillColumn] = checkNumber; 
                    }
                }
            } 

            //check and solve for each column 
            for (int checkNumber = 1; checkNumber <= 9; checkNumber++) {
                for (int checkColumn = 0; checkColumn <= 8; checkColumn++){
                    possibleSquares = 0;
                    fillColumn = 0;
                    fillRow = 0; 
                    for (int checkRow = 0; checkRow <= 8; checkRow++){
                        if(isPossible(sudoku, checkRow, checkColumn, checkNumber)){
                            possibleSquares++;
                            fillColumn = checkColumn;
                            fillRow = checkRow; 
                        }
                    }
                    if(possibleSquares == 1) {
                        hasMadeProgress = true; 
                        sudoku[fillRow][fillColumn] = checkNumber; 
                    }
                }
            }    

            //check and solve for each 3x3 box 
            for (int checkNumber = 1; checkNumber <= 9; checkNumber++) {
                if (checkFirstRow(sudoku, checkNumber, hasMadeProgress)) {
                    hasMadeProgress = true; 
                }
                if (checkSecondRow(sudoku, checkNumber, hasMadeProgress)) {
                    hasMadeProgress = true; 
                }
                if (checkThirdRow(sudoku, checkNumber, hasMadeProgress)) {
                    hasMadeProgress = true; 
                }
            }
        } while (hasMadeProgress); 
    }

    public static boolean checkFirstRow(int sudoku[][], int checkNumber, boolean hasMadeProgress) {
        int possibleSquares = 0;
        int fillColumn = 0;
        int fillRow = 0;
        hasMadeProgress = false; 
        for (int startColumn = 0, finishColumn = 2; finishColumn <= 8; startColumn += 3, finishColumn += 3) {
            for (int checkRow = 0; checkRow <= 2; checkRow++) {
                for (int checkColumn = startColumn; checkColumn <= finishColumn; checkColumn++) {
                    if(isPossible(sudoku, checkRow, checkColumn, checkNumber)){
                        possibleSquares++;
                        fillColumn = checkColumn;
                        fillRow = checkRow; 
                    }
                } 
            }
            if(possibleSquares == 1) {
                hasMadeProgress = true; 
                sudoku[fillRow][fillColumn] = checkNumber; 
            }
        }

        return hasMadeProgress; 
    }

    public static boolean checkSecondRow(int sudoku[][], int checkNumber, boolean hasMadeProgress) {
        int possibleSquares = 0;
        int fillColumn = 0;
        int fillRow = 0;
        hasMadeProgress = false; 
        for (int startColumn = 0, finishColumn = 2; finishColumn <= 8; startColumn += 3, finishColumn += 3) {
            for (int checkRow = 3; checkRow <= 5; checkRow++) {
                for (int checkColumn = startColumn; checkColumn <= finishColumn; checkColumn++) {
                    if(isPossible(sudoku, checkRow, checkColumn, checkNumber)){
                        possibleSquares++;
                        fillColumn = checkColumn;
                        fillRow = checkRow; 
                    }
                } 
            }
            if(possibleSquares == 1) {
                hasMadeProgress = true; 
                sudoku[fillRow][fillColumn] = checkNumber; 
            }
        }

        return hasMadeProgress; 
    }

    public static boolean checkThirdRow(int sudoku[][], int checkNumber, boolean hasMadeProgress) {
        int possibleSquares = 0;
        int fillColumn = 0;
        int fillRow = 0;
        hasMadeProgress = false; 
        for (int startColumn = 0, finishColumn = 2; finishColumn <= 8; startColumn += 3, finishColumn += 3) {
            for (int checkRow = 6; checkRow <= 8; checkRow++) {
                for (int checkColumn = startColumn; checkColumn <= finishColumn; checkColumn++) {
                    if(isPossible(sudoku, checkRow, checkColumn, checkNumber)){
                        possibleSquares++;
                        fillColumn = checkColumn;
                        fillRow = checkRow; 
                    }
                } 
            }
            if(possibleSquares == 1) {
                hasMadeProgress = true; 
                sudoku[fillRow][fillColumn] = checkNumber; 
            }
        }

        return hasMadeProgress; 
    }


    //This method returns true if it is possible for numToCheck to be 
    //inside sudoku[row][column]
    public static boolean isPossible(int[][] sudoku, int row, int column, int numToCheck) {
        //check if box already has a number
        if (sudoku[row][column] != 0) {
            return false; 
        }

        //check row and colunm for number 
        for (int c = 0; c < sudoku.length; c++) {
            if (sudoku[row][c] == numToCheck){
                return false; 
            }
            if (sudoku[c][column] == numToCheck) {
                return false; 
            }
        }
        //check each 3x3 box for the number 
        boolean checkBox = true; 
        if (row < 3) {
            checkBox = isFirstRow(sudoku, column, numToCheck);
        } else if (row > 2 && row < 6) {
            checkBox = isSecondRow(sudoku, column, numToCheck); 
        } else if (row > 5 && row < 9) {
            checkBox = isThirdRow(sudoku, column, numToCheck); 
        }

        return checkBox;
    } //end of isPossible method 


    public static boolean isFirstRow(int [][]sudoku, int column, int numToCheck) {
        if (column < 3) {
            for (int c = 0; c < 3; c++) {
                for (int d = 0; d < 3; d++) {
                    if (sudoku[c][d] == numToCheck) {
                        return false; 
                    }
                }
            }
        }
        else if (column < 6) {
            for (int c = 0; c < 3; c++) {
                for (int d = 3; d < 6; d++) {
                    if (sudoku[c][d] == numToCheck) {
                        return false; 
                    }
                }
            }
        }
        else if (column < 9) {
            for (int c = 0; c < 3; c++) {
                for (int d = 6; d < 9; d++) {
                    if (sudoku[c][d] == numToCheck) {
                        return false; 
                    }
                }
            }
        }
        return true;
    }

    public static boolean isSecondRow(int [][]sudoku, int column, int numToCheck){
        if (column < 3) {
            for (int c = 3; c < 6; c++) {
                for (int d = 0; d < 3; d++) {
                    if (sudoku[c][d] == numToCheck) {
                        return false; 
                    }
                }
            }
        }
        else if (column < 6) {
            for (int c = 3; c < 6; c++) {
                for (int d = 3; d < 6; d++) {
                    if (sudoku[c][d] == numToCheck) {
                        return false; 
                    }
                }
            }
        }
        else if (column < 9) {
            for (int c = 3; c < 6; c++) {
                for (int d = 6; d < 9; d++) {
                    if (sudoku[c][d] == numToCheck) {
                        return false; 
                    }
                }
            }
        }
        return true; 
    }

    public static boolean isThirdRow(int [][]sudoku, int column, int numToCheck) {
        if (column < 3) {
            for (int c = 6; c < 9; c++) {
                for (int d = 0; d < 3; d++) {
                    if (sudoku[c][d] == numToCheck) {
                        return false; 
                    }
                }
            }
        }
        else if (column < 6) {
            for (int c = 6; c < 9; c++) {
                for (int d = 3; d < 6; d++) {
                    if (sudoku[c][d] == numToCheck) {
                        return false; 
                    }
                }
            }
        }
        else if (column < 9) {
            for (int c = 6; c < 9; c++) {
                for (int d = 6; d < 9; d++) {
                    if (sudoku[c][d] == numToCheck) {
                        return false; 
                    }
                }
            }
        }
        return true; 
    }

    public static void printSudoku(int [][] sudoku) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                System.out.print(sudoku[row][column]);
            }
            System.out.println(); 
        }
    }

    public static void usage() {
        System.out.println("java SodokuSolver <sudoku_input>");
    }
}
