package br.com.rjterapia.model.dao;

import java.util.List;

import br.com.rjterapia.model.entity.Visualizacao;
import br.com.rjterapia.model.exception.DaoException;

public interface VisualizacaoDao extends DaoGenerico<Visualizacao> {

	public List<Object[]> getMaisVotadasMes(int mes, int quantidade) throws DaoException;

	public List<Object[]> getMaisVotadasGeral(int quantidade) throws DaoException;

}
