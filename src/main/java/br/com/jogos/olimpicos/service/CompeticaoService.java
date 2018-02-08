package br.com.jogos.olimpicos.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.jogos.olimpicos.dao.CompeticaoRepository;
import br.com.jogos.olimpicos.entity.Competicao;
import br.com.jogos.olimpicos.type.EtapaType;




@Service
public class CompeticaoService {

	@Autowired
	private CompeticaoRepository competicaoRepository;

	@PersistenceContext
	private EntityManager entityManager;


	private boolean existeCompeticaoEntityNoPeriodo(Competicao competicao) {
		List<Competicao> competicoes;

		if (competicao.getId() == null) {
			competicoes = competicaoRepository.getCompeticoesPorLocalDataInicioModalidade(
					competicao.getLocal().getId(), competicao.getDataInicio(),
					competicao.getModalidade().getId());
		} else {
			competicoes = competicaoRepository.getCompeticoesPorLocalDataInicioModalidadeUpdate(competicao.getId(),
					competicao.getLocal().getId(), competicao.getDataInicio(),
					competicao.getModalidade().getId());
		}

		return !competicoes.isEmpty();
	}


	public Competicao salvar(Competicao competicao) {
		validate(competicao);

		return competicaoRepository.save(competicao);
	}

	private void validate(Competicao competicao) {

		validaDatas(competicao);

		if (existeCompeticaoEntityNoPeriodo(competicao)) {
			throw new ValidationException("Já existe outra competição cadastrada no mesmo período");
		}

		Duration duracao = Duration.between(competicao.getDataInicio(), competicao.getDataTermino());
		long seconds = duracao.getSeconds();


		if (seconds < 1800) {
			throw new ValidationException("É permitido competições com tempo mínimo de 30 minutos.");
		}

		if (verificaQuantidadeCompeticoesDia(competicao)) {
			throw new ValidationException("Limite de 4 competições por dia e local já foi atingido.");
		}


		if ((!competicao.getEtapa().equals(EtapaType.FINAL) && !competicao.getEtapa().equals(EtapaType.SEMIFINAL))
				&& competicao.getPais1().getId().equals(competicao.getPais2().getId())) {

			throw new ValidationException("Competidores iguais são permitidos apenas na final ou semifinal.");

		}

	}

	private void validaDatas(Competicao competicao) {
		LocalDateTime inicio = competicao.getDataInicio();
		LocalDateTime termino = competicao.getDataTermino();

		if (inicio.getYear() != 2020 || termino.getYear() != 2020) {
			throw new ValidationException("Registro das competições somente para o ano de 2020'");
		}

		if (termino.isBefore(inicio)) {
			throw new ValidationException("Data de termino da competição deve ser maior que a data inicial");
		}
	}

	private boolean verificaQuantidadeCompeticoesDia(Competicao competicao) {
		LocalDateTime dataInicial = competicao.getDataInicio().with(LocalTime.MIN);
		LocalDateTime dataFinal = competicao.getDataTermino().with(LocalTime.MAX);

		List<Competicao> competicoesExistentes = competicaoRepository.getCompeticoesPorLocalData(competicao.getLocal().getId(),
				dataInicial, dataFinal);

		return !competicoesExistentes.isEmpty() && competicoesExistentes.size() >= 4;


	}

	public List<Competicao> listarPorModalidade(Integer codigo) {

		return competicaoRepository.getCompeticaoPorModalidade(codigo);
	}

	public Competicao get(Integer codigo) {
		return competicaoRepository.findOne(codigo);
	}

	public void delete(Integer codigo) {
		Competicao competicao = competicaoRepository.findOne(codigo);

		if (competicao == null) {
			throw new EmptyResultDataAccessException(1);
		}

		competicaoRepository.delete(competicao);
	}

	public List<Competicao> listarTodos() {
		return competicaoRepository.findAll(new Sort(Sort.Direction.ASC, "dataInicio"));
	}

	public Competicao atualizar(Integer codigo, Competicao competicao) {
		Competicao competicaoAtualizar = competicaoRepository.findOne(codigo);

		if (competicaoAtualizar == null) {
			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(competicao, competicaoAtualizar, "id");

		validate(competicaoAtualizar);

		return competicaoRepository.save(competicaoAtualizar);
	}
}
