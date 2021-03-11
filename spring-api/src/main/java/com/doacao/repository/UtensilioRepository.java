package com.doacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.doacao.model.Utensilio;

@Repository
public interface UtensilioRepository extends JpaRepository<Utensilio, Long>  {
	
}
