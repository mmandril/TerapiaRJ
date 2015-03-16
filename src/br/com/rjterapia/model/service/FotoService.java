package br.com.rjterapia.model.service;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.rjterapia.model.dao.FotoDao;
import br.com.rjterapia.model.entity.Foto;
import br.com.rjterapia.model.entity.Profissional;
import br.com.rjterapia.model.exception.DaoException;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.util.interceptor.TransactionalInter;

@RequestScoped
public class FotoService {

	@Inject
	private FotoDao fotoDao;
	
	public FotoService() {
		
	}

	public List<Foto> getAll(Profissional profissional) throws ServiceException {
		try {
			List<Criterion> criterions = Arrays.asList(new Criterion[]{Restrictions.eq("profissional.id", profissional.getId())});
			
			return fotoDao.listAll(criterions, null);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@TransactionalInter
	public void alterarCapa(Foto foto, Profissional profissional) throws ServiceException {
		try {
			List<Criterion> criterions = Arrays.asList(new Criterion[]{Restrictions.eq("profissional.id", profissional.getId())});
			
			List<Foto> fotos = fotoDao.listAll(criterions, null);
			
			for(Foto tmp : fotos) {
				tmp.setCapa("N");
				fotoDao.merge(tmp);
			}
			
			foto = fotoDao.getById(foto.getId());
			foto.setCapa("S");
			fotoDao.merge(foto);
			
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}