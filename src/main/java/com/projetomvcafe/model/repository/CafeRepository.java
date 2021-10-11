package com.projetomvcafe.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetomvcafe.model.entity.Usuario;

public interface CafeRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByCafe(String cafe);

}
