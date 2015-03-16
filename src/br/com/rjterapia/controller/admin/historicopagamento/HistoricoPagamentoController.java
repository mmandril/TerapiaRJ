package br.com.rjterapia.controller.admin.historicopagamento;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.model.service.HistoricoPagamentoService;

@Controller
@Path(value={"/admin/historicoPagamento"})
public class HistoricoPagamentoController {
	
	@Inject
	private Result result;
	@Inject
	private HistoricoPagamentoService historicoPagamentoService;

	public HistoricoPagamentoController() {
		
	}
	
	@Path(value={""})
	public void index() throws ServiceException {
		result.include("historicoPagamentos", historicoPagamentoService.getAllVencDesc());
	}
}