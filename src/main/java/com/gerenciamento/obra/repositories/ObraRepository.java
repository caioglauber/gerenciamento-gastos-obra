package com.gerenciamento.obra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciamento.obra.entities.Obra;


public interface ObraRepository extends JpaRepository<Obra, Long>{

}
