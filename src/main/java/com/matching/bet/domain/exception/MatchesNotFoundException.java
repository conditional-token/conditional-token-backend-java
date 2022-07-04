package com.matching.bet.domain.exception;

public class MatchesNotFoundException extends EntityNotFoundException{
	
	private static final long serialVersionUID = 1L;

	public MatchesNotFoundException(String message) {
		super(message);		
	}
	
	public MatchesNotFoundException(Long matchesId) {
		this(String.format("Não existe um match com código %d", matchesId));
	}

}
