package br.com.rjterapia.model.dao.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Session;

import br.com.rjterapia.model.dao.TipoAvaliacaoDao;
import br.com.rjterapia.model.entity.TipoAvaliacao;

@RequestScoped
public class TipoAvaliacaoDaoImpl extends DaoGenericoImpl<TipoAvaliacao> implements TipoAvaliacaoDao {

	public TipoAvaliacaoDaoImpl() {
		
	}
	
	@Inject
	public TipoAvaliacaoDaoImpl(Session session) {
		super(session);
	}
}
