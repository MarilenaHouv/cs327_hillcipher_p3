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
        for (int i = 0; i < plaintext.length - 1; i++) {
            encryptedText[i] = (encryptionKey[0][0] * plaintext[i]
                    + encryptionKey[0][1] * plaintext[i + 1]) % 26;

            encryptedText[i + 1] = (encryptionKey[1][0] * plaintext[i]
                    + encryptionKey[1][1] * plaintext[i + 1]) % 26;
        }
        return encryptedText;
    }

    // decrypt multi-letter cipher text with with given key
    public int[] decrypt(int ciphertext[], int decryptionKey[][]) {
        return ciphertext;

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
        System.out.println(alphabet.charAt(encryption[0]) + " " + alphabet.charAt(encryption[1]) + " "
                + (alphabet.charAt(encryption[2]) + " " + alphabet.charAt(encryption[3])));
        // System.out.println("check with normal nums: " + encryption[0] + " " +
        // encryption[1] + " " + encryption[2] + " "
        // + encryption[3]);

        // def ciphertext (MQGVGQSMJI) //not the same as one generated earlier
        // decrypt
        // C) PRINOUT OUT CLEARTEXT IN LETTERS

        // extra credit?
    }
}