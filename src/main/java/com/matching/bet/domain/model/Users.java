package com.matching.bet.domain.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Users {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "users_id")
	private Long id; 
	
	@Column(name = "users_email", nullable = false)
	private String userEmail;

	@Column(name = "users_encrypted_password", nullable = false)
	private String cryptedPass;
	
	@Column(name = "users_status", nullable = false)
	private Short status;
	
	@Column(name = "users_name", nullable = false)
	private String userName;
	
	@Column(name = "users_gov_number", nullable = false)
	private String govNumber;
	
	@Column(name = "users_description")
	private String description;
	
	@Column(name = "users_photo_url")
	private String photo;
	
	@Column(name = "users_birthday", nullable = false)
	@Temporal(TemporalType.DATE)
	private Calendar birthday;

	@Column(name = "users_created_at", nullable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
    private Calendar createdAt;
	
	@Transient
	private Short media;
	
	@ManyToMany
	@JoinTable(name = "user_grouping", joinColumns = @JoinColumn(name = "users_id"),
			inverseJoinColumns = @JoinColumn(name = "grouping_id"))
	private Set<Grouping> groupings = new HashSet<>();
	
    @OneToMany(mappedBy="users")
	private List<SocialLogins> socialLogins;

	public boolean removeGroup(Grouping grouping) {
		return getGroupings().remove(grouping);
	}
	
	public boolean addGroup(Grouping grouping) {
		return getGroupings().add(grouping);
	}
	
	public boolean isNew() {
		return getId() == null;
	}
	
	public boolean hasSocial(Short media) {
		List<SocialLogins> socialLogins = null;
		return (socialLogins = getSocialLogins(media)) != null && !socialLogins.isEmpty();
	}
	
	public List<SocialLogins> getSocialLogins() {
		return getSocialLogins(null);
	}
	
	public List<SocialLogins> getSocialLogins(Short media) {
		if(media == null || this.socialLogins == null) {
			return this.socialLogins;
		}
		List<SocialLogins> socialLogins = new ArrayList<SocialLogins>();
		for (SocialLogins socialLogin : this.socialLogins) {
			if(socialLogin.getMedia().equals(media)) {
				socialLogins.add(socialLogin);
			}
		}
		return socialLogins;
	}

	public boolean hasSocial() {
		List<SocialLogins> socialLogins = null;
		return (socialLogins = getSocialLogins()) != null && !socialLogins.isEmpty();
	}

	public void addSocialLogin(SocialLogins socialLogin) {
		if(socialLogins == null) {
			socialLogins = new ArrayList<SocialLogins>();
		}
		
		socialLogins.add(socialLogin);
	}
	
}
