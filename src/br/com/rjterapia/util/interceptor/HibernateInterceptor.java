package br.com.rjterapia.util.interceptor;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.interceptor.AcceptsWithAnnotations;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;

@Intercepts
@AcceptsWithAnnotations(TransactionalInter.class)
public class HibernateInterceptor {
	
	@Inject
	private Session session;

	public HibernateInterceptor()
	{
	}
	
	@AroundCall
    public void intercept(SimpleInterceptorStack stack) {
		System.out.println("***************************************************************** Transactional");
		Transaction transaction = null;
		try
		{
			transaction = this.session.beginTransaction();
			stack.next();
			transaction.commit();
		}
		finally
		{
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
		System.out.println("***************************************************************** Transactional");
    }
}