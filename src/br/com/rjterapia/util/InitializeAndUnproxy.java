/**
 * @author Marcus Vinicius Silva de Souza  
 *	31/07/2014
 */
package br.com.rjterapia.util;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

/**
 * @author Marcus Vinicius Silva de Souza  
 * 31/07/2014
 */
public class InitializeAndUnproxy {

	@SuppressWarnings("unchecked")
	public static <T> T initializeAndUnproxy(T entity) {
	    if (entity == null) {
	        throw new 
	           NullPointerException("Entity passed for initialization is null");
	    }

	    Hibernate.initialize(entity);
	    if (entity instanceof HibernateProxy) {
	        entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer()
	                .getImplementation();
	    }
	    return entity;
	}
}
