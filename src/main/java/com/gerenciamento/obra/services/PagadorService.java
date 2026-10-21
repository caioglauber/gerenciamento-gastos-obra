package com.gerenciamento.obra.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciamento.obra.entities.Pagador;
import com.gerenciamento.obra.repositories.PagadorRepository;

@Service
public class PagadorService {

	@Autowired
	private PagadorRepository pagadorRepository;

	public List<Pagador> findAll() {
		return pagadorRepository.findAll();
	}

	public Pagador findById(Long id) {
		Optional<Pagador> obj = pagadorRepository.findById(id);
		return obj.get();
	}

	public Pagador insert(Pagador obj) {
		return pagadorRepository.save(obj);
	}
}
