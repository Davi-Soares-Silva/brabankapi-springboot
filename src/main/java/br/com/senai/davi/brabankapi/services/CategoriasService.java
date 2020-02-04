package br.com.senai.davi.brabankapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.senai.davi.brabankapi.domain.Categoria;
import br.com.senai.davi.brabankapi.repository.CategoriasRepository;
import br.com.senai.davi.brabankapi.services.exceptions.CategoriaNãoEncontradaException;

@Service
public class CategoriasService {
	
	@Autowired
	CategoriasRepository categoriasRepository;
	
	public List<Categoria> listarTodos(){
		return categoriasRepository.findAll();
	}
	
	public Categoria buscarPorId(Long id){
		Categoria categoria = categoriasRepository.findById(id).orElse(null);
		
		if(categoria == null){
			throw new CategoriaNãoEncontradaException("Categoria não pode ser localizada");
		}
		
		return categoria;
	}
	
	public Categoria inserir(Categoria categoria) {
		categoria.id = null;
		
		return categoriasRepository.save(categoria);
	}
	
	public void editar(Categoria categoria) {
		verificarExistencia(categoria);
		categoriasRepository.save(categoria);
	}
	
	private void verificarExistencia(Categoria categoria){
		buscarPorId(categoria.id);
	}
	
	public void deletar(Long id) {
		try { 
			categoriasRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e){
			throw new CategoriaNãoEncontradaException("Categoria não pode ser localizada");
		}
		
	}
}
