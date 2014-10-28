package com.iphoneservice.iintel.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TTUsers database table.
 * 
 */
@Entity
public class TTUsers implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String active;

	private String birthyear;

	@Column(name = "FIRST_NAME")
	private String firstName;

	private String gender;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "MEDIUM_NAME")
	private String mediumName;

	private String password;

	@Column(name = "PREFERRED_MEDIUM_USERNAME")
	private String preferredMediumUsername;

	@Lob()
	@Column(name = "profile_image")
	private byte[] profileImage;

	private String username;

	// bi-directional many-to-one association to Ethnicity
	@ManyToOne
	private Ethnicity ethnicity;

	// bi-directional many-to-one association to Language
	@ManyToOne
	private Language language;

	// bi-directional many-to-one association to Location
	@ManyToOne
	private Location location;

	@Transient
	private String ethnicityId;
	@Transient
	private String locationId;
	
	@Transient
	private String languageId;
	
	

	public String getLanguageId() {
		return languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	public String getEthnicityId() {
		return ethnicityId;
	}

	public void setEthnicityId(String ethnicityId) {
		this.ethnicityId = ethnicityId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public TTUsers() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getBirthyear() {
		return this.birthyear;
	}

	public void setBirthyear(String birthyear) {
		this.birthyear = birthyear;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMediumName() {
		return this.mediumName;
	}

	public void setMediumName(String mediumName) {
		this.mediumName = mediumName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPreferredMediumUsername() {
		return this.preferredMediumUsername;
	}

	public void setPreferredMediumUsername(String preferredMediumUsername) {
		this.preferredMediumUsername = preferredMediumUsername;
	}

	public byte[] getProfileImage() {
		return this.profileImage;
	}

	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Ethnicity getEthnicity() {
		return this.ethnicity;
	}

	public void setEthnicity(Ethnicity ethnicity) {
		this.ethnicity = ethnicity;
	}

	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}