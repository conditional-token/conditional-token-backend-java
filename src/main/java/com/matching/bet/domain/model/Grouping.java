package com.matching.bet.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Grouping {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "grouping_id")
	private Long id;

	@Column(name = "grouping_name", nullable = false)	
	private String name;
	
	@ManyToMany
	@JoinTable(name = "grouping_permission", joinColumns = @JoinColumn(name = "grouping_id"),
	           inverseJoinColumns = @JoinColumn(name = "grouping_permission_id"))
	private Set<Permission> permissions = new HashSet<>();
	
	public boolean removePermission(Permission permission) {
		return getPermissions().remove(permission);
	}
	
	public boolean addPermission(Permission permission) {
		return getPermissions().add(permission);
	}
	
}