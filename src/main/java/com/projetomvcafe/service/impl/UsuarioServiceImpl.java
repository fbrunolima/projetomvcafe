package com.projetomvcafe.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;


import com.projetomvcafe.api.dto.UsuarioDTO;
import com.projetomvcafe.exceptions.ErroAutenticacao;
import com.projetomvcafe.exceptions.RegraNegocioException;
import com.projetomvcafe.model.entity.Usuario;
import com.projetomvcafe.model.entity.UsuarioCafe;
import com.projetomvcafe.model.repository.UsuarioRepository;
import com.projetomvcafe.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private UsuarioRepository repository;
	
	@Autowired
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}



	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarCpf(usuario.getCpf());
		validarCafe(usuario.getCafe());
		return repository.save(usuario);
	}

	@Override
	public void validarCpf(String cpf) {
		boolean existe = repository.existsByCpf(cpf);
		if(existe) {
			throw new RegraNegocioException("Já existe o Cpf cadastrado.");
		}
	}
	
	@Override
	public void validarCafe(String cafe) {
		boolean ex = repository.existsByCafe(cafe);
		if(ex) {
			throw new RegraNegocioException("Já existe um café registrado por outro usuário, escolha outro.");
		}
	}
	
	@Override
	public List<UsuarioDTO> buscar(){
		List<Usuario> usuario = repository.findAll();
		List<UsuarioDTO> dto = usuario.stream().map(obj-> new UsuarioDTO(obj.getId(),obj.getNome(),obj.getCpf(),obj.getCafe())).collect(Collectors.toList());
		return dto;
		
	}
	
	@Override
	@Transactional
	public void deletar(Usuario usuario) {
		Objects.requireNonNull(usuario.getId());
		repository.delete(usuario);
	}
	
	@Override
	public Optional<Usuario> obterPorId(Long id) {
		return repository.findById(id);
	}

}
