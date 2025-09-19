import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HashFile {
    public static String hashFile(String filePath) {
        try {
            // choose to use SHA-256 algorithm
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

            try (FileInputStream fis = new FileInputStream(filePath)) {

                byte[] buffer = new byte[8192]; // 8KB buffer, you can use any size, I prefer 8KB
                int bytesRead;

                // the most important part: read the file in chunks and update the digest (update method)
                while ((bytesRead = fis.read(buffer)) != -1) {
                    sha256.update(buffer, 0, bytesRead);
                }
            }

            // complete the hash computation
            byte[] hashBytes = sha256.digest();


            return bytesToHex(hashBytes);

        } catch (NoSuchAlgorithmException | IOException e) {
    
            e.printStackTrace();
            return null;
        }
    }


    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }


        public static void main(String[] args) {

        String filePath = "C:\\Users\\hp\\Downloads\\VisualStudioSetup (2).exe"; 
        String fileHash = hashFile(filePath);

        if (fileHash != null) {
            System.out.println("The SHA-256 hash of the file is:");
            System.out.println(fileHash);
        }
    }

}
