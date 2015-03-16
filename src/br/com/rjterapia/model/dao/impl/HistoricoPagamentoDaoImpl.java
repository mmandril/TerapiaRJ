package br.com.rjterapia.model.dao.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Session;

import br.com.rjterapia.model.dao.HistoricoPagamentoDao;
import br.com.rjterapia.model.entity.HistoricoPagamento;

@RequestScoped
public class HistoricoPagamentoDaoImpl extends DaoGenericoImpl<HistoricoPagamento> implements HistoricoPagamentoDao {

	public HistoricoPagamentoDaoImpl() {
		
	}
	
	@Inject
	public HistoricoPagamentoDaoImpl(Session session) {
		super(session);
	}
}
