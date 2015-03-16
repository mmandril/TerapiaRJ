package br.com.rjterapia.model.dao.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Session;

import br.com.rjterapia.model.dao.PerfilDao;
import br.com.rjterapia.model.entity.Perfil;

@RequestScoped
public class PerfilDaoImpl extends DaoGenericoImpl<Perfil> implements PerfilDao {

	public PerfilDaoImpl() {
		
	}
	
	@Inject
	public PerfilDaoImpl(Session session) {
		super(session);
	}
}
