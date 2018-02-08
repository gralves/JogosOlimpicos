	package br.com.jogos.olimpicos.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import br.com.jogos.olimpicos.entity.Competicao;

public interface CompeticaoRepository extends JpaRepository<Competicao, Integer> {
	
	@Query("select c from Competicao c where c.modalidade.id = :idmodalidade order by c.dataInicio ASC")
	List<Competicao> getCompeticaoPorModalidade(@Param("idmodalidade") Integer id);

	@Query("select c from Competicao c where c.local.id = :id_local AND (c.dataInicio >=  :data_inicio AND c.dataTermino <= :data_final)")
	List<Competicao> getCompeticoesPorLocalData(@Param("id_local") Integer id, @Param("data_inicio") LocalDateTime dataInicio,
			@Param("data_final") LocalDateTime dataFinal);


	@Query("select c from Competicao c where c.id != :id_competicao AND c.local.id = :id_local AND (c.dataInicio <= :data AND c.dataTermino > :data) and c.modalidade.id = :id_modalidade ")
	List<Competicao> getCompeticoesPorLocalDataInicioModalidadeUpdate(@Param("id_competicao") Integer id,
			@Param("id_local") Integer idLocal, @Param("data") LocalDateTime data,
			@Param("id_modalidade") Integer idModalidade);

	@Query("select c from Competicao c where c.local.id = :id_local AND (c.dataInicio <= :data AND c.dataTermino > :data) and c.modalidade.id = :id_modalidade ")
	List<Competicao> getCompeticoesPorLocalDataInicioModalidade(@Param("id_local") Integer idLocal,
			@Param("data") LocalDateTime data, @Param("id_modalidade") Integer idModalidade);

	
}
