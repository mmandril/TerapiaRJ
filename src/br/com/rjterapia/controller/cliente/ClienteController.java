package br.com.rjterapia.controller.cliente;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.rjterapia.util.interceptor.ClienteInter;

@Controller
@Path(value={"/adminCliente"})
public class ClienteController {

	
	public ClienteController() {
		
	}
	
	
	@Path(value={""})
	@ClienteInter
	public void index() {
		
	}
}
