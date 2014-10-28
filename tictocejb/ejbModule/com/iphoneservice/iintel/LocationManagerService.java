/**
 * 
 */
package com.iphoneservice.iintel;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.hibernate.Session;
import org.hibernate.criterion.Property;

import com.iphoneservice.iintel.entity.Location;

/**
 * @author przaca
 * 
 */

@LocalBean
@Stateless
public class LocationManagerService {
	@PersistenceContext(unitName = "mypersistence")
	private EntityManager em;

	public Location findLocation(String searchWildCard) {
		// em.
		Session session = ((org.hibernate.ejb.EntityManagerImpl) em
				.getDelegate()).getSession();

		List<Location> itemList = session.createCriteria(Location.class)
				.add(Property.forName("name").eq(searchWildCard)).list();

		if (itemList!=null && itemList.size()>0) {
			return itemList.get(0);
		} else {
			Location tempLoc=new Location();
			tempLoc.setId(-1);
			tempLoc.setName("No Record");
			return tempLoc;
			
		}

	}

}
