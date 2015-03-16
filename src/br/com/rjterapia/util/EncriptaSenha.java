/**
 * @author Marcus Vinicius Silva de Souza  
 *	17/07/2014
 */
package br.com.rjterapia.util;

import java.security.MessageDigest;

/**
 * @author Marcus Vinicius Silva de Souza  
 * 17/07/2014
 */
public class EncriptaSenha {

	public static String encripta(String senha) {
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
			 
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
			  hexString.append(String.format("%02X", 0xFF & b));
			}
			senha = hexString.toString();
			return senha;
		}catch(Exception e) {
			return senha;
		}
	}
}
