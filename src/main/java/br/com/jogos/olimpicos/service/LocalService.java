package br.com.jogos.olimpicos.service;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.jogos.olimpicos.dao.LocalRepository;
import br.com.jogos.olimpicos.entity.Local;




@Service
public class LocalService {
	
	@Autowired
	LocalRepository localRepository;

	private String pattern(String nomeLocal) {
		return nomeLocal.trim().toUpperCase();
	}
	
	public Local save(Local local) {
		String nomeLocal = pattern(local.getNome());
		
		Local localExistente = localRepository.findByNome(nomeLocal);
		
		if(localExistente == null) {
			local.setNome(nomeLocal);
			return localRepository.save(local);
		}else {
			throw new ValidationException("Já existe um Local com este nome!");
		}
	}
	
	
	public Local atualizar(Integer codigo, Local local) {
		Local localSalvo = localRepository.findOne(codigo);
		
		if(localSalvo ==  null) {
			throw new EmptyResultDataAccessException(codigo);
		}
		
		localSalvo.setNome(pattern(local.getNome()));
		
		localRepository.save(localSalvo);
		
		return localSalvo;
	}

	public List<Local> listAll() {
		return localRepository.findAll();
	}

	public Local get(Integer codigo) {
		return localRepository.findOne(codigo);
	}

	
	public void delete(Integer codigo) {
		Local local = localRepository.findOne(codigo);

		if (local == null) {
			throw new EmptyResultDataAccessException(1);
		}

		localRepository.delete(local);
	}
}
