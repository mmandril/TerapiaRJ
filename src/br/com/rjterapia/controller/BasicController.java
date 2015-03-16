//package br.com.rjterapia.controller;
//
//import javax.inject.Inject;
//import javax.servlet.http.HttpSession;
//
//import br.com.caelum.vraptor.Result;
//import br.com.caelum.vraptor.validator.Validator;
//import br.com.rjterapia.util.VariaveisSessao;
//
//public class BasicController {
//
//	@Inject
//	private Result result;
//	@Inject
//	private HttpSession session;
//	@Inject
//	private Validator validator;
//	@Inject
//	private VariaveisSessao variaveisSessao;
//	
//	public BasicController() {
//		
//	}
//
//	public Result result {
//		return result;
//	}
//
//	public HttpSession getSession() {
//		return session;
//	}
//
//	public Validator validator {
//		return validator;
//	}
//
//	public VariaveisSessao variaveisSessao {
//		return variaveisSessao;
//	}
//
//	public void setResult(Result result) {
//		this.result = result;
//	}
//
//	public void setSession(HttpSession session) {
//		this.session = session;
//	}
//
//	public void setValidator(Validator validator) {
//		this.validator = validator;
//	}
//
//	public void setVariaveisSessao(VariaveisSessao variaveisSessao) {
//		this.variaveisSessao = variaveisSessao;
//	}
//}
