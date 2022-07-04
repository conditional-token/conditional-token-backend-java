package com.matching.bet.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.matching.bet.api.model.input.UserInput;
import com.matching.bet.domain.model.Users;

@Component
public class UserInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Users toDomainObject(UserInput userInput) {
		return modelMapper.map(userInput, Users.class);
	}
	
	public void copyToDomainObject(UserInput userInput, Users users) {
		modelMapper.map(userInput, users);
	}

}
