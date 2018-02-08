package br.com.jogos.olimpicos.dao;

import org.springframework.data.jpa.repository.JpaRepository;



import br.com.jogos.olimpicos.entity.Pais;

public interface PaisRepository extends JpaRepository<Pais, Integer>{

	public Pais findByNome(String nomePais);

}

