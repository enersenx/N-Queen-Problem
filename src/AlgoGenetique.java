import java.io.*;

import java.util.Random;
import java.util.Stack;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Iterator;

public class AlgoGenetique {
	
	private int populationSize;
    private double mutationRate;
    private double crossoverRate;
    private int selectCount;
    private String selection_choice;
    private int crossover_points;
    int stop_condition;
    
	
	public AlgoGenetique(int stop_condition,int populationSize, double mutationRate, double crossoverRate, int selectCount, int crossover_points, String selection_choice) {
		// TODO Auto-generated constructor stub
		this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.selectCount = selectCount;
        this.selection_choice = selection_choice;
        this.stop_condition = stop_condition;
        this.crossover_points = crossover_points;
	}

	//structure to modelise solution
		//TV vector for the initial array
		//VR vector for the chosen values
	private Integer[] TV;
		//takes 1 if element in S1
		//takes 0 if element in S2
	private Integer[] VR;
	
	//generate instance
	public void generate_instance(int n) {
		this.TV = new Integer [n];
		Random rand = new Random();
		try {
        	
        	String name_file = "TV" + ".txt";
			PrintWriter writer = new PrintWriter(name_file, "UTF-8");
			for (int i = 0; i < TV.length; i++) {
				int value = rand.nextInt(100);
	            this.TV[i] = value; // generates a random integer between 0 and 99
	            writer.print(value + " ");
	        }
			String name_file_tv = "TV_array" + ".txt";
			PrintWriter writer_tv = new PrintWriter(name_file_tv, "UTF-8");
			writer_tv.print(Arrays.toString(TV));
			
		    writer.close();
		    writer_tv.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("TV = "+ Arrays.toString(TV));
	}
	
	//generate VR instance
			public Integer[] generate_instance_VR() {
				Random rand = new Random();
				Integer[] VR = new Integer[TV.length];
				for (int i = 0; i < TV.length; i++) {
		            VR[i] = rand.nextInt(2); // generates a random integer between 0 and 1 and 2
		        }
				return VR;
			}
			
			
			//evaluate solution
			//atkerence of sums S1 and S2
			public int evaluate_solution( Integer[] TV, Integer[] VR) {
				
				// initialize variables to track the running sum of elements in each set
			    int sumSet1 = 0;
			    int sumSet2 = 0;

			    // iterate over the vector and sum the elements in each set
			    for (int i = 0; i < TV.length; i++) {
			        if (VR[i] == 1) {
			            sumSet1 += TV[i];
			        } else if (VR[i] == 0) {
			            sumSet2 += TV[i];
			        }
			    }
				return Math.abs(sumSet1-sumSet2);
				
			}
			
			public void selection_elitism(PriorityQueue<NodeAG> population,PriorityQueue<NodeAG>newPopulation,Vector<NodeAG>population_vector){
				
				for (int i = 0; i < population_vector.size()-selectCount; i++) {
	                newPopulation.add(population.poll());
	                
	            }
	            
	            // Create an iterator for the priority queue
		        Iterator<NodeAG> iterator_population = population.iterator();

		        // Use the iterator to iterate over the elements of the priority queue
		        population_vector.clear();
		        while (iterator_population.hasNext()) {
		        	population_vector.add(iterator_population.next());
		        }
				
			}
			public void selection_roulette(PriorityQueue<NodeAG> population,PriorityQueue<NodeAG>newPopulation,Vector<NodeAG>population_vector){
				
				Vector<NodeAG> population_rest = new Vector<NodeAG>();
				
		         // Create an iterator for the priority queue
			        Iterator<NodeAG> iterator = population.iterator();

			        // Use the iterator to iterate over the elements of the priority queue
			        while (iterator.hasNext()) {
			        	population_rest.add(iterator.next());
			        }
				
				int population_size = population_rest.size();
				Vector<Double> probabilities = new Vector<Double>();
				//calculate sum of fitness
				double sum = 0;
				for (int i = 0; i < population_size; i++) {
					sum +=  population_rest.get(i).atk;
				}
				//calculate probabilities
				for (int i = 0; i < population_size; i++) {
	                Random random = new Random();
	                NodeAG current_element = population_rest.get(random.nextInt(population_size));//param1
	                int count = 0;
					for (NodeAG element : population_rest) {
					    if ((element.chessboard).equals(current_element.chessboard)) {
					        count++;
					    }
					}
					double probability = (current_element.atk * count)/ sum;
					probabilities.add(probability);
	                
	            }
				
				//select
				int count_selected = 0;
				Vector<NodeAG> population_selected = new Vector<NodeAG>();
				while(count_selected != selectCount) {
	                Random random = new Random();
	                int index = random.nextInt(population_rest.size());
	                NodeAG current_element = population_rest.get(index);
	                if(random.nextDouble() > probabilities.get(index)) {
	                	//add in population vector
		                population_selected.add(current_element);
		                population_rest.remove(index);
		                count_selected++;
	                }
		                
	            }
				
				
				//update newPopulation
				for (int i = 0; i < population_rest.size(); i++) {
	                newPopulation.add(population_rest.get(i));
	                
	            }
				
				
				//update population_vector with selected individuals
				// Create an iterator for the priority queue
				
		        Iterator<NodeAG> iterator_population = population_selected.iterator();

		        // Use the iterator to iterate over the elements of the priority queue
		        population_vector.clear();
		        while (iterator_population.hasNext()) {
		        	population_vector.add(iterator_population.next());
		        }
				
			
			}
			
			//crossover function
			public Vector<Integer[]> Crossover(int point,Integer[] parent1,Integer[] parent2,Integer[] numbers){
		    	Vector<Integer[]> children = new Vector<Integer[]>();	
		    
		    	Random random = new Random();
		        if (random.nextDouble() < crossoverRate){
		        	Integer[] child1 = new Integer[numbers.length];
		        	Integer[] child2 = new Integer[numbers.length];
		        	if (point==1) {
		        		int k=numbers.length/2;
		        		for (int j = 0; j <k; j++) {
		        			child1[j] = parent1[j];
		        			child2[j] = parent2[j];
		        		}
		        		for (int j = k; j <numbers.length; j++) {
		        			child1[j] = parent2[j];
		        			child2[j] = parent1[j];
						}
		        	}else if(point==2) {
		        		int k=numbers.length/3;
		        		for (int j = 0; j <k; j++) {
							child1[j] = parent1[j];
							child2[j] = parent2[j];
						}
		        		for (int j = k; j <2*k; j++) {
							child1[j] = parent2[j];
							child2[j] = parent1[j];
		        		}
		        		for (int j = 2*k; j <numbers.length; j++) {
							child1[j] = parent1[j];
							child2[j] = parent2[j];
		        		}
		        		
		        	}
		            
		            children.add(child1);
		            children.add(child2);
		            }
		        else {
		        	children.add(parent1);
		        	children.add(parent2);
		        	}   
		        return children;
		    }
			
			public Integer [] Mutation(Integer[] child,Integer[] numbers){
				Random random = new Random();
				for (int j = 0; j < numbers.length; j++) {
                    if (random.nextDouble() < mutationRate) {
                    	child[j] = 1 - child[j];
                    }
                }
				return child;
				
			}
			


			    public NodeAG solve(Integer[] numbers) {
			        Random random = new Random();
			        PriorityQueue<NodeAG> newPopulation = new PriorityQueue<>(Comparator.comparingInt(NodeAG::getAtk));
			        PriorityQueue<NodeAG> population = new PriorityQueue<>(Comparator.comparingInt(NodeAG::getAtk));
			        
			        Vector<NodeAG> population_vector = new Vector<NodeAG>();
			        
			        //generate random VRs
			        for (int i = 0; i < populationSize; i++) {
			        	Integer[] VR = new Integer[TV.length];
			            VR= this.generate_instance_VR();
			            //fill vector and calculate fitness
			            NodeAG NodeAG = new NodeAG(VR,this.evaluate_solution(numbers, VR));
			            population.add(NodeAG);
			            
			        }

			        for (int generation = 0; generation < stop_condition; generation++) {
			            

			            // Create new population through elitism, crossover, and mutation
			            
			            //selection
			        	if(selection_choice.equals("roulette")) {
			        		//System.out.println("Roulette selection is applied");
			        		selection_roulette(population,newPopulation,population_vector);
			        	}
			        	if(selection_choice.equals("elitism")) {
			        		//System.out.println("Elitism selection is applied");
			        		selection_elitism(population,newPopulation,population_vector);
			        	}
			            
			            //crossover
			        	int population_select_size = population_vector.size();
			            for (int i = 0; i < population_select_size/2; i++) {
			            	Integer[] parent1 = population_vector.get(random.nextInt(population_select_size)).chessboard;//param1
			            	Integer[] parent2 = population_vector.get(random.nextInt(population_select_size)).chessboard;//param2

			            	Vector<Integer[]> children = Crossover(crossover_points,parent1,parent2,numbers)	;
			            	
			            	
					        Integer [] child1=children.get(0);
					        Integer [] child2=children.get(1);
					        
					        //mutation
					        Integer [] mutated_child1 = Mutation(child1,numbers)	;
					        Integer [] mutated_child2 = Mutation(child2,numbers)	;
			                
			                NodeAG child_NodeAG1 = new NodeAG(mutated_child1,this.evaluate_solution(numbers, child1));
			                NodeAG child_NodeAG2 = new NodeAG(mutated_child2,this.evaluate_solution(numbers, child2));
			                newPopulation.add(child_NodeAG1);
			                newPopulation.add(child_NodeAG2);
			            }

			            population.clear();
			         // Create an iterator for the priority queue
				        Iterator<NodeAG> iterator = newPopulation.iterator();

				        // Use the iterator to iterate over the elements of the priority queue
				        while (iterator.hasNext()) {
				            population.add(iterator.next());
				        }
			            newPopulation.clear();
			        }

			        // Return the best solution found
			        
			        return population.peek();
			    }


			public Integer[] getTV() {
				return TV;
			}

			public void setTV(Integer[] tV) {
				TV = tV;
			}

			public Integer[] getVR() {
				return VR;
			}

			public void setVR(Integer[] vR) {
				VR = vR;
			}
}
