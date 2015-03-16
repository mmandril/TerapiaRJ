package br.com.rjterapia.model.dao.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Session;

import br.com.rjterapia.model.dao.FotoDao;
import br.com.rjterapia.model.entity.Foto;

@RequestScoped
public class FotoDaoImpl extends DaoGenericoImpl<Foto> implements FotoDao {
	
	public FotoDaoImpl() {
		
	}
	
	@Inject
	public FotoDaoImpl(Session session) {
		super(session);
	}
}
