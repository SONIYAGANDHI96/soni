
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESFileDecryption {
	public static void main(String[] args) throws Exception {

		

		// reading the iv
	

		String key = "dAtAbAsE98765432"; // 128 bit key

     // Create key and cipher
     Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
		// file decryption
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, aesKey);
                FileOutputStream fos;
            try (FileInputStream fis = new FileInputStream("D:\\deduplication\\src\\encryptedfile.des")) {
                fos = new FileOutputStream("D:\\deduplication\\src\\plainfile_decrypted.txt");
                byte[] in = new byte[64];
                int read;
                while ((read = fis.read(in)) != -1) {
                    byte[] output = cipher.update(in, 0, read);
                    if (output != null)
                        fos.write(output);
                }
                byte[] output = cipher.doFinal();
                if (output != null)
                    fos.write(output);
            }
		fos.flush();
		fos.close();
		System.out.println("File Decrypted.");
	}
}