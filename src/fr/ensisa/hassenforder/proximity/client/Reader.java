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
                    case Protocol.REP_USERS : readOthers(readInt()); break;
                    default: break;
		}
		
	}
        
        public User readLogin(){
            String name = readString();
            int x = readInt();
            int y = readInt();
            int radius = readInt();
            int modeInt = readInt(); 
            Mode mode = null;
            if (modeInt == 1){ mode= Mode.VISIBLE; }
            else if (modeInt == 0){ mode = Mode.HIDDEN;}
            else{ mode= Mode.OCCUPIED;}
            
            User user = new User(name, x, y, radius, mode);
            
            return user;
        }
        
        public User readUser(){
            String name = readString();
            int x = readInt();
            int y = readInt();
            int radius = readInt();
            int modeInt = readInt(); 
            Mode mode = null;
            
            if (modeInt == 1){ mode= Mode.VISIBLE; }
            else if (modeInt == 0){ mode = Mode.HIDDEN;}
            else{ mode= Mode.OCCUPIED;}
            
            User user = new User(name, x, y, radius, mode);
            
            return user;
        }
        

      public List<User> readOthers(){
            List<User> users = null;
			return users;
      }
      
       public List<User> readOthers(int size){
            List<User> users = null;

            
            for (int i =0; i < size; i++){
                String name = readString();
                int x = readInt();
                int y = readInt();
                int radius = readInt();
                String modestring = readString();
                Mode mode = Mode.valueOf(modestring);
                
                User temp = new User(name, x, y, radius, mode);
                
                users.add(i,temp);
            }
          
            
            return users;
        }

}