package br.com.rjterapia.controller.admin.areaatuacao;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.rjterapia.model.entity.AreaAtuacao;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.model.service.AreaAtuacaoService;
import br.com.rjterapia.util.interceptor.AdministradorInter;

@Controller
@Path(value={"/admin/areaAtuacao"})
public class AreaAtuacaoController {
	
	@Inject
	private Result result;
	@Inject
	private Validator validator;
	@Inject
	private AreaAtuacaoService areaAtuacaoService;
	
	public AreaAtuacaoController() {
		
	}
	
	@Path(value={"", "/"})
	@AdministradorInter
	public void index() throws ServiceException {
		result.include("areaAtuacaos", areaAtuacaoService.getAll("nome"));
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
	
	@Path(value={"editar/{areaAtuacao.id}"})
	@AdministradorInter
	public void editar(AreaAtuacao areaAtuacao) throws ServiceException {
		result.include("areaAtuacao", areaAtuacaoService.getById(areaAtuacao));
		result.redirectTo(this).manter();
	}
	
	@Path(value={"salvar"})
	@AdministradorInter
	public void salvar(AreaAtuacao areaAtuacao) throws ServiceException {
		valida(areaAtuacao);		
		validator.onErrorRedirectTo(this).manter();
		
		if(areaAtuacao.getId() == null) {
			areaAtuacaoService.salvar(areaAtuacao);
			result.include("mensagem", new I18nMessage("", "mensagem.salvo", ""));
		}else {
			areaAtuacaoService.alterar(areaAtuacao);
			result.include("mensagem", new I18nMessage("", "mensagem.alterado", ""));
		}
		
		result.redirectTo(this).index();
	}

	private void valida(AreaAtuacao areaAtuacao) {
		if(StringUtils.isEmpty(areaAtuacao.getNome())) {
			validator.add(new I18nMessage("nome", "campo.obrigatorio", "Nome"));
		}
		
		if(StringUtils.isEmpty(areaAtuacao.getDescricao())) {
			validator.add(new I18nMessage("nome", "campo.obrigatorio", "Descrição"));
		}
		
		if(validator.hasErrors()) {
			result.include("areaAtuacao", areaAtuacao);
		}
	}
}