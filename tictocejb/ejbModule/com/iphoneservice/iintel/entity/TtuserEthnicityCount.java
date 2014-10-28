package com.iphoneservice.iintel.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TTUSER_ETHNICITY_COUNT database table.
 * 
 */
@Entity
@Table(name="TTUSER_ETHNICITY_COUNT")
public class TtuserEthnicityCount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int count;

	@Column(name="ETHNICITY_ID")
	private String ethnicityId;

	@Column(name="TTUSER_ID")
	private String ttuserId;

    public TtuserEthnicityCount() {
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

	public String getEthnicityId() {
		return this.ethnicityId;
	}

	public void setEthnicityId(String ethnicityId) {
		this.ethnicityId = ethnicityId;
	}

	public String getTtuserId() {
		return this.ttuserId;
	}

	public void setTtuserId(String ttuserId) {
		this.ttuserId = ttuserId;
	}

}