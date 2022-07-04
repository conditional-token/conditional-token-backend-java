package com.matching.bet.domain.repository;

import org.springframework.stereotype.Repository;

import com.matching.bet.domain.model.Matches;

@Repository
public interface MatchesRepository extends CustomJpaRepository<Matches, Long> {


}
