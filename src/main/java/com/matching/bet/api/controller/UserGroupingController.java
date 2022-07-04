package com.matching.bet.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.matching.bet.api.BackLinks;
import com.matching.bet.api.assembler.GroupingModelAssembler;
import com.matching.bet.api.model.GroupingModel;
import com.matching.bet.api.openapi.controller.UserGroupControllerOpenApi;
import com.matching.bet.core.security.BackSecurity;
import com.matching.bet.core.security.CheckSecurity;
import com.matching.bet.domain.model.Users;
import com.matching.bet.domain.service.UserRegistrationService;

@RestController
@RequestMapping(path = "/users/{userId}/groups", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserGroupingController implements UserGroupControllerOpenApi {

	@Autowired
	private UserRegistrationService userRegistrationService;
    
    @Autowired
    private GroupingModelAssembler groupingModelAssembler;
    
    @Autowired
    private BackLinks backLinks;
    
    @Autowired
    private BackSecurity backSecurity;
    
    @CheckSecurity.UsersGroupsPermissions.CanConsult
    @Override
    @GetMapping
    public CollectionModel<GroupingModel> list(@PathVariable Long userId) {
        Users user = userRegistrationService.seekOrFail(userId);
        
        CollectionModel<GroupingModel> groupingsModel = groupingModelAssembler.toCollectionModel(user.getGroupings())
        		.removeLinks();
        
	        if (backSecurity.canEditUsersGroupsPermissions()) {
	        	groupingsModel.add(backLinks.linkToUserGroupAssociate(userId, "associate"));
	        
	        groupingsModel.getContent().forEach(groupModel -> {
	        	groupModel.add(backLinks.linkToUserGroupDesassociate(
	        			userId, groupModel.getId(), "disassociate"));
	        });
        }
        return groupingsModel;
    }
    
    @CheckSecurity.UsersGroupsPermissions.CanEdit
    @Override  
    @DeleteMapping("/{groupingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> disassociate(@PathVariable Long userId, @PathVariable Long groupingId) {
    	userRegistrationService.disassociateGrouping(userId, groupingId);
    	
    	return ResponseEntity.noContent().build();
    }
    
    @CheckSecurity.UsersGroupsPermissions.CanEdit
    @Override
    @PutMapping("/{groupingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associate(@PathVariable Long userId, @PathVariable Long groupingId) {
    	userRegistrationService.associateGrouping(userId, groupingId);
    	
    	return ResponseEntity.noContent().build();
    }        
}