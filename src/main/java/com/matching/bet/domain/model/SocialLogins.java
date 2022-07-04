package com.matching.bet.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class SocialLogins {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "social_logins_id")
	private Long id;
	
	@Column(name = "social_logins_media", nullable = false)
	private Short media;
	
	@Column(name = "social_logins_hash", nullable = false)
	private String hash;
	
	@Column(name = "social_logins_status", nullable = true)
	private Short status;
	
	@Column(name = "social_logins_email", nullable = false)
	private String email;
	
	@Column(name = "social_logins_name", nullable = true)
	private String name;
	
	@Column(name = "social_logins_photo_url", nullable = true)
	private String photoUrl;
	
	@ManyToOne
	@JoinColumn(name = "users_id",foreignKey = @ForeignKey(name = "fk_social_logins_users"))
	private Users users;
	
	public static SocialLogins from(Users user) {
		SocialLogins socialLogins = new SocialLogins();
		socialLogins.setEmail(user.getUserEmail());
		socialLogins.setHash(user.getCryptedPass());
		socialLogins.setMedia(user.getMedia());
		socialLogins.setStatus(user.getStatus());
		socialLogins.setName(user.getUserName());
		socialLogins.setPhotoUrl(user.getPhoto());
		socialLogins.setUsers(user);
		return socialLogins;
	}

}
