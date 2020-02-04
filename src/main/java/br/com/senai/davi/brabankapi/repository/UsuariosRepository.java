package br.com.senai.davi.brabankapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.davi.brabankapi.domain.Usuario;
/*
 *  Mapeia a tebela usuário para manipular os registros
 * 
 * */


public interface UsuariosRepository extends JpaRepository<Usuario, Long>{
	
	public  Usuario findByEmail(String email);
	
}
