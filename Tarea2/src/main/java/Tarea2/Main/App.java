package Tarea2.Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class App{
	
	static Namekiano NamekianoGlobal;
	
    public static void main( String[] args ) throws NumberFormatException, IOException{
    	ArrayList<Namekiano> Namekianos = new ArrayList<Namekiano>();
    	BufferedReader filein = new BufferedReader( new FileReader( "tarea2.in" ) );
    	int casos = Integer.parseInt(filein.readLine());
    	while(casos>0){
    		while(true){
    			String nombres = filein.readLine();
    			if(nombres.length()==1)
    				break;
    			String nombres_separados[] = nombres.split(" ");
    			Namekiano Padre = new Namekiano();
				Namekiano Hijo = new Namekiano();
    			if(Namekianos.isEmpty()){
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
    			}else if(findnamekiano(Namekianos,nombres_separados[1])) {
    				Padre.Nombre=nombres_separados[0];
    				Hijo = NamekianoGlobal;
    				Hijo.Padre = Padre;
    				ArrayList<Namekiano> Hijos = new ArrayList<Namekiano>();
    				Hijos.add(Hijo);
    				Padre.Hijos = Hijos;
    				Namekianos.add(Padre);
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
    		casos--;
    	}
    	
    	for(int i =0; i < Namekianos.size(); i++){
    		System.out.println(Namekianos.get(i).Nombre);
    	}
    	
    	filein.close();
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
}
