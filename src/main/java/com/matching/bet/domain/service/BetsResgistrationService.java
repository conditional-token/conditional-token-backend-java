package com.matching.bet.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matching.bet.domain.repository.BetsRepository;

@Service
public class BetsResgistrationService {

	@Autowired
	private BetsRepository betsRepository;

	
	public Long sumTotalBetsOrFail(Long optionsId) {
		return betsRepository.sumBetsByOptions(optionsId);
	}
}
