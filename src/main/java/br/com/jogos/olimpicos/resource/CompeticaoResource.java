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



import br.com.jogos.olimpicos.entity.Competicao;
import br.com.jogos.olimpicos.service.CompeticaoService;

	
@RestController
@RequestMapping("/competicao")
public class CompeticaoResource {

	@Autowired
	CompeticaoService competicaoService;

	/**
	 * Retorna todas {@link CompeticaoEntity} cadastradas
	 * @return
	 */
	@GetMapping("listAll")
	public List<Competicao> listAll() {
		return competicaoService.listarTodos();
	}

	/**
	 * Retorna {@link CompeticaoEntity} pelo seu código
	 * @param codigo
	 * @return
	 */
	@GetMapping("/{codigo}")
	public ResponseEntity<Competicao> get(@PathVariable Integer codigo) {
		Competicao competicao = competicaoService.get(codigo);
		return competicao != null ? ResponseEntity.ok(competicao) : ResponseEntity.noContent().build();
	}

	/**
	 * Lista {@link CompeticaoEntity} pelo codigo de uma modalidade
	 * @param codigo
	 * @return
	 */
	@GetMapping("/modalidade/{codigo}")
	public List<Competicao> getByModalidade(@PathVariable Integer codigo) {
		return competicaoService.listarPorModalidade(codigo);
	}

	/**
	 * Salvar a Competicao com validacoes
	 * @param competicao
	 * @param response
	 * @return
	 */
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Competicao> save(@Valid @RequestBody Competicao competicao, HttpServletResponse response) {
		Competicao competicaoSalva = competicaoService.salvar(competicao);

		return ResponseEntity.status(HttpStatus.CREATED).body(competicaoSalva);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Integer codigo) {
		competicaoService.delete(codigo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Competicao> update(@PathVariable Integer codigo,
			@Valid @RequestBody Competicao competicao) {
		Competicao competicaoAtualizada = competicaoService.atualizar(codigo, competicao);
		return ResponseEntity.ok(competicaoAtualizada);
	}
}
