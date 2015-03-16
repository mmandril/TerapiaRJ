package br.com.rjterapia.model.dao.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Session;

import br.com.rjterapia.model.dao.AreaAtuacaoDao;
import br.com.rjterapia.model.entity.AreaAtuacao;

@RequestScoped
public class AreaAtuacaoDaoImpl extends DaoGenericoImpl<AreaAtuacao> implements AreaAtuacaoDao {

	public AreaAtuacaoDaoImpl() {
		
	}
	
	@Inject
	public AreaAtuacaoDaoImpl(Session session) {
		super(session);
	}
}
