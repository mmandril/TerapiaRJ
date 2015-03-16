package br.com.rjterapia.model.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.rjterapia.model.dao.ClinicaDao;
import br.com.rjterapia.model.entity.Clinica;
import br.com.rjterapia.model.exception.DaoException;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.util.interceptor.TransactionalInter;

@RequestScoped
public class ClinicaService {

	@Inject
	private ClinicaDao clinicaDao;
	
	public ClinicaService() {
		
	}

	public List<Clinica> getAll(String propriedade) throws ServiceException {
		try {
			List<Order> orders = Arrays.asList(new Order[] {Order.asc(propriedade)});

			return clinicaDao.listAll(null, orders);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	public Clinica getById(Clinica clinica) throws ServiceException {
		try {
			List<Criterion> criterions = Arrays.asList(new Criterion[] {Restrictions.eq("id", clinica.getId())});

			List<Clinica> clinicas = clinicaDao.listAll(criterions, null);

			if(clinicas != null && clinicas.size() > 0) {
				return clinicas.get(0);
			}

			return null;
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	@TransactionalInter
	public void salvar(Clinica clinica) throws ServiceException {
		try {
			clinica.setDtIncl(new Date());
			clinicaDao.merge(clinica);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@TransactionalInter
	public void alterar(Clinica clinica) throws ServiceException {
		try {
			Clinica tmp = clinicaDao.getById(clinica.getId());
			clinica.setDtIncl(tmp.getDtIncl());
			clinicaDao.merge(clinica);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@TransactionalInter
	public void inativar(Clinica clinica) throws ServiceException {
		clinica = getById(clinica);
		clinica.setDtInat(new Date());

		salvar(clinica);
	}

	@TransactionalInter
	public void ativar(Clinica clinica) throws ServiceException {
		clinica = getById(clinica);
		clinica.setDtInat(null);

		salvar(clinica);
	}
}