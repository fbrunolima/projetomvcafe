package com.projetomvcafe.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetomvcafe.model.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	boolean existsByCpf(String Cpf);
	
	boolean existsByCafe(String cafe);
	
	Optional<Usuario> findByCpf(String cpf);
}
