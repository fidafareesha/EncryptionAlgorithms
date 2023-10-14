import java.util.Scanner;

public class HillCipher {

    public String encrypt(int[][] key, String plaintext) {
        StringBuilder cipher = new StringBuilder();
        StringBuilder plain = new StringBuilder(plaintext);
        if(plaintext.length() % 2 != 0) {
            plain.append('Z');
        }

        for(int i=0; i<plain.length(); i+=2) {
            String pair = plain.substring(i, i+2);
            String code = Substitution(key, pair);
            cipher.append(code);
        }
        return cipher.toString();
    }

    public String Substitution(int[][] key, String plain) {
        StringBuilder subs = new StringBuilder();
        int[] text = new int[2];
        for(int i=0; i<2; i++) {
            text[i] = (int) plain.charAt(i) - 'A';
            //System.out.print(text[i]+ "text");
        }
        int[] cipher = matrixMul(key,text);
        for(int i=0; i< cipher.length; i++) {
            char sub = (char) ('A' + cipher[i]);
            subs.append(sub);
        }
        
        return subs.toString();
    }

    public int[] matrixMul(int[][] key, int[] text) {
        int[] cipher = new int[2];
        for(int i =0; i<key.length; i++) {
            int sum = 0;
            for(int j=0; j<text.length; j++) {
                sum += key[i][j] * text[j];
            }
            cipher[i] = sum % 26;
            //System.out.print(cipher[i] +"-");
        }
        //System.out.println("");
        return cipher;
    }

    //Decryption

    public String decrypt(int[][] key, String ciphertext) {
        StringBuilder plain = new StringBuilder();
        int[][] inverseKey = inverse(key);
        for(int i =0; i<ciphertext.length(); i+=2) {
            String pair = ciphertext.substring(i, i+2);
            String code = Substitution(inverseKey, pair);
            plain.append(code);
        }
        return plain.toString();
    }

    public int[][] inverse(int[][] key) {
        int det = (key[0][0] * key[1][1] - key[1][0] * key[0][1]) % 26;
        det = (det+26) % 26;
        System.out.println("Mod: "+ det);
        int detInv = -1;
        for(int i=1; i<26; i++) {
            if(i*det % 26 == 1) {
                detInv = i;
                break;
            }
        }
        int[][] inverseKey = new int[2][2];
        int[][] inv = new int[2][2];
        inverseKey[0][0] = key[1][1];
        inverseKey[1][1] = key[0][0];
        inverseKey[0][1] = -key[1][0];
        inverseKey[1][0] = -key[0][1];
        for(int i=0; i<2; i++) {
            for(int j=0; j<2; j++) {
                inv[i][j] = (((detInv * inverseKey[j][i]) % 26) + 26) % 26;
                System.out.print(inv[i][j]+" ");
            }
        }
        return inv;
    }


    public static void main(String[] args) {
        HillCipher hill = new HillCipher();
        Scanner scan = new Scanner(System.in);
        int[][] key = new int[2][2];
        System.out.println("Enter 2X2 matrix :");
        for(int i = 0; i<2; i++) {
            for(int j=0; j<2; j++) {
                key[i][j] = scan.nextInt() % 26;
            }
        }
        for(int i = 0; i<2; i++) {
            for(int j=0; j<2; j++) {
                System.out.print(key[i][j]+" ");
            }
            System.out.println("");
        }
        System.out.print("Enter the plain text: ");
        String plaintext = scan.next().toUpperCase();
        System.out.println("Plain Text: "+plaintext);
        String ciphertext = hill.encrypt(key, plaintext);
        System.out.println("Cipher Text: "+ciphertext);
        String decryptedtext = hill.decrypt(key, ciphertext);
        System.out.println("Decrypted Text: "+decryptedtext);
    }
}