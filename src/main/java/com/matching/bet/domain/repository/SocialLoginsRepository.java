package com.matching.bet.domain.repository;

import org.springframework.stereotype.Repository;

import com.matching.bet.domain.model.SocialLogins;

@Repository
public interface SocialLoginsRepository extends CustomJpaRepository<SocialLogins, Long>{

}
