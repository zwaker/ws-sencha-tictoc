package com.iphoneservice.iintel.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TTUSER_EHTNICITY_LOCATION_AGE_COUNT database table.
 * 
 */
@Entity
@Table(name="TTUSER_EHTNICITY_LOCATION_AGE_COUNT")
public class TtuserEhtnicityLocationAgeCount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="BIRTH_YEAR")
	private String birthYear;

	private int count;

	@Column(name="ETHNICITY_ID")
	private String ethnicityId;

	@Column(name="LOCATION_ID")
	private String locationId;

	@Column(name="TTUSER_ID")
	private String ttuserId;

    public TtuserEhtnicityLocationAgeCount() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBirthYear() {
		return this.birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
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

	public String getLocationId() {
		return this.locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getTtuserId() {
		return this.ttuserId;
	}

	public void setTtuserId(String ttuserId) {
		this.ttuserId = ttuserId;
	}

}