package br.com.rjterapia.controller.admin.clinica;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.rjterapia.model.entity.Clinica;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.model.service.ClinicaService;
import br.com.rjterapia.util.interceptor.AdministradorInter;

@Controller
@Path(value={"/admin/clinica"})
public class ClinicaController {
	
	@Inject
	private Result result;
	@Inject
	private Validator validator;
	@Inject
	private ClinicaService clinicaService;
	
	public ClinicaController() {
		
	}
	
	
	@Path(value={""})
	@AdministradorInter
	public void index() throws ServiceException {
		result.include("clinicas", clinicaService.getAll("nome"));
	}
	
	@Path(value={"novo"})
	@AdministradorInter
	public void novo() throws ServiceException {
		result.redirectTo(this).manter();
	}
	
	@Path(value={"manter"})
	@AdministradorInter
	public void manter() throws ServiceException {
		
	}
	
	@Path(value={"editar/{clinica.id}"})
	@AdministradorInter
	public void editar(Clinica clinica) throws ServiceException {
		result.include("clinica", clinicaService.getById(clinica));
		result.redirectTo(this).manter();
	}
	
	@Path(value={"salvar"})
	@AdministradorInter
	public void salvar(Clinica clinica) throws ServiceException {
		valida(clinica);		
		validator.onErrorRedirectTo(this).manter();
		
		if(clinica.getId() == null) {
			clinicaService.salvar(clinica);
			result.include("mensagem", new I18nMessage("", "mensagem.salvo", ""));
		}else {
			clinicaService.alterar(clinica);
			result.include("mensagem", new I18nMessage("", "mensagem.alterado", ""));
		}
		
		result.redirectTo(this).index();
	}
	
	@Path(value={"inativar/{clinica.id}"})
	@AdministradorInter
	public void inativar(Clinica clinica) throws ServiceException {
		clinicaService.inativar(clinica);
		
		result.include("mensagem", new I18nMessage("", "mensagem.inativo", ""));
		result.redirectTo(this).index();
	}
	
	@Path(value={"ativar/{clinica.id}"})
	@AdministradorInter
	public void ativar(Clinica clinica) throws ServiceException {
		clinicaService.ativar(clinica);
		
		result.include("mensagem", new I18nMessage("", "mensagem.ativo", ""));
		result.redirectTo(this).index();
	}
	
	private void valida(Clinica clinica) {
		if(StringUtils.isEmpty(clinica.getNome())) {
			validator.add(new I18nMessage("nome", "campo.obrigatorio", "Nome"));
		}
		
		if(StringUtils.isEmpty(clinica.getDescricao())) {
			validator.add(new I18nMessage("nome", "campo.obrigatorio", "Descrição"));
		}
		
		if(validator.hasErrors()) {
			result.include("clinica", clinica);
		}
	}
}