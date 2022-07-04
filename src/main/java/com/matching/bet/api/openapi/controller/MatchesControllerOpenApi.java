package com.matching.bet.api.openapi.controller;

import com.matching.bet.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Matches")
public interface MatchesControllerOpenApi {

	@ApiOperation("Atualizar odds dos matches")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Odds atualizado"),
		@ApiResponse(code = 404, message = "Match não encontrado", response = Problem.class)
	})
	void updateOdds(Long matchesId);

}
