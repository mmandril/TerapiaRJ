package br.com.rjterapia.model.exception;

public class DaoException extends Exception
{
	private static final long serialVersionUID = 879094565061L;

	public DaoException(){}

	public DaoException(String s)
	{
		super(s);
	}

	public DaoException(Exception e)
	{
		super(e);
	}
}