package br.com.rjterapia.controller.profissional.foto;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.rjterapia.controller.profissional.ProfissionalController;
import br.com.rjterapia.model.entity.Foto;
import br.com.rjterapia.model.entity.Profissional;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.model.service.FotoService;
import br.com.rjterapia.util.VariaveisSessao;
import br.com.rjterapia.util.interceptor.ProfissionalInter;

@Controller
@Path(value={"/adminProfissional/foto"})
public class FotoController {

	@Inject
	private Result result;
	@Inject
	private VariaveisSessao variaveisSessao;
	@Inject
	private FotoService fotoService;
	private Profissional profissional;
	
	public FotoController() {
		 profissional = variaveisSessao.getUsuario().getProfissional();
	}
	
	@Path(value={""})
	@ProfissionalInter
	public void index() throws ServiceException {
		result.include("fotos", fotoService.getAll(profissional));
	}
	
	@Path(value={"incluirFotos"})
	@ProfissionalInter
	public void incluirFoto(List<UploadedFile> fotos) {
		
	}
	
	@Path(value={"alterarCapa/{foto.id}"})
	@ProfissionalInter
	public void alterarCapa(Foto foto) throws ServiceException {
		fotoService.alterarCapa(foto, profissional);
		
		result.include("mensagem", "Foto de capa alterada com sucesso!");
		result.redirectTo(ProfissionalController.class).index();
	}
}