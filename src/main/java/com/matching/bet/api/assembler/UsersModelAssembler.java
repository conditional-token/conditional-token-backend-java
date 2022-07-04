package com.matching.bet.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.matching.bet.api.BackLinks;
import com.matching.bet.api.controller.UsersController;
import com.matching.bet.api.model.UsersModel;
import com.matching.bet.core.security.BackSecurity;
import com.matching.bet.domain.model.Users;

@Component
public class UsersModelAssembler 
	extends RepresentationModelAssemblerSupport<Users, UsersModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BackLinks backLinks;
	
	@Autowired
	private BackSecurity backSecurity;
	
	public UsersModelAssembler() {
		super(UsersController.class, UsersModel.class);		
	}
	
	@Override
	public UsersModel toModel(Users user) {
		UsersModel usersModel = createModelWithId(user.getId(), user);
		modelMapper.map(user, usersModel);
		
		if (backSecurity.canConsultUsersGroupsPermissions()) {
			usersModel.add(backLinks.linkToUsers("users"));
			
			usersModel.add(backLinks.linkToGroupingUser(user.getId(),"user-groups"));
		}
		
		return usersModel;
	}
	
	@Override
	public CollectionModel<UsersModel> toCollectionModel(Iterable<? extends Users> entities){
		return super.toCollectionModel(entities)
				.add(backLinks.linkToUsers());
	}

}
