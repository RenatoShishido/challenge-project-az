package com.doacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.doacao.model.Cliente;
import com.doacao.repository.ClienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/administrador")
	public ResponseEntity<List<Cliente>> listartAll()  {
		try {
			List<Cliente> clientes = new ArrayList<Cliente>();
			
			clienteRepository.findAll().forEach(clientes::add);
			
			if(clientes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(clientes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/administrador/{id}")
	public ResponseEntity<Cliente> listartId(@PathVariable("id") String email)  {
		try {
			Optional<Cliente> clienteData = clienteRepository.findById(email);
			 if (clienteData.isPresent()) {
			      return new ResponseEntity<>(clienteData.get(), HttpStatus.OK);
			  } else {
			      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			 }
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@PostMapping("/administrador")
	public ResponseEntity<Cliente> adicionar(@RequestBody Cliente cliente)  {
		try {
			if(!clienteRepository.findById(cliente.getEmail()).isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
			Cliente _cliente = clienteRepository.save(cliente);
			
			return new ResponseEntity<>(_cliente,HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	 @PutMapping("/administrador/{id}")
	  public ResponseEntity<Cliente> updateTutorial(@PathVariable("id") String email, @RequestBody Cliente cliente) {
		 Optional<Cliente> clienteData = clienteRepository.findById(email);

	    if (clienteData.isPresent()) {
	      Cliente _cliente = clienteData.get();
	      _cliente.setNome(cliente.getNome());
	      _cliente.setTelefone(cliente.getTelefone());
	      return new ResponseEntity<>(clienteRepository.save(_cliente), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	
	@DeleteMapping("/administrador")
	public ResponseEntity<HttpStatus> deletaAll()  {
		try {
			clienteRepository.deleteAll();
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/administrador/{id}")
	public ResponseEntity<HttpStatus> deletaId(@PathVariable("id") String email)  {
		try {
			clienteRepository.deleteById(email);
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		
	
}
