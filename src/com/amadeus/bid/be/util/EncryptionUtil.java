package com.amadeus.bid.be.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.amadeus.bid.be.base.Base64Impl;

/**
 * utility class created to support encryption
 * @author ssinha
 *
 */
public class EncryptionUtil {
	
	/**
	 * {@link Logger} instance for tracking error while encryption
	 */
	private static Logger logger = Logger.getLogger(EncryptionUtil.class.getName());
	
	/**
	 * AES algorithm implementation
	 * @author ssinha
	 *
	 */
	public static class AES {
		
		/**
		 * encrypt {@link String} using AES algorithm
		 * @param value {@link String} to be encrypted
		 * @param key {@link String} which represent the secret key
		 * @return encrypted {@link String}
		 */
		public static String encrypt(String value, String key) {
			
			if (value == null || key == null) {
				logger.log(Level.WARNING, "Correct input are missing while trying to decrypt");
				return value;
			}
			
			try {
				Cipher cipher = Cipher.getInstance("AES");
				SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
				
				cipher.init(Cipher.ENCRYPT_MODE, keySpec);
				return new String(Base64Impl.encodeBase64(cipher.doFinal(value.getBytes("UTF-8"))), "UTF-8");
			} catch (NoSuchAlgorithmException e) {
				logger.log(Level.SEVERE, "Exception while trying to encrypt", e);				
				return value;
			} catch (NoSuchPaddingException e) {
				logger.log(Level.SEVERE, "Exception while trying to encrypt", e);
				return value;
			} catch (UnsupportedEncodingException e) {
				logger.log(Level.SEVERE, "Exception while trying to encrypt", e);
				return value;
			} catch (InvalidKeyException e) {
				logger.log(Level.SEVERE, "Exception while trying to encrypt", e);
				return value;
			} catch (BadPaddingException e) {
				logger.log(Level.SEVERE, "Exception while trying to encrypt", e);
				return value;
			} catch (IllegalBlockSizeException e) {
				logger.log(Level.SEVERE, "Exception while trying to encrypt", e);
				return value;
			}		
		}
		
		/**
		 * decrypts string using AES algorithm
		 * @param encrypted {@link String} to be decrypted
		 * @param key {@link String}
		 * @return descrypted {@link String}
		 */
		public static String decrypt(String encrypted, String key) {
			
			if (encrypted == null || key == null) {
				logger.log(Level.WARNING, "Correct input are missing while trying to decrypt");
				return encrypted;
			}
			
			try {
				Cipher cipher = Cipher.getInstance("AES");
				SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
				
				cipher.init(Cipher.DECRYPT_MODE, keySpec);
				byte[] encryptedTextBytes = Base64Impl.decodeBase64(encrypted.getBytes("UTF-8"));
			    byte[] decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
			     
			    return new String(decryptedTextBytes);
			} catch (NoSuchAlgorithmException e) {
				logger.log(Level.SEVERE, "Exception while trying to decrypt", e);
				return encrypted;
			} catch (NoSuchPaddingException e) {
				logger.log(Level.SEVERE, "Exception while trying to decrypt", e);
				return encrypted;
			} catch (UnsupportedEncodingException e) {
				logger.log(Level.SEVERE, "Exception while trying to decrypt", e);
				return encrypted;
			} catch (InvalidKeyException e) {
				logger.log(Level.SEVERE, "Exception while trying to decrypt", e);
				return encrypted;
			} catch (BadPaddingException e) {
				logger.log(Level.SEVERE, "Exception while trying to decrypt", e);
				return encrypted;
			} catch (IllegalBlockSizeException e) {
				logger.log(Level.SEVERE, "Exception while trying to decrypt", e);
				return encrypted;
			}
		}
	}
}
