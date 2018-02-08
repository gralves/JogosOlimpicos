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

import br.com.jogos.olimpicos.entity.Local;
import br.com.jogos.olimpicos.service.LocalService;


@RestController
@RequestMapping("/local")
public class LocalResource {

	@Autowired
	private LocalService localService;

	/**
	 * Lista todos locais 
	 * @return
	 */
	@GetMapping("/listAll")
	public List<Local> listar() {
		return localService.listAll();
	}

	/**
	 * Get Local por Id
	 * @param codigo
	 * @return
	 */
	@GetMapping("/{codigo}")
	public ResponseEntity<Local> get(@PathVariable Integer codigo) {
		Local local = localService.get(codigo);
		return local != null ? ResponseEntity.ok(local) : ResponseEntity.noContent().build();
	}

	/**
	 * Delete Local por Id
	 * @param codigo
	 */
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Integer codigo) {
		localService.delete(codigo);
	}

	/**
	 * Salva novo Local
	 * @param local
	 * @param response
	 * @return
	 */
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Local> save(@Valid @RequestBody Local local, HttpServletResponse response) {
		Local localSave = localService.save(local);

		return ResponseEntity.status(HttpStatus.CREATED).body(localSave);
	}

	/**
	 * Atualiza Local por Id
	 * @param codigo
	 * @param local
	 * @return
	 */
	@PutMapping("/{codigo}")
	public ResponseEntity<Local> update(@PathVariable Integer codigo, @Valid @RequestBody Local local) {
		Local localSave = localService.atualizar(codigo, local);
		return ResponseEntity.ok(localSave);
	}
}
