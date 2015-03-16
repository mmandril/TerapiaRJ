package br.com.rjterapia.util;

public enum PerfilEnum {
	
	ADMINISTRADOR(1), CLIENTE(2), PROFISSIONAL(3);
	
	public int id;
	
	private PerfilEnum(int id) {
		this.id = id;
	}

}