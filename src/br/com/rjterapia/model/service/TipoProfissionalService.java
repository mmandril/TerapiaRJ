package br.com.rjterapia.model.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.rjterapia.model.dao.TipoProfissionalDao;
import br.com.rjterapia.model.entity.TipoProfissional;
import br.com.rjterapia.model.exception.DaoException;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.util.interceptor.TransactionalInter;

@RequestScoped
public class TipoProfissionalService {

	@Inject
	private TipoProfissionalDao tipoProfissionalDao;
	
	public TipoProfissionalService() {
		
	}

	public List<TipoProfissional> getAll(String propriedade) throws ServiceException {
		try {
			List<Order> orders = Arrays.asList(new Order[] {Order.asc(propriedade)});
			
			return tipoProfissionalDao.listAll(null, orders);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	public TipoProfissional getById(TipoProfissional tipoProfissional) throws ServiceException {
		try {
			List<Criterion> criterions = null;
			if(tipoProfissional.getId() == null) {
				criterions = Arrays.asList(new Criterion[] {Restrictions.eq("nome", tipoProfissional.getNome())});
				List<TipoProfissional> tipoProfissionals = tipoProfissionalDao.listAll(criterions, null);
				if(tipoProfissionals != null && tipoProfissionals.size() > 0) {
					tipoProfissional = tipoProfissionals.get(0);
				}
			}
			
			criterions = Arrays.asList(new Criterion[] {Restrictions.eq("id", tipoProfissional.getId())});
			
			List<TipoProfissional> tipoProfissionals = tipoProfissionalDao.listAll(criterions, null);
			
			if(tipoProfissionals != null && tipoProfissionals.size() > 0) {
				return tipoProfissionals.get(0);
			}
			
			return null;
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@TransactionalInter
	public void salvar(TipoProfissional tipoProfissional) throws ServiceException {
		try {
			tipoProfissional.setDtIncl(new Date());
			tipoProfissionalDao.merge(tipoProfissional);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	@TransactionalInter
	public void alterar(TipoProfissional tipoProfissional) throws ServiceException {
		try {
			TipoProfissional tmp = getById(tipoProfissional);
			tipoProfissional.setDtIncl(tmp.getDtIncl());
			tipoProfissionalDao.merge(tipoProfissional);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}