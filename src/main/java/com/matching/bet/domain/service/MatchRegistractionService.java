package com.matching.bet.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matching.bet.domain.exception.MatchesNotFoundException;
import com.matching.bet.domain.model.Matches;
import com.matching.bet.domain.model.Options;
import com.matching.bet.domain.repository.MatchesRepository;

@Service
public class MatchRegistractionService {

	@Autowired
	private MatchesRepository matchesRepository;
	
	@Autowired
	private OptionsRegistrationService optionsRegistrationService;
	
	@Transactional
	public void updateOdds(Long matchesId) {		
		Long sum = totalBets(matchesId);
		
		optionsRegistrationService.updateOddsByMatch(matchesId, sum);
		
	}
	
	public Matches seekOrFail(Long matchesId) {
		return matchesRepository.findById(matchesId)
			.orElseThrow(() -> new MatchesNotFoundException(matchesId));
	}

	/**
	 * Valor total das apostas
	 * @return
	 */
	public Long totalBets(Long matchesId) {
		Matches match = seekOrFail(matchesId);
		return totalBets(match);
	}

	/**
	 * Valor total das apostas
	 * @return
	 */
	public Long totalBets(Matches match) {
		Long sum = 0L;
		for (Options option : match.getOptions()) {
			sum += optionsRegistrationService.sumTotalBetsOrFail(option.getId());
		}
		return Math.round(Math.floor((sum + match.getInsuranceAmount()) * (1 - match.juice())))  ;
	}

	/**
	 * Bet maximo calculado em funcao do lucro
	 * @return
	 */
	public Double maxBet(Long matchesId) {
		Matches match = seekOrFail(matchesId);
		return totalBets(match) * match.juice();
	}
}
