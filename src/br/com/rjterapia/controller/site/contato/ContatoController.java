package br.com.rjterapia.controller.site.contato;

import java.util.Date;
import java.util.Properties;

import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.EmailException;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.rjterapia.util.Autenticar;
import br.com.rjterapia.util.interceptor.LiberadoInter;

@Controller
@Path(value={"/contato"})
public class ContatoController {

	@Inject
	private Result result;
	
	public ContatoController() {

	}

	@Path(value={"anuncie"})
	@LiberadoInter
	public void anuncie() {

	}

	@Path(value={"anuncio/enviar"})
	@LiberadoInter
	public void enviar(String nome, String email, String tipo, String clinica, String telefone, String mensagem) throws EmailException, AddressException, MessagingException {
		
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "localhost");
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.auth","true");
		Session session = Session.getInstance(props,
				new Autenticar("anuncie@terapiarj.com.br","wide85")
				);
		MimeMessage message = new MimeMessage(session);
		message.setRecipient(
				Message.RecipientType.TO, 
				new InternetAddress("anuncie@terapiarj.com.br")
		);
		message.setFrom(new InternetAddress(email));
		message.setSubject("Novo Anuncio - " + nome);
		
		message.setSentDate(new Date());
		
		String mensagemEnviar = "Olá! Gostaria de fazer um anúncio!<br/>";
		mensagemEnviar += "Tipo Anúncion: "+tipo+"</br>";
		mensagemEnviar += "Clínica: "+clinica+"</br>";
		mensagemEnviar += "Email: "+email+"</br>";
		mensagemEnviar += "Telefone: "+telefone+"</br>";
		mensagemEnviar += "Mensagem: "+mensagem+"</br>";
		
		message.setContent(mensagemEnviar,"text/html");
		Transport.send(message);
		
		result.include("mensagem", "E-mail enviado com sucesso! Em breve você terá uma resposta em seu e-mail!");
		result.redirectTo(this).anuncie();
	}
}
