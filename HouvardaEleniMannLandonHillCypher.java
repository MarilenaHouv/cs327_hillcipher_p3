import java.math.BigInteger;


class HouvardaEleniMannLandonHillCypher {    
    
    
    public static int[][] findDecryptionKey(int[][] encryptionKey) {
         
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
        while (newb < 0) {
            newb += 26;
        }
        while (newc < 0) {
            newc += 26;
        }
        while (newa < 0) {
            newa += 26;
        }
        while (newd < 0) {
            newd += 26;
        }
        // System.out.println(newa);
        int[][] decryptionKey = { { newa, newc }, { newb, newd } };
        return decryptionKey;
    }
    
    // encrypt multiple letter plaintext msg with given key
    public static int[] encrypt(int segment[], int encryptionKey[][]) {
        int[] encryptedText = new int[segment.length];
        //
        encryptedText[0] = (encryptionKey[0][0] * segment[0]
                + encryptionKey[0][1] * segment[1]) % 26;
        encryptedText[1] = (encryptionKey[1][0] * segment[0]
                + encryptionKey[1][1] * segment[1]) % 26;
        return encryptedText; 

    }

    // decrypt multi-letter cipher text with with given key
    public static int[] decrypt(int ciphertext[], int decryptionKey[][]) {
        int[] decryptedText = new int[ciphertext.length];
        for (int i = 0; i < ciphertext.length; i += 2) {
            decryptedText[i] = (decryptionKey[0][0] * ciphertext[i]
                    + decryptionKey[0][1] * ciphertext[i + 1]) % 26;

            decryptedText[i + 1] = (decryptionKey[1][0] * ciphertext[i]
                    + decryptionKey[1][1] * ciphertext[i + 1]) % 26;

            while (decryptedText[i] < 0)
                decryptedText[i] += 26;
            while (decryptedText[i + 1] < 0)
                decryptedText[i + 1] += 26;
        }
        return decryptedText;

    }
    
    
    
    
    
    
    
    public static void main(String[] args) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        
        
        int[][] encryptionKey = new int[2][2];
        encryptionKey[0][0] = 16;
        encryptionKey[0][1] = 7;
        encryptionKey[1][0] = 9;
        encryptionKey[1][1] = 14;
        System.out.println("ENCRYPTION KEY");
        System.out.println(encryptionKey[0][0] + " " + encryptionKey[0][1]);
        System.out.println(encryptionKey[1][0] + " " + encryptionKey[1][1]);


        int[][] decryptionKey = findDecryptionKey(encryptionKey);
        System.out.println("DECRYPTION KEY");

        System.out.println(decryptionKey[0][0] + " " + decryptionKey[0][1]);
        System.out.println(decryptionKey[1][0] + " " + decryptionKey[1][1]);

        System.out.println("ENCRYPTING MESSAGE: \"JMUCSISCOOL\"");
        
        int[][] plaintext = {{9,12}, {20,2}, {18,8}, {18, 2}, {14,14}, {11, 25}}; //JMUCSISCOOL with Z appended bc odd value of letters

        int[][] ciphertext = new int[plaintext.length][2];
        
        for (int i = 0; i < plaintext.length; i++) {
            ciphertext[i] = encrypt(plaintext[i], encryptionKey);
        }

        System.out.println("CIPHERTEXT:");
        for (int i = 0; i < ciphertext.length; i++) {
            System.out.print(alphabet.charAt(ciphertext[i][0]) + " " + alphabet.charAt(ciphertext[i][1]) + " ");
        }

        int[][] cipher2 = {{12,16},{6,21},{6,16},{18,12}, {9,8}};  // MQGVGQSMJI in numbers  
        int[][] decryptedText = new int[cipher2.length][2];

        for (int i = 0; i < cipher2.length; i++) {
            decryptedText[i] = decrypt(cipher2[i], decryptionKey);
        }

        System.out.println("\n\nDECRYPTED TEXT of MQGVGQSMJI:");
        for (int i = 0; i < decryptedText.length; i++) {
            System.out.print(alphabet.charAt(decryptedText[i][0]) + " " + alphabet.charAt(decryptedText[i][1]) + " ");
        }



        System.out.println();














       
    }
  }