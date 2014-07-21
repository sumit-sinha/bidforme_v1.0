package com.amadeus.bid.be.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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
				return value;
			}
			
			try {
				Cipher cipher = Cipher.getInstance("AES");
				SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
				
				cipher.init(Cipher.ENCRYPT_MODE, keySpec);
				return new String(Base64Impl.encodeBase64(cipher.doFinal(value.getBytes("UTF-8"))), "UTF-8");
			} catch (NoSuchAlgorithmException e) {
				return value;
			} catch (NoSuchPaddingException e) {
				return value;
			} catch (UnsupportedEncodingException e) {
				return value;
			} catch (InvalidKeyException e) {
				return value;
			} catch (BadPaddingException e) {
				return value;
			} catch (IllegalBlockSizeException e) {
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
				return encrypted;
			} catch (NoSuchPaddingException e) {
				return encrypted;
			} catch (UnsupportedEncodingException e) {
				return encrypted;
			} catch (InvalidKeyException e) {
				return encrypted;
			} catch (BadPaddingException e) {
				return encrypted;
			} catch (IllegalBlockSizeException e) {
				return encrypted;
			}
		}
	}
}
