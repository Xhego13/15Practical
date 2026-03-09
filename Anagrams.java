import java.util.*;
import java.io.*;

public class Anagrams {

    
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

    
    static String signature(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            String inputFile = args[0];
            System.out.println("Data file: " + inputFile);

            Map<String, Integer> D = new HashMap<>();

            // Read file
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(inputFile), "ISO-8859-1"))) {

                String line;
                int lineNumber = 0;
                int lines = 0;

                while ((line = reader.readLine()) != null) {
                    lineNumber++;
                    lines++;
                    String[] words = line.split("\\s+");
                    for (String w : words) {
                        String cleaned = w.replaceAll("[0-9(),.;:_.!?\\-\\[\\]]", "").trim();
                        if (!cleaned.isEmpty()) {
                            D.put(cleaned, D.getOrDefault(cleaned, 0) + 1);
                        }
                    }
                }

            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }

            
            Map<String, List<String>> A = new HashMap<>();
            for (String w : D.keySet()) {
                String a = signature(w);
                if (!A.containsKey(a)) {
                    A.put(a, new ArrayList<>(Arrays.asList(w)));
                } else {
                    A.get(a).add(w);
                }
            }

            
            try (PrintWriter f = new PrintWriter(new FileWriter("anagrams"))) {
                for (String key : A.keySet()) {
                    if (A.get(key).size() > 1) {
                        String anagramlist = "";
                        for (String word : A.get(key)) {
                            if (anagramlist.isEmpty()) {
                                anagramlist = word;
                            } else {
                                anagramlist += " " + word;
                            }
                        }
                        f.println(anagramlist + " \\\\");

                        // Rotate words
                        for (int repeat = 0; repeat < A.get(key).size() - 1; repeat++) {
                            int space = anagramlist.indexOf(' ');
                            anagramlist = anagramlist.substring(space + 1) + " " + anagramlist.substring(0, space);
                            f.println(anagramlist + " \\\\");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        
            System("sort anagrams > anagrams.sorted");

            
            List<String> aa = new ArrayList<>();
            try (BufferedReader asf = new BufferedReader(new FileReader("anagrams.sorted"))) {
                String line;
                while ((line = asf.readLine()) != null) {
                    aa.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            
            try (PrintWriter asftex = new PrintWriter(new FileWriter("latex/theAnagrams.tex"))) {
                char letter = 'X';
                for (String lemma : aa) {
                    char initial = lemma.charAt(0);
                    if (Character.toLowerCase(initial) != Character.toLowerCase(letter)) {
                        letter = initial;
                        asftex.printf("\n\\vspace{14pt}\n\\noindent\\textbf{\\Large %s}\\\\*[+12pt]\n",
                                Character.toUpperCase(initial));
                    }
                    asftex.println(lemma);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            
            System("rm anagrams anagrams.sorted");

        } else {
            System.out.println("Usage: java Anagrams inputfile");
            if (args.length > 0) {
                System.out.println("You gave:\n" + args[0]);
            }
            System.exit(1);
        }
    }
}

