import java.io.*;
import java.util.*;

public class SimpleCryp {

    public static void main(String[] args) {
        SimpleCryp sc = new SimpleCryp();
        String input = "This is an example string.";
        String encrypted = encrypt(input);
        String decrypted = decrypt(encrypted);
        System.out.println("Original String: " + input);
        System.out.println("After Encryption: " + encrypted);
        System.out.println("After Decryption: " + decrypted);
    }
    
    private final static String chars = "abcdefghijklmnopqrstuvwxyz1234567890?!.,`~@#$%^&*()-_+=[]{}|:;\"'<> ";
    private final static Map<Character, Character> encryptMap = new HashMap<Character, Character>();
    private final static Map<Character, Character> decryptMap = new HashMap<Character, Character>();
    private static int offset = 10;
    
    public SimpleCryp() {
        fillHashMap();
    }
    
    private void fillHashMap() {
        char[] ch = chars.toCharArray();
        for(int i = 0; i < chars.length(); i++) {
	    encryptMap.put(ch[i], ch[(i + offset) % ch.length]);
	    decryptMap.put(ch[(i + offset) % ch.length], ch[i]);
        }
    }
    
    public static void encrypt(File fileName) {
        File encryptedFile = new File("Encrypted.txt");
        try{
            PrintWriter p = new PrintWriter(new FileWriter(encryptedFile));
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while((line = br.readLine()) != null) {
            p.println(encrypt(line));
            }
            p.close();
            }
            catch (Exception e) {
            System.out.println(e);
            encryptedFile.delete();
        }
    }
    
    public static String encrypt(String s) {
        char[] encryptString = s.toLowerCase().toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c : encryptString) {
	    sb.append(encryptMap.get(c));
        }
        
        return sb.toString();
    }
    
    public static void decrypt(File fileName) {
        File decryptedFile = new File("Decrypted.txt");
        try{
            PrintWriter p = new PrintWriter(new FileWriter(decryptedFile));
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while((line = br.readLine()) != null) {
            p.println(decrypt(line));
            }
            p.close();
            }
            catch (Exception e) {
            System.out.println(e);
            decryptedFile.delete();
        }
    }
    
    public static String decrypt(String s) {
        char[] decryptString = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c : decryptString) {
	    sb.append(decryptMap.get(c));
        }
        
        return sb.toString();
    }
    
    public static void setOffset(int o) {
        offset = o;
    }
}