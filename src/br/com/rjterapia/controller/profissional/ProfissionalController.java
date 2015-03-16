package br.com.rjterapia.controller.profissional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.rjterapia.model.entity.Profissional;
import br.com.rjterapia.model.entity.Usuario;
import br.com.rjterapia.model.exception.ServiceException;
import br.com.rjterapia.model.service.AreaAtuacaoService;
import br.com.rjterapia.model.service.ProfissionalService;
import br.com.rjterapia.model.service.UsuarioService;
import br.com.rjterapia.util.VariaveisSessao;
import br.com.rjterapia.util.interceptor.ProfissionalInter;

@Controller
@Path(value={"/adminProfissional"})
public class ProfissionalController {

	@Inject
	private Result result;
	@Inject
	private VariaveisSessao variaveisSessao;
	@Inject
	private UsuarioService usuarioService;
	@Inject
	private ProfissionalService profissionalService;
	@Inject
	private AreaAtuacaoService areaAtuacaoService;
	
	public ProfissionalController() {
		
	}
	
	@Path(value={""})
	@ProfissionalInter
	public void index() throws ServiceException {
		result.include("areaAtuacaos", areaAtuacaoService.getAll("nome"));
		result.include("profissional", profissionalService.getById(variaveisSessao.getUsuario().getProfissional()));
	}
	
	@Path(value={"alteraSenha"})
	@ProfissionalInter
	public void alteraSenha(Usuario usuario) throws ServiceException {
		usuarioService.alteraSenha(usuario);
		
		result.redirectTo(this).index();
	}
	
	@Post(value={"alterar"})
	@ProfissionalInter
	public void alterar(Profissional profissional, List<UploadedFile> fotos, Usuario usuario) throws ServiceException, FileNotFoundException, IOException {
		profissionalService.alterar(profissional, fotos, usuario);
		
		result.include("mensagem", "Anuncio alterado com sucesso!");
		result.redirectTo(this).index();
	}
}