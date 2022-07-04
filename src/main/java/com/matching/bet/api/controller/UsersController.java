package com.matching.bet.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.matching.bet.api.assembler.UserInputDisassembler;
import com.matching.bet.api.assembler.UsersModelAssembler;
import com.matching.bet.api.assembler.UsersShortModelAssembler;
import com.matching.bet.api.model.UsersModel;
import com.matching.bet.api.model.UsersShortModel;
import com.matching.bet.api.model.input.CryptedPassInput;
import com.matching.bet.api.model.input.UserInput;
import com.matching.bet.api.model.input.UserWithPasswordInput;
import com.matching.bet.api.openapi.controller.UsersControllerOpenApi;
import com.matching.bet.core.security.CheckSecurity;
import com.matching.bet.domain.model.Users;
import com.matching.bet.domain.repository.UsersRepository;
import com.matching.bet.domain.service.UserRegistrationService;

@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController implements UsersControllerOpenApi{

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private UserRegistrationService userRegistrationService;
	
	@Autowired
	private UsersModelAssembler usersModelAssembler;

	@Autowired
	private UsersShortModelAssembler usersShortModelAssembler;
	
	@Autowired
	private UserInputDisassembler usuerInputDisassembler;
	
	@CheckSecurity.UsersGroupsPermissions.CanConsult
	@PostMapping("/filter")
	public CollectionModel<UsersShortModel> filter(@RequestBody UserInput userInput){

	    ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny()
	      .withMatcher("userName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
	      .withMatcher("userEmail", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
	    
	    Users user = usuerInputDisassembler.toDomainObject(userInput);
	    
	    Example<Users> example = Example.of(user, customExampleMatcher);
		
		List<Users> allUsers = usersRepository.findAll(example);
		
		return usersShortModelAssembler.toCollectionModel(allUsers);
	}
	
	@CheckSecurity.UsersGroupsPermissions.CanConsult
	@GetMapping
	public CollectionModel<UsersShortModel> list(){

		List<Users> allUsers = usersRepository.findAll();
		
		return usersShortModelAssembler.toCollectionModel(allUsers);
	}
	
	@CheckSecurity.UsersGroupsPermissions.CanConsult
	@GetMapping("/{userId}")
	public UsersModel search(@PathVariable Long userId) {
		Users user = userRegistrationService.seekOrFail(userId);
		
		return usersModelAssembler.toModel(user);
	}
	
	@Override
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsersModel add(@RequestBody @Valid UserWithPasswordInput userInput) {
        Users user = usuerInputDisassembler.toDomainObject(userInput);
        user = userRegistrationService.save(user);
        
        return usersModelAssembler.toModel(user);
    }
	
	@CheckSecurity.UsersGroupsPermissions.CanChangeUser
	@Override
	@PutMapping("/{userId}")
	public UsersModel update(@PathVariable Long userId,
			@RequestBody @Valid UserInput userInput){
		Users actualUser = userRegistrationService.seekOrFail(userId);
		usuerInputDisassembler.copyToDomainObject(userInput, actualUser);
		actualUser = userRegistrationService.save(actualUser);
		
		return usersModelAssembler.toModel(actualUser);
	}
	
	@CheckSecurity.UsersGroupsPermissions.CanChangeOwnPassword
	@Override
	@PutMapping("/{userId}/cryptedPass")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateCryptedPass(@PathVariable Long userId, @RequestBody @Valid CryptedPassInput cryptedPass) {
		userRegistrationService.updateCryptedPass(userId, cryptedPass.getActualCryptedPass(), cryptedPass.getNewCryptedPass());
	}
	
}
