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

import com.iphoneservice.iintel.entity.Ethnicity;
import com.iphoneservice.iintel.entity.Language;

/**
 * @author przaca
 * 
 */

@LocalBean
@Stateless
public class LanguageManagerService {
	@PersistenceContext(unitName = "mypersistence")
	private EntityManager em;

	public Language findLanguage(String searchWildCard) {
		// em.
		Session session = ((org.hibernate.ejb.EntityManagerImpl) em
				.getDelegate()).getSession();

		List<Language> itemList = session.createCriteria(Language.class)
				.add(Property.forName("name").eq(searchWildCard)).list();

		if (itemList!=null && itemList.size()>0) {
			return itemList.get(0);
		} else {
			Language tempLanguage=new Language();
			tempLanguage.setId(-1);
			tempLanguage.setName("No Record");
			return tempLanguage;
			
		}

	}

}
