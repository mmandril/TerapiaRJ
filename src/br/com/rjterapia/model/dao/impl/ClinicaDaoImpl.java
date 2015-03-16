package br.com.rjterapia.model.dao.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Session;

import br.com.rjterapia.model.dao.ClinicaDao;
import br.com.rjterapia.model.entity.Clinica;

@RequestScoped
public class ClinicaDaoImpl extends DaoGenericoImpl<Clinica> implements ClinicaDao {

	public ClinicaDaoImpl() {
		
	}
	
	@Inject
	public ClinicaDaoImpl(Session session) {
		super(session);
	}
}
