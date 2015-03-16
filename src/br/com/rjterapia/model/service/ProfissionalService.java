package br.com.rjterapia.model.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.rjterapia.model.dao.AreaAtuacaoDao;
import br.com.rjterapia.model.dao.FotoDao;
import br.com.rjterapia.model.dao.ProfissionalDao;
import br.com.rjterapia.model.dao.TipoProfissionalDao;
import br.com.rjterapia.model.dao.UsuarioDao;
import br.com.rjterapia.model.dao.VisualizacaoDao;
import br.com.rjterapia.model.entity.AreaAtuacao;
import br.com.rjterapia.model.entity.Foto;
import br.com.rjterapia.model.entity.Profissional;
import br.com.rjterapia.model.entity.TipoProfissional;
import br.com.rjterapia.model.entity.Usuario;
import br.com.rjterapia.model.entity.Visualizacao;
import br.com.rjterapia.model.exception.DaoException;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.util.InitializeAndUnproxy;
import br.com.rjterapia.util.OrderRandom;
import br.com.rjterapia.util.interceptor.TransactionalInter;

@RequestScoped
public class ProfissionalService {

	@Inject
	private ProfissionalDao profissionalDao;
	@Inject
	private TipoProfissionalDao tipoProfissionalDao;
	@Inject
	private AreaAtuacaoDao areaAtuacaoDao;
	@Inject
	private ServletContext context;
	@Inject
	private FotoDao fotoDao;
	@Inject
	private UsuarioDao usuarioDao;
	@Inject
	private VisualizacaoDao visualizacaoDao;
	

	public ProfissionalService() {

	}

	public List<Profissional> getAll(String propriedade) throws ServiceException {
		try {

			if(!StringUtils.isEmpty(propriedade)) {
				List<Order> orders = Arrays.asList(new Order[] {Order.asc(propriedade)});

				return profissionalDao.listAll(null, orders);
			}else {
				return profissionalDao.listAll();
			}
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	public Profissional getById(Profissional profissional) throws ServiceException {
		try {
			List<Criterion> criterions = Arrays.asList(new Criterion[] {Restrictions.eq("id", profissional.getId())});

			List<Profissional> profissionals = profissionalDao.listAll(criterions, null);

			if(profissionals != null && profissionals.size() > 0) {
				profissional = profissionals.get(0);
				InitializeAndUnproxy.initializeAndUnproxy(profissional.getFotos());				
				return profissional;
			}

			return null;
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@TransactionalInter
	public void salvar(Profissional profissional, List<UploadedFile> fotos, Usuario usuario) throws ServiceException, FileNotFoundException, IOException {
		try {
			profissional.setDtIncl(new Date());
			profissional = profissionalDao.merge(profissional);


			if(fotos != null && fotos.size() > 0) {
				String caminhoImagens = ("/home/terapia/public_html/imagens");
				File pastaImagens = new File(caminhoImagens);
				for(UploadedFile foto : fotos) {
					File destino = new File(pastaImagens+"/"+profissional.getId()+ "-"+ foto.getFileName());
					IOUtils.copy(foto.getFile(), new FileOutputStream(destino));

					Foto fotoProf = new Foto();
					fotoProf.setProfissional(profissional);
					fotoProf.setFoto(profissional.getId()+ "-"+ foto.getFileName());
					fotoProf.setCapa("N");
					fotoDao.merge(fotoProf);
				}
			}

			if(usuario != null && usuario.getId() != null) {
				usuario = usuarioDao.getById(usuario.getId());
				usuario.setDtAlter(new Date());
				usuario.setProfissional(profissional);
				usuarioDao.merge(usuario);
			}
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@TransactionalInter
	public void alterar(Profissional profissional, List<UploadedFile> fotos, Usuario usuario) throws ServiceException, FileNotFoundException, IOException {
		try {
			Profissional tmp = profissionalDao.getById(profissional.getId());
			profissional.setDtIncl(tmp.getDtIncl());
			profissional.setPlano(tmp.getPlano());
			profissional.setDestaque(tmp.getDestaque());
			profissional.setTipoProfissional(tmp.getTipoProfissional());
			profissionalDao.merge(profissional);

			if(fotos != null && fotos.size() > 0) {
				String caminhoImagens = ("/home/terapia/public_html/imagens");
				File pastaImagens = new File(caminhoImagens);
				for(UploadedFile foto : fotos) {
					File destino = new File(pastaImagens+"/"+profissional.getId()+ "-"+ foto.getFileName());
					IOUtils.copy(foto.getFile(), new FileOutputStream(destino));

					Foto fotoProf = new Foto();
					fotoProf.setProfissional(profissional);
					fotoProf.setFoto(profissional.getId()+ "-"+ foto.getFileName());
					fotoProf.setCapa("N");
					fotoDao.merge(fotoProf);
				}
			}
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	@TransactionalInter
	public void alterarAdmin(Profissional profissional, List<UploadedFile> fotos, Usuario usuario) throws ServiceException, FileNotFoundException, IOException {
		try {
			Profissional tmp = profissionalDao.getById(profissional.getId());
			profissional.setDtIncl(tmp.getDtIncl());
			profissionalDao.merge(profissional);

			if(fotos != null && fotos.size() > 0) {
				String caminhoImagens = ("/home/terapia/public_html/imagens/");
				File pastaImagens = new File(caminhoImagens);
				for(UploadedFile foto : fotos) {
					File destino = new File(pastaImagens+"/"+profissional.getId()+ "-"+ foto.getFileName());
					IOUtils.copy(foto.getFile(), new FileOutputStream(destino));

					Foto fotoProf = new Foto();
					fotoProf.setProfissional(profissional);
					fotoProf.setFoto(profissional.getId()+ "-"+ foto.getFileName());
					fotoProf.setCapa("N");
					fotoDao.merge(fotoProf);
				}
			}
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@TransactionalInter
	public void inativar(Profissional profissional) throws ServiceException, FileNotFoundException, IOException {

		try {
			profissional = getById(profissional);
			profissional.setDtInat(new Date());

			profissionalDao.merge(profissional);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@TransactionalInter
	public void ativar(Profissional profissional) throws ServiceException, FileNotFoundException, IOException {
		try {
			profissional = getById(profissional);
			profissional.setDtInat(null);

			profissionalDao.merge(profissional);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	public List<Profissional> getDestaques(int quantidade) throws ServiceException {
		try {
			List<Criterion> criterions = Arrays.asList(new Criterion[] {Restrictions.eq("destaque", "S"), Restrictions.isNull("dtInat")});

			return profissionalDao.list(criterions, null, quantidade);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	public List<Profissional> getUltimCadast(int quantidade) throws ServiceException {
		try {
			List<Order> orders = Arrays.asList(new Order[] {Order.desc("dtIncl")});
			List<Criterion> criterions = Arrays.asList(new Criterion[] {Restrictions.isNull("dtInat")});

			return profissionalDao.list(criterions, orders, quantidade);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	public List<Profissional> getAllAtivo() throws ServiceException {
		try {
			List<Criterion> criterions = Arrays.asList(new Criterion[] {Restrictions.isNull("dtInat")});
			List<Order> oders = Arrays.asList(new OrderRandom());
			return profissionalDao.listAll(criterions, oders);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	public List<Profissional> getByTipoArea(TipoProfissional tipoProfissional, AreaAtuacao areaAtuacao) throws ServiceException {
		try {
			List<Criterion> criterions = null;
			if(tipoProfissional.getId() == null && areaAtuacao.getId() == null) {
				List<Criterion> criterionsTipoProfissional = Arrays.asList(new Criterion[] {Restrictions.eq("nome", tipoProfissional.getNome())});
				List<TipoProfissional> tipoProfissionals = tipoProfissionalDao.listAll(criterionsTipoProfissional, null);
				if(tipoProfissionals != null && tipoProfissionals.size() > 0) {
					tipoProfissional = tipoProfissionals.get(0);
				}

				List<Criterion> criterionsAreaAtuacao = Arrays.asList(new Criterion[] {Restrictions.eq("nome", areaAtuacao.getNome())});
				List<AreaAtuacao> areaAtuacaos = areaAtuacaoDao.listAll(criterionsAreaAtuacao, null);
				if(areaAtuacaos != null && areaAtuacaos.size() > 0) {
					areaAtuacao = areaAtuacaos.get(0);
				}
			}
			criterions = Arrays.asList(new Criterion[] {Restrictions.eq("areaAtuacao.id", areaAtuacao.getId()), Restrictions.eq("tipoProfissional.id", tipoProfissional.getId())});
			List<Order> oders = Arrays.asList(new OrderRandom());
			return profissionalDao.listAll(criterions, oders);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	public List<Profissional> getByTipo(TipoProfissional tipoProfissional) throws ServiceException {
		try {
			List<Criterion> criterions = null;
			if(tipoProfissional.getId() == null) {
				List<Criterion> criterionsTipoProfissional = Arrays.asList(new Criterion[] {Restrictions.eq("nome", tipoProfissional.getNome())});
				List<TipoProfissional> tipoProfissionals = tipoProfissionalDao.listAll(criterionsTipoProfissional, null);
				if(tipoProfissionals != null && tipoProfissionals.size() > 0) {
					tipoProfissional = tipoProfissionals.get(0);
				}
			}
			criterions = Arrays.asList(new Criterion[] {Restrictions.eq("tipoProfissional.id", tipoProfissional.getId())});

			List<Order> oders = Arrays.asList(new OrderRandom());

			return profissionalDao.listAll(criterions, oders);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@TransactionalInter
	public void atualizaVisualizacao(Profissional profissional) throws ServiceException {
		try {
			Visualizacao visualizacao = new Visualizacao();
			visualizacao.setDtVisualizacao(new Date());
			visualizacao.setProfissional(profissional);
			
			visualizacaoDao.merge(visualizacao);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}