package br.com.rjterapia.util;

public enum TipoProfissionalEnum {

	TERAPEUTA(1, "Terapeuta"), MASSAGISTA(2, "Massagista");

	public int id;
	public String tipo;

	private TipoProfissionalEnum(Integer id, String tipo) {
		this.id = id;
		this.tipo = tipo;
	}
}
