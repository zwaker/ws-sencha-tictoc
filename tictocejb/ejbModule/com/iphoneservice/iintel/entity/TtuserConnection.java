package com.iphoneservice.iintel.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TTUSER_CONNECTION database table.
 * 
 */
@Entity
@Table(name="TTUSER_CONNECTION")
public class TtuserConnection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="CONNECTION_TTUSER_ID")
	private String connectionTtuserId;

	@Column(name="REQUEST_SENT_RECEIVE_ACCEPTED_DATE")
	private String requestSentReceiveAcceptedDate;

	@Column(name="REQUEST_TYPE")
	private String requestType;

	private String status;

	@Column(name="TTUSER_ID")
	private String ttuserId;

	@Transient
	private String connectionTtuserName;
	
	@Transient
	private String connectionFirstName;
	
	@Transient
	private String connectionLastName;
	
	@Transient
	private String connectionMediumName;
	
	@Transient
	private String connectionPrefferedMediumUserName;
	
	
    public String getConnectionMediumName() {
		return connectionMediumName;
	}

	public void setConnectionMediumName(String connectionMediumName) {
		this.connectionMediumName = connectionMediumName;
	}

	public String getConnectionPrefferedMediumUserName() {
		return connectionPrefferedMediumUserName;
	}

	public void setConnectionPrefferedMediumUserName(
			String connectionPrefferedMediumUserName) {
		this.connectionPrefferedMediumUserName = connectionPrefferedMediumUserName;
	}

	public String getConnectionTtuserName() {
		return connectionTtuserName;
	}

	public void setConnectionTtuserName(String connectionTtuserName) {
		this.connectionTtuserName = connectionTtuserName;
	}

	public String getConnectionFirstName() {
		return connectionFirstName;
	}

	public void setConnectionFirstName(String connectionFirstName) {
		this.connectionFirstName = connectionFirstName;
	}

	public String getConnectionLastName() {
		return connectionLastName;
	}

	public void setConnectionLastName(String connectionLastName) {
		this.connectionLastName = connectionLastName;
	}

	public TtuserConnection() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConnectionTtuserId() {
		return this.connectionTtuserId;
	}

	public void setConnectionTtuserId(String connectionTtuserId) {
		this.connectionTtuserId = connectionTtuserId;
	}

	public String getRequestSentReceiveAcceptedDate() {
		return this.requestSentReceiveAcceptedDate;
	}

	public void setRequestSentReceiveAcceptedDate(String requestSentReceiveAcceptedDate) {
		this.requestSentReceiveAcceptedDate = requestSentReceiveAcceptedDate;
	}

	public String getRequestType() {
		return this.requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTtuserId() {
		return this.ttuserId;
	}

	public void setTtuserId(String ttuserId) {
		this.ttuserId = ttuserId;
	}

}