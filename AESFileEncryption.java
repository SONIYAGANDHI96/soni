
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.AlgorithmParameters;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESFileEncryption {
	public static void main(String[] args) throws Exception {

            FileOutputStream outFile;
            // encrypted file
            try ( // file to be encrypted
                    FileInputStream inFile = new FileInputStream("D:\\deduplication\\src\\newfile.txt")) {
                // encrypted file
                outFile = new FileOutputStream("D:\\deduplication\\src\\encryptedfile.des");
                String key = "dAtAbAsE98765432"; 
                Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
                //SecretKey secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
                
                
     
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.ENCRYPT_MODE, aesKey);
                AlgorithmParameters params = cipher.getParameters();
                // iv adds randomness to the text and just makes the mechanism more
                // secure
                // used while initializing the cipher
                // file to store the iv
               
                //file encryption
                byte[] input = new byte[64];
                int bytesRead;
                while ((bytesRead = inFile.read(input)) != -1) {
                    byte[] output = cipher.update(input, 0, bytesRead);
                    if (output != null)
                        outFile.write(output);
                }
                byte[] output = cipher.doFinal();
                if (output != null)
                    outFile.write(output);
            }
		outFile.flush();
		outFile.close();

		System.out.println("File Encrypted.");
		
	}

}
