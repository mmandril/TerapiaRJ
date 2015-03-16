package br.com.rjterapia.controller.admin;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.rjterapia.controller.profissional.ProfissionalController;
import br.com.rjterapia.model.entity.Usuario;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.model.service.UsuarioService;
import br.com.rjterapia.util.PerfilEnum;
import br.com.rjterapia.util.VariaveisSessao;
import br.com.rjterapia.util.interceptor.AdministradorInter;
import br.com.rjterapia.util.interceptor.LiberadoInter;

@Controller
@Path(value={"/admin"})
public class IndexController {
	
	@Inject
	private Result result;
	@Inject
	private Validator validator;
	@Inject
	private VariaveisSessao variaveisSessao;
	@Inject
	private HttpSession session;
	@Inject
	private UsuarioService usuarioService;
	
	
    public IndexController() {
    	
    }
    
 
    @LiberadoInter
    @Path(value={""})
    public void index() {
    	
    }
    
	@Path("login")
	@LiberadoInter
	public void login(Usuario usuario) throws ServiceException {
		validarLogin(usuario);
		validator.onErrorRedirectTo(this).index();
		
		usuario = usuarioService.login(usuario);
		if(usuario == null) {
			validator.add(new I18nMessage("", "usuario.invalido"));
			validator.onErrorRedirectTo(this).index(); 
		} else {
			variaveisSessao.setUsuario(usuario);
			
			if(variaveisSessao.getUsuario().getPerfil().getId().equals(PerfilEnum.ADMINISTRADOR.id)) {
				result.redirectTo(this).home();
			}
			
			if(variaveisSessao.getUsuario().getPerfil().getId().equals(PerfilEnum.CLIENTE.id)) {
			}
			
			if(variaveisSessao.getUsuario().getPerfil().getId().equals(PerfilEnum.PROFISSIONAL.id)) {
				result.redirectTo(ProfissionalController.class).index();
			}
		}
	}
	
	@Path(value={"home"})
	@AdministradorInter
	public void home() {
		
	}


	private void validarLogin(Usuario usuario) {
		if(StringUtils.isEmpty(usuario.getEmail())) {
			validator.add(new I18nMessage("email", "campo.obrigatorio", "Email"));
		}
		
		if(StringUtils.isEmpty(usuario.getSenha())) {
			validator.add(new I18nMessage("senha", "campo.obrigatorio", "Senha"));
		}
	}

	@Path(value={"logout"})
	@LiberadoInter
	public void logout() {
		session.invalidate();
		result.redirectTo(this).index();
	}
}