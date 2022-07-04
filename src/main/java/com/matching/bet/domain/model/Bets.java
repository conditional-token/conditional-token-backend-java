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
public class Bets {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bets_id")
	private Long id; 
	
	@Column(name = "bets_short_description", nullable = true)
	private String shortDescription;
	
	@Column(name = "bets_hash", nullable = false)
	private String hash;

	@Column(name = "bets_odd", nullable = false)
	private Double odds;

	@Column(name = "bets_created_at", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
    private Calendar createdAt;

	@Column(name = "bets_status", nullable = false)
	private Integer status;

	@Column(name = "bets_amount", nullable = false)
	private Long amount;

	@Column(name = "options_id", nullable = false)
	private Integer optionsId;
	
}
