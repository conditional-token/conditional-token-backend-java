package com.matching.bet.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matching.bet.domain.exception.PermissionNotFoundException;
import com.matching.bet.domain.model.Permission;
import com.matching.bet.domain.repository.PermissionRepository;

@Service
public class PermissionRegistrationService {

	@Autowired
	private PermissionRepository permissionRepository;
	
	public Permission seekOrFail(Long permissionId) {
		return permissionRepository.findById(permissionId)
				.orElseThrow(() -> new PermissionNotFoundException(permissionId));
	}
	
}
