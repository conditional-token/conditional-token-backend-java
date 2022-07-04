package com.matching.bet.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.matching.bet.api.model.PermissionModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Permissões")
public interface PermissionControllerOpenApi {
	
	@ApiOperation("Lsita as pemrissões")
	CollectionModel<PermissionModel> list();

}
