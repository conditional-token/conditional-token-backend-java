package com.matching.bet.domain.repository;

import org.springframework.stereotype.Repository;

import com.matching.bet.domain.model.Options;

@Repository
public interface OptionsRepository extends CustomJpaRepository<Options, Long> {

	Iterable<Options> findByMatchesId(Long matchesId);

}
