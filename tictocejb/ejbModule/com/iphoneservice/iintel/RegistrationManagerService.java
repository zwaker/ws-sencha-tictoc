/**
 * 
 */
package com.iphoneservice.iintel;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

import com.iphoneservice.iintel.entity.Ethnicity;
import com.iphoneservice.iintel.entity.Location;
import com.iphoneservice.iintel.entity.TTUsers;
import com.iphoneservice.iintel.entity.User;

/**
 * @author przaca
 * 
 */

@LocalBean
@Stateless
public class RegistrationManagerService {
	@PersistenceContext(unitName = "mypersistence")
	private EntityManager em;

	public String registerUser(TTUsers user, Location location, Ethnicity ethnicity) {
		// em.
		Session session = ((org.hibernate.ejb.EntityManagerImpl) em
				.getDelegate()).getSession();
		if (userExists(user)) {
			return "userexists";
		} else {
			Location locFromSource=new Location();
			Ethnicity ethFromSource = new Ethnicity();
			if (StringUtils.isNumeric(user.getLocationId()) || user.getLocationId().equals("-1")) {
				 locFromSource = em.find(Location.class, new Integer(
						user.getLocationId()).intValue());
				if (locFromSource == null) {
					return "locationdoesnotexists";
				}
			} else {
				locFromSource.setName(user.getLocationId());
				em.persist(locFromSource);
				//return "locationdoesnotexists";
			}
			if (StringUtils.isNumeric(user.getLocationId()) || user.getEthnicityId().equals("-1")) {
				 ethFromSource = em.find(Ethnicity.class, new Integer(
						user.getEthnicityId()).intValue());
				if (ethFromSource == null) {
					return "ethnicitydoesnotexists";
				}
			} else {
				ethFromSource.setName(user.getEthnicityId());
				em.persist(ethFromSource);
				//return "ethnicitydoesnotexists";
			}
			// em.persist(ethnicity);
			user.setLocation(locFromSource);
			user.setEthnicity(ethFromSource);
			em.persist(user);
			return "registrationsuccessful";
		}

	}

	public boolean userExists(TTUsers user) {

		User userFromSource = em.find(User.class, user.getUsername());
		if (userFromSource == null) {
			return false;
		}
		return true;
	}

}
