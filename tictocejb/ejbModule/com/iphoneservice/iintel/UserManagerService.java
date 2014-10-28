/**
 * 
 */
package com.iphoneservice.iintel;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.iphoneservice.iintel.entity.TTUsers;
import com.iphoneservice.iintel.entity.User;

/**
 * @author przaca
 * 
 */

@LocalBean
@Stateless
public class UserManagerService {
	@PersistenceContext(unitName = "mypersistence")
	private EntityManager em;

	/*public String registerUser(User user) {
		em.persist(user);
		return "successRegistration";
	}*/

	/*public String loginUser(TTUser user) {
		User userFromDB = em.find(User.class, user.getUsername());
		if (userFromDB != null) {
			if (userFromDB.getPassword().equals(user.getPassword())) {
				return "successLogin";
			} else {
				return "loginRetry";
			}
		} else {
			return "failedLogin";
		}
	}*/

}
