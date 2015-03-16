package br.com.rjterapia.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;

import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.rjterapia.model.entity.Cliente;

public class Imagens {
	
	private File pastaImagens;

	public Imagens(ServletContext context){
		String caminhoImagens = context.getRealPath("/public_html/imagens/");
		pastaImagens = new File(caminhoImagens);
		//pastaImagens.mkdir();
	}
	
	public void salva(UploadedFile imagem, Cliente cliente){
		File destino = new File(pastaImagens+"/"+cliente.getId()+".jpg");

		try {
			IOUtils.copy(imagem.getFile(), new FileOutputStream(destino));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao copiar imagem", e);
		}
	}
	public File mostra(Cliente cliente){
		return new File(pastaImagens+"/"+cliente.getId()+".jpg");
	}
}
