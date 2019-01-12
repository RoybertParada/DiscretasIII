package Tarea2.Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class App{
	
	static Namekiano NamekianoGlobal;
	static ArrayList< ArrayList<Namekiano> > NamekianosCasos = new ArrayList< ArrayList<Namekiano> >();
	static ArrayList< ArrayList<String> > Parentescos = new ArrayList< ArrayList<String> >();
	static int altura=0;
	static int casos;
	
	static void input() throws IOException{
		BufferedReader filein = new BufferedReader( new FileReader( "tarea2.in" ) );
    	casos = Integer.parseInt(filein.readLine());
    	
    	while(casos>0){
    		ArrayList<Namekiano> Namekianos = new ArrayList<Namekiano>();
    		int M = 0;
    		while(true){
    			String nombres = filein.readLine();
    			if(!nombres.contains(" ")){
    				M = Integer.parseInt(nombres);
    				break;
    			}
    			String nombres_separados[] = nombres.split(" ");
    			Namekiano Padre = new Namekiano();
				Namekiano Hijo = new Namekiano();
    			if((Namekianos.isEmpty())||((!findnamekiano(Namekianos,nombres_separados[0]))&&(!findnamekiano(Namekianos,nombres_separados[1])))){
    				Padre.Nombre=nombres_separados[0];
    				Hijo.Nombre=nombres_separados[1];
    				Hijo.Padre=Padre;
    				ArrayList<Namekiano> Hijos = new ArrayList<Namekiano>();
    				Hijos.add(Hijo);
    				Padre.Hijos = Hijos;
    				Namekianos.add(Padre);
    				Namekianos.add(Hijo);
    			}else if(findnamekiano(Namekianos,nombres_separados[0])&&(!findnamekiano(Namekianos,nombres_separados[1]))){
    				Hijo.Nombre=nombres_separados[1];
	    			Hijo.Padre = NamekianoGlobal;
					ArrayList<Namekiano> Hijos = new ArrayList<Namekiano>();
					Hijos = NamekianoGlobal.Hijos;
					Hijos.add(Hijo);
					Namekianos.add(Hijo);	
    			}else if(findnamekiano(Namekianos,nombres_separados[1])&&(!findnamekiano(Namekianos,nombres_separados[0]))) {
    				Padre.Nombre=nombres_separados[0];
    				Hijo = NamekianoGlobal;
    				Hijo.Padre = Padre;
    				ArrayList<Namekiano> Hijos = new ArrayList<Namekiano>();
    				Hijos.add(Hijo);
    				Padre.Hijos = Hijos;
    				Namekianos.add(Padre);
    			}else if((findnamekiano(Namekianos,nombres_separados[0]))&&(findnamekiano(Namekianos,nombres_separados[1]))){
    				findnamekiano(Namekianos,nombres_separados[0]);
    				Padre = NamekianoGlobal;
    				findnamekiano(Namekianos,nombres_separados[1]);
    				Hijo = NamekianoGlobal;
    				Hijo.Padre = Padre;
    				Padre.Hijos.add(Hijo);
    			}
    		}
    		ArrayList<String> Parentesco = new ArrayList<String>();
    		while(M>0){
    			String namekiano = filein.readLine();
    			Parentesco.add(namekiano);
    			M--;
    		}
    		Parentescos.add(Parentesco);
    		NamekianosCasos.add(Namekianos);
    		casos--;
    	}
    	filein.close();
	}
    public static void main( String[] args ) throws NumberFormatException, IOException{
    	input();
    	FileWriter fileWriter = new FileWriter("C:\\Users\\b712590\\Desktop\\PARADA_ROYBERT.out");
    	PrintWriter printWriter = new PrintWriter(fileWriter);
    	
    	int AlturaGuerreros=0;
    	int AlturaDemonios=0;
    	for(int i = 0; i < NamekianosCasos.size(); i++){
    		printWriter.println("Caso "+(i+1)+":");
    		ArrayList<Namekiano> Namekianos = new ArrayList<Namekiano>();
    		Namekianos = NamekianosCasos.get(i);
    		for(int j = 0; j < Namekianos.size(); j++){
    			Namekiano Namek = Namekianos.get(j);
    			if(Namek.Nombre.equals("Piccoro")){
    				AlturaDemonios = findlast(Namek,1);
    				altura=0;
    			}else if((Namek.Padre==null)){
    				AlturaGuerreros =  findlast(Namek,1);
    				altura=0;
    			}
    		}
    		
    		printWriter.println(AlturaGuerreros+" "+AlturaDemonios);
    		
    		ArrayList<String> parentesco = new ArrayList<String>();
    		parentesco = Parentescos.get(i);
    		for(int k = 0; k < parentesco.size(); k++){
    			String par = parentesco.get(k);
    			String par_separado[] = par.split(" ");
    			ArrayList<Namekiano> aux = new ArrayList<Namekiano>();
    			aux = Namekianos;
    			for(int l = 0; l < Namekianos.size(); l++){
    				Namekiano Namek = Namekianos.get(l);
    	    		if(Namek.Nombre.equals(par_separado[0])){
    	    			int arriba = findparent(Namek,par_separado[1],0);
    	    			if(arriba !=-1) {
    	    				System.out.println("something");
    	    			}
    	    			int abajo = findchild(Namek,par_separado[1],0);
    	    			if( abajo !=-1) {
    	    				System.out.println("Don't find");
    	    			}
    	    			break;
    	    		}
    			}	    
     		}
    	}
    	
    	
    	
    	printWriter.close();
    }
    
    public static int findchild(Namekiano Namek, String finder, int level){
    	ArrayList<Namekiano> papan = new ArrayList<Namekiano>();
    	papan = Namek.Hijos;
    	if(!papan.isEmpty()){
    		for(Namekiano help : papan){
    			if((help.Nombre.equals(finder))){
            		return level;
            	}else{
            		level++;
            		level = findchild(help,finder, level);
            	}
    		}
    	}
    	level--;
    	return level;
    }
    public static int findparent(Namekiano Namek, String finder, int level){
    	Namekiano aux = new Namekiano();	
    	aux = Namek.Padre;
    	if(aux!=null) {
    		if((aux.Nombre.equals(finder))){
    			level++;
        		return level;
        	}else{
        		level++;
        		level = findparent(aux,finder, level);
        		level--;
        	}
    	}
    	
    	return level;
    }
    
    public static boolean findnamekiano(ArrayList<Namekiano> Namekianos, String Namekiano) {
    	for(int i = 0; i < Namekianos.size(); i++){
			if(Namekianos.get(i).Nombre.equals(Namekiano)){
				NamekianoGlobal = new Namekiano();
				NamekianoGlobal = Namekianos.get(i);
				return true;
			}
    	}
    	
    	return false;
    }
    
    public static int findlast(Namekiano root, int Height){
    	
    	if(root.Hijos.isEmpty()){
    		return 0;
    	}else {
    		Height++;
    		for(int i = 0; i < root.Hijos.size(); i++){
    			findlast(root.Hijos.get(i),Height);
    			if(altura<Height){
        			altura = Height;
        		}
    		}
    	}
    	return altura;
    }
}
