import java.util.*;

public class BFS {
	
	private static int totalNodes; // to keep track of the total number of nodes
	private static int visitedNodes; // to keep track of the number of visited nodes
	
	// This method solves the n-queens problem using BFS and returns a list of solutions
	public static Integer[] solveNQueensBFS(int n) {
	    Queue<Node> queue = new LinkedList<>(); // initialize a queue to hold the nodes to be explored
	    Set<Integer[]> visited = new HashSet<>(); // initialize a set to keep track of visited states
	    
	    queue.offer(new Node(new Integer[n], 0)); // add the initial empty state to the queue
	    visited.add(new Integer[n]); // mark the initial state as visited
	    totalNodes++; // increment the total nodes counter
	    
	    while (!queue.isEmpty()) { // loop until the queue is empty
	        Node node = queue.poll(); // remove the next node from the front of the queue
	        Integer[] state = node.getState(); // get the state represented by the node
	        visitedNodes++; // increment the visited nodes counter
	         
	        // if the state is a complete solution
	        	if (isGoal(node)==0) {
	        		return state;
	        	
	            
	        }
	        if(evaluate(node)==0) { //if the node we are exploring is not valid we will not develop it
		        //get the successors
	        	for (Node successor : getSuccessors(node,n)) {
			        if (!visited.contains(successor.getState())) { // if the state has not been visited before
			        	
		                queue.offer(successor); // add it to the stack of nodes to explore
		                totalNodes++; // increment the total nodes counter
		                visited.add(successor.getState()); // mark it as visited
			        	}
		        }
		        
	        }
	        
	    }
	    
	    return null; // return the list of solutions
	}
	
	public static List<Node> getSuccessors(Node node, int n) {
	    List<Node> successors = new ArrayList<>();
	    Integer[] state = node.getState();

	    for (int i = 0; i < n; i++) {
	    	Integer[] nextState = state.clone();
	        nextState[node.getCost()] = i; // place a queen in the ith column of the next row
	        Node nextNode = new Node(nextState, node.getCost() + 1);
	        successors.add(nextNode);
	        
	        
	    }

	    return successors;
	}
	
	public static int isGoal(Node node) {
	    Integer[] state = node.getState();
	    int n = state.length;
	    int atkCount = 0;

	    for (int i = 0; i < n; i++) {
	        if (state[i] == null) {
	        	atkCount++; // a row is not filled completely, invalid state
	        }else {
	        	for (int j = i + 1; j < n; j++) {
		        	if (state[j] == null) {
		        		atkCount++; // a row is not filled completely, invalid state
			        }else {
			        	if (state[i].intValue() == state[j].intValue() || Math.abs(state[i].intValue() - state[j].intValue()) == j - i) {
		                // Two queens are attacking each other in the same row, column or diagonal
		                atkCount++;
		            }
			        }
		            
		        }
	        }
	        
	    }

	    // All queens are placed without attacking each other, goal state found
	    return atkCount;
	}
	public static int evaluate(Node node) {
	    Integer[] state = node.getState();
	    int n = state.length;
	    int atkCount = 0;

	    for (int i = 0; i < n; i++) {
	        if (state[i] == null) {
	            continue; // a row is not filled completely, invalid state
	        }else {
	        	for (int j = i + 1; j < n; j++) {
		        	if (state[j] == null) {
			            continue; // a row is not filled completely, invalid state
			        }else {
			        	if (state[i].intValue() == state[j].intValue() || Math.abs(state[i].intValue() - state[j].intValue()) == j - i) {
		                // Two queens are attacking each other in the same row, column or diagonal
			        		atkCount++;
		            }
			        }
		            
		        }
	        }
	        
	    }

	    // All queens are placed without attacking each other, goal state found
	    return atkCount;
	}
	
    // This method prints a chessboard representation of a given state
    public static void printBoard(Integer[] state) {
        int n = state.length; // determine the size of the board
        for (int row = 0; row < n; row++) { // iterate over all rows
            for (int col = 0; col < n; col++) { // iterate over all columns
                if (state[row] == col) { // if there's a queen in this row and column
                    System.out.print("Q "); // print a queen symbol
                } else {
                    System.out.print(". "); // otherwise, print an empty space
                }
            }
            System.out.println(); // move to the next row
        }
    }
    
    public static void reset() {
    	totalNodes =0;
    	visitedNodes=0;
    }

	public static int getTotalNodes() {
		return totalNodes;
	}

	public static int getVisitedNodes() {
		return visitedNodes;
	}

    
}