package br.com.rjterapia.model.dao.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Session;

import br.com.rjterapia.model.dao.PlanoDao;
import br.com.rjterapia.model.entity.Plano;

@RequestScoped
public class PlanoDaoImpl extends DaoGenericoImpl<Plano> implements PlanoDao {

	public PlanoDaoImpl() {
		
	}
	
	@Inject
	public PlanoDaoImpl(Session session) {
		super(session);
	}
}
