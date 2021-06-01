package com.cnam.nfa019projet.repository;

import com.cnam.nfa019projet.model.Frigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrigoRepository extends JpaRepository<Frigo, Long> {
}
