package br.com.rjterapia.controller.admin.config;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.rjterapia.controller.site.IndexController;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.model.service.ConfigService;
import br.com.rjterapia.util.interceptor.LiberadoInter;

@Controller
@Path(value={"/admin/config"})
public class ConfigController {

	@Inject
	private Result result;
	@Inject
	private ConfigService configService;
	
	public ConfigController() {
		
	}
	
	
	@LiberadoInter
	@Path(value={""})
	public void index() throws ServiceException {
		configService.config();
		
		result.redirectTo(IndexController.class).index();
	}
}
