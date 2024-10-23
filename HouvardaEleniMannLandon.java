public class HouvardaEleniMannLandon {
    // calculate decryption key from given encryption key
    public static int[][] findDecryptionKey(int encryptionKey[][]) {
        // mult inverse of encryption key mod 26
        int a = encryptionKey[0][0];
        int b = encryptionKey[1][0];
        int c = encryptionKey[0][1];
        int d = encryptionKey[1][1];
        int newa = d / (a * d - b * c);
        int newb = (-b) / (a * d - b * c);
        int newc = (-c) / (a * d - b * c);
        int newd = a / (a * d - b * c);
        int[][] decryptionKey = { { newa, newc }, { newb, newd } };
        return decryptionKey;
    }

    // encrypt multiple letter plaintext msg with given key
    public static int[] encrypt(int plaintext[], int encryptionKey[][]) {
        int[] encryptedText = new int[plaintext.length];
        // for each pair
        for (int i = 0; i < plaintext.length; i++) {
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
        int[][] encryptionKey = { { 16, 9 }, { 7, 14 } };
        int[][] decryptionKey = findDecryptionKey(encryptionKey);
        // A) PRINT DECRYPTION KEY
        // a16 b7
        // c9 d14

        // def plaintext (JMUCSISCOOL with letter vals) = 9 12 20 8 18 2 14 14 11
        int[] plaintext = { 9, 12, 20, 8, 18, 2, 14, 14, 11, 25 }; // 25 is the extra
        encrypt(plaintext, encryptionKey);
        // B) PRINT OUT ENCRYPTION IN LETTERS NOT NUMBERS
        // def ciphertext (MQGVGQSMJI) //not the same as one generated earlier
        // decrypt
        // C) PRINOUT OUT CLEARTEXT IN LETTERS
    }
}