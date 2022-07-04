package com.matching.bet.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class BackSecurity {

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public Long getUserId() {
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		
		return jwt.getClaim("users_id");
	}
	
	public boolean userAutenticatedEquals(Long userId) {
		return getUserId() != null && userId != null
				&& getUserId().equals(userId);
	}
	
	public boolean hasAuthority(String authorityName) {
		return getAuthentication().getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equals(authorityName));
	}
	
	public boolean isAutenticated() {
	    return getAuthentication().isAuthenticated();
	}
	
	public boolean hasWrittenScope() {
	    return hasAuthority("SCOPE_WRITE");
	}

	public boolean hasReadScope() {
	    return hasAuthority("SCOPE_READ");
	}
	
	public boolean canConsultUsersGroupsPermissions() {
	    return hasReadScope() && hasAuthority("CONSULT_USERS_GROUPS_PERMISSIONS");
	}

	public boolean canEditUsersGroupsPermissions() {
	    return hasWrittenScope() && hasAuthority("EDIT_USERS_GROUPS_PERMISSIONS");
	}
}
