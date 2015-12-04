package fr.ensisa.hassenforder.proximity.client;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Mode;
import fr.ensisa.hassenforder.proximity.model.Preference;
import fr.ensisa.hassenforder.proximity.model.User;

public class Reader extends BasicAbstractReader {
        
    
       
	
	public Reader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {
		type = readInt ();
		switch (type) {
                    case Protocol.REP_KO : break;
                    case Protocol.REP_LOGIN: readLogin();  break;
                    case Protocol.REP_USER : readUser(); break;
                   // case Protocol.REP_USERS : readOthers(); break;
                    default: break;
		}
		
	}
        
        public User readLogin(){
            String name = readString();
            int x = readInt();
            int y = readInt();
            int radius = readInt();
            String modestring = readString(); // le mode je le d�finie par 3 chiffres 
            Mode mode = Mode.valueOf(modestring);
            
            User user = new User(name, x, y, radius, mode);
            
            return user;
        }
        
        public User readUser(){
            String name = readString();
            int x = readInt();
            int y = readInt();
            int radius = readInt();
            String modestring = readString();
            Mode mode = Mode.valueOf(modestring);
            
            User user = new User(name, x, y, radius, mode);
            
            return user;
        }
        
      /*  public List<User> readOthers(){
            List<User> users;
            
          
            
            return users;
        }*/

}