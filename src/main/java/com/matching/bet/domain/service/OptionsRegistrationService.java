package com.matching.bet.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matching.bet.domain.model.Options;
import com.matching.bet.domain.repository.OptionsRepository;

@Service
public class OptionsRegistrationService {

	@Autowired
	private OptionsRepository optionsRepository;
	
	@Autowired
	private BetsResgistrationService betsResgistrationService;

	public void updateOddsByMatch(Long matchesId, Long sumAllOptions) {
		for (Options option : findByMatchesIdOrFail(matchesId)) {
			//multiplica sumAllOptions por 1.0 para forcar a conversao para Double 
			option.setOdd((1.0 * sumAllOptions)/sumTotalBetsOrFail(option.getId()));//sum/amount
		}		
	}
	
	public Iterable<Options> findByMatchesIdOrFail(Long matchesId) {
		return optionsRepository.findByMatchesId(matchesId);
	}
	
	public Long sumTotalBetsOrFail(Long optionsId) {
		return betsResgistrationService.sumTotalBetsOrFail(optionsId);
	}

}
