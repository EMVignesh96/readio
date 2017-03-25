
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vignesh
 */
public class Recognizer {
        
    private static final String OUT_FILE = "out";
    
    public void recognize(String filePath) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("tesseract " + filePath + " " + filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        
        String reply = "";
        String line = "";
        while((line = reader.readLine()) != null) {
            reply += line;
        }
        System.out.println(reply);
        
        process.waitFor();
    } 
}
