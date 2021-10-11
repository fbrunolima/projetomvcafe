package com.projetomvcafe.service;


import com.projetomvcafe.api.dto.UsuarioDTO;
import com.projetomvcafe.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
	
	
	Usuario salvarUsuario(Usuario usuario);
	
	List<UsuarioDTO> buscar();
	
	void validarCpf(String cpf);
	
	void validarCafe(String cafe);
	void deletar(Usuario usuario);
	
	Optional<Usuario> obterPorId(Long usuario);

}
