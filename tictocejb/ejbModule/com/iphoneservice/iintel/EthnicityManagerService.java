/**
 * 
 */
package com.iphoneservice.iintel;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.criterion.Property;

import com.iphoneservice.iintel.entity.Ethnicity;
import com.iphoneservice.iintel.entity.Location;

/**
 * @author przaca
 * 
 */

@LocalBean
@Stateless
public class EthnicityManagerService {
	@PersistenceContext(unitName = "mypersistence")
	private EntityManager em;

	public Ethnicity findEthnicity(String searchWildCard) {
		// em.
		Session session = ((org.hibernate.ejb.EntityManagerImpl) em
				.getDelegate()).getSession();

		List<Ethnicity> itemList = session.createCriteria(Ethnicity.class)
				.add(Property.forName("name").eq(searchWildCard)).list();

		if (itemList!=null && itemList.size()>0) {
			return itemList.get(0);
		} else {
			Ethnicity tempEth=new Ethnicity();
			tempEth.setId(-1);
			tempEth.setName("No Record");
			return tempEth;
			
		}

	}

}
