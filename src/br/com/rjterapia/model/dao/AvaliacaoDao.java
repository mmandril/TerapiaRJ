package br.com.rjterapia.model.dao;

import java.util.List;

import br.com.rjterapia.model.entity.Avaliacao;
import br.com.rjterapia.model.exception.DaoException;

public interface AvaliacaoDao extends DaoGenerico<Avaliacao>{

	public List<Object[]> getMaisVotadas(int quantidade) throws DaoException;

}
