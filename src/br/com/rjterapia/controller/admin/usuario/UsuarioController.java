package br.com.rjterapia.controller.admin.usuario;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.rjterapia.model.entity.Usuario;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.model.service.PerfilService;
import br.com.rjterapia.model.service.UsuarioService;
import br.com.rjterapia.util.interceptor.AdministradorInter;

@Controller
@Path(value={"/admin/usuario"})
public class UsuarioController {

	@Inject
	private Result result;
	@Inject
	private Validator validator;
	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private PerfilService perfilService;
	
	public UsuarioController() {
		
	}
	
	@Path(value={"", "/"})
	@AdministradorInter
	public void index() throws ServiceException {
		result.include("usuarios", usuarioService.getAll("email"));
	}
	
	@Path(value={"novo"})
	@AdministradorInter
	public void novo() throws ServiceException {
		result.redirectTo(this).manter();
	}
	
	@Path(value={"manter"})
	@AdministradorInter
	public void manter() throws ServiceException {
		result.include("perfils", perfilService.getAll("nome"));
	}
	
	@Path(value={"editar/{usuario.id}"})
	@AdministradorInter
	public void editar(Usuario usuario) throws ServiceException {
		result.include("usuario", usuarioService.getById(usuario));
		result.redirectTo(this).manter();
	}
	
	@Path(value={"salvar"})
	@AdministradorInter
	public void salvar(Usuario usuario) throws ServiceException {
		valida(usuario);		
		validator.onErrorRedirectTo(this).manter();
		
		if(usuario.getId() == null) {
			usuarioService.salvar(usuario);
			result.include("mensagem", new I18nMessage("", "mensagem.salvo", ""));
		}else {
			usuarioService.alterar(usuario);
			result.include("mensagem", new I18nMessage("", "mensagem.alterado", ""));
		}
		
		result.redirectTo(this).index();
	}
	
	@Path(value={"inativar/{usuario.id}"})
	@AdministradorInter
	public void inativar(Usuario usuario) throws ServiceException {
		usuarioService.inativar(usuario);
		
		result.include("mensagem", new I18nMessage("", "mensagem.inativo", ""));
		result.redirectTo(this).index();
	}
	
	@Path(value={"ativar/{usuario.id}"})
	@AdministradorInter
	public void ativar(Usuario usuario) throws ServiceException {
		usuarioService.ativar(usuario);
		
		result.include("mensagem", new I18nMessage("", "mensagem.ativo", ""));
		result.redirectTo(this).index();
	}

	private void valida(Usuario usuario) {
		if(StringUtils.isEmpty(usuario.getEmail())) {
			validator.add(new I18nMessage("email", "campo.obrigatorio", "Email"));
		}
		
		if(StringUtils.isEmpty(usuario.getSenha())) {
			validator.add(new I18nMessage("senha", "campo.obrigatorio", "Senha"));
		}
		
		if(validator.hasErrors()) {
			result.include("usuario", usuario);
		}
	}
}