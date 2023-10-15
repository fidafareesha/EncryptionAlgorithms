import java.util.Scanner;

public class VigenereCipher {

    public String encrypt(String plaintext, String key) {
        int len = key.length();
        StringBuilder cipher = new StringBuilder();
        for(int i=0; i<plaintext.length(); i++) {
            int ckey = (int) (key.charAt(i%len) - 'A');
            char subs = caeser(plaintext.charAt(i), ckey);
            cipher.append(subs);
        }
        return cipher.toString();
    }

    public char caeser(char plain, int key) {
    
        return (char) ('A' + ((plain - 'A' + key) % 26));
    }

    //Decryption

    public String decrypt(String ciphertext, String key) {
        int len = key.length();
        StringBuilder plain = new StringBuilder();
        for(int i=0; i<ciphertext.length(); i++) {
            int ckey = (int) (key.charAt(i%len) - 'A');
            char subs = caeserdecrypt(ciphertext.charAt(i), ckey);
            plain.append(subs);
        }
        return plain.toString();
    }

    public char caeserdecrypt(char cipher, int key) {
        return (char) ('A' + ((cipher - 'A' - key + 26) % 26));
    }

    public static void main(String[] args) {
        VigenereCipher vigenere = new VigenereCipher();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the key: ");
        String key = scan.next();
        System.out.println("Enter plain text: ");
        String plaintext = scan.next();
        String ciphertext = vigenere.encrypt(plaintext.toUpperCase(), key.toUpperCase());
        System.out.println("Cipher Text : "+ciphertext);
        String decryptedtext = vigenere.decrypt(ciphertext, key.toUpperCase());
        System.out.println("Decrypted Text: "+decryptedtext);
    }
}