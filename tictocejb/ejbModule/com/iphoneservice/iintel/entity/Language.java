package com.iphoneservice.iintel.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the LANGUAGE database table.
 * 
 */
@Entity
public class Language implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String name;

	//bi-directional many-to-one association to TTUsers
	@OneToMany(mappedBy="language")
	private Set<TTUsers> ttusers;

    public Language() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public Set<TTUsers> getTtusers() {
		return this.ttusers;
	}

	public void setTtusers(Set<TTUsers> ttusers) {
		this.ttusers = ttusers;
	}*/
	
}