package com.gerenciamento.obra.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciamento.obra.entities.Fornecedor;
import com.gerenciamento.obra.entities.Gasto;
import com.gerenciamento.obra.repositories.GastoRepository;

@Service
public class GastoService {
	
	@Autowired
	private GastoRepository gastoRepository;
	
	public List<Gasto> findAll(){
		return gastoRepository.findAll();		
	}
	
	public Gasto findById(Long id) {
		Optional<Gasto> obj = gastoRepository.findById(id);
		return obj.get();
	}
	
	public Gasto insert(Gasto obj) {
		return gastoRepository.save(obj);
	}
}
