public class HouvardaEleniMannLandon {
    // calculate decryption key from given encryption key
    public int[][] findDecryptionKey(int encryptionKey[][]) {
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
    public int[] encrypt(int plaintext[], int encryptionKey[][]) {
        return plaintext;
    }

    // decrypt multy letter cipher text with with given key
    public int[] decrypt(int ciphertext[], int decryptionKey[][]) {
        return ciphertext;

    }

    public static void main(String[] args) {
        int[][] encryptionKey = { { 16, 9 }, { 7, 14 } };
        // a16 b7
        // c9 d14
        // def plaintext (JMUCSISCOOL with letter vals)
        // def ciphertext (MQGVGQSMJI) //not the same as one generated earlier
        // def decryption key
    }
}