package br.com.rjterapia.controller.admin.perfil;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.rjterapia.model.entity.Perfil;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.model.service.PerfilService;
import br.com.rjterapia.util.interceptor.AdministradorInter;

@Controller
@Path(value={"/admin/perfil"})
public class PerfilController {

	@Inject
	private Result result;
	@Inject
	private Validator validator;
	@Inject
	private PerfilService perfilService;
	
	public PerfilController() {
		
	}
	
	@Path(value={"", "/"})
	@AdministradorInter
	public void index() throws ServiceException {
		result.include("perfils", perfilService.getAll("nome"));
	}
	
	@Path(value={"novo"})
	@AdministradorInter
	public void novo() {
		result.redirectTo(this).manter();
	}
	
	@Path(value={"manter"})
	@AdministradorInter
	public void manter() {
		
	}
	
	@Path(value={"editar/{perfil.id}"})
	@AdministradorInter
	public void editar(Perfil perfil) throws ServiceException {
		result.include("perfil", perfilService.getById(perfil));
		result.redirectTo(this).manter();
	}
	
	@Path(value={"salvar"})
	@AdministradorInter
	public void salvar(Perfil perfil) throws ServiceException {
		valida(perfil);		
		validator.onErrorRedirectTo(this).manter();
		
		if(perfil.getId() == null) {
			perfilService.salvar(perfil);
			result.include("mensagem", new I18nMessage("", "mensagem.salvo", ""));
		}else {
			perfilService.alterar(perfil);
			result.include("mensagem", new I18nMessage("", "mensagem.alterado", ""));
		}
		
		result.redirectTo(this).index();
	}

	private void valida(Perfil perfil) {
		if(StringUtils.isEmpty(perfil.getNome())) {
			validator.add(new I18nMessage("nome", "campo.obrigatorio", "Nome"));
		}
		
		if(validator.hasErrors()) {
			result.include("perfil", perfil);
		}
	}
}