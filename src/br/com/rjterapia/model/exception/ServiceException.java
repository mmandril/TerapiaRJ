package br.com.rjterapia.model.exception;


public class ServiceException extends Exception
{

	private static final long serialVersionUID = 1312515020123L;
	
	public ServiceException(){
		super("error.default");
	}

	public ServiceException(String service){
		super(service);
	}

	public ServiceException(Exception exception){
		super(exception);
	}
	
}