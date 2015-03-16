package br.com.rjterapia.model.service;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.rjterapia.model.dao.PerfilDao;
import br.com.rjterapia.model.entity.Perfil;
import br.com.rjterapia.model.exception.DaoException;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.util.interceptor.TransactionalInter;

@RequestScoped
public class PerfilService {

	@Inject
	private PerfilDao perfilDao;
	
	public PerfilService() {
		
	}
	
	public List<Perfil> getAll(String propriedade) throws ServiceException {
		try {
			List<Order> orders = Arrays.asList(new Order[] {Order.asc(propriedade)});
			
			return perfilDao.listAll(null, orders);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	public Perfil getById(Perfil perfil) throws ServiceException {
		try {
			List<Criterion> criterions = Arrays.asList(new Criterion[] {Restrictions.eq("id", perfil.getId())});
			
			List<Perfil> perfils = perfilDao.listAll(criterions, null);
			
			if(perfils != null && perfils.size() > 0) {
				return perfils.get(0);
			}
			
			return null;
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@TransactionalInter
	public void salvar(Perfil perfil) throws ServiceException {
		try {
			perfilDao.merge(perfil);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	@TransactionalInter
	public void alterar(Perfil perfil) throws ServiceException {
		try {
			perfilDao.merge(perfil);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}
