package fr.ensisa.hassenforder.proximity.client;

import java.io.OutputStream;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Mode;

public class Writer extends BasicAbstractWriter {

	public Writer(OutputStream outputStream) {
		super (outputStream);
	}
        
    public void send(){
        
    }
        
    public void requestLogin(String name) {
       writeString(Protocol.REP_LOGIN);
    }
    
    public void updateRadius(int rad){
        writeInt(Protocol.REP_OK);
    }
    
    public void updateMove(int x, int y){
        
    }
    
    public void searchNear(){
        
    }
    
    public void searchOther(){
        
    }
    
    public void updateMode(){
        
    }
    
    //Finir liste

}
