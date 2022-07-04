package com.matching.bet.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.matching.bet.api.openapi.controller.MatchesControllerOpenApi;
import com.matching.bet.domain.service.MatchRegistractionService;

@RestController
@RequestMapping(path = "/matches", produces = MediaType.APPLICATION_JSON_VALUE)
public class MatchesController implements MatchesControllerOpenApi{

	@Autowired
	private MatchRegistractionService matchRegistractionService;
	
	@Override
	@PutMapping("/{matchesId}/odds")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateOdds(@PathVariable Long matchesId) {
		matchRegistractionService.updateOdds(matchesId);
	}
}
