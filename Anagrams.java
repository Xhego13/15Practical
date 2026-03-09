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
        
         if (args.length == 1) {
            String inputFile = args[0];
            System.out.println("Data file: " + inputFile);    
        }
       Map<String, Integer> D = new HashMap<>();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(inputFile), "ISO-8859-1"))) {

                // File opened successfully, ready for processing later
                // Example: just read one line to show it works
                String line = reader.readLine();
                if (line != null) {
                    System.out.println("First line: " + line);
                }

            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }

        } else {
            System.out.println("Usage: java Anagrams inputfile");
            if (args.length > 0) {
                System.out.println("You gave:\n" + args[0]);
            }
            System.exit(1);
        }
        int lineNumber = 0;
        int lines = 0;
        while ((line = f.readLine()) != null) {
            lineNumber++;
            lines++;
            String[] words = line.split("\\s+");
            for (String w : words) {
                String cleaned = w.replaceAll("[0-9(),.;:_.!?\\-\\[\\]]", "").trim();
                if (!cleaned.isEmpty()) {
                    wordCounts.put(cleaned, wordCounts.getOrDefault(cleaned, 0) + 1);
                 }
            }
        }      
    }
        

        
    
    

    
}
