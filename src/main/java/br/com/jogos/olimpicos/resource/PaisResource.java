package br.com.jogos.olimpicos.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jogos.olimpicos.entity.Pais;
import br.com.jogos.olimpicos.service.PaisService;


@RestController
@RequestMapping("/competidor")
public class PaisResource {
	
	@Autowired
	private PaisService competidorService;
	
	/**
	 * Lista todos {@link Pais} cadastrados
	 * @return
	 */
	@GetMapping("/listAll")
	public List<Pais> listAll(){
		return competidorService.listAll(); 
	}
	
	/**
	 * Retorna o {@link Pais} pelo codigo 
	 * @param codigo
	 * @return
	 */
	@GetMapping("/{codigo}")
	public ResponseEntity<Pais> get(@PathVariable Integer codigo){
		Pais competidor = competidorService.get(codigo); 
		return competidor != null ? ResponseEntity.ok(competidor) : ResponseEntity.noContent().build();
	}
	
	/**
	 * Deleta o {@link Pais} pelo codigo
	 * @param codigo
	 */
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Integer codigo){
		competidorService.delete(codigo);
	}
	
	/**
	 * 
	 * @param competidor
	 * @param response
	 * @return
	 */
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Pais> save(@Valid @RequestBody Pais competidor, HttpServletResponse response) {
		br.com.jogos.olimpicos.entity.Pais competidorSalvo = competidorService.salvar(competidor);
	
		return ResponseEntity.status(HttpStatus.CREATED).body(competidorSalvo);
	}
	
	/**
	 * Atualiza o {@link Pais} pelo codigo
	 * @param codigo
	 * @param competidor
	 * @return
	 */
	@PutMapping("/{codigo}")
	public ResponseEntity<Pais> atualizar(@PathVariable Integer codigo, @Valid @RequestBody Pais competidor){
		Pais competidorSalvo = competidorService.atualizaPorCodigo(codigo, competidor);
		return ResponseEntity.ok(competidorSalvo);
	}
}
