package br.com.rjterapia.controller.site.profissional;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.rjterapia.model.entity.AreaAtuacao;
import br.com.rjterapia.model.entity.Profissional;
import br.com.rjterapia.model.entity.TipoProfissional;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.model.service.AreaAtuacaoService;
import br.com.rjterapia.model.service.ProfissionalService;
import br.com.rjterapia.model.service.TipoProfissionalService;
import br.com.rjterapia.util.interceptor.LiberadoInter;

@Controller
@Path(value={"/profissionais"})
public class ProfissionalController {

	@Inject
	private Result result;
	@Inject
	private ProfissionalService profissionalService;
	@Inject
	private AreaAtuacaoService areaAtuacaoService;
	@Inject
	private TipoProfissionalService tipoProfissionalService;
	
	public ProfissionalController() {
		
	}
	
	@Path(value={""}) 
	@LiberadoInter
	public void index() {
		
	}
	
	@Path(value={"tipoArea/{tipoProfissional.id}/{areaAtuacao.id}"})
	@LiberadoInter
	public void tipoArea(TipoProfissional tipoProfissional, AreaAtuacao areaAtuacao) throws ServiceException {
		result.redirectTo(this).tipoProfissionalArea(tipoProfissionalService.getById(tipoProfissional), areaAtuacaoService.getById(areaAtuacao));
	}
	
	@Path(value={"view/{profissional.id}"})
	@LiberadoInter
	public void view(Profissional profissional) throws ServiceException {
		result.include("profissional", profissionalService.getById(profissional));
		
		//Atualiza Visualização
		profissionalService.atualizaVisualizacao(profissional);
	}
	
	@Path(value={"tipo/{tipoProfissional.id}"})
	@LiberadoInter
	public void tipo(TipoProfissional tipoProfissional) throws ServiceException {
		result.redirectTo(this).tipoProfissional(tipoProfissionalService.getById(tipoProfissional));
	}
	
	@Path(value={"{tipoProfissional.nome}"})
	@LiberadoInter
	public void tipoProfissional(TipoProfissional tipoProfissional) throws ServiceException {
		result.include("profissionals", profissionalService.getByTipo(tipoProfissional));
		result.include("tipoProfissional", tipoProfissionalService.getById(tipoProfissional));
		result.include("areaAtuacaos", areaAtuacaoService.getAll("posicao"));
		result.include("areaAtuacao", null);
		result.forwardTo(this).index();
	}
	
	@Path(value={"tipo/{tipoProfissional.nome}/area/{areaAtuacao.nome}"})
	@LiberadoInter
	public void tipoProfissionalArea(TipoProfissional tipoProfissional, AreaAtuacao areaAtuacao) throws ServiceException {
		result.include("profissionals", profissionalService.getByTipoArea(tipoProfissional, areaAtuacao));
		result.include("tipoProfissional", tipoProfissionalService.getById(tipoProfissional));
		result.include("areaAtuacaos", areaAtuacaoService.getAll("posicao"));
		result.include("areaAtuacao", areaAtuacaoService.getById(areaAtuacao));
		result.forwardTo(this).index();
	}
}