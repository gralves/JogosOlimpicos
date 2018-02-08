package br.com.jogos.olimpicos.service;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.jogos.olimpicos.dao.ModalidadeRepository;
import br.com.jogos.olimpicos.entity.Modalidade;



@Service
public class ModalidadeService {

	@Autowired
	ModalidadeRepository modalidadeRepository;
	
	public List<Modalidade> listAll() {
		return modalidadeRepository.findAll();
	}

	public Modalidade get(Integer codigo) {
		return modalidadeRepository.findOne(codigo);
	}

	public Modalidade save(Modalidade modalidade) {

		String nome = modalidade.getNome().toUpperCase();

		Modalidade modalidadeExistente = modalidadeRepository.findByNome(nome);

		if (modalidadeExistente == null) {
			modalidade.setNome(nome);
			return modalidadeRepository.save(modalidade);
		} else {
			throw new ValidationException("Já existe uma modalidade com este nome!");
		}
	}

	public Modalidade update(Integer codigo, Modalidade modalidade) {
		Modalidade modalidadeSalvo = modalidadeRepository.findOne(codigo);

		if (modalidadeSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}

		modalidadeSalvo.setNome(modalidade.getNome().toUpperCase());

		modalidadeRepository.save(modalidadeSalvo);

		return modalidadeSalvo;
	}

	public void delete(Integer codigo) {
		Modalidade modalidade = modalidadeRepository.findOne(codigo);

		if (modalidade == null) {
			throw new EmptyResultDataAccessException(1);
		}

		modalidadeRepository.delete(modalidade);
	}

	
}
