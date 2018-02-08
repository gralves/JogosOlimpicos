package br.com.jogos.olimpicos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jogos.olimpicos.entity.Local;

public interface LocalRepository extends JpaRepository<Local, Integer>{
	
	public Local findByNome(String nome);

}
