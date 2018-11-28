package Tarea1.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class BattleProcess {
    static ArrayList< HashMap<String, GraphNode> > BattleFields = new ArrayList< HashMap<String, GraphNode> >();
    public static void main( String[] args ){
    	
    	
    	Scanner in = new Scanner(System.in);
    	int BattleNum = in.nextInt();
    	int TitanClassNum = in.nextInt();
    	
    	String s;
    	while(BattleNum>0){
    		HashMap<String, GraphNode> BattleGraph = new HashMap<String, GraphNode>();
        	GraphNode Mikasa = new GraphNode();
        	Mikasa.Height = 20;
        	Mikasa.Name = "M";
        	Mikasa.adjacencyList = new HashMap< String, Integer>();
        	BattleGraph.put("M", Mikasa);
    		while(TitanClassNum >= 0){
    			
    			String TitanClass = in.nextLine();
    			String TitanClassArray[] = TitanClass.split(" ");
    			
    			for(int i = 0; i<TitanClassArray.length; i++) {
    				if(i==0)
    					continue;
    				GraphNode Titan = new GraphNode();
    				int height = Integer.parseInt(TitanClassArray[0]);
        			Titan.Height = height;
        			Titan.Name = TitanClassArray[i];
        			Titan.adjacencyList = new HashMap< String, Integer>();
        			BattleGraph.put(TitanClassArray[i], Titan);
        			System.out.println("i "+i);
    			}	
    			System.out.println("TitanClassNum "+TitanClassNum);
    			TitanClassNum--;
    		}
    		
    		
    		int RemainingTime;
    		do{
    			String Battle = in.nextLine(); System.out.println("Lenght "+Battle.length());
    			if(Battle.length()>=5){
	    			String BattleArray[] = Battle.split(" ");
	    			String Fighter_1 = BattleArray[0];
	    			String Fighter_2 = BattleArray[1];
	    			int Time = Integer.parseInt(BattleArray[2]);
	    			GraphNode Fighter_1_Info = BattleGraph.get(Fighter_1);
	    			GraphNode Fighter_2_Info = BattleGraph.get(Fighter_2);
	    			Fighter_1_Info.adjacencyList.put(Fighter_2,Time);
	    			Fighter_2_Info.adjacencyList.put(Fighter_1,Time);
    			}else{
    			    RemainingTime = Integer.parseInt(Battle);
    			    break;
    			}
    		}while(true); 
    		
    		BattleFields.add(BattleGraph);
    		System.out.println(RemainingTime);
    		
    	 BattleNum--;
    	 
    	}
    }
    	
}
