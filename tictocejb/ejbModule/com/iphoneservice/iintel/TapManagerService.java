/**
 * 
 */
package com.iphoneservice.iintel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Property;

import com.iphoneservice.iintel.entity.TTUsers;
import com.iphoneservice.iintel.entity.TtuserConnection;

/**
 * @author przaca
 * 
 */

@LocalBean
@Stateless
public class TapManagerService {
	@PersistenceContext(unitName = "mypersistence")
	private EntityManager em;

	@Inject
	private UserEthnicityManagerService userEthnicityManagerService;

	@Inject
	private UserLocationManagerService userLocationManagerService;

	@Inject
	private UserBirthYearManagerService userBirthYearManagerService;
	
	
	@Inject
	private UserLanguageManagerService languageManagerService;
	
	

	
	public UserLanguageManagerService getLanguageManagerService() {
		return languageManagerService;
	}

	public void setLanguageManagerService(
			UserLanguageManagerService languageManagerService) {
		this.languageManagerService = languageManagerService;
	}

	public UserBirthYearManagerService getUserBirthYearManagerService() {
		return userBirthYearManagerService;
	}

	public void setUserBirthYearManagerService(
			UserBirthYearManagerService userBirthYearManagerService) {
		this.userBirthYearManagerService = userBirthYearManagerService;
	}

	public UserEthnicityManagerService getUserEthnicityManagerService() {
		return userEthnicityManagerService;
	}

	public void setUserEthnicityManagerService(
			UserEthnicityManagerService userEthnicityManagerService) {
		this.userEthnicityManagerService = userEthnicityManagerService;
	}

	public UserLocationManagerService getUserLocationManagerService() {
		return userLocationManagerService;
	}

	public void setUserLocationManagerService(
			UserLocationManagerService userLocationManagerService) {
		this.userLocationManagerService = userLocationManagerService;
	}

	public void performTapRequest(TTUsers user) {
		userEthnicityManagerService.sendRequestToUserByEthnicity(user);
		userLocationManagerService.sendRequestToUserByLocation(user);
		userBirthYearManagerService.sendRequestToUserByBirthYear(user);
		languageManagerService.sendRequestToUserByBirthYear(user);
	}
	
	public boolean userCanMakeTapRequest(TTUsers user) {
		Date today = new Date();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = df.format(today);
		Session session = ((org.hibernate.ejb.EntityManagerImpl) em
				.getDelegate()).getSession();

		List<TtuserConnection> itemList = session.createCriteria(TtuserConnection.class)
				.add(Property.forName("ttuserId").eq(new Integer(user.getId()).toString()))
				.add(Property.forName("requestSentReceiveAcceptedDate").eq(dateString))
				.list();
		if(itemList!=null && itemList.size()>0){
			if(userEthnicityManagerService.maximumRequestSentReached(user))
				return false;
		}else{
			return true;
		}
		return true;
	}

	public List retrieveTapRequests(TTUsers user, int pageSize, int pageNumber) {
		Session session = ((org.hibernate.ejb.EntityManagerImpl) em
				.getDelegate()).getSession();

		Criteria c = session
				.createCriteria(TtuserConnection.class)
				.add(Property.forName("ttuserId").eq(
						new Integer(user.getId()).toString()))
				.add(Property.forName("status").ne("REMOVED"))
				.add(Property.forName("requestType").eq("REQUEST_SENT"));

		c = c.setFirstResult(pageSize * (pageNumber - 1));
		c.setMaxResults(pageSize);
		List<TtuserConnection> ttuserConnectionList = c.list();
		for (TtuserConnection ttuserConnection : ttuserConnectionList) {
			TTUsers connectionTtUserTemp = em.find(TTUsers.class, new Integer(ttuserConnection.getConnectionTtuserId()));
			ttuserConnection.setConnectionTtuserName(connectionTtUserTemp.getUsername());
			ttuserConnection.setConnectionFirstName(connectionTtUserTemp.getFirstName());
			ttuserConnection.setConnectionLastName(connectionTtUserTemp.getLastName());
			ttuserConnection.setConnectionMediumName(connectionTtUserTemp.getMediumName());
			ttuserConnection.setConnectionPrefferedMediumUserName(connectionTtUserTemp.getPreferredMediumUsername());
		}
		
		
		return ttuserConnectionList;
	}
	
	public List retrieveTocRequests(TTUsers user, int pageSize, int pageNumber) {
		Session session = ((org.hibernate.ejb.EntityManagerImpl) em
				.getDelegate()).getSession();

		Criteria c = session
				.createCriteria(TtuserConnection.class)
				.add(Property.forName("ttuserId").eq(
						new Integer(user.getId()).toString()))
				.add(Property.forName("status").ne("REMOVED"))
				.add(Property.forName("requestType").eq("REQUEST_RECEIVED"));

		c = c.setFirstResult(pageSize * (pageNumber - 1));
		c.setMaxResults(pageSize);
		List<TtuserConnection> ttuserConnectionList = c.list();
		for (TtuserConnection ttuserConnection : ttuserConnectionList) {
			TTUsers connectionTtUserTemp = em.find(TTUsers.class, new Integer(ttuserConnection.getConnectionTtuserId()));
			ttuserConnection.setConnectionTtuserName(connectionTtUserTemp.getUsername());
			ttuserConnection.setConnectionFirstName(connectionTtUserTemp.getFirstName());
			ttuserConnection.setConnectionLastName(connectionTtUserTemp.getLastName());
			ttuserConnection.setConnectionMediumName(connectionTtUserTemp.getMediumName());
			ttuserConnection.setConnectionPrefferedMediumUserName(connectionTtUserTemp.getPreferredMediumUsername());
		}
		
		
		return ttuserConnectionList;
	}
	
	public boolean isConfirmSuccessful(TtuserConnection ttuserConnection){
		TtuserConnection ttuserConnectionFromDB=em.find(TtuserConnection.class, ttuserConnection.getId());
		ttuserConnectionFromDB.setStatus("REQUEST_ACCEPTED");
		return true;
	}

	public boolean isRejectSuccessful(TtuserConnection ttuserConnection){
		TtuserConnection ttuserConnectionFromDB=em.find(TtuserConnection.class, ttuserConnection.getId());
		ttuserConnectionFromDB.setStatus("REQUEST_REJECTED");
		return true;
	}

}
