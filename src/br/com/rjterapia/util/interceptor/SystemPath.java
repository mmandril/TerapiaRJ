package br.com.rjterapia.util.interceptor;

import javax.enterprise.inject.Specializes;

import org.apache.commons.lang3.StringUtils;

import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.view.DefaultPathResolver;

/** Classe que sobrescreve o PathResolver do VRaptor
 * 
 * @author CSP - Marcus Vinicius Silva de Souza 
 * 29/01/2014
 */

@Specializes
public class SystemPath extends DefaultPathResolver  {  
	
	/**
     * @deprecated CDI eyes only
     */
    public SystemPath() {
    }
	
	@Override
	public String pathFor(ControllerMethod method) {
		final Class<?> clazz = method.getController().getType();
        final Package pkg = clazz.getPackage();  
        
        String caminho[] = pkg.getName().split("controller");
        String pastaTmp[] = null;
        
        if(caminho != null && caminho.length > 1)
        	pastaTmp = caminho[1].split("\\.");
        
        String pasta = "";
        
        if(pastaTmp != null)
        {
        	for(int i = 0; i < pastaTmp.length; i++)
	        {
	        	if(!StringUtils.isEmpty(pastaTmp[i]))
	        	{
	        		pasta = pasta + pastaTmp[i].toLowerCase()+"/";
	        	}
	        }
        }
        
        final StringBuilder s = new StringBuilder(60);  
        s.append("/jsp/"); 
        if(pasta != "")
        {
        	s.append(pasta);  
        	s.append("/");
        }
          
        s.append(method.getMethod().getName());  
        s.append(".jsp");  
        
        return s.toString();
	}  
}  