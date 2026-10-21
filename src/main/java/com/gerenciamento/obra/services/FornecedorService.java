package com.gerenciamento.obra.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciamento.obra.entities.Fornecedor;
import com.gerenciamento.obra.entities.Obra;
import com.gerenciamento.obra.repositories.FornecedorRepository;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;

	public List<Fornecedor> findAll() {
		return fornecedorRepository.findAll();
	}

	public Fornecedor findById(Long id) {
		Optional<Fornecedor> obj = fornecedorRepository.findById(id);
		return obj.get();
	}

	public Fornecedor insert(Fornecedor obj) {
		return fornecedorRepository.save(obj);
	}

}
