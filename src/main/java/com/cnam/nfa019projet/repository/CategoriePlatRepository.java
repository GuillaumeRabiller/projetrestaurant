package com.cnam.nfa019projet.repository;

import com.cnam.nfa019projet.model.CategoriePlat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriePlatRepository extends JpaRepository<CategoriePlat, Long> {
}
