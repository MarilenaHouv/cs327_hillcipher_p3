import java.math.*;
public class HouvardaEleniMannLandon {
    // calculate decryption key from given encryption key
    public static int[][] findDecryptionKey(int encryptionKey[][]) {
        // mult inverse of encryption key mod 26
        int a = encryptionKey[0][0];

        int b = encryptionKey[1][0];
        int c = encryptionKey[0][1];
        int d = encryptionKey[1][1];
        int mod = (-a) * (-d) - (b * c); // take this whole num and inverse mod 26 :(
        BigInteger bmod = BigInteger.valueOf(mod); // should be 161
        // System.out.println(bmod);
        BigInteger bmodd = new BigInteger("26");
        BigInteger result = bmod.modInverse(bmodd); // 161 inverse mod 26 = 21
        // System.out.println(result);

        int newa = d * result.intValue() % 26;
        int newb = -b * result.intValue() % 26;
        int newc = -c * result.intValue() % 26;
        int newd = a * result.intValue() % 26;
        // System.out.println(newa);
        int[][] decryptionKey = { { newa, newc }, { newb, newd } };
        return decryptionKey;
    }

    // encrypt multiple letter plaintext msg with given key
    public static int[] encrypt(int plaintext[], int encryptionKey[][]) {
        int[] encryptedText = new int[plaintext.length];
        // for each pair
        for (int i = 0; i < plaintext.length - 1; i += 2) {
            encryptedText[i] = (encryptionKey[0][0] * plaintext[i]
                    + encryptionKey[1][0] * plaintext[i + 1]) % 26;

            encryptedText[i + 1] = (encryptionKey[0][1] * plaintext[i]
                    + encryptionKey[1][1] * plaintext[i + 1]) % 26;
        }
        return encryptedText;
    }

    // decrypt multi-letter cipher text with with given key
    public static int[] decrypt(int ciphertext[], int decryptionKey[][]) {
        int[] decryptedText = new int[ciphertext.length];
        for (int i = 0; i < ciphertext.length; i += 2) {
            decryptedText[i] = (decryptionKey[0][0] * ciphertext[i]
                    + decryptionKey[1][0] * ciphertext[i + 1]) % 26;

            decryptedText[i + 1] = (decryptionKey[0][1] * ciphertext[i]
                    + decryptionKey[1][1] * ciphertext[i + 1]) % 26;

            if (decryptedText[i] < 0)
                decryptedText[i] += 26;
            if (decryptedText[i + 1] < 0)
                decryptedText[i + 1] += 26;
            if (decryptedText[i] < 0)
                decryptedText[i] += 26;
            if (decryptedText[i + 1] < 0)
                decryptedText[i + 1] += 26;
        }
        return decryptedText;

    }

    public static void main(String[] args) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[][] encryptionKey = { { 16, 9 }, { 7, 14 } };
        int[][] decryptionKey = findDecryptionKey(encryptionKey);
        System.out.println("DECRYPTION KEY");

        System.out.println(decryptionKey[0][0] + " " + decryptionKey[1][0]);
        System.out.println(decryptionKey[0][1] + " " + decryptionKey[1][1]);
        // A) PRINT DECRYPTION KEY (works :))
        // a16 b7
        // c9 d14

        // def plaintext (JMUCSISCOOL with letter vals) = 9 12 20 8 18 2 14 14 11
        int[] plaintext = { 9, 12, 20, 8, 18, 2, 14, 14, 11, 25 }; // 25 is the extra
        int[] encryption = encrypt(plaintext, encryptionKey);
        // B) PRINT OUT ENCRYPTION IN LETTERS NOT NUMBERS
        System.out.println("ENCRYPTION IN LETTERS");
        for (int i = 0; i < encryption.length; i++) {
            System.out.print(alphabet.charAt(encryption[i]) + " ");
            // test: System.out.print( encryption[i] + " ");
        }
        // def ciphertext (MQGVGQSMJI) //not the same as one generated earlier
        int[] ciphertext = { 12, 17, 6, 22, 6, 17, 19, 12, 9, 8 };
        // decrypt
        int[] decryption = decrypt(ciphertext, decryptionKey); // this works with encryption var "JMUISCOOL" but not for
                                                               // cipher text "GODUKESGO"
        // C) PRINOUT OUT CLEARTEXT IN LETTERS
        System.out.println("\nDECRYPTION IN LETTERS");
        for (int i = 0; i < encryption.length; i++) {
            System.out.print(alphabet.charAt(decryption[i]) + " ");
            // test: System.out.print( decryption[i] + " ");
        }
        // extra credit?
    }
}