package com.matching.bet.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.matching.bet.domain.model.Bets;
import com.matching.bet.domain.model.Users;

@Repository
public interface BetsRepository extends CustomJpaRepository<Bets, Long>{
	
	@Query("SELECT SUM(b.amount) FROM Bets b where b.optionsId = :options")
	Long sumBetsByOptions(@Param("options") Long optionsId);

}
