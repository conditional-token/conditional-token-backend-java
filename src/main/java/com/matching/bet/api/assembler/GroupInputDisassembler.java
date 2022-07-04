package com.matching.bet.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.matching.bet.api.model.input.GroupInput;
import com.matching.bet.domain.model.Grouping;

@Component
public class GroupInputDisassembler {
	 
	@Autowired
    private ModelMapper modelMapper;
	
	public Grouping toDomainObject(GroupInput groupInput) {
		return modelMapper.map(groupInput, Grouping.class);
	}
	
	public void copyToDomainObject(GroupInput groupInput, Grouping grouping) {
		modelMapper.map(groupInput, grouping);
	}

}
