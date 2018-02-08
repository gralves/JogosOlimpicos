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

import br.com.jogos.olimpicos.entity.Modalidade;
import br.com.jogos.olimpicos.service.ModalidadeService;



@RestController
@RequestMapping("/modalidade")
public class ModalidadeResource {

	@Autowired
	private ModalidadeService modalidadeService;

	/**
	 * Retorna lista de modalidades
	 * @return
	 */
	@GetMapping("/listAll")
	public List<Modalidade> listAll() {
		return modalidadeService.listAll();
	}

	/**
	 * Get Modalidade por Código
	 * @param codigo
	 * @return
	 */
	@GetMapping("/{codigo}")
	public ResponseEntity<Modalidade> get(@PathVariable Integer codigo) {
		Modalidade modalidade = modalidadeService.get(codigo);
		
		return modalidade != null ? ResponseEntity.ok(modalidade) : ResponseEntity.noContent().build();
	}

	
	/**
	 * Delete Modalidade por Código
	 * @param codigo
	 */
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Integer codigo) {
		modalidadeService.delete(codigo);
	}

	/**
	 * Salva ou Atualiza Modalidade
	 * @param modalidade
	 * @param response
	 * @return
	 */
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Modalidade> save(@RequestBody Modalidade modalidade,
			HttpServletResponse response) {
		Modalidade modalidadeSave = modalidadeService.save(modalidade);
			
		return ResponseEntity.status(HttpStatus.CREATED).body(modalidadeSave);
	}

	/**
	 * Atualiza Modalidade por Codigo
	 * @param codigo
	 * @param modalidade
	 * @return
	 */
	@PutMapping("/{codigo}")
	public ResponseEntity<Modalidade> update(@PathVariable Integer codigo,
			@Valid @RequestBody Modalidade modalidade) {
		Modalidade modalidadeSalvo = modalidadeService.update(codigo, modalidade);
		return ResponseEntity.ok(modalidadeSalvo);
	}
}
