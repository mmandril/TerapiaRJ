package br.com.rjterapia.model.service;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.rjterapia.model.dao.ClienteDao;
import br.com.rjterapia.model.entity.Cliente;
import br.com.rjterapia.model.exception.DaoException;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.util.interceptor.TransactionalInter;

@RequestScoped
public class ClienteService {

	@Inject
	private ClienteDao clienteDao;
	
	public ClienteService() {
		
	}
	
	public List<Cliente> getAll(String propriedade) throws ServiceException {
		try {
			List<Order> orders = Arrays.asList(new Order[] {Order.asc(propriedade)});

			return clienteDao.listAll(null, orders);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	public Cliente getById(Cliente cliente) throws ServiceException {
		try {
			List<Criterion> criterions = Arrays.asList(new Criterion[] {Restrictions.eq("id", cliente.getId())});

			List<Cliente> clientes = clienteDao.listAll(criterions, null);

			if(clientes != null && clientes.size() > 0) {
				return clientes.get(0);
			}

			return null;
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@TransactionalInter
	public void salvar(Cliente cliente) throws ServiceException {
		try {
			clienteDao.merge(cliente);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@TransactionalInter
	public void alterar(Cliente cliente) throws ServiceException {
		try {
			clienteDao.merge(cliente);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}
