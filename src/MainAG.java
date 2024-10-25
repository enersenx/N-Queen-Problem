
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class MainAG {
	
	public static int n;

	public static void main(String[] args) {
		
		//create instance
		int crossover_points = 2;
		String selection_choice = "elitism";
		//best parameters
		 int populationSize = 500;
	     double mutationRate = 0.5;
	     double crossoverRate = 0.5;
	     int stop_condition = 100;
	     
	     
	     int selectCount;
	     if((populationSize/2)%2 == 0) {
	    	 selectCount = populationSize/2;
	     }else {
	    	 selectCount = (populationSize/2)+1;
	     }
	     
	    Integer[] sizes = {100};
	    
	    for (int i = 0; i < sizes.length; i++) {
			int n = sizes[i];
		    
			AlgoGenetique AlgoGenetique = new AlgoGenetique(stop_condition, populationSize, mutationRate, crossoverRate, selectCount,crossover_points, selection_choice);
			
			//generate an instance of the problem
			AlgoGenetique.generate_instance(n);
			
			
			//generate an instance of VR
			
			long sum = 0;
			long sum_atk = 0;
	        double moy = 0;
	        double moy_atk = 0;
	        NodeAG best_solution = null;
		
	        try {
	        	
	        	String name_file = "Chess"+(i+1)+"__"+crossover_points +"II"+ selection_choice +"II"+populationSize+"II"+stop_condition +"II"+ mutationRate +"II"+crossoverRate +   ".txt";
				PrintWriter writer = new PrintWriter(name_file, "UTF-8");
				//function
				
				for(int j=0;j<10;j++) {
					writer.println("---------------------------------------------");
					writer.println("TV : "+ Arrays.toString(AlgoGenetique.getTV()));
					writer.println("iteration "+ j + ":");
					
			          long startTime_a1 = System.nanoTime();
			          best_solution = AlgoGenetique.solve(AlgoGenetique.getTV());
			          long endTime_a1 = System.nanoTime();
			          //save atkerence in file
			          writer.println("atkerence: " + best_solution.atk);
			  	    	// Calculate the elapsed time
			  	    long elapsedTime_a1 = endTime_a1 - startTime_a1;
			  	   	sum += elapsedTime_a1;
			  	   	sum_atk += best_solution.atk;
		        }
				moy = sum / 10;
		        moy_atk = sum_atk / 10;
		        writer.println("Average atkerence:" +moy_atk+ "\nAverage elapsed time: " + (moy / 1000000) + " ms");
			    writer.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        System.out.println("Average atkerence:" +moy_atk+ "\nAverage elapsed time: " + (moy / 1000000) + " ms");
			
		    }

		}
		

	
	

}
