package br.com.rjterapia.model.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import br.com.rjterapia.model.dao.AvaliacaoDao;
import br.com.rjterapia.model.entity.Avaliacao;
import br.com.rjterapia.model.exception.DaoException;

@RequestScoped
public class AvaliacaoDaoimpl extends DaoGenericoImpl<Avaliacao> implements AvaliacaoDao {

	public AvaliacaoDaoimpl() {
		
	}
	
	@Inject
	public AvaliacaoDaoimpl(Session session) {
		super(session);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getMaisVotadas(int quantidade) throws DaoException {
		Criteria criteria = getSession().createCriteria(Avaliacao.class);
		ProjectionList projList = Projections.projectionList(); 
		
		projList.add(Projections.alias(Projections.rowCount(), "total"));
		projList.add(Projections.groupProperty("profissional.id"));
		
		criteria.setMaxResults(quantidade);
		criteria.setProjection(projList);
		criteria.addOrder(Order.desc("total"));
		
		return criteria.list();
	}
}