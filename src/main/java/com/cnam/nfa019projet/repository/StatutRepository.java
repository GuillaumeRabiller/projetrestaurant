package com.cnam.nfa019projet.repository;

import com.cnam.nfa019projet.model.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatutRepository extends JpaRepository<Statut, Long> {

    Statut findByNomStatut(String nomStatut);
}
