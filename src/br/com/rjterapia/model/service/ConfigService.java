package br.com.rjterapia.model.service;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.rjterapia.model.dao.AreaAtuacaoDao;
import br.com.rjterapia.model.dao.PerfilDao;
import br.com.rjterapia.model.dao.PlanoDao;
import br.com.rjterapia.model.dao.TipoAvaliacaoDao;
import br.com.rjterapia.model.dao.TipoProfissionalDao;
import br.com.rjterapia.model.dao.UsuarioDao;
import br.com.rjterapia.model.entity.AreaAtuacao;
import br.com.rjterapia.model.entity.Perfil;
import br.com.rjterapia.model.entity.Plano;
import br.com.rjterapia.model.entity.TipoAvaliacao;
import br.com.rjterapia.model.entity.TipoProfissional;
import br.com.rjterapia.model.entity.Usuario;
import br.com.rjterapia.model.exception.DaoException;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.util.EncriptaSenha;
import br.com.rjterapia.util.PerfilEnum;
import br.com.rjterapia.util.interceptor.TransactionalInter;

@RequestScoped
public class ConfigService {
	
	@Inject
	private UsuarioDao usuarioDao;
	@Inject
	private PerfilDao perfilDao;
	@Inject
	private TipoProfissionalDao tipoProfissionalDao; 
	@Inject
	private AreaAtuacaoDao areaAtuacaoDao;
	@Inject
	private PlanoDao planoDao;
	@Inject
	private TipoAvaliacaoDao tipoAvaliacaoDao;
	
	public ConfigService() {
		
	}

	@TransactionalInter
	public void config() throws ServiceException {
		try {
			List<Usuario> usuarios = usuarioDao.listAll();
			if(usuarios == null || usuarios.size() == 0) {
				//PERFIL
				Perfil perfil = new Perfil("Administrador");
				perfilDao.merge(perfil);
				perfil = new Perfil("Cliente");
				perfilDao.merge(perfil);
				perfil = new Perfil("Profissional");
				perfilDao.merge(perfil);
				
				perfil = new Perfil();
				perfil.setId(PerfilEnum.ADMINISTRADOR.id);
				
				//USUÁRIO
				Usuario usuario = new Usuario();
				usuario.setPerfil(perfil);
				usuario.setEmail("souzamarcusrj@gmail.com");
				usuario.setSenha(EncriptaSenha.encripta("wide85"));
				usuario.setDtIncl(new Date());
				usuarioDao.merge(usuario);
				
				//TIPO PROFISSIONAL
				TipoProfissional tipoProfissional = new TipoProfissional("Fotos onde o rosto aparece", new Date(), "Terapeuta");
				tipoProfissionalDao.merge(tipoProfissional);
				tipoProfissional = new TipoProfissional("Fotos onde o rosto não aparece", new Date(), "Massagista");
				tipoProfissionalDao.merge(tipoProfissional);
				
				//ÁREA ATUAÇÃO
				AreaAtuacao areaAtuacao = new AreaAtuacao("Centro do RJ", "Centro", null, 1);
				areaAtuacaoDao.merge(areaAtuacao);
				areaAtuacao = new AreaAtuacao("Zona Sul do RJ", "Zona Sul", null, 2);
				areaAtuacaoDao.merge(areaAtuacao);
				areaAtuacao = new AreaAtuacao("Niterói", "Niterói", null, 3);
				areaAtuacaoDao.merge(areaAtuacao);
				areaAtuacao = new AreaAtuacao("Baixada fluminense", "Baixada", null, 4);
				areaAtuacaoDao.merge(areaAtuacao);
				areaAtuacao = new AreaAtuacao("Outros locais", "Outros", null, 5);
				areaAtuacaoDao.merge(areaAtuacao);
				
				//PLANO
				Plano plano = new Plano("VIP", null);
				planoDao.merge(plano);
				plano = new Plano("Normal", null);
				planoDao.merge(plano);
				
				//Tipo Avaliacao
				TipoAvaliacao tipoAvaliacao = new TipoAvaliacao("Rosto");
				tipoAvaliacaoDao.merge(tipoAvaliacao);
				tipoAvaliacao = new TipoAvaliacao("Corpo");
				tipoAvaliacaoDao.merge(tipoAvaliacao);
				tipoAvaliacao = new TipoAvaliacao("Atendimento");
				tipoAvaliacaoDao.merge(tipoAvaliacao);
			}
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}