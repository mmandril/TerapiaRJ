package br.com.rjterapia.controller.admin.plano;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.rjterapia.model.entity.Plano;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.model.service.PlanoService;
import br.com.rjterapia.util.interceptor.AdministradorInter;

@Controller
@Path(value={"/admin/plano"})
public class PlanoController {

	@Inject
	private Result result;
	@Inject
	private Validator validator;
	@Inject
	private PlanoService planoService;
	
	public PlanoController() {
		
	}
	
	@Path(value={"", "/"})
	@AdministradorInter
	public void index() throws ServiceException {
		result.include("planos", planoService.getAll("nome"));
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
	
	@Path(value={"editar/{plano.id}"})
	@AdministradorInter
	public void editar(Plano plano) throws ServiceException {
		result.include("plano", planoService.getById(plano));
		result.redirectTo(this).manter();
	}
	
	@Path(value={"salvar"})
	@AdministradorInter
	public void salvar(Plano plano) throws ServiceException {
		valida(plano);		
		validator.onErrorRedirectTo(this).manter();
		
		if(plano.getId() == null) {
			planoService.salvar(plano);
			result.include("mensagem", new I18nMessage("", "mensagem.salvo", ""));
		}else {
			planoService.alterar(plano);
			result.include("mensagem", new I18nMessage("", "mensagem.alterado", ""));
		}
		
		result.redirectTo(this).index();
	}

	private void valida(Plano plano) {
		if(StringUtils.isEmpty(plano.getNome())) {
			validator.add(new I18nMessage("nome", "campo.obrigatorio", "Nome"));
		}
		
		if(validator.hasErrors()) {
			result.include("plano", plano);
		}
	}
}