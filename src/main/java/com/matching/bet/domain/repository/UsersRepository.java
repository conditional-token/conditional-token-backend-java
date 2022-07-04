package com.matching.bet.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.matching.bet.domain.model.Users;

@Repository
public interface UsersRepository extends CustomJpaRepository<Users, Long>{	

	Optional<Users> findByUserEmail(String email);
	
}
