import java.util.Scanner;
import java.lang.StringBuilder;
public class CaeserCipher {

    public String encrypt(String plainText, int key) {
        StringBuilder cipherText = new StringBuilder();
        plainText = plainText.toLowerCase();
        char[] plain = plainText.toCharArray();
        for(int i = 0; i < plain.length; i++) {
            char encrptedChar = (char) (((plain[i] - 'a' + key) % 26) + 'a');
            cipherText.append(encrptedChar);
        }
        return cipherText.toString();
    }

    public String decrypt(String cipherText, int key) {
        StringBuilder text = new StringBuilder();
        char[] cipher = cipherText.toCharArray();
        for(int i = 0; i < cipher.length; i++) {
            char decrypted = (char) (((cipher[i] - 'a' -key) % 26) + 'a');
            text.append(decrypted);
        }
        return text.toString();
    }

    public static void main(String[] args) {
        CaeserCipher caeser = new CaeserCipher();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the plain text without space:");
        String plainText = scan.next();
        System.out.println("Enter a number:");
        int key = scan.nextInt();
        String cipherText = caeser.encrypt(plainText, key);
        System.out.println(cipherText);
        String decryptedText = caeser.decrypt(cipherText, key);
        System.out.println(decryptedText);
    }
}