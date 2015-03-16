package br.com.rjterapia.controller.admin.profissional;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.rjterapia.model.entity.Profissional;
import br.com.rjterapia.model.entity.Usuario;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.model.service.AreaAtuacaoService;
import br.com.rjterapia.model.service.PlanoService;
import br.com.rjterapia.model.service.ProfissionalService;
import br.com.rjterapia.model.service.TipoProfissionalService;
import br.com.rjterapia.model.service.UsuarioService;
import br.com.rjterapia.util.interceptor.AdministradorInter;

@Controller
@Path(value={"/admin/profissional"})
public class ProfissionalController {
	
	@Inject
	private Result result;
	@Inject
	private Validator validator;
	@Inject
	private ProfissionalService profissionalService;
	@Inject
	private PlanoService planoService;
	@Inject
	private AreaAtuacaoService areaAtuacaoService;
	@Inject
	private TipoProfissionalService tipoProfissionalService;
	@Inject
	private UsuarioService usuarioService;

	public ProfissionalController() {
		
	}
	
	@Path(value={"", "/"})
	@AdministradorInter
	public void index() throws ServiceException {
		result.include("profissionals", profissionalService.getAll("nome"));
	}
	
	@Path(value={"novo/{usuario.id}"})
	@AdministradorInter
	public void novo(Usuario usuario) throws ServiceException {
		usuario = usuarioService.getById(usuario);
		result.include("usuario", usuario);
		
		if(usuario.getProfissional() != null && usuario.getProfissional().getId() != null) {
			result.include("profissional", profissionalService.getById(usuario.getProfissional()));
		}
		result.redirectTo(this).manter();
	}
	
	@Path(value={"manter"})
	@AdministradorInter
	public void manter() throws ServiceException {
		result.include("planos", planoService.getAll("nome"));
		result.include("areaAtuacaos", areaAtuacaoService.getAll("nome"));
		result.include("tipoProfissionals", tipoProfissionalService.getAll("nome"));
	}
	
	@Path(value={"editar/{profissional.id}"})
	@AdministradorInter
	public void editar(Profissional profissional) throws ServiceException {
		result.include("profissional", profissionalService.getById(profissional));
		result.redirectTo(this).manter();
	}
	
	@Path(value={"salvar"})
	@AdministradorInter
	public void salvar(Profissional profissional, Usuario usuario) throws ServiceException, FileNotFoundException, IOException {
		valida(profissional);		
		validator.onErrorRedirectTo(this).manter();
		
		if(profissional.getId() == null) {
			profissionalService.salvar(profissional, null, usuario);
			result.include("mensagem", new I18nMessage("", "mensagem.salvo", ""));
		}else {
			profissionalService.alterarAdmin(profissional, null, usuario);
			result.include("mensagem", new I18nMessage("", "mensagem.alterado", ""));
		}
		
		result.redirectTo(this).index();
	}
	
	@Path(value={"inativar/{profissional.id}"})
	@AdministradorInter
	public void inativar(Profissional profissional) throws ServiceException, FileNotFoundException, IOException {
		profissionalService.inativar(profissional);
		
		result.include("mensagem", new I18nMessage("", "mensagem.inativo", ""));
		result.redirectTo(this).index();
	}
	
	@Path(value={"ativar/{profissional.id}"})
	@AdministradorInter
	public void ativar(Profissional profissional) throws ServiceException, FileNotFoundException, IOException {
		profissionalService.ativar(profissional);
		
		result.include("mensagem", new I18nMessage("", "mensagem.ativo", ""));
		result.redirectTo(this).index();
	}

	private void valida(Profissional profissional) {
		if(StringUtils.isEmpty(profissional.getNome())) {
			validator.add(new I18nMessage("nome", "campo.obrigatorio", "Nome"));
		}
		
		if(StringUtils.isEmpty(profissional.getDescricao())) {
			validator.add(new I18nMessage("nome", "campo.obrigatorio", "Descrição"));
		}
		
		if(validator.hasErrors()) {
			result.include("profissional", profissional);
		}
	}
}
