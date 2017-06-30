package fileUpload;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
 
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
 
/**
 * A utility class that encrypts or decrypts a file.
 * @author www.codejava.net
 *
 */

public class encryption {

	    private static final String ALGORITHM = "AES";
	    private static final String TRANSFORMATION = "AES";
	 
	    public static void encrypt(String key, File inputFile, File outputFile) throws CryptoException {
	        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);//initialize cipher to encryption mode,
	    }
	 
	 
	    private static void doCrypto(int cipherMode, String key, File inputFile,
	            File outputFile) throws CryptoException {
	        try {
	            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);//Constructs a secret key from the given byte array
	            Cipher cipher = Cipher.getInstance(TRANSFORMATION);//Returns a Cipher object that implements the specified transformation
	            cipher.init(cipherMode, secretKey);//Initializes this cipher with a key
	             
	            FileInputStream inputStream = new FileInputStream(inputFile);
	            byte[] inputBytes = new byte[(int) inputFile.length()];
	            inputStream.read(inputBytes);
	             
	            byte[] outputBytes = cipher.doFinal(inputBytes); //encrypts the byte file
	             
	            FileOutputStream outputStream = new FileOutputStream(outputFile);//implements an output stream in which the data is written into a byte array
	            outputStream.write(outputBytes);
	             
	            inputStream.close();
	            outputStream.close();
	             
	        } catch (NoSuchPaddingException | NoSuchAlgorithmException
	                | InvalidKeyException | BadPaddingException
	                | IllegalBlockSizeException | IOException ex) {
	            throw new CryptoException("Error encrypting/decrypting file", ex);
	        }
	    }
	}

