package com.cnam.nfa019projet.repository;

import com.cnam.nfa019projet.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<Table, Long> {


}
