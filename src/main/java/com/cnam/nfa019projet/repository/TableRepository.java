package com.cnam.nfa019projet.repository;

import com.cnam.nfa019projet.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TableRepository extends JpaRepository<Table, Long> {


}
