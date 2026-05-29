import java.util.*;
import java.io.*;
import java.text.*;
import java.time.*;

public class MazeGameThing {

    // ====== 16x16 "map" of maze (2d array) ===========
    
    // The Map Key Below:
    // # = Walls (Can't move there)
    // '' = Open Space (Can move there)
    // 'S' = Starting Point (Where you start at)
    // 'E' = Exit Point (Where you escape/finish at)
    // '*' = YOU (Represents the path you took)

    static char[][] maze = {
       
        {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'},
        {'#','S',' ',' ','#',' ',' ',' ','#',' ',' ',' ','#',' ',' ','#'},
        {'#','#','#',' ','#',' ','#',' ','#',' ','#',' ','#',' ','#','#'},
        {'#',' ',' ',' ','#',' ','#',' ',' ',' ','#',' ',' ',' ','#','#'},
        {'#',' ','#','#','#',' ','#','#','#','#','#','#','#',' ','#','#'},
        {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#',' ',' ','#'},
        {'#','#','#','#','#','#','#','#','#','#','#',' ','#','#',' ','#'},
        {'#',' ',' ',' ',' ',' ','#',' ',' ',' ','#',' ',' ',' ',' ','#'},
        {'#',' ','#','#','#',' ','#',' ','#',' ','#','#','#','#',' ','#'},
        {'#',' ','#',' ','#',' ',' ',' ','#',' ',' ',' ',' ','#',' ','#'},
        {'#',' ','#',' ','#','#','#','#','#','#','#','#',' ','#',' ','#'},
        {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#',' ','#'},
        {'#','#','#','#','#','#','#','#','#','#','#','#',' ','#',' ','#'},
        {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#',' ',' ',' ','#'},
        {'#',' ','#','#','#','#','#','#','#','#',' ','#','#','#','E','#'},
        {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'}
    };

  
  
    // ====== Main Method (Literally just the setup and text outputs)
   
    public static void main(String[] args) {

        System.out.println("==== Maze Game Thing ====");
        System.out.println("\nHere is the maze before you escaped:");
        printMaze();                                                      // Prints maze before it's solved

                        // LOOP TO FIND STARTING POINTS (checks row by row until it finds starting point and saves the position)
        int startRow = 0;
        int startCol = 0;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 'S') {                   // Starting point 'S'
                    startRow = i;
                    startCol = j;
                }
            }
        }

        System.out.println("\nLooking for the way out of the maze...");            // Only prints this while looking for the way out

        if (solve(startRow, startCol)) {
            System.out.println("\nPath found! Here is the way out:");            
            printMaze();
        } else {
            System.out.println("\nThere's no way out this way!");
        }

    } // End Main

   
    // ====== Recursion Part (Actually solving the maze now) (These notes are gonna be referring to my "friend example" in my notes so I better understand recursions) ======

    public static boolean solve(int row, int col) {

        if (row < 0 || col < 0 || row >= maze.length || col >= maze[0].length) {             // Checks if I moved outside the maze and if I did then it tries a different way
            return false;
        }

        if (maze[row][col] == '#') {               // The equivalent of hitting a wall so its false
            return false;
        }

        if (maze[row][col] == '*') {               // You already went this way so it'd be false too (to avoid inf loop)
            return false;
        }

        if (maze[row][col] == 'E') {              // If a friend find the exit return that info back to ME which would be 'S'
            return true;
        }
        
        
     // Part below basically marks path im trying
        if (maze[row][col] == ' ') {               
            maze[row][col] = '*';
        }

        if (solve(row - 1, col)) return true;   // Up
        if (solve(row + 1, col)) return true;   // Down
        if (solve(row, col - 1)) return true;   // Left
        if (solve(row, col + 1)) return true;   // Right

     // Part below tries the path  and if it's wrong it gets undone
        if (maze[row][col] == '*') {              
            maze[row][col] = ' ';
        }

        return false;

    } // End Solve

    
    // ====== Completed Maze Print Below 
    


 // Code below prints maze after the way out is found
    public static void printMaze() {          
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    } // End printMaze

} // End Class
