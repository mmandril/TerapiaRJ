package br.com.rjterapia.util;

public enum PlanoEnum {

	VIP(1), NORMAL(2);
	
	public int id;
	
	private PlanoEnum(Integer id) {
		this.id = id;
	}
}
