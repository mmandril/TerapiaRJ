package br.com.rjterapia.controller.admin.cliente;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.rjterapia.model.entity.Cliente;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.model.service.ClienteService;
import br.com.rjterapia.util.interceptor.AdministradorInter;

@Controller
@Path(value={"/admin/cliente"})
public class ClienteController {
	
	@Inject
	private Result result;
	@Inject
	private Validator validator;
	@Inject
	private ClienteService clienteService;

	public ClienteController() {
		
	}
	
	@Path(value={"", "/"})
	@AdministradorInter
	public void index() throws ServiceException {
		result.include("clientes", clienteService.getAll("apelido"));
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
	
	@Path(value={"editar/{cliente.id}"})
	@AdministradorInter
	public void editar(Cliente cliente) throws ServiceException {
		result.include("cliente", clienteService.getById(cliente));
		result.redirectTo(this).manter();
	}
	
	@Path(value={"salvar"})
	@AdministradorInter
	public void salvar(Cliente cliente) throws ServiceException {
		valida(cliente);		
		validator.onErrorRedirectTo(this).manter();
		
		if(cliente.getId() == null) {
			clienteService.salvar(cliente);
			result.include("mensagem", new I18nMessage("", "mensagem.salvo", ""));
		}else {
			clienteService.alterar(cliente);
			result.include("mensagem", new I18nMessage("", "mensagem.alterado", ""));
		}
		
		result.redirectTo(this).index();
	}
	
	private void valida(Cliente cliente) {
		if(StringUtils.isEmpty(cliente.getApelido())) {
			validator.add(new I18nMessage("apelido", "campo.obrigatorio", "Apelido"));
		}
		
		if(cliente.getDtNascimento() == null) {
			validator.add(new I18nMessage("dtNasc", "campo.obrigatorio", "Data Nascimento"));
		}
		
		if(validator.hasErrors()) {
			result.include("cliente", cliente);
		}
	}
}
