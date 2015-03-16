package br.com.rjterapia.controller.admin.tipoprofissional;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.rjterapia.model.entity.TipoProfissional;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.model.service.TipoProfissionalService;
import br.com.rjterapia.util.interceptor.AdministradorInter;

@Controller
@Path(value={"/admin/tipoProfissional"})
public class TipoProfissionalController {

	@Inject
	private Result result;
	@Inject
	private Validator validator;
	@Inject
	private TipoProfissionalService tipoProfissionalService;
	
	public TipoProfissionalController() {
		
	}
	
	@Path(value={"", "/"})
	@AdministradorInter
	public void index() throws ServiceException {
		result.include("tipoProfissionals", tipoProfissionalService.getAll("nome"));
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
	
	@Path(value={"editar/{tipoProfissional.id}"})
	@AdministradorInter
	public void editar(TipoProfissional tipoProfissional) throws ServiceException {
		result.include("tipoProfissional", tipoProfissionalService.getById(tipoProfissional));
		result.redirectTo(this).manter();
	}
	
	@Path(value={"salvar"})
	@AdministradorInter
	public void salvar(TipoProfissional tipoProfissional) throws ServiceException {
		valida(tipoProfissional);		
		validator.onErrorRedirectTo(this).manter();
		
		if(tipoProfissional.getId() == null) {
			tipoProfissionalService.salvar(tipoProfissional);
			result.include("mensagem", new I18nMessage("", "mensagem.salvo", ""));
		}else {
			tipoProfissionalService.alterar(tipoProfissional);
			result.include("mensagem", new I18nMessage("", "mensagem.alterado", ""));
		}
		
		result.redirectTo(this).index();
	}

	private void valida(TipoProfissional tipoProfissional) {
		if(StringUtils.isEmpty(tipoProfissional.getNome())) {
			validator.add(new I18nMessage("nome", "campo.obrigatorio", "Nome"));
		}
		
		if(StringUtils.isEmpty(tipoProfissional.getDescricao())) {
			validator.add(new I18nMessage("nome", "campo.obrigatorio", "Descrição"));
		}
		
		if(validator.hasErrors()) {
			result.include("tipoProfissional", tipoProfissional);
		}
	}
}