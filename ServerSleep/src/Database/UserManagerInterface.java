/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import java.util.List;
import Server.User;



public interface UserManagerInterface {
    
        public void connect();

	public void disconnect();

	public void createUser(User user);

	//public User checkPassword(User user);
        
        //public User getUserByDNI(String dni);
        
	//public void deleteUser(User user);


}
