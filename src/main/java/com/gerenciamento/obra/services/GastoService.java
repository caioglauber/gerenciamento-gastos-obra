package com.gerenciamento.obra.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gerenciamento.obra.entities.Gasto;
import com.gerenciamento.obra.repositories.GastoRepository;
import com.gerenciamento.obra.services.exceptions.DataBaseException;
import com.gerenciamento.obra.services.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

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
	
	public void delete(Long id) {
	    if (!gastoRepository.existsById(id)) {
	        throw new ResourceNotFoundException(id);
	    }

	    try {
	        gastoRepository.deleteById(id);
	        gastoRepository.flush();
	    } catch (DataIntegrityViolationException e) {
	        throw new DataBaseException(e.getMessage());
	    }
	}
	
	@Transactional
	public Gasto update(Long id, Gasto obj) {
	    Gasto entity = gastoRepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException(id));

	    updateData(entity, obj);

	    return gastoRepository.save(entity);
	}
	
	private void updateData(Gasto entity, Gasto obj) {
		entity.setDescricao(obj.getDescricao());
		entity.setData(obj.getData());
		entity.setValor(obj.getValor());
		entity.setFornecedor(obj.getFornecedor());
		entity.setObra(obj.getObra());
		entity.setPagador(obj.getPagador());
		entity.setTipoGasto(obj.getTipoGasto());
		entity.setStatus(obj.getStatus());
	}
}
