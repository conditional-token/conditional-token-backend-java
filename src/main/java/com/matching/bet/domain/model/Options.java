package com.matching.bet.domain.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Options {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "options_id")
	private Long id; 
	
	@Column(name = "options_title", nullable = false)
	private String title;

	@Column(name = "options_description")
	private String description;

	@Column(name = "options_created_at")
	@Temporal(TemporalType.TIMESTAMP)
    private Calendar createdAt;

	@Column(name = "options_odd", nullable = false)
	private Double odd;
	
	@ManyToOne
	@JoinColumn(name = "matches_id",foreignKey = @ForeignKey(name = "fk_options_matches"))
	private Matches matches;

}
