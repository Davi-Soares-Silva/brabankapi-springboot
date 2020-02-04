package br.com.senai.davi.brabankapi.resources;

import java.net.URI;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.senai.davi.brabankapi.domain.Usuario;
import br.com.senai.davi.brabankapi.repository.UsuariosRepository;
import br.com.senai.davi.brabankapi.services.UsuariosService;
import br.com.senai.davi.brabankapi.services.exceptions.UsuarioNaoEncontradoException;

//Classe que é o nosso Controller
//Ela recebe as requisições, processa e devolve a resposta

@RestController
//Passamos a rota que será acessada por todos os métodos
@RequestMapping("/usuarios")
public class UsuariosResource {
	

	// injeção de dependência
	@Autowired
	UsuariosService usuariosService;
	// Repositório de dados da tabela de usuarios
//	UsuariosRepository usuarioRepository;

	// Captura requisições com o método GET
	@RequestMapping(method = RequestMethod.GET)
	public List<Usuario> listarTodos() {

		return usuariosService.listarTodos();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> buscarPorId(@PathVariable("id") Long id) {
		
		Usuario usuario = usuariosService.buscarPorId(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(usuario);

	}

	
	  @PostMapping 
	  public ResponseEntity<Void> inserir(@RequestBody Usuario usuario) { 
		  try {
			usuariosService.inserir(usuario);
		} catch (SQLIntegrityConstraintViolationException e) {
			return ResponseEntity.badRequest().build();
		}
		  
		  URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.id).toUri();
		  
		  
		  return ResponseEntity.created(uri).build();
	  }
	  
	  @PutMapping(value = "/{id}") 
	  public ResponseEntity<Void> editar(@PathVariable Long id, @RequestBody Usuario usuario){
		usuario.id = id;
		 
		usuariosService.editar(usuario); 
		return ResponseEntity.ok().build();
	 
	  }
	  
	  @DeleteMapping(value = "/{id}") 
	  public ResponseEntity<Void> excluir(@PathVariable Long id) { 
		usuariosService.deletar(id);
		return ResponseEntity.ok().build();
	 
	  }
	  
	  @GetMapping(value = "/email/{email}")
	  public ResponseEntity<Usuario> buscarPorEmail (@PathVariable String email){
		  Usuario usuario = usuariosService.buscarPorEmail(email);
		  return ResponseEntity.ok(usuario);
	  }
}
