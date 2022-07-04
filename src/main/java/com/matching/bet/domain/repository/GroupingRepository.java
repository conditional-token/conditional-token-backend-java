package com.matching.bet.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matching.bet.domain.model.Grouping;

@Repository
public interface GroupingRepository extends JpaRepository<Grouping, Long>{

}
