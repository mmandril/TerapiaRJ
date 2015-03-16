package br.com.rjterapia.controller.site;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.rjterapia.controller.profissional.ProfissionalController;
import br.com.rjterapia.model.entity.Usuario;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.model.service.AvaliacaoService;
import br.com.rjterapia.model.service.ProfissionalService;
import br.com.rjterapia.model.service.UsuarioService;
import br.com.rjterapia.model.service.VisualizacaoService;
import br.com.rjterapia.util.InitializeAndUnproxy;
import br.com.rjterapia.util.PerfilEnum;
import br.com.rjterapia.util.VariaveisSessao;
import br.com.rjterapia.util.interceptor.LiberadoInter;

@Controller
@Path(value={""})
public class IndexController {
	
	@Inject
	private Result result;
	@Inject
	private Validator validator;
	@Inject
	private HttpSession session;
	@Inject
	private VariaveisSessao variaveisSessao;
	@Inject
	private UsuarioService usuarioService;
	@Inject
	private ProfissionalService profissionalService;
	@Inject
	private AvaliacaoService avaliacaoService;
	@Inject
	private VisualizacaoService visualizacaoService;
	
	
    public IndexController() {
		
    }
    
    @Path(value={"/"})
    @LiberadoInter
    public void index() throws ServiceException {
    	//Carrega as destaques da Semana (5)
    	result.include("destaques", profissionalService.getDestaques(5));
    	//Carrega as mais votadas (5)
    	result.include("votadas", avaliacaoService.getMaisVotadas(5));
    	//Carrega mais visualizadas mês(5)
    	result.include("visualizadasMes", visualizacaoService.getMaisVotadasMes(5));
    	//Carrega mais visualizadas geral(5)
    	result.include("visualizadasGeral", visualizacaoService.getMaisVotadasGeral(5));
    	//Carrega ultimas cadastradas(5)
    	result.include("ultimasCadastradas", profissionalService.getUltimCadast(5));
    }
    
	@Post("login")
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
			if(usuario.getPerfil().equals(PerfilEnum.ADMINISTRADOR.id)) {
				result.redirectTo(br.com.rjterapia.controller.admin.IndexController.class).home();
			}
			
			if(usuario.getPerfil().equals(PerfilEnum.CLIENTE.id)) {
				InitializeAndUnproxy.initializeAndUnproxy(usuario.getCliente());
			}
			
			if(usuario.getPerfil().equals(PerfilEnum.PROFISSIONAL.id)) {
				InitializeAndUnproxy.initializeAndUnproxy(usuario.getProfissional());
				result.redirectTo(ProfissionalController.class).index();
			}
		}
	}
	
	private void validarLogin(Usuario usuario) {
		if(StringUtils.isEmpty(usuario.getEmail())) {
			validator.add(new I18nMessage("email", "campo.obrigatorio", "Email"));
		}
		
		if(StringUtils.isEmpty(usuario.getSenha())) {
			validator.add(new I18nMessage("senha", "campo.obrigatorio", "Senha"));
		}
	}

	@Post(value={"logout"})
	@LiberadoInter
	public void logout() {
		session.invalidate();
		result.use(Results.status()).accepted();
	}
}