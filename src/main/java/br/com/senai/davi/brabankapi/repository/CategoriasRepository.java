package br.com.senai.davi.brabankapi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.davi.brabankapi.domain.Categoria;
import br.com.senai.davi.brabankapi.domain.Usuario;

public interface CategoriasRepository extends JpaRepository<Categoria, Long>{
		
	@Override
	default List<Categoria> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
