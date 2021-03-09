package com.doacao.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doacao.model.Cliente;
import com.doacao.model.Utensilio;
import com.doacao.repository.UtensilioRepository;

@RestController
@RequestMapping("/api")
public class UtensilioController {
	
	@Autowired
	private UtensilioRepository utensilioRepository;
	
	@GetMapping("/utensilio")
	public ResponseEntity<List<Utensilio>> listartAll()  {
		try {
			List<Utensilio> utensilio = new ArrayList<Utensilio>();
			
			utensilioRepository.findAll().forEach(utensilio::add);
			
			if(utensilio.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(utensilio, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/utensilio/{id}")
	public ResponseEntity<Utensilio> listartId(@PathVariable("id") Long id)  {
		try {
			Optional<Utensilio> utensilioData = utensilioRepository.findById(id);
			 if (utensilioData.isPresent()) {
			      return new ResponseEntity<>(utensilioData.get(), HttpStatus.OK);
			  } else {
			      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			 }
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/utensilio")
	public ResponseEntity<Utensilio> adicionar(@RequestBody Utensilio utensilio)  {
		try {
			return new ResponseEntity<>(utensilioRepository.save(utensilio),HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/utensilio/{id}")
	  public ResponseEntity<Utensilio> updateTutorial(@PathVariable("id") Long id, @RequestBody Utensilio utensilio) {
		 Optional<Utensilio> utensilioData = utensilioRepository.findById(id);

	    if (utensilioData.isPresent()) {
	      Utensilio _utensilio = utensilioData.get();
	      _utensilio.setNome(utensilio.getNome());
	      _utensilio.setDescricao(utensilio.getDescricao());
	      return new ResponseEntity<>(utensilioRepository.save(_utensilio), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	@DeleteMapping("/utensilio")
	public ResponseEntity<HttpStatus> deletaAll()  {
		try {
			utensilioRepository.deleteAll();
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/utensilio/{id}")
	public ResponseEntity<Utensilio> deletaId(@PathVariable("id") Long id)  {
		try {
			utensilioRepository.deleteById(id);
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
