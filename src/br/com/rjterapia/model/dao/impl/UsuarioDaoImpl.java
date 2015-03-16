package br.com.rjterapia.model.dao.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Session;

import br.com.rjterapia.model.dao.UsuarioDao;
import br.com.rjterapia.model.entity.Usuario;

@RequestScoped
public class UsuarioDaoImpl extends DaoGenericoImpl<Usuario> implements UsuarioDao {

	public UsuarioDaoImpl() {
		
	}
	
	@Inject
	public UsuarioDaoImpl(Session session) {
		super(session);
	}
}
