package com.matching.bet.core.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {
	
	 public @interface UsersGroupsPermissions {
	        
	        @PreAuthorize("hasAuthority('SCOPE_WRITE') and "
	                + "@backSecurity.userAutenticatedEquals(#userId)")
	        @Retention(RUNTIME)
	        @Target(METHOD)
	        public @interface CanChangeOwnPassword { }
	        
	        @PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDIT_USERS_GROUPS_PERMISSIONS') or "
	                + "@backSecurity.userAutenticatedEquals(#userId))")
	        @Retention(RUNTIME)
	        @Target(METHOD)
	        public @interface CanChangeUser { }

	        @PreAuthorize("@backSecurity.canEditUsersGroupsPermissions()")
	        @Retention(RUNTIME)
	        @Target(METHOD)
	        public @interface CanEdit { }	        

	        
	        @PreAuthorize("@backSecurity.canConsultUsersGroupsPermissions()")
	        @Retention(RUNTIME)
	        @Target(METHOD)
	        public @interface CanConsult { }
	        
	    }
	
}
