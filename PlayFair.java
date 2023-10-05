import java.util.Scanner;
import java.lang.StringBuilder;

public class PlayFair {
    public String encrypt(String plaintext, String keyword) {
        StringBuilder ciphertext = new StringBuilder();
        plaintext = plaintext.toUpperCase();

        char[][] matrix = getMatrix(keyword);
        for(int i = 0; i <5; i++) {
            for(int j=0; j<5; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println(" ");
        }
        String plain = addFiller(plaintext);
        System.out.println(plain);

        for(int i=0; i < plain.length(); i+=2) {
            String newChars = replacements(plain.charAt(i), plain.charAt(i+1), matrix);
            ciphertext.append(newChars);
        }
        return ciphertext.toString();
    }

    public String replacements(char c1, char c2, char[][] matrix) {
        int row1 = -1, row2 = -1, col1 =-1, col2 =-1;
        StringBuilder newChars = new StringBuilder();
        for(int i = 0; i <5; i++) {
            for(int j = 0; j < 5; j++) {
                if(matrix[i][j] == c1) {
                    row1 = i;
                    col1 = j;
                }
                if(matrix[i][j] == c2) {
                    row2 = i;
                    col2 = j;
                }
            }
        }
        if(row1 == row2) {
            newChars.append(matrix[row1][(col1+1) % 5]);
            newChars.append(matrix[row1][(col2+1)%5]);
        }
        else if(col1 == col2) {
            newChars.append(matrix[(row1+1)%5][col1]);
            newChars.append(matrix[(row2+1)%5][col1]);
        }
        else {
            newChars.append(matrix[row1][col2]);
            newChars.append(matrix[row2][col1]);
        }
        return newChars.toString();
    }

    public char[][] getMatrix(String keyword) {
        char[][] matrix = new char[5][5];
        String key = keyword.toUpperCase();
        StringBuilder usedChar = new StringBuilder();
        int row = 0, col = 0;
        for(char c: key.toCharArray()) {
            if(c == 'J') {
                c = 'I';
            }
            if(usedChar.indexOf(String.valueOf(c)) == -1) {
                matrix[row][col] = c;
                usedChar.append(c);
                col++;
                if(col == 5) {
                    row++;
                    col = 0;
                }
            }
        }
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(char c: alphabets.toCharArray()) {
            if(c != 'J' && usedChar.indexOf(String.valueOf(c)) == -1) {
                matrix[row][col] = c;
                usedChar.append(c);
                col++;
                if(col == 5) {
                    row++;
                    col = 0;
                }
            }
        }
        return matrix;
    }

    public String addFiller(String plaintext) {
        StringBuilder plain = new StringBuilder();
        plain.append(plaintext);
        for(int i = 0; i < plain.length()-1; i++) {
            if(plain.charAt(i) == plain.charAt(i+1)) {
                plain.insert(i+1, 'X');
            }
        }
        if(plain.length() % 2 != 0) {
            plain.append('Z');
        }
        return plain.toString();
        
    }

    public static void main(String[] args) {
        PlayFair pf = new PlayFair();
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the plain text without spaces:");
        String plainText = scan.next();
        System.out.println("PLAINTEXT : "+plainText);
        System.out.println("Enter the keyword:");
        String keyword = scan.next();
        String cipherText = pf.encrypt(plainText, keyword);
        System.out.println("CIPHERTEXT : "+cipherText);
    }
}
