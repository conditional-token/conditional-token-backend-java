package com.matching.bet.domain.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Bookmakers {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookmakers_id")
	private Long id; 
	
	@Column(name = "bookmakers_name")
	private String name;

	@Column(name = "bookmakers_description")
	private String description;

	@Column(name = "bookmakers_created_at")
	@Temporal(TemporalType.TIMESTAMP)
    private Calendar createdAt;


	@Column(name = "bookmakers_status")
	private Integer status;

	@Column(name = "bookmakers_classification")
	private Integer classification;
	
	@OneToOne
	@JoinColumn(name = "balances_id",foreignKey = @ForeignKey(name = "fk_bookmakers_balances"))
	private Balances balances;
}
