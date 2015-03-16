package br.com.rjterapia.model.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.rjterapia.model.dao.TipoAvaliacaoDao;

@RequestScoped
public class TipoAvaliacaoService {

	@Inject
	private TipoAvaliacaoDao tipoAvaliacaoDao;
	
	public TipoAvaliacaoService() {
		
	}
}
