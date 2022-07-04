package com.matching.bet.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matching.bet.api.assembler.PermissionModelAssembler;
import com.matching.bet.api.model.PermissionModel;
import com.matching.bet.api.openapi.controller.PermissionControllerOpenApi;
import com.matching.bet.core.security.CheckSecurity;
import com.matching.bet.domain.model.Permission;
import com.matching.bet.domain.repository.PermissionRepository;

@RestController
@RequestMapping(path = "/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissionController implements PermissionControllerOpenApi{

	@Autowired
	private PermissionRepository permissionRepository;
	
	@Autowired
	private PermissionModelAssembler permissionModelAssembler;
	
	@CheckSecurity.UsersGroupsPermissions.CanConsult	
	@Override
	@GetMapping
	public CollectionModel<PermissionModel> list() {
		List<Permission> allPermissions = permissionRepository.findAll();
		
		return permissionModelAssembler.toCollectionModel(allPermissions);
	}

}
