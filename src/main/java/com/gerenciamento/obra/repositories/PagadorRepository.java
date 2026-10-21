package com.gerenciamento.obra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciamento.obra.entities.Pagador;

public interface PagadorRepository extends JpaRepository<Pagador, Long> {

}
