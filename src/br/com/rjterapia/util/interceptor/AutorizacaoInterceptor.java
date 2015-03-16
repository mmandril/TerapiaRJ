package br.com.rjterapia.util.interceptor;

import javax.inject.Inject;

import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.rjterapia.controller.site.IndexController;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.util.PerfilEnum;
import br.com.rjterapia.util.VariaveisSessao;

/**
 * Classe que intercepta todo acesso do sistema
 * @author CSP - Marcus Vinicius Silva de Souza 
 * 29/01/2014
 */

@Intercepts
public class AutorizacaoInterceptor {
	
	@Inject
	private Result result;
	@Inject
	private Validator validator;
	@Inject
	private VariaveisSessao variaveisSessao;

	public AutorizacaoInterceptor() {
	}

	@AroundCall
	public void intercept(SimpleInterceptorStack stack, ControllerMethod method) throws InterceptionException, ServiceException {
		
		if(method.containsAnnotation(LiberadoInter.class)) {
			stack.next();
		}else {
			if(variaveisSessao == null || variaveisSessao.getUsuario() == null) {
				validator.add(new I18nMessage("erro", "mesangem.acessonegado"));
				validator.onErrorRedirectTo(IndexController.class).index();
			} else {
				if(method.containsAnnotation(AdministradorInter.class) && variaveisSessao.getUsuario().getPerfil().getId().equals(PerfilEnum.ADMINISTRADOR.id)) {
					stack.next();
				} else if(method.containsAnnotation(ClienteInter.class) && variaveisSessao.getUsuario().getPerfil().getId().equals(PerfilEnum.CLIENTE.id)) {
					stack.next();
				} else if(method.containsAnnotation(ProfissionalInter.class) && variaveisSessao.getUsuario().getPerfil().getId().equals(PerfilEnum.PROFISSIONAL.id)) {
					stack.next();
				} else {
					validator.add(new I18nMessage("erro", "mesangem.acessonegado"));
					validator.onErrorRedirectTo(IndexController.class).index();
				}
			}
		}			
	}
}