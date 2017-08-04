package messaging;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;

import javax.crypto.Cipher;

public class GenerationOfKey {
	private KeyPairGenerator keyGen;
	private KeyPair pair;
	private PrivateKey privateKey;
	public PublicKey publicKey;
	public static PublicKey publicKey2;

	public GenerationOfKey() throws NoSuchAlgorithmException, NoSuchProviderException {
		this.keyGen = KeyPairGenerator.getInstance("RSA");
		this.keyGen.initialize(1024);
	}

	public void createKeys() {
		this.pair = this.keyGen.generateKeyPair();
		this.privateKey = pair.getPrivate();
		this.publicKey = pair.getPublic();
	}

	public byte[] encrypt(String text, PublicKey userPublicKey1) {
		byte[] cipherText = null;
		try {
			// get an RSA cipher object and print the provider
			final Cipher cipher = Cipher.getInstance("RSA");
			// encrypt the plain text using the public key
			cipher.init(Cipher.ENCRYPT_MODE, userPublicKey1);
			cipherText = cipher.doFinal(text.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cipherText;
	}

	public String decrypt(byte[] encryptedText, PrivateKey privateKey1) {
		byte[] decryptedText = null;
		try {
			// get an RSA cipher object and print the provider
			final Cipher cipher = Cipher.getInstance("RSA");

			// decrypt the text using the private key
			cipher.init(Cipher.DECRYPT_MODE, privateKey1);
			decryptedText = cipher.doFinal(encryptedText);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return new String(decryptedText);
	}

	public byte[] getHash(byte[] encrypted) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(encrypted);
		
		byte[] byteData = md.digest();
		return byteData;
	}
	
	public byte[] signHash(byte[] hash, PrivateKey privateKey1) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException{
		Signature rsa = Signature.getInstance("SHA256withRSA");
		rsa.initSign(privateKey1);
		rsa.update(hash);
		return rsa.sign();
	}
	
	public boolean verifySignature(byte[] data, byte[] signature, PublicKey userPublicKey1) throws Exception {
		Signature sig = Signature.getInstance("SHA256withRSA");
		sig.initVerify(userPublicKey1);
		sig.update(data);

		return sig.verify(signature);
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	/*
	public static void main(String[] args) throws Exception {
		String original = "Hello";
		try {
			System.out.println("Main started");
			GenerationOfKey gk = new GenerationOfKey();
			gk.createKeys();
			System.out.println("User Key created");
			GenerationOfKey gk1 = new GenerationOfKey();
			gk1.createKeys();
			System.out.println("User1 Key Created");
			
			byte[] encrypted = gk.encrypt(original, gk1.getPublicKey());
			byte[] encodedBytes = Base64.getEncoder().encode(encrypted);
			System.out.println("encodedBytes " + new String(encodedBytes));
			
			byte[] hashString = gk.getHash(encrypted);
			byte[] encodedBytes1 = Base64.getEncoder().encode(hashString);
			System.out.println("User Hash: " + new String(encodedBytes1));
			
			byte[] digitalSignture = gk.signHash(encodedBytes1, gk.getPrivateKey());
			byte[] ds = Base64.getEncoder().encode(digitalSignture);
			System.out.println("User digital signture: " + new String(ds));
			
			byte[] hashString1 = gk1.getHash(encrypted);
			byte[] encodedBytes2 = Base64.getEncoder().encode(hashString1);
			System.out.println("\nUser1 Hash: " + new String(encodedBytes2));
			
			if(gk1.verifySignature(encodedBytes2, digitalSignture, gk.getPublicKey())){
				String text = gk1.decrypt(encrypted, gk1.getPrivateKey());
				System.out.println("User: " + text);
			}else{
				System.out.println("Failed to verify...");
			}
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			System.err.println(e.getMessage());
		}

	}*/
}
