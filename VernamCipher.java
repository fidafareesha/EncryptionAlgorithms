import java.util.Scanner;

public class VernamCipher {

    public String encrypt(String plaintext, String key) {
        if(plaintext.length() != key.length()) {
            System.out.println("The key must be of same length as plain text");
            return "";
        }
        StringBuilder cipher = new StringBuilder();
        for(int i=0; i<key.length(); i++){
            int res = (plaintext.charAt(i) - 'A') ^ (key.charAt(i) - 'A') % 128;
            cipher.append((char) ('A' + res));
        }
        return cipher.toString();
    }

    public String decrypt(String ciphertext, String key) {
        if (ciphertext.length() != key.length()) {
            System.out.println("The key must be of the same length as the ciphertext");
            return "";
        }
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < key.length(); i++) {
            int res = (ciphertext.charAt(i) - 'A') ^ (key.charAt(i) - 'A') % 128;
            plaintext.append((char) ('A' + res));
        }
        return plaintext.toString();
    }
    

    public static void main(String[] args) {
        VernamCipher vernam = new VernamCipher();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the key: ");
        String key = scan.next();
        System.out.println("Enter the plain text: ");
        String plaintext = scan.next();
        String ciphertext= vernam.encrypt(plaintext.toUpperCase(), key.toUpperCase());
        System.out.println("Cipher Text: "+ciphertext);
        String decryptedtext = vernam.decrypt(ciphertext, key.toUpperCase());
        System.out.println("Decrypted Text: "+decryptedtext);
    }
}