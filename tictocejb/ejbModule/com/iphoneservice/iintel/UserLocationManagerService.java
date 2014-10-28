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

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Property;
import org.hibernate.transform.Transformers;

import com.iphoneservice.iintel.entity.TTUsers;
import com.iphoneservice.iintel.entity.TtuserConnection;
import com.iphoneservice.iintel.entity.TtuserEthnicityCount;
import com.iphoneservice.iintel.entity.TtuserLocationCount;
import com.iphoneservice.iintel.entity.TtuserRequestReceived;
import com.iphoneservice.iintel.entity.TtuserRequestSent;
import com.iphoneservice.iintel.entity.User;

/**
 * @author przaca
 * 
 */

@LocalBean
@Stateless
public class UserLocationManagerService {
	@PersistenceContext(unitName = "mypersistence")
	private EntityManager em;

	@Inject
	private UserEthnicityManagerService userEthnicityManagerService;

	public UserEthnicityManagerService getUserEthnicityManagerService() {
		return userEthnicityManagerService;
	}

	public void setUserEthnicityManagerService(
			UserEthnicityManagerService userEthnicityManagerService) {
		this.userEthnicityManagerService = userEthnicityManagerService;
	}

	public void sendRequestToUserByLocation(TTUsers user) {
		if (!maximumRequestSentReached(user)) {
			TTUsers potentialConnectedUser = null;
			boolean connected = true;
			boolean maximumReqReceivedReached=true;
			potentialConnectedUser=findUserByLocation(user, potentialConnectedUser,connected,maximumReqReceivedReached);
			if (potentialConnectedUser != null) {
				Date today = new Date();
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				String dateString = df.format(today);

				// primary user
				TtuserConnection ttuserConnection = new TtuserConnection();
				ttuserConnection.setTtuserId(new Integer(user.getId())
						.toString());
				ttuserConnection.setConnectionTtuserId(new Integer(
						potentialConnectedUser.getId()).toString());
				ttuserConnection.setStatus("REQUEST_SENT");
				ttuserConnection.setRequestSentReceiveAcceptedDate(dateString);
				ttuserConnection.setRequestType("REQUEST_SENT");
				em.merge(ttuserConnection);

				// potentially connected user
				TtuserConnection potetntialUserConnection = new TtuserConnection();
				potetntialUserConnection.setTtuserId(new Integer(
						potentialConnectedUser.getId()).toString());
				potetntialUserConnection.setConnectionTtuserId(new Integer(user
						.getId()).toString());
				potetntialUserConnection.setStatus("REQUEST_RECEIVED");
				potetntialUserConnection
						.setRequestSentReceiveAcceptedDate(dateString);
				potetntialUserConnection.setRequestType("REQUEST_RECEIVED");
				em.merge(potetntialUserConnection);
			}
		}
	}

	public TTUsers findUserByLocation(TTUsers user,TTUsers potentialConnectedUser, boolean connected,
			boolean maximumReqReceivedReached) {
		// em.
		Session session = ((org.hibernate.ejb.EntityManagerImpl) em
				.getDelegate()).getSession();
		// TTUsers tempUser=null;
		if (connected || maximumReqReceivedReached) {
			int count = findLocationCountOfUser(user);
			potentialConnectedUser = retrieveNthRecordByLocation(count, user);
			if (potentialConnectedUser == null)
				return potentialConnectedUser;
			maximumReqReceivedReached = maximumRequestReceivedReached(potentialConnectedUser);

			connected = isConnectionTTUserAlreadyConnectedSomehow(user,
					potentialConnectedUser);
			potentialConnectedUser=findUserByLocation(user,  potentialConnectedUser,connected,maximumReqReceivedReached);
			return potentialConnectedUser;
		} else {
			return potentialConnectedUser;
		}

	}

	public int findLocationCountOfUser(TTUsers user) {
		Session session = ((org.hibernate.ejb.EntityManagerImpl) em
				.getDelegate()).getSession();
		TTUsers tempUser = (TTUsers) session.createCriteria(TTUsers.class)
				.add(Property.forName("username").eq(user.getUsername()))
				.uniqueResult();
		int locationCount = 0;
		if (tempUser != null) {// && tempUserList.size()>0) {
			// TTUsers tempUser = tempUserList.get(0);
			List<TtuserLocationCount> locationCountList = session
					.createCriteria(TtuserLocationCount.class)
					.add(Property.forName("ttuserId").eq(
							new Integer(tempUser.getId()).toString()))
					.add(Property.forName("locationId")
							.eq(user.getLocationId())).list();
			TtuserLocationCount ttuserLocationCount = null;

			if (locationCountList != null & locationCountList.size() > 0) {
				ttuserLocationCount = locationCountList.get(0);
				locationCount = ttuserLocationCount.getCount();
				ttuserLocationCount.setCount(locationCount + 1);
				// we increase the count here
				em.merge(ttuserLocationCount);
			} else {

				ttuserLocationCount = new TtuserLocationCount();
				ttuserLocationCount.setTtuserId(new Integer(tempUser.getId())
						.toString());
				ttuserLocationCount.setLocationId(user.getLocationId());
				ttuserLocationCount.setCount(0);
				em.merge(ttuserLocationCount);
			}

		}
		return locationCount;
	}

	public TTUsers retrieveNthRecordByLocation(int count, TTUsers user) {

		Session session = ((org.hibernate.ejb.EntityManagerImpl) em
				.getDelegate()).getSession();
		SQLQuery query = session
				.createSQLQuery("SELECT id as id,FIRST_NAME as firstName,LAST_NAME as lastName,username as username "
						+ " FROM TTUSERS "
						+ " WHERE username <> '"
						+ user.getUsername()
						+ "' AND location_id = '"
						+ user.getLocationId()
						+ "' ORDER BY ID LIMIT "
						+ count
						+ " ,1");
		query.setResultTransformer(Transformers.aliasToBean(TTUsers.class));
		TTUsers userByLocation = (TTUsers) query.uniqueResult();
		return userByLocation;

	}

	public boolean isConnectionTTUserAlreadyConnectedSomehow(TTUsers user,
			TTUsers possibleConnectionTTUser) {

		Session session = ((org.hibernate.ejb.EntityManagerImpl) em
				.getDelegate()).getSession();
		List<TtuserConnection> ttUserConnectionList = session
				.createCriteria(TtuserConnection.class)
				.add(Property.forName("ttuserId").eq(
						new Integer(user.getId()).toString()))
				.add(Property.forName("connectionTtuserId").eq(
						new Integer(possibleConnectionTTUser.getId())
								.toString()))
				.add(Property.forName("status").ne("REMOVED")).list();

		if (ttUserConnectionList != null && ttUserConnectionList.size() > 0)
			return true;
		return false;

	}

	public boolean userExists(TTUsers user) {

		User userFromSource = em.find(User.class, user.getUsername());
		if (userFromSource == null) {
			return false;
		}
		return true;
	}

	public boolean maximumRequestSentReached(TTUsers user) {
		Date today = new Date();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = df.format(today);
		Session session = ((org.hibernate.ejb.EntityManagerImpl) em
				.getDelegate()).getSession();
		List<TtuserRequestSent> ttRequestSentList = session
				.createCriteria(TtuserRequestSent.class)
				.add(Property.forName("ttuserId").eq(
						new Integer(user.getId()).toString()))
				.add(Property.forName("requestDate").eq(dateString)).list();
		if (ttRequestSentList != null & ttRequestSentList.size() > 0) {
			TtuserRequestSent ttRequestSentObj = ttRequestSentList.get(0);
			if (ttRequestSentObj.getCount() >= 5)
				return true;

		} else {
			TtuserRequestSent ttRequestSentObj = new TtuserRequestSent();
			ttRequestSentObj.setTtuserId(new Integer(user.getId()).toString());
			ttRequestSentObj.setRequestDate(dateString);
			ttRequestSentObj.setCount(0);
			em.merge(ttRequestSentObj);

		}
		return false;

	}

	public boolean maximumRequestReceivedReached(TTUsers user) {
		Date today = new Date();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = df.format(today);
		Session session = ((org.hibernate.ejb.EntityManagerImpl) em
				.getDelegate()).getSession();
		List<TtuserRequestReceived> ttRequestReceivedList = session
				.createCriteria(TtuserRequestSent.class)
				.add(Property.forName("ttuserId").eq(
						new Integer(user.getId()).toString()))
				.add(Property.forName("requestDate").eq(dateString)).list();
		if (ttRequestReceivedList != null & ttRequestReceivedList.size() > 0) {
			TtuserRequestReceived ttRequestReceivedObj = ttRequestReceivedList
					.get(0);
			if (ttRequestReceivedObj.getCount() >= 5)
				return true;

		} else {
			TtuserRequestReceived ttRequestReceivedObj = new TtuserRequestReceived();
			ttRequestReceivedObj.setTtuserId(new Integer(user.getId())
					.toString());
			ttRequestReceivedObj.setRequestDate(dateString);
			ttRequestReceivedObj.setCount(0);
			em.merge(ttRequestReceivedObj);

		}
		return false;

	}

}
