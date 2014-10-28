package com.iphoneservice.iintel.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CONNECTION_MEDIUM database table.
 * 
 */
@Entity
@Table(name="CONNECTION_MEDIUM")
public class ConnectionMedium implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="MEDIUM_NAME")
	private String mediumName;

	@Column(name="TTUSER_ID")
	private String ttuserId;

	@Column(name="TTUSER_MEDIUM_USERNAME")
	private String ttuserMediumUsername;

    public ConnectionMedium() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMediumName() {
		return this.mediumName;
	}

	public void setMediumName(String mediumName) {
		this.mediumName = mediumName;
	}

	public String getTtuserId() {
		return this.ttuserId;
	}

	public void setTtuserId(String ttuserId) {
		this.ttuserId = ttuserId;
	}

	public String getTtuserMediumUsername() {
		return this.ttuserMediumUsername;
	}

	public void setTtuserMediumUsername(String ttuserMediumUsername) {
		this.ttuserMediumUsername = ttuserMediumUsername;
	}

}