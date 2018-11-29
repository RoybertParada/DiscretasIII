package Tarea1.Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;


public class BattleProcess {
    static ArrayList< HashMap<String, GraphNode> > BattleFields = new ArrayList< HashMap<String, GraphNode> >();
    
    public static void main( String[] args ) throws IOException{
    	BufferedReader r = new BufferedReader( new FileReader( "tarea1.in" ) );

    	int BattleNum = Integer.parseInt(r.readLine());
    	int BattleNumAux = BattleNum;
    	
    	ArrayList <Integer> RemainingTime = new ArrayList<Integer>();
    	while(BattleNum>0){
    		
    		int TitanClassNum = Integer.parseInt(r.readLine());
    		
    		HashMap<String, GraphNode> BattleGraph = new HashMap<String, GraphNode>();
        	GraphNode Mikasa = new GraphNode();
        	Mikasa.Height = 20;
        	Mikasa.Name = "M";
        	Mikasa.adjacencyList = new HashMap< String, Integer>();
        	BattleGraph.put("M", Mikasa);
    		while(TitanClassNum > 0){
    			
    			String TitanClass = r.readLine();
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

    			}	
    			TitanClassNum--;
    		}

    		do{
    			String Battle = r.readLine(); 
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
    			    RemainingTime.add(Integer.parseInt(Battle));
    			    break;
    			}
    		}while(true); 
    		
    		BattleFields.add(BattleGraph);
    	 BattleNum--;
    	}
    	
    	FileWriter fileWriter = new FileWriter("PARADA_ROYBERT.out");
    	PrintWriter printWriter = new PrintWriter(fileWriter);
    	int batallas=1;
    	
    	while(batallas<=BattleNumAux){
    		printWriter.println("Batalla: "+batallas);
	    	HashMap<String, GraphNode> Batalla = new HashMap<String, GraphNode>();
	    	Batalla= BattleFields.get(batallas-1);
	    	GraphNode Mikasiña = new GraphNode();
	    	Mikasiña = Batalla.get("M");
	    	HashMap<String, Integer> TitanesAdjacentes = new HashMap<String, Integer>();
	    	TitanesAdjacentes = Mikasiña.adjacencyList;
	    	int counter = 0;
    		while(!TitanesAdjacentes.isEmpty()){
    			GraphNode hf= new GraphNode();
    	    	hf.Height = 0;
		    	for (String name: TitanesAdjacentes.keySet()){
		            String key =  name;
		            GraphNode Titansiño = new GraphNode();
		            Titansiño = Batalla.get(key);
		            if(Titansiño.Height >  hf.Height ){
		            	hf.Height = Titansiño.Height; 
		              	hf.Name = Titansiño.Name;    
		              	hf.Weight = TitanesAdjacentes.get(key);  
		            }else if(Titansiño.Height ==  hf.Height && TitanesAdjacentes.get(key) < hf.Weight){		
		            	hf.Height = Titansiño.Height; 
		                hf.Name = Titansiño.Name;     
		                hf.Weight = TitanesAdjacentes.get(key); 
		            }
		    	}
		    	counter = counter + hf.Weight;
		    	if(counter > RemainingTime.get(batallas-1)){
		    		printWriter.println("Vencida "+RemainingTime.get(batallas-1));
		    		printWriter.println();
		    		break;
		    	}else{
		    		printWriter.println(hf.Name+" "+counter);
		    	}
		    	
		    	TitanesAdjacentes.remove(hf.Name);
    		}
    		if(counter <= RemainingTime.get(batallas-1)){
    			printWriter.println("Vence "+counter);
    			printWriter.println();
    		}
    		batallas++;
    	}
    	printWriter.close();
    	r.close();
    }
    	
}
