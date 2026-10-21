package com.gerenciamento.obra.services;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciamento.obra.entities.Obra;
import com.gerenciamento.obra.repositories.ObraRepository;

@Service
public class ObraService {

	@Autowired
	private ObraRepository obraRepository;
	
	public List<Obra> findAll(){
		return obraRepository.findAll();		
	}
	
	public Obra findById(Long id) {
		Optional<Obra> obj = obraRepository.findById(id);
		return obj.get();
	}
	
	public Obra insert(Obra obj) {
		return obraRepository.save(obj);
	}
	
}
