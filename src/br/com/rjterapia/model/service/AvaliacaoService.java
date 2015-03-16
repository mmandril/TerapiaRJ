package br.com.rjterapia.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.rjterapia.model.dao.AvaliacaoDao;
import br.com.rjterapia.model.dao.ProfissionalDao;
import br.com.rjterapia.model.entity.Profissional;
import br.com.rjterapia.model.exception.DaoException;
import br.com.rjterapia.model.exception.ServiceException;

@RequestScoped
public class AvaliacaoService {

	@Inject
	private AvaliacaoDao avaliacaoDao;
	@Inject
	private ProfissionalDao profissionalDao;
	
	public AvaliacaoService() {
		
	}

	public List<Profissional> getMaisVotadas(int quantidade) throws ServiceException {
		try {
			List<Object[]> avaliacaos = avaliacaoDao.getMaisVotadas(quantidade);
			List<Profissional> profissionals = new ArrayList<Profissional>();
			
			for(Object[] avaliacao : avaliacaos) {
				Profissional profissional = profissionalDao.getById((int)avaliacao[1]);
				profissional.setTotalVotos((long)avaliacao[0]);
				profissionals.add(profissional);
			}
			
			return profissionals;
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}