/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Server.*;
import java.sql.Connection;




public class UserManager implements UserManagerInterface {
	private EntityManager em;
        private Connection c;
	
        public UserManager(Connection con){
            this.c=con;
        }
	@Override
	public void connect() {
		em = Persistence.createEntityManagerFactory("SleepControlProjectPU").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
	}

	@Override
	public void disconnect() {
		em.close();
	}

	@Override
	public void createUser(User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}
        @Override
	public User getUserByDNI(String dni) {
		Query q = em.createNativeQuery("SELECT patient_dni, password, from patients WHERE patient_dni =?", User.class);
		q.setParameter(1,dni);   
		User user = (User) q.getSingleResult();
		return user;	
	}
	
	

	public void deleteUser(User user) {
		em.getTransaction().begin();
		em.remove(user);
		em.getTransaction().commit();
		em.close();
		
	}
	
	@Override
	public User checkPassword(User userps) {
		User user = null;
		try {
			Query q = em.createNativeQuery("SELECT * FROM Users WHERE patient_dni = ? AND password = ?", User.class);
			q.setParameter(1, userps.getUsername());
			q.setParameter(2, userps.getPassword());
			user = (User) q.getSingleResult();
		} catch (NoResultException nre) {
			// This is what happens when no result is retrieved
			return null;
		}
		return user;
	}
        
       


}
