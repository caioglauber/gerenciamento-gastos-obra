package com.gerenciamento.obra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.gerenciamento.obra.entities.Obra;
import com.gerenciamento.obra.repositories.FornecedorRepository;
import com.gerenciamento.obra.repositories.GastoRepository;
import com.gerenciamento.obra.repositories.ObraRepository;
import com.gerenciamento.obra.repositories.PagadorRepository;
import com.gerenciamento.obra.repositories.TipoGastoRepository;



public class TestConfig implements CommandLineRunner {

	@Autowired
	private ObraRepository obraRepository;
	
	@Autowired
	private GastoRepository gastoRepository;
	
		
	@Autowired
	private PagadorRepository pagadorRepository;
	
	@Autowired
	private TipoGastoRepository tipoGastoRepository;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Obra obraA = new Obra(null, "Obra A", "Rua Cajueiro");
		Obra obraB = new Obra(null, "Obra B", "Rua Projetada");
		
		
	}

}
