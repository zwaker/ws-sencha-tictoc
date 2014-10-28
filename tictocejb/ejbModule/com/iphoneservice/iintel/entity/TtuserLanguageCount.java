package com.iphoneservice.iintel.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TTUSER_LANGUAGE_COUNT database table.
 * 
 */
@Entity
@Table(name="TTUSER_LANGUAGE_COUNT")
public class TtuserLanguageCount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int count;

	@Column(name="LANGUAGE_ID")
	private String languageId;

	@Column(name="TTUSER_ID")
	private String ttuserId;

    public TtuserLanguageCount() {
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

	public String getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	public String getTtuserId() {
		return this.ttuserId;
	}

	public void setTtuserId(String ttuserId) {
		this.ttuserId = ttuserId;
	}

}