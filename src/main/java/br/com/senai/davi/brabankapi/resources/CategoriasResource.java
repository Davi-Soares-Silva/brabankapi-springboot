package br.com.senai.davi.brabankapi.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.senai.davi.brabankapi.domain.Categoria;
import br.com.senai.davi.brabankapi.repository.CategoriasRepository;
import br.com.senai.davi.brabankapi.services.CategoriasService;
import br.com.senai.davi.brabankapi.services.exceptions.CategoriaNãoEncontradaException;

//Classe que é o nosso Controller
//Ela recebe as requisições, processa e devolve a resposta

@RestController
//Passamos a rota que será acessada por todos os métodos
@RequestMapping("/categorias")
public class CategoriasResource {
	

	// injeção de dependência
	@Autowired
	CategoriasService categoriasService;
	// Repositório de dados da tabela de categorias
//	CategoriasRepository categoriaRepository;

	// Captura requisições com o método GET
	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listarTodos() {

		return categoriasService.listarTodos();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> buscarPorId(@PathVariable("id") Long id) {
		
		Categoria categoria = categoriasService.buscarPorId(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(categoria);

	}

	
	  @PostMapping 
	  public ResponseEntity<Void> inserir(@RequestBody Categoria categoria) { 
		  categoriasService.inserir(categoria);
		  
		  URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.id).toUri();
		  
		  return ResponseEntity.created(uri).build();
	  }
	  
	  @PutMapping(value = "/{id}") 
	  public ResponseEntity<Void> editar(@PathVariable Long id, @RequestBody Categoria categoria){
		categoria.id = id;
		 
		categoriasService.editar(categoria); 
		return ResponseEntity.ok().build();
	 
	  }
	  
	  @DeleteMapping(value = "/{id}") 
	  public ResponseEntity<Void> excluir(@PathVariable Long id) { 
		categoriasService.deletar(id);
		return ResponseEntity.ok().build();
	 
	  }
	 
}
