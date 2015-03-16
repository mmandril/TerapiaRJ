package br.com.rjterapia.model.dao.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Session;

import br.com.rjterapia.model.dao.TipoProfissionalDao;
import br.com.rjterapia.model.entity.TipoProfissional;

@RequestScoped
public class TipoProfissionalDaoImpl extends DaoGenericoImpl<TipoProfissional> implements TipoProfissionalDao {

	public TipoProfissionalDaoImpl() {
		
	}
	
	@Inject
	public TipoProfissionalDaoImpl(Session session) {
		super(session);
	}
}
