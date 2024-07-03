package other_resources;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Alejandro
 */
public abstract class MISAParser {
    
    public static Map<String, String> readFastaFile(String filePath) throws IOException {
        Map<String, String> sequences = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        StringBuilder sequence = new StringBuilder();
        String header = null;
        
        while ((line = reader.readLine()) != null) {
            if (line.startsWith(">")) {
                if (header != null) {
                    sequences.put(header, sequence.toString());
                    sequence = new StringBuilder();
                }
                header = line.substring(1).trim();
            } else {
                sequence.append(line.trim());
            }
        }
        if (header != null) {
            sequences.put(header, sequence.toString());
        }
        reader.close();
        return sequences;
    }
    
    public static String processFiles(String tabFilePath, Map<String, String> sequences) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(tabFilePath));
        String line = reader.readLine(); // Skip header line
        
        String header=sequences.keySet().toArray()[0].toString();
        
        String sequence = sequences.getOrDefault(header, "");
        
        String output="";
        
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split("\t");
            if (fields.length < 7 || fields[2].charAt(0)=='c') continue; // Skip malformed lines
            
            String ssrNumber = fields[1];
            String ssrType = fields[2];
            String ssr = fields[3];
            String size = fields[4];
            int start = Integer.parseInt(fields[5]);
            int end = Integer.parseInt(fields[6]);
           
            String repSeq = extractSequence(sequence, start, end);
            
            output+=">SSR_" + ssrNumber + " " + ssrType + " " + ssr + " Size: " + size + " Start: " + start + " End: " + end + "\n";
            output+=repSeq + "\n";
        }
        
        reader.close();
        return output;
    }
    
    private static String extractSequence(String sequence, int start, int end) {
        int startContext = Math.max(0, start - 21);
        int endContext = Math.min(sequence.length(), end + 20);
        
        return sequence.substring(startContext, start - 1).toUpperCase() +
                sequence.substring(start - 1, end).toLowerCase() +
                sequence.substring(end, endContext).toUpperCase();
    }
}
