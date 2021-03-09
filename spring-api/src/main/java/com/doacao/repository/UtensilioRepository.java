package com.doacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doacao.model.Utensilio;

@Repository
public interface UtensilioRepository extends JpaRepository<Utensilio, Long>  {

}
