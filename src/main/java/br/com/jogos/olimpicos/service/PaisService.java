package br.com.jogos.olimpicos.service;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.jogos.olimpicos.dao.PaisRepository;
import br.com.jogos.olimpicos.entity.Pais;


@Service
public class PaisService {

	@Autowired
	PaisRepository competidorRepository;
	
	public List<Pais> listAll() {
		return competidorRepository.findAll();
	}

	public Pais salvar(Pais competidor) {

		String nomeCompetidor = competidor.getNome();

		Pais competidorExistente = competidorRepository.findByNome(nomeCompetidor);

		if (competidorExistente == null) {
			competidor.setNome(nomeCompetidor);
			return competidorRepository.save(competidor);
		} else {
			throw new ValidationException("Já existe um competidor com este nome!");
		}
	}

	public Pais atualizaPorCodigo(Integer codigo, Pais competidor) {
		Pais competidorSalvo = competidorRepository.findOne(codigo);

		if (competidorSalvo == null) {
			throw new EmptyResultDataAccessException(codigo);
		}

		competidorSalvo.setNome(competidor.getNome());

		competidorRepository.save(competidorSalvo);

		return competidorSalvo;
	}

	
	public Pais get(Integer codigo) {
		return competidorRepository.findOne(codigo);
	}

	public void delete(Integer codigo) {
		Pais competidor = competidorRepository.findOne(codigo);

		if (competidor == null) {
			throw new EmptyResultDataAccessException(1);
		}

		competidorRepository.delete(competidor);
	}
}
