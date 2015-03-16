package br.com.rjterapia.model.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.rjterapia.model.dao.UsuarioDao;
import br.com.rjterapia.model.entity.Usuario;
import br.com.rjterapia.model.exception.DaoException;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.util.EncriptaSenha;
import br.com.rjterapia.util.InitializeAndUnproxy;
import br.com.rjterapia.util.PerfilEnum;
import br.com.rjterapia.util.interceptor.TransactionalInter;

@RequestScoped
public class UsuarioService {

	@Inject
	private UsuarioDao usuarioDao;

	public UsuarioService() {

	}

	public Usuario login(Usuario usuario) throws ServiceException {
		try {
			usuario.setSenha(EncriptaSenha.encripta(usuario.getSenha()));
			List<Criterion> criterions = Arrays.asList(new Criterion[] {Restrictions.eq("email", usuario.getEmail()), Restrictions.eq("senha", usuario.getSenha())});

			List<Usuario> usuarios = usuarioDao.listAll(criterions, null);

			if(usuarios != null && usuarios.size() > 0) {
				usuario = usuarios.get(0);
				InitializeAndUnproxy.initializeAndUnproxy(usuario.getPerfil());
				
				if(usuario.getPerfil().getId() == PerfilEnum.CLIENTE.id) {
					InitializeAndUnproxy.initializeAndUnproxy(usuario.getCliente());
				}
				
				if(usuario.getPerfil().getId() == PerfilEnum.PROFISSIONAL.id) {
					InitializeAndUnproxy.initializeAndUnproxy(usuario.getProfissional());
				}
				return usuario;
			}

			return null;
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	public List<Usuario> getAll(String propriedade) throws ServiceException {
		try {
			List<Order> orders = Arrays.asList(new Order[] {Order.asc(propriedade)});

			return usuarioDao.listAll(null, orders);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	public Usuario getById(Usuario usuario) throws ServiceException {
		try {
			List<Criterion> criterions = Arrays.asList(new Criterion[] {Restrictions.eq("id", usuario.getId())});

			List<Usuario> usuarios = usuarioDao.listAll(criterions, null);

			if(usuarios != null && usuarios.size() > 0) {
				return usuarios.get(0);
			}

			return null;
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@TransactionalInter
	public void salvar(Usuario usuario) throws ServiceException {
		try {
			usuario.setSenha(EncriptaSenha.encripta(usuario.getSenha()));
			usuario.setDtIncl(new Date());
			usuarioDao.merge(usuario);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@TransactionalInter
	public void alterar(Usuario usuario) throws ServiceException {
		try {
			Usuario tmp = usuarioDao.getById(usuario.getId());
			usuario.setSenha(EncriptaSenha.encripta(usuario.getSenha()));
			usuario.setDtIncl(tmp.getDtIncl());
			usuario.setDtAlter(new Date());
			usuarioDao.merge(usuario);
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@TransactionalInter
	public void inativar(Usuario usuario) throws ServiceException {
		usuario = getById(usuario);
		usuario.setDtInat(new Date());

		salvar(usuario);
	}
	
	@TransactionalInter
	public void ativar(Usuario usuario) throws ServiceException {
		usuario = getById(usuario);
		usuario.setDtInat(null);

		salvar(usuario);
	}

	@TransactionalInter
	public void alteraSenha(Usuario usuario) throws ServiceException {
		Usuario tmp = getById(usuario);
		tmp.setSenha(EncriptaSenha.encripta(usuario.getSenha()));
		
		salvar(tmp);
	}
}