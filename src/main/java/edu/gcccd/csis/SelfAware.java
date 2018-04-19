package edu.gcccd.csis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class SelfAware implements Language{

    public static void main(String[] args) throws Exception {
        final String code = System.getProperty("user.dir") + File.separator +
                "src" + File.separator + "main" + File.separator + "java" + File.separator +
                SelfAware.class.getName().replace(".", File.separator) + ".java";

        SelfAware sa = new SelfAware();
        sa.append(code,"\n//Keyword occurrences: " + sa.occurrences(code));
    }

    @Override
    public int occurrences(String sourceFile) throws Exception {
        int count = 0;
        try{
            File source = new File(sourceFile);
            Scanner scan = new Scanner(source);
            scan.useDelimiter("\\s");
            scan.useDelimiter("\\W");
            while(scan.hasNext()){
                String next = scan.next();
                for(int i = 0; i < ReservedWords.length; i++) {
                    if(next.contains(ReservedWords[i])) {
                        int startIndex = next.indexOf(ReservedWords[i]);
                        String inQuestion = next.substring(startIndex);
                        if(inQuestion.equals(ReservedWords[i]))
                            count++;
                    }
                }
            }
            scan.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void append(String sourceFile, String message) throws IOException {
        Path p = Paths.get(sourceFile);
        Files.write(p, message.getBytes(), StandardOpenOption.APPEND);
    }
}