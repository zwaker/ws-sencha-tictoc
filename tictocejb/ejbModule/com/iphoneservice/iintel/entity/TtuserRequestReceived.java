package com.iphoneservice.iintel.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TTUSER_REQUEST_RECEIVED database table.
 * 
 */
@Entity
@Table(name="TTUSER_REQUEST_RECEIVED")
public class TtuserRequestReceived implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int count;

	@Column(name="REQUEST_DATE")
	private String requestDate;

	@Column(name="TTUSER_ID")
	private String ttuserId;

    public TtuserRequestReceived() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getTtuserId() {
		return this.ttuserId;
	}

	public void setTtuserId(String ttuserId) {
		this.ttuserId = ttuserId;
	}

}