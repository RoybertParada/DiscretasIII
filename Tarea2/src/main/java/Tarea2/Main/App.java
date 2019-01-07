package Tarea2.Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class App{
	
	static ArrayList<Namekiano> Namekianos = new ArrayList<Namekiano>();
	
    public static void main( String[] args ) throws NumberFormatException, IOException{
    	BufferedReader filein = new BufferedReader( new FileReader( "tarea2.in" ) );
    	int casos = Integer.parseInt(filein.readLine());
    	while(casos>0){
    		while(true){
    			System.out.println("Infinite :v");
    			String nombres = filein.readLine();
    			if(nombres.length()==1)
    				break;
    			String nombres_separados[] = nombres.split(" ");
    			
    			if(Namekianos.isEmpty()){
    				System.out.println("Namekiano Firts");
    				Namekiano Padre = new Namekiano();
    				Namekiano Hijo = new Namekiano();
    				Padre.Nombre=nombres_separados[0];
    				Hijo.Nombre=nombres_separados[1];
    				Hijo.Padre=Padre;
    				ArrayList<Namekiano> Hijos = new ArrayList<Namekiano>();
    				Hijos.add(Hijo);
    				Padre.Hijos = Hijos;
    				Namekianos.add(Padre);
    				Namekianos.add(Hijo);
    			}else{
    				System.out.println("Namekiano In");
    				Namekiano Padre = new Namekiano();
    				Namekiano Hijo = new Namekiano();
    				for(int i = 0; i < Namekianos.size(); i++){
    					System.out.println("Namekiano For");
    					if(Namekianos.get(i).Nombre==nombres_separados[0]){
    						Hijo.Nombre=nombres_separados[1];
    	    				Hijo.Padre = Namekianos.get(i);
    						ArrayList<Namekiano> Hijos = new ArrayList<Namekiano>();
    						Hijos = Namekianos.get(i).Hijos;
    						Hijos.add(Hijo);
    					}else {
    						Padre.Nombre=nombres_separados[0];
    	    				Hijo.Nombre=nombres_separados[1];
    	    				Hijo.Padre=Padre;
    	    				ArrayList<Namekiano> Hijos = new ArrayList<Namekiano>();
    	    				Hijos.add(Hijo);
    	    				Padre.Hijos = Hijos;
    	    				Namekianos.add(Padre);
    	    				Namekianos.add(Hijo);
    					}
    				}
    			}
    		}
    		casos--;
    	}
    	
    	for(int i =0; i < Namekianos.size(); i++){
    		System.out.println(Namekianos.get(i).Nombre);
    	}
    	
    	filein.close();
    }
}
