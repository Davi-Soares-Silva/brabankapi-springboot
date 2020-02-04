package br.com.senai.davi.brabankapi.services;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.senai.davi.brabankapi.domain.Usuario;
import br.com.senai.davi.brabankapi.repository.UsuariosRepository;
import br.com.senai.davi.brabankapi.services.exceptions.UsuarioNaoEncontradoException;

@Service
public class UsuariosService {
	
	@Autowired
	UsuariosRepository usuariosRepository;
	
	public List<Usuario> listarTodos(){
		return usuariosRepository.findAll();
	}
	
	public Usuario buscarPorId(Long id){
		Usuario usuario = usuariosRepository.findById(id).orElse(null);
		
		if(usuario == null){
			throw new UsuarioNaoEncontradoException("O usuário não poderá ser localizado");
		}
		
		return usuario;
	}
	
	public Usuario inserir(Usuario usuario) throws SQLIntegrityConstraintViolationException {
		usuario.id = null;
		
		return usuariosRepository.save(usuario);
	}
	
	public void editar(Usuario usuario) {
		verificarExistencia(usuario);
		usuariosRepository.save(usuario);
	}
	
	private void verificarExistencia(Usuario usuario){
		buscarPorId(usuario.id);
	}
	
	public void deletar(Long id) {
		try { 
			usuariosRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e){
			throw new UsuarioNaoEncontradoException("Usuário não pode ser localizado");
		}
		
	}
	
	public Usuario buscarPorEmail(String email) {
		Usuario usuario = usuariosRepository.findByEmail(email);
		if (usuario == null) {
			 throw new UsuarioNaoEncontradoException("O usuário não pode ser localizado!");
		}
		return usuario;
	}
}
