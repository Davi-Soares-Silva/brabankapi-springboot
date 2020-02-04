package br.com.senai.davi.brabankapi.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

// Mapeamos a entidade com a tabela usuario
// O spring irá buscar no banco uma tabela com o nome da classe em letra minúscula
@Entity
public class Usuario {
	
//	Para o spring ligar a nossa classe à uma tabela no banco, precisamos informar a nossa chave primária
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String nome, cpf, senha; 
	
	@Column(unique = true)
	public String email;
	
	@JsonInclude(Include.NON_NULL)
	public String sexo;
	

	
//	Define o campo de join com o usuário para as categorias, junto ao gerenciamento da referÊncia
//	@JsonManagedReference
	@OneToMany(mappedBy = "usuario")
	@JsonIgnore
	public List<Categoria> categorias;
	
}
