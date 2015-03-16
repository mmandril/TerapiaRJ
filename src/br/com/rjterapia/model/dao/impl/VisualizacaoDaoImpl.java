package br.com.rjterapia.model.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import br.com.rjterapia.model.dao.VisualizacaoDao;
import br.com.rjterapia.model.entity.Visualizacao;
import br.com.rjterapia.model.exception.DaoException;
import br.com.rjterapia.util.MonthEqExpression;

@RequestScoped
public class VisualizacaoDaoImpl extends DaoGenericoImpl<Visualizacao> implements VisualizacaoDao {

	public VisualizacaoDaoImpl() {

	}

	@Inject
	public VisualizacaoDaoImpl(Session session) {
		super(session);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getMaisVotadasMes(int mes, int quantidade) throws DaoException {
		Criteria criteria = getSession().createCriteria(Visualizacao.class);
		ProjectionList projList = Projections.projectionList(); 
		
		projList.add(Projections.alias(Projections.rowCount(), "total"));
		projList.add(Projections.groupProperty("profissional.id"));
		
		criteria.setMaxResults(quantidade);
		criteria.setProjection(projList);
		criteria.add(new MonthEqExpression("dtVisualizacao", mes));
		criteria.addOrder(Order.desc("total"));

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getMaisVotadasGeral(int quantidade) throws DaoException {
		Criteria criteria = getSession().createCriteria(Visualizacao.class);
		ProjectionList projList = Projections.projectionList(); 
		
		projList.add(Projections.alias(Projections.rowCount(), "total"));
		projList.add(Projections.groupProperty("profissional.id"));
		
		criteria.setMaxResults(quantidade);
		criteria.setProjection(projList);
		criteria.addOrder(Order.desc("total"));

		return criteria.list();
	}
}