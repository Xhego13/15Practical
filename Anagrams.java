import java.util.*;
import java.io.*;
public class Anagrams{
    
   
    static int System(String command) {
        System.out.println("-".repeat(50) + ">" + command);
        try {
            Process process = Runtime.getRuntime().exec(command);
            return process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    static String Signature(String word){
        char[] chars= word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
    public static void main(String[] args){
        if(args.length!=2){
            System.out.println("Usage: java Anagrams <input_file> <output_file>");
            return;
        }
        BufferedReader f= new BufferedReader(new FileReader(args[0])){

        }
    }

    
}