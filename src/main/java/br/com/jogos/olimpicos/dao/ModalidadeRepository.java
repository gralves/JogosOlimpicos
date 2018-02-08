package br.com.jogos.olimpicos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jogos.olimpicos.entity.Modalidade;

public interface ModalidadeRepository extends JpaRepository<Modalidade, Integer>{
	
	public Modalidade findByNome(String nomeModalidade);

}
