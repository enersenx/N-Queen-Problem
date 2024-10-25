import java.util.Arrays;

/*
 * Modélisation:
 * a "state" represents a solution to the problem. 
 * Specifically, a state is represented as an array of integers 
 * where each index of the array corresponds to a row on the chessboard, 
 * and the value of that index corresponds to the column where a queen is placed in that row.
 */

public class Main {
    public static void main(String[] args) {
    	System.out.println("BFS in main . . .");
        int n = 9; //min = 9
        
//        long sumb = 0;
//      double moyb;
//      
//      for(int i=0;i<10;i++) {
//      	long startTime_bfs = System.nanoTime();
//      	Integer[] solutions_bfs = BFS.solveNQueensBFS(n);
//  	    long endTime_bfs = System.nanoTime();
//	          
//	  	    	// Calculate the elapsed time
//	  	    	long elapsedTime_b = endTime_bfs - startTime_bfs;
//	  	    	sumb += elapsedTime_b;
//	  	    	BFS.reset();
//      }
//      moyb = sumb / 10;
//        
//        long startTime_bfs = System.nanoTime();
//        Integer[] solutions_bfs = BFS.solveNQueensBFS(n);
//        long endTime_bfs = System.nanoTime();
//        // Calculate the elapsed time
//        long elapsedTime_bfs = endTime_bfs - startTime_bfs;
//        
//        int totalNodesSolution_bfs = BFS.getTotalNodes();
//    	int visitedNodesSolution_bfs = BFS.getVisitedNodes();
//
//        
//      //for interface
//        //print for first solution only (FERIEL)
//        
//        System.out.println("\n\n Print first solution bfs. . .");
//        //System.out.println(Arrays.toString(solutions_bfs));
//        BFS.printBoard(solutions_bfs);
//        System.out.println("Nodes generated to reach solution #0 = " + totalNodesSolution_bfs + "\n");
//        System.out.println("Nodes visited to reach solution #0 = " + visitedNodesSolution_bfs + "\n");
//        System.out.println();
//        System.out.println("Elapsed time: " + (moyb / 1000000) + " ms");
//       
        
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        
        System.out.println("\n\nDFS in main . . .");
       
        long sumd = 0;
        double moyd;
        
        for(int i=0;i<30;i++) {
        	long startTime_dfs = System.nanoTime();
        	Integer[] solutions_dfs = DFS.solveNQueensDFS(n);
    	    long endTime_dfs = System.nanoTime();
	          
	  	    	// Calculate the elapsed time
	  	    	long elapsedTime_d = endTime_dfs - startTime_dfs;
	  	    	sumd += elapsedTime_d;
	  	    	DFS.reset();
        }
        moyd = sumd / 30;
        
        long startTime_dfs = System.nanoTime();
    	Integer[] solutions_dfs = DFS.solveNQueensDFS(n);
	    long endTime_dfs = System.nanoTime();
	    // Calculate the elapsed time
	    long elapsedTime_dfs = endTime_dfs - startTime_dfs;
	    
	    int totalNodesSolution_dfs = DFS.getTotalNodes();
		int visitedNodesSolution_dfs = DFS.getVisitedNodes();
	
	    
	  //for interface
	    //print for first solution only (FERIEL)
	    System.out.println("\n\n Print first solution dfs. . .");
	    System.out.println(Arrays.toString(solutions_dfs));
	    DFS.printBoard(solutions_dfs);
	    System.out.println("Nodes generated to reach solution #0 = " + totalNodesSolution_dfs + "\n");
	    System.out.println("Nodes visited to reach solution #0 = " + visitedNodesSolution_dfs+ "\n");
	    System.out.println();
	 // Print the elapsed time in milliseconds
	    System.out.println("Elapsed time: " + (moyd / 1000000) + " ms");
	    
	    
        
//	        
//	    System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
//        
//        System.out.println("\n\nHeuristic h1 in main . . .");
//        
//        Integer[] startState = new Integer[n]; // Initial state
//        Arrays.fill(startState, 0);
//        
//        long sum1 = 0;
//        double moy1;
//        
//        for(int i=0;i<30;i++) {
//      	  AStar solver1 = new AStar(n);
//	          long startTime_a1 = System.nanoTime();
//	          Integer[] solution_a1 = solver1.findSolution(startState);
//	          long endTime_a1 = System.nanoTime();
//	  	    	// Calculate the elapsed time
//	  	    	long elapsedTime_a1 = endTime_a1 - startTime_a1;
//	  	    	sum1 += elapsedTime_a1;
//        }
//        moy1 = sum1 / 30;
//        
//        AStar solver = new AStar(n);
//        long startTime_a1 = System.nanoTime();
//        Integer[] solution_a1 = solver.findSolution(startState);
//        long endTime_a1 = System.nanoTime();
//	    // Calculate the elapsed time
//	    long elapsedTime_a1 = endTime_a1 - startTime_a1;
//	    
//	    int totalNodesSolution_a1 = solver.getTotalNodes();
//    	int visitedNodesSolution_a1 = solver.getVisitedNodes();
//    	
//    	//for interface
//        //print for first solution only (FERIEL)
//          System.out.println("\n\n Print first solution mnc. . .");
//          System.out.println(Arrays.toString(solution_a1));
//          //solver.printBoard(solution_a1);
//          System.out.println("Nodes generated to reach solution #0 = " + totalNodesSolution_a1 + "\n");
//          System.out.println("Nodes visited to reach solution #0 = " + visitedNodesSolution_a1 + "\n");
//          System.out.println();
//          System.out.println("Elapsed time: " + (moy1 / 1000000) + " ms");
//          
//          System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
//          
//          System.out.println("\n\nHeuristic h2 in main . . .");
//          
//          Integer[] startState2 = new Integer[n]; // Initial state
//          Arrays.fill(startState2, 0);
//
//          long sum2 = 0;
//          double moy2;
//          
//          for(int i=0;i<30;i++) {
//        	  AStar solver2 = new AStar(n);
//	          long startTime_a2 = System.nanoTime();
//	          Integer[] solution_a2 = solver2.findSolution2(startState2);
//	          long endTime_a2 = System.nanoTime();
//	  	    	// Calculate the elapsed time
//	  	    	long elapsedTime_a2 = endTime_a2 - startTime_a2;
//	  	    	sum2 += elapsedTime_a2;
//          }
//          moy2 = sum2 / 30;
//          
//          AStar solver2 = new AStar(n);
//          long startTime_a2 = System.nanoTime();
//          Integer[] solution_a2 = solver2.findSolution2(startState2);
//          long endTime_a2 = System.nanoTime();
//  	    
//  	    int totalNodesSolution_a2 = solver2.getTotalNodes();
//      	int visitedNodesSolution_a2 = solver2.getVisitedNodes();
//      	
//      	//for interface
//          //print for first solution only (FERIEL)
//            System.out.println("\n\n Print first solution mnc. . .");
//            System.out.println(Arrays.toString(solution_a2));
//            //solver2.printBoard(solution_a2);
//            System.out.println("Nodes generated to reach solution #0 = " + totalNodesSolution_a2 + "\n");
//            System.out.println("Nodes visited to reach solution #0 = " + visitedNodesSolution_a2 + "\n");
//            System.out.println();
//            System.out.println("Elapsed time: " + (moy2 / 1000000) + " ms");
//            
//          
    
        
    }
}