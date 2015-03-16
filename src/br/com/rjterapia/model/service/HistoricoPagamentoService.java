package br.com.rjterapia.model.service;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.criterion.Order;

import br.com.rjterapia.model.dao.HistoricoPagamentoDao;
import br.com.rjterapia.model.entity.HistoricoPagamento;
import br.com.rjterapia.model.exception.DaoException;
import br.com.rjterapia.model.exception.ServiceException;

@RequestScoped
public class HistoricoPagamentoService {

	@Inject
	private HistoricoPagamentoDao historicoPagamentoDao;
	
	public HistoricoPagamentoService() {
		
	}

	public List<HistoricoPagamento> getAllVencDesc() throws ServiceException {
		try {
			List<Order> orders = Arrays.asList(new Order[]{Order.desc("dtFimVigencia")});
			
			return historicoPagamentoDao.listAll(null, orders);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}
