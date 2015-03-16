package br.com.rjterapia.model.service;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.rjterapia.model.dao.PlanoDao;
import br.com.rjterapia.model.entity.Plano;
import br.com.rjterapia.model.exception.DaoException;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.util.interceptor.TransactionalInter;

@RequestScoped
public class PlanoService {

	@Inject
	private PlanoDao planoDao;
	
	public PlanoService() {
		
	}
	
	public List<Plano> getAll(String propriedade) throws ServiceException {
		try {
			List<Order> orders = Arrays.asList(new Order[] {Order.asc(propriedade)});
			
			return planoDao.listAll(null, orders);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	public Plano getById(Plano plano) throws ServiceException {
		try {
			List<Criterion> criterions = Arrays.asList(new Criterion[] {Restrictions.eq("id", plano.getId())});
			
			List<Plano> planos = planoDao.listAll(criterions, null);
			
			if(planos != null && planos.size() > 0) {
				return planos.get(0);
			}
			
			return null;
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@TransactionalInter
	public void salvar(Plano plano) throws ServiceException {
		try {
			planoDao.merge(plano);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	@TransactionalInter
	public void alterar(Plano plano) throws ServiceException {
		try {
			planoDao.merge(plano);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}
