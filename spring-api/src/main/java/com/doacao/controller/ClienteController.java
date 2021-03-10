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

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/administrador")
	public List<Cliente> listartAll() throws Exception {
		try {
			List<Cliente> cliente =  clienteRepository.findAll();
			
			if( cliente.isEmpty()) {
				throw new Exception("Lista de Clinetes vazia");
			}
			return cliente;
			
			
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	
	}
	
	@GetMapping("/administrador/{id}")
	public Cliente listartId(@PathVariable("id") String email) throws Exception  {
		try {
			Optional<Cliente> clienteData = clienteRepository.findById(email);
			 if (clienteData.isPresent()) {
			      return clienteData.get();
			  } else {
				  throw new Exception("Erro ao procurar cliente com email: " + email);
			 }
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}
	
	
	@PostMapping("/administrador")
	public Cliente adicionar(@RequestBody Cliente cliente) throws Exception  {
		try {

			if(cliente.getEmail() == null && cliente.getNome() == null && cliente.getTelefone() == null) {
				throw new Exception("Precisa preencher os campos");
			}
			
			if(cliente.getEmail() == null) {
				throw new Exception("Precisa preencher o email");
			}
			
			if(!clienteRepository.findById(cliente.getEmail()).isEmpty()) {
				throw new Exception("Email ja cadastrado");
			}
			
			if(cliente.getEmail().indexOf('@') < 0) {
				throw new Exception("O email precisa ser valido");
			}
			
			if(cliente.getNome() == null) {
				throw new Exception("Precisa preencher o nome");
			}
			if(cliente.getTelefone() == null) {
				throw new Exception("Precisa preencher o telefone");
			}
			
			if(!cliente.getTelefone().matches("[0-9]*")) {
				throw new Exception("O telefone precisa ser numero");
			}
			
			
			Cliente _cliente = clienteRepository.save(cliente);
			
			return _cliente;
			
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}
	
	 @PutMapping("/administrador/{id}")
	  public Cliente updateTutorial(@PathVariable("id") String email, @RequestBody Cliente cliente) throws Exception {
		 Optional<Cliente> clienteData = clienteRepository.findById(email);

	    if (clienteData.isPresent()) {
	      Cliente _cliente = clienteData.get();
	      _cliente.setNome(cliente.getNome());
	      _cliente.setTelefone(cliente.getTelefone());
	      return clienteRepository.save(_cliente);
	    } else {
	    	throw new Exception("Erro ao atualizar Cliente");
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
