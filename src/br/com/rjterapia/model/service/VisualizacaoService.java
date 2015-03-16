package br.com.rjterapia.model.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.rjterapia.model.dao.ProfissionalDao;
import br.com.rjterapia.model.dao.VisualizacaoDao;
import br.com.rjterapia.model.entity.Profissional;
import br.com.rjterapia.model.exception.DaoException;
import br.com.rjterapia.model.exception.ServiceException;

@RequestScoped
public class VisualizacaoService {

	@Inject
	private VisualizacaoDao visualizacaoDao;
	@Inject
	private ProfissionalDao profissionalDao;
	
	public VisualizacaoService() {
		
	}

	public List<Profissional> getMaisVotadasMes(int quantidade) throws ServiceException {
		try {
			//Pegar Mes corrente
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(new Date());
			
			int mes = calendar.get(Calendar.MONTH) + 1;
			
			List<Object[]> visualizacoes = visualizacaoDao.getMaisVotadasMes(mes, quantidade);
			List<Profissional> profissionals = new ArrayList<Profissional>();
			
			for(Object[] avaliacao : visualizacoes) {
				Profissional profissional = profissionalDao.getById((int)avaliacao[1]);
				profissional.setTotalVotos((long)avaliacao[0]);
				profissionals.add(profissional);
			}
			
			return profissionals;
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	public List<Profissional> getMaisVotadasGeral(int quantidade) throws ServiceException {
		try {
			List<Object[]> visualizacoes = visualizacaoDao.getMaisVotadasGeral(quantidade);
			List<Profissional> profissionals = new ArrayList<Profissional>();
			
			for(Object[] avaliacao : visualizacoes) {
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
