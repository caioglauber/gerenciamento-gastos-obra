package com.gerenciamento.obra.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciamento.obra.entities.Pagador;
import com.gerenciamento.obra.entities.TipoGasto;
import com.gerenciamento.obra.repositories.TipoGastoRepository;

@Service
public class TipoGastoService {

	@Autowired
	private TipoGastoRepository tipoGastoRepository;
	
	public List<TipoGasto> findAll(){
		return tipoGastoRepository.findAll();		
	}
	
	public TipoGasto findById(Long id) {
		Optional<TipoGasto> obj = tipoGastoRepository.findById(id);
		return obj.get();
	}
	
	public TipoGasto insert(TipoGasto obj) {
		return tipoGastoRepository.save(obj);
	}
	
}
