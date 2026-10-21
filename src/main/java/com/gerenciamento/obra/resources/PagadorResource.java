package com.gerenciamento.obra.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gerenciamento.obra.entities.Pagador;
import com.gerenciamento.obra.services.PagadorService;

@RestController
@RequestMapping(value = "/pagadores")
public class PagadorResource {

	@Autowired
	private PagadorService pagadorService;
	
	@GetMapping
	public ResponseEntity<List<Pagador>> findAll(){
		List<Pagador> list = pagadorService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pagador> findById(@PathVariable Long id){
		Pagador obj = pagadorService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Pagador> insert(@RequestBody Pagador obj){
		obj = pagadorService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
