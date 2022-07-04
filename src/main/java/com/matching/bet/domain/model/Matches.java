package com.matching.bet.domain.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ForeignKey;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Matches {
	
	private static final Double JUICE = 0.1; // juice minimo
	private static final Double BOOKMAKER_JUICE_PART = 0.3; // parte cabivel ao bookmaker no juice

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "matches_id")
	private Long id; 
	
	@Column(name = "matches_title", nullable = false)
	private String title;

	@Column(name = "matches_description", nullable = false)
	private String description;

	@Column(name = "matches_created_at", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
    private Calendar createdAt;

	@Column(name = "matches_expired_at", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
    private Calendar expiredAt;

	@Column(name = "matches_visibility", nullable = false)
	private Integer visibility;

	@Column(name = "matches_insurance_amount", nullable = false)
	private Integer insuranceAmount;
	
	@ManyToOne
	@JoinColumn(name = "bookmakers_id",foreignKey = @ForeignKey(name = "fk_matches_bookmakers"))
	private Bookmakers bookmakers;
	
	@OneToMany
	@JoinColumn(name = "matches_id",foreignKey = @ForeignKey(name = "fk_options_matches"))
	private List<Options> options;

	/**
	 * Fracao de lucro a ser rateado entre o bookmaker e o servico
	 * @return
	 */
	public Double juice() {		
		return JUICE ;
	}
	
	/**
	 * Parte do juice cabivel ao bookmaker
	 * @return
	 */
	public Double bookMakerJuice() {
		return juice() * BOOKMAKER_JUICE_PART;
	}


}
