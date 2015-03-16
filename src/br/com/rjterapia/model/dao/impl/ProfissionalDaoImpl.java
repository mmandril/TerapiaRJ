package br.com.rjterapia.model.dao.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Session;

import br.com.rjterapia.model.dao.ProfissionalDao;
import br.com.rjterapia.model.entity.Profissional;

@RequestScoped
public class ProfissionalDaoImpl extends DaoGenericoImpl<Profissional> implements ProfissionalDao {

	public ProfissionalDaoImpl() {

	}

	@Inject
	public ProfissionalDaoImpl(Session session) {
		super(session);
	}
}
