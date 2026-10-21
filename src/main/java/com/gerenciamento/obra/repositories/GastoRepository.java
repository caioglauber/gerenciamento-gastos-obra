package com.gerenciamento.obra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciamento.obra.entities.Gasto;

public interface GastoRepository extends JpaRepository<Gasto, Long>{

}
