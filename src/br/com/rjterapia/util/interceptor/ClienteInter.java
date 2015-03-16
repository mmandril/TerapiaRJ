package br.com.rjterapia.util.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Interface usada para permitir acesso a alguns métodos sem passar pelo interceptor
 * 
 * @author CSP - Marcus Vinicius Silva de Souza 
 * 29/01/2014
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ClienteInter {

}
