import java.util.*;

public class DFS {
	
	private static int totalNodes; // to keep track of the total number of nodes
	private static int visitedNodes; // to keep track of the number of visited nodes
	
	// This method solves the n-queens problem using BFS and returns a list of solutions
	public static Integer[] solveNQueensDFS(int n) {
	    Stack<Node> stack = new Stack<>();
	    Set<Integer[]> visited = new HashSet<>();

	    stack.push(new Node(new Integer[n], 0));
	    visited.add(new Integer[n]);
	    totalNodes++;

	    while (!stack.isEmpty()) {
	        Node node = stack.pop();
	        Integer[] state = node.getState();
	        visitedNodes++;

	        if (isGoal(node) == 0) {
	            return state; // found a valid solution, return it
	        }

	        if (evaluate(node) == 0) {
	        // get the successors
	        for (Node successor : getSuccessors(node, n)) {
	            if (!visited.contains(successor.getState())) {
	                stack.push(successor);
	                totalNodes++;
	                visited.add(successor.getState());
	            }
	        
	        }
	    }
	    }

	    return null; // no valid solution found, return null
	}

	public static List<Node> getSuccessors(Node node, int n) {
	    List<Node> successors = new ArrayList<>();
	    Integer[] state = node.getState();

	    for (int i = 0; i < n; i++) {
	    	Integer[] nextState = state.clone();
	        if(node.getCost()<n) {
	        	nextState[node.getCost()] = i; // place a queen in the ith column of the next row
	        Node nextNode = new Node(nextState, node.getCost() + 1);
	        successors.add(nextNode);
	        }
	        
	    }
	    Collections.reverse(successors);
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
    public static void printBoard(Integer[] solutions_dfs) {
        int n = solutions_dfs.length; // determine the size of the board
        for (int row = 0; row < n; row++) { // iterate over all rows
            for (int col = 0; col < n; col++) { // iterate over all columns
                if (solutions_dfs[row] == col) { // if there's a queen in this row and column
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