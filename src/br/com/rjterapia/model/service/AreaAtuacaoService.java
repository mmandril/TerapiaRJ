package br.com.rjterapia.model.service;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.rjterapia.model.dao.AreaAtuacaoDao;
import br.com.rjterapia.model.entity.AreaAtuacao;
import br.com.rjterapia.model.exception.DaoException;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.util.interceptor.TransactionalInter;

@RequestScoped
public class AreaAtuacaoService {

	@Inject
	private AreaAtuacaoDao areaAtuacaoDao;
	
	public AreaAtuacaoService() {
		
	}

	public List<AreaAtuacao> getAll(String propriedade) throws ServiceException {
		try {
			List<Order> orders = Arrays.asList(new Order[] {Order.asc(propriedade)});
			
			return areaAtuacaoDao.listAll(null, orders);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	public AreaAtuacao getById(AreaAtuacao areaAtuacao) throws ServiceException {
		try {
			List<Criterion> criterions = null;
			if(areaAtuacao.getId() != null) {
				criterions = Arrays.asList(new Criterion[] {Restrictions.eq("id", areaAtuacao.getId())});
			}else {
				criterions = Arrays.asList(new Criterion[] {Restrictions.eq("nome", areaAtuacao.getNome())});
			}
			
			List<AreaAtuacao> areaAtuacaos = areaAtuacaoDao.listAll(criterions, null);
			
			if(areaAtuacaos != null && areaAtuacaos.size() > 0) {
				return areaAtuacaos.get(0);
			}
			
			return null;
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@TransactionalInter
	public void salvar(AreaAtuacao areaAtuacao) throws ServiceException {
		try {
			areaAtuacaoDao.merge(areaAtuacao);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	@TransactionalInter
	public void alterar(AreaAtuacao areaAtuacao) throws ServiceException {
		try {
			areaAtuacaoDao.merge(areaAtuacao);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}