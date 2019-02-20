package Tarea2.Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class App {

	static Namekiano NamekianoGlobal;
	static ArrayList< ArrayList<Namekiano> > NamekianosCasos = new ArrayList< ArrayList<Namekiano> >();
	static ArrayList< ArrayList<String> > Parentescos = new ArrayList< ArrayList<String> >();
	static ArrayList<Namekiano> HNamekianos = new ArrayList<Namekiano>();
	static int altura=0;
	static int casos;
	static int resultado = 0;

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
    	FileWriter fileWriter = new FileWriter("PARADA_ROYBERT.out");
    	PrintWriter printWriter = new PrintWriter(fileWriter);
    	
    	int AlturaGuerreros=0;
    	int AlturaDemonios=0;
    	for(int i = 0; i < NamekianosCasos.size(); i++){
    		printWriter.println("Caso "+(i+1)+":");
    		Object NamekianosObj;
    		NamekianosObj = NamekianosCasos.get(i).clone();
    		
    		ArrayList<Namekiano> Namekianos = new ArrayList<Namekiano>();
    		Namekianos = (ArrayList<Namekiano>) NamekianosObj;
    		
    		for(int j = 0; j < Namekianos.size(); j++){
    			Namekiano Namek = Namekianos.get(j);
    			if(Namek.Nombre.equals("Piccoro")){
    				AlturaDemonios = findlast(Namek,1);
    				altura=0;
    			}else if((Namek.Padre==null)){
    				printWriter.println(Namek.Nombre);
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
    			for(int l = 0; l < Namekianos.size(); l++){
    				Namekiano Namek = Namekianos.get(l);
    	    		if(Namek.Nombre.equals(par_separado[0])){
    	    			int arriba = findparent(Namek,par_separado[1],0);
    	    			if(arriba == 0) {
    	    				if((Namek.Padre!=null)) {
    	    				for(int n = 0; n < Namekianos.size(); n++){
    	    					Namekiano finder = Namekianos.get(n);
    	    					if(finder.Nombre.equals(par_separado[1])){
    	    						if((Namek.Padre.Nombre.equals(finder.Padre.Nombre))){
    	    							printWriter.println(par_separado[0]+" Es hermano de "+par_separado[1]);
    	    						}else if(!Namek.Padre.Padre.Hijos.isEmpty()){
    	    							ArrayList<Namekiano> hijos = new ArrayList<Namekiano>();
    	    							hijos = Namek.Padre.Padre.Hijos;
    	    							for(Namekiano tio: hijos){
    	    								if(tio.Nombre.equals(par_separado[1])){
    	    									printWriter.println(par_separado[0]+" Es primo de "+par_separado[1]);
    	    								}
    	    							}
    	    						}	
    	    					}
    	    				}
    	    				}else {
    							printWriter.println("Enemigos");
    						}	
    	    				
    	    			}else if(arriba == 1){
    	    				if((Namek.Padre!=null)&&Namek.Padre.Nombre==par_separado[1]){
    	    					printWriter.println(par_separado[0]+" Es hijo de "+par_separado[1]);
    	    				}else {
    	    					printWriter.println(par_separado[0]+" Es hijo del hermano de "+par_separado[1]);
    	    				}
    	    			}else if(arriba == 2){
    	    				if((Namek.Padre.Padre!=null)&&Namek.Padre.Padre.Nombre.equals(par_separado[1])){
    	    					printWriter.println(par_separado[0]+" Es nieto de "+par_separado[1]);
    	    				}
    	    			}else if(arriba == 3){
    	    				if((Namek.Padre.Padre.Padre!=null)&&Namek.Padre.Padre.Padre.Nombre.equals(par_separado[1])){
    	    					printWriter.println(par_separado[0]+" Es bisnieto de "+par_separado[1]);
    	    				}
    	    			}else if(arriba == 4){
    	    				if((Namek.Padre.Padre.Padre.Padre!=null)&&Namek.Padre.Padre.Padre.Padre.Nombre.equals(par_separado[1])){
    	    					printWriter.println(par_separado[0]+" Es tatara-nieto de "+par_separado[1]);
    	    				}
    	    			}else if(arriba == -1){
    	    				ArrayList<Namekiano> hijos = new ArrayList<Namekiano>();
							hijos = Namek.Hijos;
							for(Namekiano hijo: hijos){
								if(hijo.Nombre.equals(par_separado[1])){
									printWriter.println(par_separado[0]+" Es padre de "+par_separado[1]);
								}
							}
    	    			}else if(arriba == -2){
    	    				printWriter.println(par_separado[0]+" Es abuelo de "+par_separado[1]);
    	    			}else if(arriba == -3){
    	    				printWriter.println(par_separado[0]+" Es bisabuelo de "+par_separado[1]);
    	    			}else if(arriba == -4){
    	    				printWriter.println(par_separado[0]+" Es tatara-abuelo de "+par_separado[1]);
    	    			}else if(arriba < -4) {
    	    				StringBuilder sb = new StringBuilder();
    	    				for(int p = -4; p > arriba; p--){
    	    					sb.append("tatara-");
    	    				}    	    				
    	    				printWriter.println(par_separado[0]+" Es tatara-"+sb+"abuelo de "+par_separado[1]);
    	    			}
    	    			break;
    	    		}
    	    		
    			}	    
     		}
    		printWriter.println();
    	}
    	printWriter.println();
    	printWriter.close();
    }

    public static int findparent(Namekiano Namek, String finder, int level){
    	Namekiano aux = new Namekiano();	
    	aux = Namek.Padre;
    	int i = 0;
    	if((aux!=null)&&(aux.visitado==0)) {
    		if((aux.Nombre.equals(finder))){
    			level++;
    			resultado = level;
        		return level;
        	}else{
        		level++;
        		Namek.visitado=1;
        		int a = findparent(aux,finder, level);
        		Namek.visitado=0;
        		if(a==0){
        			level--;
        		}else {
        			level = a;
        			return level;
        		}
        	}
    	}
    	
    	ArrayList<Namekiano> papan = new ArrayList<Namekiano>();
    	papan = Namek.Hijos;
    	if((!papan.isEmpty())){
    		for(Namekiano help : papan){
    			if(help.visitado==0) {
					if((help.Nombre.equals(finder))){
						level--;
						resultado = level;
		        		return level;
		        	}else{
		        		level--;
		        		help.Padre.visitado=1;
		        		int a = findparent(help,finder, level);
		        		help.Padre.visitado=0;
		        		if(a==0){
		        			level++;
		        		}else{
		        			level = a;
		        			return level;
		        		}
		        	}
    			}
    		}
    	}
    	
    	return 0;
    }
    
    public static boolean checkchild(ArrayList<Namekiano> Hijos){
    	boolean flag = false;
    	for(Namekiano namek: Hijos){
    		 if(namek.visitado==1){
    			 flag = true;
    		 }else{
    			 flag = false;
    		 }
    	}
    	return flag;
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
