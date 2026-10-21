package com.gerenciamento.obra.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.gerenciamento.obra.entities.Fornecedor;
import com.gerenciamento.obra.entities.Gasto;
import com.gerenciamento.obra.entities.Obra;
import com.gerenciamento.obra.entities.Pagador;
import com.gerenciamento.obra.entities.TipoGasto;
import com.gerenciamento.obra.entities.enums.StatusGasto;
import com.gerenciamento.obra.repositories.FornecedorRepository;
import com.gerenciamento.obra.repositories.GastoRepository;
import com.gerenciamento.obra.repositories.ObraRepository;
import com.gerenciamento.obra.repositories.PagadorRepository;
import com.gerenciamento.obra.repositories.TipoGastoRepository;
import java.time.LocalDate;


@Component
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
		
	/*
	
		Obra obraA = new Obra(null, "Obra A", "Rua Cajueiro");
		Obra obraB = new Obra(null, "Obra B", "Rua Projetada");
		
		obraRepository.saveAll(Arrays.asList(obraA, obraB));
	
		Pagador pagador1 = new Pagador(null, "Elson");
		Pagador pagador2 = new Pagador(null, "Cooper");
		pagadorRepository.saveAll(Arrays.asList(pagador1,pagador2));
		
		Fornecedor a = new Fornecedor(null, "Metal Loc");
		Fornecedor b = new Fornecedor(null, "Loq Facil");
		Fornecedor c = new Fornecedor(null, "LDF");
		Fornecedor d = new Fornecedor(null, "Caixa Economica");
		Fornecedor e = new Fornecedor(null, "Mundo das Tintas");
		
		fornecedorRepository.saveAll(Arrays.asList(a,b,c,d,e));
		
		TipoGasto locacao = new TipoGasto(null, "Locacao");
		TipoGasto funcionario = new TipoGasto(null, "Funcionario");
		TipoGasto construcao = new TipoGasto(null, "Construcao");
		TipoGasto encanamento = new TipoGasto(null, "Encanamento");
		TipoGasto concretagem = new TipoGasto(null, "Concretagem");
		TipoGasto documentacao = new TipoGasto(null, "Documentacao");
		TipoGasto eletrica = new TipoGasto(null, "Eletrica");
		TipoGasto ferragem = new TipoGasto(null, "Ferragem");
		TipoGasto madeira = new TipoGasto(null, "madeira");
		TipoGasto areia = new TipoGasto(null, "Areia");
		TipoGasto cimento = new TipoGasto(null, "Cimento");
		
		tipoGastoRepository.saveAll(Arrays.asList(locacao,funcionario,construcao,encanamento,concretagem,documentacao,
				eletrica,ferragem,madeira,areia,cimento));
		
	
		
		Gasto gasto1 = new Gasto(
			    null,
			    "Porta grelha cinza",
			    400.0,
			    LocalDate.of(2026, 10, 20),
			    StatusGasto.PAGO,
			    obraA,
			    pagador1,
			    a,
			    locacao
			);
		
		gastoRepository.saveAll(Arrays.asList(gasto1));
		
	*/	
	}

}
