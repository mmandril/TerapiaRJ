package br.com.rjterapia.model.dao.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Session;

import br.com.rjterapia.model.dao.ClienteDao;
import br.com.rjterapia.model.entity.Cliente;

@RequestScoped
public class ClienteDaoImpl extends DaoGenericoImpl<Cliente> implements ClienteDao {

	public ClienteDaoImpl() {
		
	}
	
	@Inject
	public ClienteDaoImpl(Session session) {
		super(session);
	}
}
