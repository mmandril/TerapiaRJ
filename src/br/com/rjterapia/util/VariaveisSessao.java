package br.com.rjterapia.util;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.rjterapia.model.entity.Usuario;

@SessionScoped
@Named(value="variaveisSessao")
public class VariaveisSessao implements Serializable {

	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
